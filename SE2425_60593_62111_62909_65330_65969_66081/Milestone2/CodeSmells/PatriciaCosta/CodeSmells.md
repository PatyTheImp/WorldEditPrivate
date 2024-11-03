# Code Smell 1 (God Class)

## 1. Code snippet:

The following code snippet from the `EditSession` class illustrates the broad range of responsibilities managed by this class, including block manipulation, geometry creation, and environmental effects. 
Here’s an excerpt that shows some of these methods:

    public class EditSession implements Extent, AutoCloseable {

        public int makeCylinder(BlockVector3 pos, Pattern block, double radius, int height, boolean filled) throws MaxChangedBlocksException {
            // Cylinder generation code
        }

        public int makeSphere(BlockVector3 pos, Pattern block, double radius, boolean filled) throws MaxChangedBlocksException {
            // Sphere generation code
        }

        public int makeForest(BlockVector3 basePosition, int size, double density, TreeGenerator.TreeType treeType) throws MaxChangedBlocksException {
            // Forest generation code
        }

        public void undo(EditSession editSession) {
            // Undo functionality
        }

        public int setBlock(BlockVector3 position, BlockState block) {
            // Basic block manipulation
        }

        public int replaceBlocks(Region region, Mask mask, Pattern pattern) throws MaxChangedBlocksException {
            // Block replacement functionality
        }
        
        public int moveRegion(Region region, BlockVector3 offset, int multiplier, boolean copyAir, Pattern replacement) throws MaxChangedBlocksException {
            // Region movement code
        }

        // Additional methods...
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit`
- **Class:** `EditSession`

## 3. Explanation:

The `EditSession` class is a "**God Class**" because it handles too many unrelated responsibilities, like shape creation, region management, environmental effects, and history tracking, making it complex and hard to maintain. <br>
This class also have a **Weighted Method Count (WMC) of 397**, which indicates that the `EditSession` class has a very high number of methods, contributing to its **complexity and lack of cohesion**. In essence, a high WMC in `EditSession` reflects that it is overloaded with various functionalities, making it difficult to understand, maintain, and extend.

## 4. Proposal of a refactoring:

To address the "**God Class**" smell in `EditSession`, the responsibilities can be split across multiple smaller, more focused classes. Here’s a proposed refactoring:

1. **GeometryEditor Class:**
    - **Responsibility:** Manage geometric operations like creating shapes (e.g., cylinders, spheres).
    - **Methods to Move:** `makeCylinder`, `makeSphere`, `makePyramid`, `drawLine`, `drawSpline`.
    - **Benefit:** This isolates shape creation in a single class, making it easier to maintain and test.

2. **RegionManager Class:**
    - **Responsibility:** Handle region-related operations, such as moving or replacing blocks within a region.
    - **Methods to Move:** `moveRegion`, `replaceBlocks`.
    - **Benefit:** With region-related functionality separated, `EditSession` no longer needs to manage regions directly.

3. **EnvironmentManager Class:**
    - **Responsibility:** Manage environment modifications, such as forest generation, snow simulation, and liquid draining.
    - **Methods to Move:** `makeForest`, `simulateSnow`, `drainArea`.
    - **Benefit:** Centralizing environment manipulation improves modularity and makes it easier to test these specific functionalities.

4. **HistoryManager Class:**
    - **Responsibility:** Manage history tracking and undo/redo functionality.
    - **Methods to Move:** `undo`, `redo`.
    - **Benefit:** Encapsulating undo/redo behavior reduces the responsibility of `EditSession` and makes history tracking reusable.

5. **EditSession Class (Refactored):**
    - After moving these methods, `EditSession` will focus on managing basic block manipulation and session management (e.g., managing the session lifecycle and setting block limits).
    - **Benefit:** The refactored `EditSession` is now focused on managing the core editing session, with other responsibilities delegated to more specific classes.

# Code Smell 1 (Feature Envy)

## 1. Code snippet:

`undo` Method:

    public EditSession undo(@Nullable BlockBag newBlockBag, Actor actor) {
        checkNotNull(actor);
        --historyPointer;
        if (historyPointer >= 0) {
            EditSession editSession = history.get(historyPointer);
            try (EditSession newEditSession =
                        WorldEdit.getInstance().newEditSessionBuilder()
                            .world(editSession.getWorld()).blockBag(newBlockBag).actor(actor)
                            .build()) {
                prepareEditingExtents(newEditSession, actor);
                editSession.undo(newEditSession);
            }
            return editSession;
        } else {
            historyPointer = 0;
            return null;
        }
    }

`redo` Method:

    public EditSession redo(@Nullable BlockBag newBlockBag, Actor actor) {
        checkNotNull(actor);
        if (historyPointer < history.size()) {
            EditSession editSession = history.get(historyPointer);
            try (EditSession newEditSession =
                        WorldEdit.getInstance().newEditSessionBuilder()
                            .world(editSession.getWorld()).blockBag(newBlockBag).actor(actor)
                            .build()) {
                prepareEditingExtents(newEditSession, actor);
                editSession.redo(newEditSession);
            }
            ++historyPointer;
            return editSession;
        }

        return null;
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit`
- **Class:** `LocalSession`

## 3. Explanation:

Both `undo` and `redo` methods are performing operations that involve extensive interaction with `EditSession`, such as creating new instances, setting up `EditSession` for undo/redo, and invoking methods like `undo` and `redo` directly on `EditSession`. This makes these methods dependent on the internal details of `EditSession`, demonstrating a **Feature Envy** smell, as they rely on behaviors that might logically belong closer to EditSession. <br>

However, since `EditSession` is already a **God Class**, moving this logic there would worsen the design. Instead, we can improve the design by creating, the already proposed, helper class **HistoryManager** focused in managing history tracking and undo/redo functionality.

The **high Response for Class (RFC) value of 194** in `LocalSession` suggests it's doing too much and relies heavily on methods from other classes, especially `EditSession`. This reliance reflects the **Feature Envy** smell, as `LocalSession` frequently accesses external methods, indicating it's overextended and lacks cohesion.

## 4. Proposal of a refactoring:

1. **Create, the already proposed, class** called `HistoryManager`, which will be responsible for handling undo and redo operations.
2. **Encapsulate the logic** for undo and redo in this helper class, removing the need for `LocalSession` to directly manipulate `EditSession` instances for these operations.
3. **Delegate** the undo and redo calls from `LocalSession` to `HistoryManager`, thereby reducing the dependency on `EditSession`.
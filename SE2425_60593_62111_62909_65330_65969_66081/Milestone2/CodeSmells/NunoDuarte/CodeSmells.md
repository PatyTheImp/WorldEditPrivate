# Code Smells
## 1. Long Method

### 1.1  Code Snippet
```
    public int morph(BlockVector3 position, double brushSize, int minErodeFaces, int numErodeIterations, int minDilateFaces, int numDilateIterations) throws MaxChangedBlocksException {
        int ceilBrushSize = (int) Math.ceil(brushSize);
        int bufferSize = ceilBrushSize * 2 + 3;  // + 1 due to checking the adjacent blocks, plus the 0th block
        // Store block states in a 3d array so we can do multiple mutations then commit.
        // Two are required as for each iteration, one is "current" and the other is "new"
        BlockState[][][] currentBuffer = new BlockState[bufferSize][bufferSize][bufferSize];
        BlockState[][][] nextBuffer = new BlockState[bufferSize][bufferSize][bufferSize];

        // Simply used for swapping the two
        BlockState[][][] tmp;

        // Load into buffer
        for (int x = 0; x < bufferSize; x++) {
            for (int y = 0; y < bufferSize; y++) {
                for (int z = 0; z < bufferSize; z++) {
                    BlockState blockState = getBlock(position.add(x - ceilBrushSize - 1, y - ceilBrushSize - 1, z - ceilBrushSize - 1));
                    currentBuffer[x][y][z] = blockState;
                    nextBuffer[x][y][z] = blockState;
                }
            }
        }

        double brushSizeSq = brushSize * brushSize;
        Map<BlockState, Integer> blockStateFrequency = new HashMap<>();
        int totalFaces;
        int highestFreq;
        BlockState highestState;
        for (int i = 0; i < numErodeIterations; i++) {
            for (int x = 0; x <= ceilBrushSize * 2; x++) {
                for (int y = 0; y <= ceilBrushSize * 2; y++) {
                    for (int z = 0; z <= ceilBrushSize * 2; z++) {
                        int realX = x - ceilBrushSize;
                        int realY = y - ceilBrushSize;
                        int realZ = z - ceilBrushSize;
                        if (lengthSq(realX, realY, realZ) > brushSizeSq) {
                            continue;
                        }

                        // Copy across changes
                        nextBuffer[x + 1][y + 1][z + 1] = currentBuffer[x + 1][y + 1][z + 1];

                        BlockState blockState = currentBuffer[x + 1][y + 1][z + 1];

                        if (blockState.getBlockType().getMaterial().isLiquid() || blockState.getBlockType().getMaterial().isAir()) {
                            continue;
                        }

                        blockStateFrequency.clear();
                        totalFaces = 0;
                        highestFreq = 0;
                        highestState = blockState;
                        for (BlockVector3 vec3 : recurseDirections) {
                            BlockState adj = currentBuffer[x + 1 + vec3.x()][y + 1 + vec3.y()][z + 1 + vec3.z()];

                            if (!adj.getBlockType().getMaterial().isLiquid() && !adj.getBlockType().getMaterial().isAir()) {
                                continue;
                            }

                            totalFaces++;
                            int newFreq = blockStateFrequency.getOrDefault(adj, 0) + 1;
                            blockStateFrequency.put(adj, newFreq);

                            if (newFreq > highestFreq) {
                                highestFreq = newFreq;
                                highestState = adj;
                            }
                        }

                        if (totalFaces >= minErodeFaces) {
                            nextBuffer[x + 1][y + 1][z + 1] = highestState;
                        }
                    }
                }
            }
            // Swap current and next
            tmp = currentBuffer;
            currentBuffer = nextBuffer;
            nextBuffer = tmp;
        }

        for (int i = 0; i < numDilateIterations; i++) {
            for (int x = 0; x <= ceilBrushSize * 2; x++) {
                for (int y = 0; y <= ceilBrushSize * 2; y++) {
                    for (int z = 0; z <= ceilBrushSize * 2; z++) {
                        int realX = x - ceilBrushSize;
                        int realY = y - ceilBrushSize;
                        int realZ = z - ceilBrushSize;
                        if (lengthSq(realX, realY, realZ) > brushSizeSq) {
                            continue;
                        }

                        // Copy across changes
                        nextBuffer[x + 1][y + 1][z + 1] = currentBuffer[x + 1][y + 1][z + 1];

                        BlockState blockState = currentBuffer[x + 1][y + 1][z + 1];
                        // Needs to be empty
                        if (!blockState.getBlockType().getMaterial().isLiquid() && !blockState.getBlockType().getMaterial().isAir()) {
                            continue;
                        }

                        blockStateFrequency.clear();
                        totalFaces = 0;
                        highestFreq = 0;
                        highestState = blockState;
                        for (BlockVector3 vec3 : recurseDirections) {
                            BlockState adj = currentBuffer[x + 1 + vec3.x()][y + 1 + vec3.y()][z + 1 + vec3.z()];
                            if (adj.getBlockType().getMaterial().isLiquid() || adj.getBlockType().getMaterial().isAir()) {
                                continue;
                            }

                            totalFaces++;
                            int newFreq = blockStateFrequency.getOrDefault(adj, 0) + 1;
                            blockStateFrequency.put(adj, newFreq);

                            if (newFreq > highestFreq) {
                                highestFreq = newFreq;
                                highestState = adj;
                            }
                        }

                        if (totalFaces >= minDilateFaces) {
                            nextBuffer[x + 1][y + 1][z + 1] = highestState;
                        }
                    }
                }
            }
            // Swap current and next
            tmp = currentBuffer;
            currentBuffer = nextBuffer;
            nextBuffer = tmp;
        }

        // Commit to world
        int changed = 0;
        for (int x = 0; x < bufferSize; x++) {
            for (int y = 0; y < bufferSize; y++) {
                for (int z = 0; z < bufferSize; z++) {
                    if (setBlock(position.add(x - ceilBrushSize - 1, y - ceilBrushSize - 1, z - ceilBrushSize - 1), currentBuffer[x][y][z])) {
                        changed++;
                    }
                }
            }
        }

        return changed;
    }
```

### 1.2 Location on the codebase
- **Package:** `com.sk89q.worldedit`
- **Class:** `EditSession`
- **Method** `morph`
- **Line** `2816`

### 1.3 Explanation

The morph method is long and handles many responsibilities: it loads the buffer, processes the erosion and dilation iterations, and then commits changes to the world. This violates the Single Responsibility Principle (SRP) and makes the code harder to understand and maintain.
This method has a cognitive complexity of 31 (very high), 126 lines of code (very high) and 32 cyclomatic complexity (very high).

### 1.4 Proposal of a refactoring 

- A method to load the buffer.
- A method to perform erosion.
- A method to perform dilation.
- A method to commit changes.


# 2. Speculative Generality

### 2.1 Code Snippet

```
public class FlatRegionMaskingFilter implements FlatRegionFunction {

    private final FlatRegionFunction function;
    private final Mask2D mask;

    /**
     * Create a new masking filter.
     *
     * @param mask the mask
     * @param function the delegate function to call
     */
    public FlatRegionMaskingFilter(Mask2D mask, FlatRegionFunction function) {
        checkNotNull(function);
        checkNotNull(mask);

        this.mask = mask;
        this.function = function;
    }

    @Override
    public boolean apply(BlockVector2 position) throws WorldEditException {
        return mask.test(position) && function.apply(position);
    }

}
```

### 2.2 Location on the codebase

- **Package**: `com.sk89q.worldedit.function`

### 2.3 Explanation

This class is not used.

### 2.4 Proposal of a refactoring 

- A simple delete is enough.
- @Deprecated is also an option.


# 3. Primitive Obsession

### 3.1 Code Snippet

```
public int drawLine(Pattern pattern, List<BlockVector3> vectors, double radius, boolean filled)
            throws MaxChangedBlocksException {

        Set<BlockVector3> vset = new HashSet<>();

        for (int i = 0; !vectors.isEmpty() && i < vectors.size() - 1; i++) {
            BlockVector3 pos1 = vectors.get(i);
            BlockVector3 pos2 = vectors.get(i + 1);

            int x1 = pos1.x();
            int y1 = pos1.y();
            int z1 = pos1.z();
            int x2 = pos2.x();
            int y2 = pos2.y();
            int z2 = pos2.z();
            int tipx = x1;
            int tipy = y1;
            int tipz = z1;
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);
            int dz = Math.abs(z2 - z1);

            if (dx + dy + dz == 0) {
                vset.add(BlockVector3.at(tipx, tipy, tipz));
                continue;
            }

            int dMax = Math.max(Math.max(dx, dy), dz);
            if (dMax == dx) {
                for (int domstep = 0; domstep <= dx; domstep++) {
                    tipx = x1 + domstep * (x2 - x1 > 0 ? 1 : -1);
                    tipy = (int) Math.round(y1 + domstep * ((double) dy) / ((double) dx) * (y2 - y1 > 0 ? 1 : -1));
                    tipz = (int) Math.round(z1 + domstep * ((double) dz) / ((double) dx) * (z2 - z1 > 0 ? 1 : -1));

                    vset.add(BlockVector3.at(tipx, tipy, tipz));
                }
            } else if (dMax == dy) {
                for (int domstep = 0; domstep <= dy; domstep++) {
                    tipy = y1 + domstep * (y2 - y1 > 0 ? 1 : -1);
                    tipx = (int) Math.round(x1 + domstep * ((double) dx) / ((double) dy) * (x2 - x1 > 0 ? 1 : -1));
                    tipz = (int) Math.round(z1 + domstep * ((double) dz) / ((double) dy) * (z2 - z1 > 0 ? 1 : -1));

                    vset.add(BlockVector3.at(tipx, tipy, tipz));
                }
            } else /* if (dMax == dz) */ {
                for (int domstep = 0; domstep <= dz; domstep++) {
                    tipz = z1 + domstep * (z2 - z1 > 0 ? 1 : -1);
                    tipy = (int) Math.round(y1 + domstep * ((double) dy) / ((double) dz) * (y2 - y1 > 0 ? 1 : -1));
                    tipx = (int) Math.round(x1 + domstep * ((double) dx) / ((double) dz) * (x2 - x1 > 0 ? 1 : -1));

                    vset.add(BlockVector3.at(tipx, tipy, tipz));
                }
            }
        }

        vset = getBallooned(vset, radius);
        if (!filled) {
            vset = getHollowed(vset);
        }
        return setBlocks(vset, pattern);
    }
```

### 3.2 Location on the codebase

- **Package:** `com.sk89q.worldedit`
- **Class:** `EditSession`
- **Method** `drawLine`
- **Line** `2593`

### 2.3 Explanation

Primitive Obsession occurs when primitive data types (such as int, double, boolean, etc.) are used excessively to represent concepts that would be better modeled by objects or classes. In this case, all the `int` variables created at the beginning of the method. 

### 2.4 Proposal of a refactoring 

- Rather than working with individual coordinates like x1, y1, z1, consider using BlockVector3 objects for position manipulations. This will encapsulate the x, y, and z values and make the code more readable.
- Abstract the Axis Calculation, create a helper class or method that encapsulates the logic of determining the dominant axis.
- Encapsulate the Position Update Logic, the logic for updating the `tipx`, `tipy`, and `tipz` values could be moved into a method or class that encapsulates the logic of stepping through 3D space, reducing the complexity of the method.



# Code Smell 1 - Data Class

## 1. Code snippet

The following code from the `BaseItem` class illustrates how the class's only purpose is to store data

    public class BaseItem implements NbtValued {
    
        private ItemType itemType;
        @Nullable
        private LazyReference<LinCompoundTag> nbtData;
    
        /**
         * Construct the object.
         *
         * @param itemType Type of the item
         */
        public BaseItem(ItemType itemType) {
            checkNotNull(itemType);
            this.itemType = itemType;
        }
    
        /**
         * Construct the object.
         *
         * @param itemType Type of the item
         * @param nbtData NBT Compound tag
         */
        @Deprecated
        public BaseItem(ItemType itemType, @Nullable CompoundTag nbtData) {
            this(itemType, nbtData == null ? null : LazyReference.from(nbtData::toLinTag));
        }
    
        /**
         * Construct the object.
         *
         * @param itemType Type of the item
          * @param tag NBT Compound tag
         */
        public BaseItem(ItemType itemType, @Nullable LazyReference<LinCompoundTag> tag) {
            checkNotNull(itemType);
            this.itemType = itemType;
            this.nbtData = tag;
        }
    
        /**
         * Get the type of item.
         *
         * @return the type
         */
        public ItemType getType() {
            return this.itemType;
        }
    
        /**
         * Set the type of the item.
         *
         * @param itemType The type to set
         */
        public void setType(ItemType itemType) {
            this.itemType = itemType;
        }
    
        @Nullable
        @Override
        public LazyReference<LinCompoundTag> getNbtReference() {
            return this.nbtData;
        }
    
        @Override
        public void setNbtReference(@Nullable LazyReference<LinCompoundTag> nbtData) {
            this.nbtData = nbtData;
        }
    
        @Override
        public String toString() {
            String nbtString = "";
            if (nbtData != null) {
                nbtString = LinStringIO.writeToString(nbtData.getValue());
            }
    
            return getType().id() + nbtString;
        }
    }
       

## 2. Location on the codebase

- **Package:** `com.sk89q.worldedit.blocks`
- **Class:** `BaseItem.java`

## 3. Explanation

The `BaseItem` class' functionality is centered around managing data rather than performing significant operations or computations. The class primarily serves as a holder for data without meaningful behavior, making it a **data class**.

## 4. Refactoring Proposal

To mitigate the data class smell, we can consider introducing more methods that operate on the `BaseItem`'s state to enrich its functionality. For example, methods that manipulate item data, validate item properties, or interact with game mechanics to add significant value.

# Code Smell 2 - Lazy Class

## 1. Code snippet    

    package com.sk89q.worldedit.extension.platform;
    
    import com.google.auto.value.AutoAnnotation;
    import com.sk89q.worldedit.internal.annotation.Radii;
    
    /**
     * Holder for generated annotation classes.
     */
    class Annotations {
    
        @AutoAnnotation
        static Radii radii(int value) {
            return new AutoAnnotation_Annotations_radii(value);
        }
    
        private Annotations() {
        }
    
    }

## 2. Location on the codebase

- **Package:** `com.sk89q.worldedit.extension.platform;`
- **Class:** `Annotations.java`

## 3. Explanation

The `Annotations` class is minimal in its functionality — it doesn’t manage state, hold data, or perform significant logic. It merely serves to generate an annotation with a single method. It doesn't provide much functionality beyond the single method since it only has a single usage, so it might be considered under-utilized.

## 4. Refactoring Proposal

To mitigate the data class smell, we can consider refactoring to be simply an auxiliary method in the class where it is used, removing the need for a separate `Annotations` class altogether:
    
    private Radii radii(int value) {
        return new AutoAnnotation_Annotations_radii(value);
    }
    
    private void registerArgumentConverters() {
        // Direct use of the radii method
        commandManager.registerConverter(Key.of(double.class, radii(count)),
            CommaSeparatedValuesConverter.wrapAndLimit(ArgumentConverters.get(
                TypeToken.of(double.class)
            ), count)
        );
    }

# Code Smell 3 - Speculative Generality

## 1. Code snippet   

### BlockBag.java

    /**
     * Represents a source to get blocks from and store removed ones.
     */
    public abstract class BlockBag {
    
        /**
         * Checks to see if a block exists without removing it.
         *
         * @param blockState the block state
         * @return whether the block exists
         */
        public boolean peekBlock(BlockState blockState) {
            try {
                fetchBlock(blockState);
                storeBlock(blockState);
                return true;
            } catch (BlockBagException e) {
                return false;
            }
        }
        /**
         * Adds a position to be used a source.
         *
         * @param pos the position
         */
        public abstract void addSourcePosition(Location pos);
    
        /**
         * Adds a position to be used a source.
         *
         * @param pos the position
         */
        public abstract void addSingleSourcePosition(Location pos);
    }

### BukkitPlayerBlockBag.java

    @Override
    public void addSourcePosition(Location pos) {
    }

    @Override
    public void addSingleSourcePosition(Location pos) {
    }
    
## 2. Location on the codebase

- **Class:** BlockBag.java
- **Package:** com.sk89q.worldedit.extent.inventory

- **Class:** BukkitPlayerBlockBag
- **Package:** com.sk89q.worldedit.bukkit


## 3. Explanation

Speculative generality occurs when code includes abstractions or methods in anticipation of future needs, but without a clear, immediate use case. 
Since neither the base `BlockBag` class nor its subclass `BukkitPlayerBlockBag` currently make use of these methods, it suggests they were added with potential, but unfounded, future needs in mind.
Currently they add extra complexity without delivering any direct benefit. Unused methods can create confusion for developers, who may assume they have a specific purpose or wonder if they’re needed for some functionality.

## 4. Refactoring Proposal

Reducing speculative generality helps keep the codebase focused, cleaner, and easier to understand. In this case we can:

 - Document as optional and make them protected: If these methods could potentially be useful in some contexts (e.g., by future subclasses), they could be documented as “optional” and changed from public to protected. This would limit their visibility to subclasses within the same package and indicate that they aren’t core methods of `BlockBag`.
 - Remove them entirely: Deleting these methods from both `BlockBag` and `BukkitPlayerBlockBag` where they are implemented, which reduces the class size and complexity, making it easier to read and maintain.

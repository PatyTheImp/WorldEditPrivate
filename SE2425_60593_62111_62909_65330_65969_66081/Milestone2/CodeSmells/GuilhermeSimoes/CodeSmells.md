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
- **Class:** `BaseItem`

## 3. Explanation

The `BaseItem` class' functionality is centered around managing data rather than performing significant operations or computations. The class primarily serves as a holder for data without meaningful behavior, making it a **data class**.

## 4. Refactoring Proposal

To mitigate the data class smell, we can consider:

  - Adding More Behavior: Introducing more methods that operate on the `BaseItem`'s state to enrich its functionality. For example, methods that manipulate item data, validate item properties, or interact with game mechanics to add significant value.


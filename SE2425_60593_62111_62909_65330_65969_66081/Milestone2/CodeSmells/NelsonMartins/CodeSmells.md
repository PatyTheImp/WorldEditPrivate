# Code Smell 1 (Long Method)

## 1. Code snippet:

    @Override
    public Set<Entry<BlockVector3, V>> entrySet() {
        Set<Entry<BlockVector3, V>> es = entrySet;
        if (es == null) {
            entrySet = es = new AbstractSet<>() {
                @Override
                public Iterator<Entry<BlockVector3, V>> iterator() {
                    return new Iterator<>() {

                        private final ObjectIterator<Long2ObjectMap.Entry<Int2ObjectMap<V>>> primaryIterator
                            = Long2ObjectMaps.fastIterator(maps);
                        private Long2ObjectMap.Entry<Int2ObjectMap<V>> currentPrimaryEntry;
                        private ObjectIterator<Int2ObjectMap.Entry<V>> secondaryIterator;
                        private boolean finished;
                        private LazyEntry next;

                        @Override
                        public boolean hasNext() {
                            if (finished) {
                                return false;
                            }
                            if (next == null) {
                                LazyEntry proposedNext = computeNext();
                                if (proposedNext == null) {
                                    finished = true;
                                    return false;
                                }
                                next = proposedNext;
                            }
                            return true;
                        }

                        private LazyEntry computeNext() {
                            if (secondaryIterator == null || !secondaryIterator.hasNext()) {
                                if (!primaryIterator.hasNext()) {
                                    return null;
                                }

                                currentPrimaryEntry = primaryIterator.next();
                                secondaryIterator = Int2ObjectMaps.fastIterator(currentPrimaryEntry.getValue());
                                // be paranoid
                                checkState(secondaryIterator.hasNext(),
                                    "Should not have an empty map entry, it should have been removed!");
                            }
                            Int2ObjectMap.Entry<V> next = secondaryIterator.next();
                            return new LazyEntry(currentPrimaryEntry.getLongKey(), next.getIntKey(), next.getValue());
                        }

                        @Override
                        public Entry<BlockVector3, V> next() {
                            if (!hasNext()) {
                                throw new NoSuchElementException();
                            }
                            LazyEntry tmp = next;
                            next = null;
                            return tmp;
                        }

                        @Override
                        public void remove() {
                            secondaryIterator.remove();
                            // ensure invariants hold
                            if (currentPrimaryEntry.getValue().isEmpty()) {
                                // the remove call cleared this map. call remove on the primary iter
                                primaryIterator.remove();
                            }
                        }
                    };
                }

                @Override
                public int size() {
                    return BlockMap.this.size();
                }
            };
        }
        return es;
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit.util.collection`
- **Class:** `BlockMap`

## 3. Explanation:

This method has a long method code smell because it has to many lines of code, due to the fact that it has a nested
iterator class, most of the code in it is this iterator class. It has a total of 71 lines of code.

## 4. Proposal of a refactoring:

The method has an iterator class nested inside it, so we must make that nested class a concrete class.
It should be something like this:
- EntryIterator.java
   
        public class EntryIterator implements Iterator<Entry<BlockVector3, V>> {
            private final ObjectIterator<Long2ObjectMap.Entry<Int2ObjectMap<V>>> primaryIterator = Long2ObjectMaps.fastIterator(maps);
            private Long2ObjectMap.Entry<Int2ObjectMap<V>> currentPrimaryEntry;
            private ObjectIterator<Int2ObjectMap.Entry<V>> secondaryIterator;
            private boolean finished;
            private LazyEntry next;
            
            public EntryIterator

            @Override
            public boolean hasNext() {
                if (finished) {
                    return false;
                }
                if (next == null) {
                    LazyEntry proposedNext = computeNext();
                    if (proposedNext == null) {
                        finished = true;
                        return false;
                    }
                    next = proposedNext;
                }
                return true;
            }

            private LazyEntry computeNext() {
                if (secondaryIterator == null || !secondaryIterator.hasNext()) {
                    if (!primaryIterator.hasNext()) {
                        return null;
                    }

                    currentPrimaryEntry = primaryIterator.next();
                    secondaryIterator = Int2ObjectMaps.fastIterator(currentPrimaryEntry.getValue());
                    // be paranoid
                    checkState(secondaryIterator.hasNext(),
                        "Should not have an empty map entry, it should have been removed!");
                }
                Int2ObjectMap.Entry<V> next = secondaryIterator.next();
                return new LazyEntry(currentPrimaryEntry.getLongKey(), next.getIntKey(), next.getValue());
            }

            @Override
            public Entry<BlockVector3, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                LazyEntry tmp = next;
                next = null;
                return tmp;
            }

            @Override
            public void remove() {
                secondaryIterator.remove();
                // ensure invariants hold
                if (currentPrimaryEntry.getValue().isEmpty()) {
                    // the remove call cleared this map. call remove on the primary iter
                    primaryIterator.remove();
                }
            }
        }
- BlockMap.entrySet

        public Set<Entry<BlockVector3, V>> entrySet() {
            Set<Entry<BlockVector3, V>> es = entrySet;
            if (es == null) {
                entrySet = es = new AbstractSet<>() {
                    @Override
                    public Iterator<Entry<BlockVector3, V>> iterator() {
                        return new EntryIterator();
                    }

                    @Override
                    public int size() {
                        return BlockMap.this.size();
                    }
                };
            }
            return es;
        }

# Code Smell 2 (God Class)

## 1. Code snippet:
    
    public class LocalSession {

        private static final transient int CUI_VERSION_UNINITIALIZED = -1;
        public static transient int MAX_HISTORY_SIZE = 15;

        // total of 37 atributtes

        /**
         * Construct the object.
         *
         * <p>{@link #setConfiguration(LocalConfiguration)} should be called
         * later with configuration.</p>
         */
        public LocalSession() {
        }
    
        /**
         * Construct the object.
         *
         * @param config the configuration
         */
        public LocalSession(@Nullable LocalConfiguration config) {
            this.config = config;
        }
    
        /**
         * Set the configuration.
         *
         * @param config the configuration
         */
        public void setConfiguration(LocalConfiguration config) {
            checkNotNull(config);
            this.config = config;
        }
    
        /**
         * Called on post load of the session from persistent storage.
         */
        public void postLoad() {
            if (defaultSelector != null) {
                this.selector = defaultSelector.createSelector();
            }
        }

        // total of 94 methods
    }
    // total of 1036 lines of code

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit`
- **Class:** `LocalSession`

## 3. Explanation:

This class is a god class because it has a large number of attributes (37) and methods (94), which makes it difficult
to find some functionality in the class. It has a total of 1036 lines of code.

## 4. Proposal of a refactoring:

To refactor this class we should split it into smaller classes, each one with a single responsibility. For example,
we could create a class for the configuration, another for the selector, and so on.

# Code Smell 3 (Feature Envy)

## 1. Code snippet:

    public class CLIWorldEdit {
        
        //...

        public void onStarted() {
            setupPlatform();
    
            setupRegistries();
            WorldEdit.getInstance().loadMappings();
    
            config.load();
    
            WorldEdit.getInstance().getEventBus().post(new PlatformReadyEvent(platform));
        }
    
        public void onStopped() {
            WorldEdit worldEdit = WorldEdit.getInstance();
            worldEdit.getSessionManager().unload();
            worldEdit.getPlatformManager().unregister(platform);
        }
        
        //...
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit.cli`
- **Class:** `CLIWorldEdit`

## 3. Explanation:

In this class, the methods `onStarted` and `onStopped` are using a lot of methods from the `WorldEdit` class.
This suggests that some of this logic should be moved to the `WorldEdit` class, as it seems to be more related to
the `WorldEdit` class than to the `CLIWorldEdit` class.

## 4. Proposal of a refactoring:

Create methods `onStarted` and `onStopped` in the `WorldEdit` class to handle the logic that is currently in the `CLIWorldEdit` class.
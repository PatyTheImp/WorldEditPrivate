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
        }

# Code Smell 2 (...)

## 1. Code snippet:
    
    ...

## 2. Location on the codebase:

- **Package:** `...`
- **Class:** `...`

## 3. Explanation:

...

## 4. Proposal of a refactoring:

...

# Code Smell 3 (...)

## 1. Code snippet:

    ...

## 2. Location on the codebase:

- **Package:** `...`
- **Class:** `...`

## 3. Explanation:

...

## 4. Proposal of a refactoring:

...
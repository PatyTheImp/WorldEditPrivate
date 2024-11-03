# Design Pattern 1 - Template Method Pattern

## 1 - Code Snippets:

### AbstractDirectionConverter.java

    public abstract class AbstractDirectionConverter<D> implements ArgumentConverter<D> {
    
      @Override
      public ConversionResult<D> convert(String argument, InjectedValueAccess context) {
          Player player = context.injectedValue(Key.of(Player.class, OptionalArg.class))
              .orElse(null);
          try {
              return SuccessfulConversion.fromSingle(convertDirection(argument, player, includeDiagonals));
          } catch (Exception e) {
              return FailedConversion.from(e);
          }
      }

      protected abstract D convertDirection(String argument, @Nullable Player player, boolean includeDiagonals) throws UnknownDirectionException;

    // Other Methods and fields
    }

### DirectionConverter.java

    public final class DirectionConverter extends AbstractDirectionConverter<Direction> {

      private DirectionConverter(WorldEdit worldEdit, boolean includeDiagonals) {
          super(worldEdit, includeDiagonals);
      }
  
      public static void register(WorldEdit worldEdit, CommandManager commandManager) {
          for (boolean includeDiagonals : new boolean[] { false, true }) {
              DirectionConverter directionConverter = new DirectionConverter(worldEdit, includeDiagonals);
              register(commandManager, directionConverter, Direction.class, includeDiagonals);
          }
      }
  
      @Override
      protected Direction convertDirection(String argument, @Nullable Player player, boolean includeDiagonals) throws UnknownDirectionException {
          final BlockVector3 vec = includeDiagonals
                  ? getWorldEdit().getDiagonalDirection(player, argument)
                  : getWorldEdit().getDirection(player, argument);
          return Optional.ofNullable(Direction.findClosest(vec.toVector3(), Direction.Flag.ALL))
                  .orElseThrow(() -> new UnknownDirectionException(argument));
      }
    }

### DirectionVectorConverter.java

    public final class DirectionVectorConverter extends AbstractDirectionConverter<BlockVector3> {
  
      public DirectionVectorConverter(WorldEdit worldEdit, boolean includeDiagonals) {
          super(worldEdit, includeDiagonals);
      }
  
      public static void register(WorldEdit worldEdit, CommandManager commandManager) {
          for (boolean includeDiagonals : new boolean[] { false, true }) {
              DirectionVectorConverter directionConverter = new DirectionVectorConverter(worldEdit, includeDiagonals);
              register(commandManager, directionConverter, BlockVector3.class, includeDiagonals);
          }
      }
  
      @Override
      protected BlockVector3 convertDirection(String argument, @Nullable Player player, boolean includeDiagonals) throws UnknownDirectionException {
          return includeDiagonals
                  ? getWorldEdit().getDiagonalDirection(player, argument)
                  : getWorldEdit().getDirection(player, argument);
      }
    }


## 2 - Class Diagram

![TemplateMethod](https://github.com/user-attachments/assets/c4b515a2-ed85-4430-aafa-efa4aacc3326)

## 3 - Location on the codebase

**Package: `com.sk89q.worldedit.command.argument`**

**Classes and Methods:**
  - `AbstractDirectionConverter.java`
    - `convertDirection(String argument, @Nullable Player player, boolean includeDiagonals)`: Takes a direction in text form and converts it into a direction object.

  - `DirectionConverter.java`
    - `convertDirection(String argument, @Nullable Player player, boolean includeDiagonals)`:  Takes a direction in text form and converts it into a `Direction` object.
   
  - `DirectionVectorConverter.java`
    - `convertDirection(String argument, @Nullable Player player, boolean includeDiagonals)`:  Takes a direction in text form and converts it into a `BlockVector3` object.
   
## 4 - Discussion

The abstract class `AbstractDirectionConverter` defines the template structure of the conversion process, 
outlining the general steps required to convert a direction argument. The `DirectionVectorConverter` and 
`DirectionConverter` classes override `convertDirection` to provide concrete behavior specific to their 
type of direction conversion.



# Design Pattern 2 - Iterator Pattern

## 1 - Code Snippets:

### DoubleArrayList.java

    public class DoubleArrayList<A, B> implements Iterable<Map.Entry<A, B>> {
    
        private final List<A> listA = new ArrayList<>();
        private final List<B> listB = new ArrayList<>();
        private boolean isReversed = false;

        public class ForwardEntryIterator<T extends Map.Entry<A, B>>
            implements Iterator<Map.Entry<A, B>> {

            private final Iterator<A> keyIterator;
            private final Iterator<B> valueIterator;
    
            public ForwardEntryIterator(Iterator<A> keyIterator, Iterator<B> valueIterator) {
                this.keyIterator = keyIterator;
                this.valueIterator = valueIterator;
            }

            @Override
            public boolean hasNext() {
                return keyIterator.hasNext();
            }
    
            @Override
            public Map.Entry<A, B> next() throws NoSuchElementException {
                return new Entry<A, B>(keyIterator.next(), valueIterator.next());
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public class ReverseEntryIterator<T extends Map.Entry<A, B>>
            implements Iterator<Map.Entry<A, B>> {
    
            private final ListIterator<A> keyIterator;
            private final ListIterator<B> valueIterator;
    
            public ReverseEntryIterator(ListIterator<A> keyIterator, ListIterator<B> valueIterator) {
                this.keyIterator = keyIterator;
                this.valueIterator = valueIterator;
            }
    
            @Override
            public boolean hasNext() {
                return keyIterator.hasPrevious();
            }
    
            @Override
            public Map.Entry<A, B> next() throws NoSuchElementException {
                return new Entry<A, B>(keyIterator.previous(), valueIterator.previous());
            }
    
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }    
        // Other methods and fields
    }

### EditSession.java

    public class EditSession implements Extent, AutoCloseable {

        // Constructor, parameters, methods and other fields

        public int deformRegion(final Region region, final Vector3 zero, final Vector3 unit, final Expression expression,
                            final int timeout) throws ExpressionException, MaxChangedBlocksException {
            final Variable x = expression.getSlots().getVariable("x")
                .orElseThrow(IllegalStateException::new);
            final Variable y = expression.getSlots().getVariable("y")
                .orElseThrow(IllegalStateException::new);
            final Variable z = expression.getSlots().getVariable("z")
                .orElseThrow(IllegalStateException::new);
    
            final WorldEditExpressionEnvironment environment = new WorldEditExpressionEnvironment(this, unit, zero);
            expression.setEnvironment(environment);
    
            final DoubleArrayList<BlockVector3, BaseBlock> queue = new DoubleArrayList<>(false);
    
            for (BlockVector3 targetBlockPosition : region) {
                final Vector3 targetPosition = targetBlockPosition.toVector3();
                environment.setCurrentBlock(targetPosition);
    
                // offset, scale
                final Vector3 scaled = targetPosition.subtract(zero).divide(unit);
    
                // transform
                expression.evaluate(new double[]{ scaled.x(), scaled.y(), scaled.z() }, timeout);
    
                final BlockVector3 sourcePosition = environment.toWorld(x.value(), y.value(), z.value());
    
                // read block from world
                final BaseBlock material = world.getFullBlock(sourcePosition);
    
                // queue operation
                queue.put(targetBlockPosition, material);
            }
    
            int affected = 0;
            for (Map.Entry<BlockVector3, BaseBlock> entry : queue) {
                BlockVector3 position = entry.getKey();
                BaseBlock material = entry.getValue();
    
                // set at new position
                if (setBlock(position, material)) {
                    ++affected;
                }
            }
    
            return affected;
        }
    }



## 2 - Class Diagram

![Iterator](https://github.com/user-attachments/assets/4d2e5ee0-91c7-4c3f-86c4-9d5bde41c1ba)

## 3 - Location on the codebase

**Package: `EditSession.java`: `com.sk89q.worldedit`, `DoubleArrayList.java`: `com.sk89q.worldedit.util.collection`**

**Classes and Methods:**
  - `DoubleArrayList.java`
    - `ForwardEntryIterator<T extends Map.Entry<A, B>>` class: Iterates over the entries in the same order they were added to the `DoubleArrayList`.
    - `ReverseEntryIterator<T extends Map.Entry<A, B>>` class: Iterates over the entries in the reverse order of how they were added to the `DoubleArrayList`.
   
  - `EditSession.java`
    - `deformRegion(final Region region, final Vector3 zero, final Vector3 unit, final Expression expression, final int timeout)`:  Utilizes `DoubleArrayList` as a temporary storage for block position and material pairs, maintaining their association throughout the processing. It also facilitates the usage of 2 types of iterators.
   
## 4 - Discussion

The `DoubleArrayList` class relates to the Iterator Pattern in several ways, particularly in how it allows iteration over its elements through the `ForwardEntryIterator` and `ReverseEntryIterator` classes. The `deformRegion` method effectively utilizes this pattern to manage and apply changes to a region.

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

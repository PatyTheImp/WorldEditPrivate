# Design Pattern 1 (Facade Pattern)

## 1. Code snippet:
    
    //...

    public class CLIWorldEdit {

        //...
    
        private void setupPlatform() {
            WorldEdit.getInstance().getPlatformManager().register(platform);
    
            registerCommands();
    
            config = new CLIConfiguration(this);
    
            // There's no other platforms, so fire this immediately
            WorldEdit.getInstance().getEventBus().post(new PlatformsRegisteredEvent());
    
            this.fileRegistries = new FileRegistries(this);
            this.fileRegistries.loadDataFiles();
        }
    
        private void registerCommands() {
            PlatformCommandManager pcm = WorldEdit.getInstance().getPlatformManager()
                .getPlatformCommandManager();
            pcm.registerSubCommands(
                "cli",
                ImmutableList.of(),
                "CLI-specific commands",
                CLIExtraCommandsRegistration.builder(),
                new CLIExtraCommands()
            );
        }
    
        //...
    
        public void onInitialized() {
            // Setup working directory
            workingDir = Paths.get("worldedit");
            if (!Files.exists(workingDir)) {
                try {
                    Files.createDirectory(workingDir);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
    
            this.commandSender = new CLICommandSender(this, LOGGER);
            this.platform = new CLIPlatform(this);
            LOGGER.info("WorldEdit CLI (version " + getInternalVersion() + ") is loaded");
        }
    
        //...
    
        public void saveAllWorlds(boolean force) {
            platform.getWorlds().stream()
                .filter(world -> world instanceof CLIWorld)
                .forEach(world -> ((CLIWorld) world).save(force));
        }

        //...
    }

## 2. Class diagram:

![Facade Class Diagram](FacadeClassDiagram.png)

## 3. Location on the codebase:

**Package:** `com.sl89q.worldedit.cli`

**Class:** `CLIWorldEdit`

**Fields and Methods:** 

- `void onInitialized()`: Initialize the command sender and platform and logs the initialization of the CLIWorldEdit.
- `void setupPlatform()`: Register the platform, commands, configuration, fire the `PlatformsRegisteredEvent` and load the data files into file registries.
- `void registerCommands()`: Register the CLI-specific commands with the `PlatformCommandManager`.
- `void saveAllWorlds(boolean force)`: Save all the worlds managed by the platform, with an option to force the save.

## 4. Discussion:

The facade pattern is used here to simplify interactions with the WorldEdit CLI
subsystem and hide his complexity. it simplifies operations like initialization and setup,
command registration, event handling and subsystem management.

# Design Pattern 2 (...)

## 1. Code snippet:

    ...

## 2. Class diagram:

    ...

## 3. Location on the codebase:

- **Package:** ...
- **Class:** ...
- **Fields and Methods:** ...

## 4. Discussion:

    ...

# Design Pattern 3 (...)

## 1. Code snippet:

    ...

## 2. Class diagram:

    ...

## 3. Location on the codebase:

- **Package:** ...
- **Class:** ...
- **Fields and Methods:** ...

## 4. Discussion:

    ...

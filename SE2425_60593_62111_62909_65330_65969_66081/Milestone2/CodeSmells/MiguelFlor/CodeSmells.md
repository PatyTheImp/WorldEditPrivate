# Code Smell 1 (Long Method)

## 1. Code snippet:
    @Command(
    name = "delchunks",
    desc = "Delete chunks that your selection includes"
    )
    @CommandPermissions("worldedit.delchunks")
    @Logging(REGION)
    public void deleteChunks(Actor actor, World world, LocalSession session,
                                @ArgFlag(name = 'o', desc = "Only delete chunks older than the specified time.")
                                    ZonedDateTime beforeTime) throws WorldEditException {
        Path worldDir = world.getStoragePath();
        if (worldDir == null) {
            throw new StopExecutionException(TextComponent.of("Couldn't find world folder for this world."));
        }

        Path chunkPath = worldEdit.getWorkingDirectoryPath(DELCHUNKS_FILE_NAME);
        ChunkDeletionInfo currentInfo = null;
        if (Files.exists(chunkPath)) {
            try {
                currentInfo = ChunkDeleter.readInfo(chunkPath);
            } catch (IOException e) {
                throw new StopExecutionException(TextComponent.of("Error reading existing chunk file."));
            }
        }
        if (currentInfo == null) {
            currentInfo = new ChunkDeletionInfo();
            currentInfo.batches = new ArrayList<>();
        }

        ChunkDeletionInfo.ChunkBatch newBatch = new ChunkDeletionInfo.ChunkBatch();
        newBatch.worldPath = worldDir.toAbsolutePath().normalize().toString();
        newBatch.backup = true;
        final Region selection = session.getSelection(world);
        if (selection instanceof CuboidRegion) {
            newBatch.minChunk = selection.getMinimumPoint().shr(4).toBlockVector2();
            newBatch.maxChunk = selection.getMaximumPoint().shr(4).toBlockVector2();
        } else {
            // this has a possibility to OOM for very large selections still
            Set<BlockVector2> chunks = selection.getChunks();
            newBatch.chunks = new ArrayList<>(chunks);
        }
        if (beforeTime != null) {
            newBatch.deletionPredicates = new ArrayList<>();
            ChunkDeletionInfo.DeletionPredicate timePred = new ChunkDeletionInfo.DeletionPredicate();
            timePred.property = "modification";
            timePred.comparison = "<";
            timePred.value = String.valueOf((int) beforeTime.toOffsetDateTime().toEpochSecond());
            newBatch.deletionPredicates.add(timePred);
        }
        currentInfo.batches.add(newBatch);

        try {
            ChunkDeleter.writeInfo(currentInfo, chunkPath);
        } catch (IOException | JsonIOException e) {
            throw new StopExecutionException(TextComponent.of("Failed to write chunk list: " + e.getMessage()));
        }

        actor.print(TextComponent.of(
            String.format("%d chunk(s) have been marked for deletion the next time the server starts.",
                newBatch.getChunkCount())
        ));
        if (currentInfo.batches.size() > 1) {
            actor.printDebug(TextComponent.of(
                String.format("%d chunks total marked for deletion. (May have overlaps).",
                    currentInfo.batches.stream().mapToInt(ChunkDeletionInfo.ChunkBatch::getChunkCount).sum())
            ));
        }
        actor.print(TextComponent.of("You can mark more chunks for deletion, or to stop now, run: ", TextColor.LIGHT_PURPLE)
                .append(TextComponent.of("/stop", TextColor.AQUA)
                        .clickEvent(ClickEvent.of(ClickEvent.Action.SUGGEST_COMMAND, "/stop"))));
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit.command`
- **Class:** `ChunkCommands`

## 3. Explanation:

This function is a long method because it has a lot of lines of code, and it does a lot of things.
It is responsible for deleting chunks that the user selects.
It has a lot of responsibilities, such as reading and writing files, creating new objects, and printing messages to the user.
This method could be refactored to be more readable and maintainable.

## 4. Proposal of a refactoring:
breakdown the method into smaller methods.
- `deleteChunks` could be broken down into:
    - `readChunkInfo` - to get the current chunk info, reading the file.
    - `isBeforeTime` - check if the beforeTime is not null. Doing all the logic that comes after that.
    - `writeChunkInfo` - to write the chunk info to the file.
    - `printMessages` - to print the messages to the user.

# Code Smell 2 (Long Parameter List)

## 1. Code snippet:
    @Override
    public boolean actPrimary(Platform server, LocalConfiguration config, Player player, LocalSession session, Location clicked, @Nullable Direction face) {
        return handleCycle(config, player, session, clicked, true);
    }

## 2. Location on the codebase:

- **Package:** `com.sk89q.worldedit.command.tool`
- **Class:** `BlockDataCyler`

## 3. Explanation:
This method has a lot of parameter lists that can be encapsulated in an object.
This increases the complexity of the code and makes it harder to maintain and understand.

## 4. Proposal of a refactoring:
Create an object that encapsulates all the parameters and pass this object as a parameter to the method.

    public class ActionContext {
        private final Platform server;
        private final LocalConfiguration config;
        private final Player player;
        private final LocalSession session;
        private final Location clicked;
        private final Direction face;
    
        public ActionContext(Platform server, LocalConfiguration config, Player player, LocalSession session, Location clicked, @Nullable Direction face) {
            this.server = server;
            this.config = config;
            this.player = player;
            this.session = session;
            this.clicked = clicked;
            this.face = face;
        }
    
        public Platform getServer() {
            return server;
        }
    
        public LocalConfiguration getConfig() {
            return config;
        }
    
        public Player getPlayer() {
            return player;
        }
    
        public LocalSession getSession() {
            return session;
        }
    
        public Location getClicked() {
            return clicked;
        }
    
        public Direction getFace() {
            return face;
        }
    } 

And modify the method so it looks like this
    
        @Override
        public boolean actPrimary(ActionContext actionContext) {
            return handleCycle(actionContext.getConfig(), actionContext.getPlayer(), actionContext.getSession(), actionContext.getClicked(), true);
        }

# Code Smell 3 (Duplicate Code)

## 1. Code snippet:


    @Command(
        name = "cylinder",
        aliases = { "cyl", "c" },
        desc = "Choose the cylinder brush"
    )
    @CommandPermissions("worldedit.brush.cylinder")
    public void cylinderBrush(Player player, LocalSession session,
                              @Arg(desc = "The pattern of blocks to set")
                                  Pattern pattern,
                              @Arg(desc = "The radius of the cylinder", def = "2")
                                  double radius,
                              @Arg(desc = "The height of the cylinder", def = "1")
                                  int height,
                              @Switch(name = 'h', desc = "Create hollow cylinders instead")
                                  boolean hollow) throws WorldEditException {
        worldEdit.checkMaxBrushRadius(radius);
        worldEdit.checkMaxBrushRadius(height);

        Brush brush = hollow ? new HollowCylinderBrush(height) : new CylinderBrush(height);

        BrushTool tool = session.forceBrush(
            player.getItemInHand(HandSide.MAIN_HAND).getType(),
            brush,
            "worldedit.brush.cylinder"
        );
        tool.setFill(pattern);
        tool.setSize(radius);

        player.printInfo(TranslatableComponent.of("worldedit.brush.cylinder.equip", TextComponent.of((int) radius), TextComponent.of(height)));
        ToolCommands.sendUnbindInstruction(player, UNBIND_COMMAND_COMPONENT);
    }

    @Command(
        name = "splatter",
        aliases = { "splat" },
        desc = "Choose the splatter brush"
    )
    @CommandPermissions("worldedit.brush.splatter")
    public void splatterBrush(Player player, LocalSession session,
                              @Arg(desc = "The pattern of blocks to set")
                                  Pattern pattern,
                              @Arg(desc = "The radius of the splatter", def = "2")
                                  double radius,
                              @Arg(desc = "The decay of the splatter between 0 and 10", def = "1")
                                  int decay) throws WorldEditException {
        worldEdit.checkMaxBrushRadius(radius);

        if (decay < 0 || decay > 10) {
            player.printError(TranslatableComponent.of("worldedit.brush.splatter.decay-out-of-range", TextComponent.of(decay)));
            return;
        }

        BrushTool tool = session.forceBrush(
            player.getItemInHand(HandSide.MAIN_HAND).getType(),
            new SplatterBrush(decay),
            "worldedit.brush.splatter"
        );
        tool.setFill(pattern);
        tool.setSize(radius);

        player.printInfo(TranslatableComponent.of("worldedit.brush.splatter.equip", TextComponent.of((int) radius), TextComponent.of(decay)));
        ToolCommands.sendUnbindInstruction(player, UNBIND_COMMAND_COMPONENT);
    }

## 2. Location on the codebase:
- **Package:** `com.sk89q.worldedit.command`
- **Class:** `BrushCommands`

## 3. Explanation:
Last lines of the methods `cylinderBrush`, and `splatterBrush` are duplicated. (There is more methods that can be applied this refactoring)
        
    tool.setFill(pattern);
    tool.setSize(radius);

    player.printInfo(TranslatableComponent.of("worldedit.brush.splatter.equip", TextComponent.of((int) radius), TextComponent.of(decay)));
    ToolCommands.sendUnbindInstruction(player, UNBIND_COMMAND_COMPONENT);

This four lines can be extracted to a new method and called in the end of each method.

## 4. Proposal of a refactoring:
Create a new method that receives the `tool`, `pattern`, `radius`, `decay`, and `player` as parameters and call this method in the end of each method.

    private void setTool(BrushTool tool, Pattern pattern, double radius, int decay, Player player) {
        tool.setFill(pattern);
        tool.setSize(radius);

        player.printInfo(TranslatableComponent.of("worldedit.brush.splatter.equip", TextComponent.of((int) radius), TextComponent.of(decay)));
        ToolCommands.sendUnbindInstruction(player, UNBIND_COMMAND_COMPONENT);
    }



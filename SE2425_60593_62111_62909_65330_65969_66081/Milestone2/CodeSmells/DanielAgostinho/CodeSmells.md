# Code Smell 1 - (Large Class)

## 1. Code snippet


       
    public class PermissionsResolverManager implements PermissionsResolver {

    private static final String CONFIG_HEADER = "#\r\n"
        + "# WEPIF Configuration File\r\n"
        + "#\r\n"
        + "# This file handles permissions configuration for every plugin using WEPIF\r\n"
        + "#\r\n"
        + "# About editing this file:\r\n"
        + "# - DO NOT USE TABS. You MUST use spaces or Bukkit will complain. If\r\n"
        + "#   you use an editor like Notepad++ (recommended for Windows users), you\r\n"
        + "#   must configure it to \"replace tabs with spaces.\" In Notepad++, this can\r\n"
        + "#   be changed in Settings > Preferences > Language Menu.\r\n"
        + "# - Don't get rid of the indents. They are indented so some entries are\r\n"
        + "#   in categories (like \"enforce-single-session\" is in the \"protection\"\r\n"
        + "#   category.\r\n"
        + "# - If you want to check the format of this file before putting it\r\n"
        + "#   into WEPIF, paste it into https://yaml-online-parser.appspot.com/\r\n"
        + "#   and see if it gives \"ERROR:\".\r\n"
        + "# - Lines starting with # are comments and so they are ignored.\r\n"
        + "\r\n";
    private static final Logger LOGGER = LogManagerCompat.getLogger();

    private static PermissionsResolverManager instance;

    public static void initialize(Plugin plugin) {
        if (!isInitialized()) {
            instance = new PermissionsResolverManager(plugin);
        }
    }

    public static boolean isInitialized() {
        return instance != null;
    }

    public static PermissionsResolverManager getInstance() {
        if (!isInitialized()) {
            throw new WEPIFRuntimeException("WEPIF has not yet been initialized!");
        }
        return instance;
    }

    private final Server server;
    private PermissionsResolver permissionResolver;
    private YAMLProcessor config;
    private final List<Class<? extends PermissionsResolver>> enabledResolvers = new ArrayList<>();

    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Class<? extends PermissionsResolver>[] availableResolvers = new Class[] {
        PluginPermissionsResolver.class,
        PermissionsExResolver.class,
        bPermissionsResolver.class,
        GroupManagerResolver.class,
        NijiPermissionsResolver.class,
        VaultResolver.class,
        DinnerPermsResolver.class,
        FlatFilePermissionsResolver.class
    };

    protected PermissionsResolverManager(Plugin plugin) {
        this.server = plugin.getServer();
        (new ServerListener()).register(plugin); // Register the events

        loadConfig(new File("wepif.yml"));
        findResolver();
    }

    public void findResolver() {
        for (Class<? extends PermissionsResolver> resolverClass : enabledResolvers) {
            try {
                Method factoryMethod = resolverClass.getMethod("factory", Server.class, YAMLProcessor.class);

                this.permissionResolver = (PermissionsResolver) factoryMethod.invoke(null, this.server, this.config);

                if (this.permissionResolver != null) {
                    break;
                }
            } catch (Throwable e) {
                LOGGER.warn("Error in factory method for " + resolverClass.getSimpleName(), e);
                continue;
            }
        }
        if (permissionResolver == null) {
            permissionResolver = new ConfigurationPermissionsResolver(config);
        }
        permissionResolver.load();
        LOGGER.info("WEPIF: " + permissionResolver.getDetectionMessage());
    }

    public void setPluginPermissionsResolver(Plugin plugin) {
        if (!(plugin instanceof PermissionsProvider)) {
            return;
        }

        permissionResolver = new PluginPermissionsResolver((PermissionsProvider) plugin, plugin);
        LOGGER.info("WEPIF: " + permissionResolver.getDetectionMessage());
    }

    @Override
    public void load() {
        findResolver();
    }

    @Override
    public boolean hasPermission(String name, String permission) {
        return permissionResolver.hasPermission(name, permission);
    }

    @Override
    public boolean hasPermission(String worldName, String name, String permission) {
        return permissionResolver.hasPermission(worldName, name, permission);
    }

    @Override
    public boolean inGroup(String player, String group) {
        return permissionResolver.inGroup(player, group);
    }

    @Override
    public String[] getGroups(String player) {
        return permissionResolver.getGroups(player);
    }

    @Override
    public boolean hasPermission(OfflinePlayer player, String permission) {
        return permissionResolver.hasPermission(player, permission);
    }

    @Override
    public boolean hasPermission(String worldName, OfflinePlayer player, String permission) {
        return permissionResolver.hasPermission(worldName, player, permission);
    }

    @Override
    public boolean inGroup(OfflinePlayer player, String group) {
        return permissionResolver.inGroup(player, group);
    }

    @Override
    public String[] getGroups(OfflinePlayer player) {
        return permissionResolver.getGroups(player);
    }

    @Override
    public String getDetectionMessage() {
        return "Using WEPIF for permissions";
    }

    private boolean loadConfig(File file) {
        boolean isUpdated = false;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.warn("Failed to create new configuration file", e);
            }
        }
        config = new YAMLProcessor(file, false, YAMLFormat.EXTENDED);
        try {
            config.load();
        } catch (IOException e) {
            LOGGER.warn("Error loading WEPIF configuration", e);
        }
        List<String> keys = config.getKeys(null);
        config.setHeader(CONFIG_HEADER);

        if (!keys.contains("ignore-nijiperms-bridges")) {
            config.setProperty("ignore-nijiperms-bridges", true);
            isUpdated = true;
        }

        if (!keys.contains("resolvers")) {
            //List<String> resolverKeys = config.getKeys("resolvers");
            List<String> resolvers = new ArrayList<>();
            for (Class<?> clazz : availableResolvers) {
                resolvers.add(clazz.getSimpleName());
            }
            enabledResolvers.addAll(Arrays.asList(availableResolvers));
            config.setProperty("resolvers.enabled", resolvers);
            isUpdated = true;
        } else {
            List<String> disabledResolvers = config.getStringList("resolvers.disabled", new ArrayList<>());
            List<String> stagedEnabled = config.getStringList("resolvers.enabled", null);
            for (Iterator<String> i = stagedEnabled.iterator(); i.hasNext();) {
                String nextName = i.next();
                Class<?> next = null;
                try {
                    next = Class.forName(getClass().getPackage().getName() + "." + nextName);
                } catch (ClassNotFoundException ignored) {
                }

                if (next == null || !PermissionsResolver.class.isAssignableFrom(next)) {
                    LOGGER.warn("WEPIF: Invalid or unknown class found in enabled resolvers: "
                            + nextName + ". Moving to disabled resolvers list.");
                    i.remove();
                    disabledResolvers.add(nextName);
                    isUpdated = true;
                    continue;
                }
                enabledResolvers.add(next.asSubclass(PermissionsResolver.class));
            }
            
            // And many other methods
    }

## 2. Location on the codebase

- **Package:** `com.sk89q.wepif`
- **Class:** `PermissionsResolverManager`

## 3. Explanation

The `PermissionsResolverManager` class exhibits the "Large Class" code smell due to the following reasons:

1. **High Number of Methods**: The class contains numerous methods, including `initialize`, `isInitialized`, 
`getInstance`, `findResolver`, `setPluginPermissionsResolver`, and several overridden methods from the 
`PermissionsResolver` interface. This indicates that the class is handling multiple responsibilities.

2. **Multiple Responsibilities**: The class is responsible for managing permissions resolvers, loading configurations, 
and handling various permission-related operations. This violates the Single Responsibility Principle (SRP), making
the class harder to maintain and understand.

3. **Large Amount of Code**: The class has a significant amount of code, including complex logic for loading 
configurations, finding resolvers, and handling exceptions. This increases the cognitive load on developers 
who need to understand or modify the class.

4. **Complex Configuration Handling**: The class includes detailed configuration handling logic, such as loading 
YAML files, setting properties, and managing enabled/disabled resolvers. This adds to the complexity and size 
of the class.

## 4. Refactoring Proposal

Overall, the `PermissionsResolverManager` class is doing too much, 
it needs heavy refactoring to improve maintainability and readability.
The class should be divided into other smaller classes that divide it's responsabilities to make the implementation less convoluted.

# Code Smell 2 - (Long Method)

## 1. Code snippet

    private boolean loadConfig(File file) {
        boolean isUpdated = false;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                LOGGER.warn("Failed to create new configuration file", e);
            }
        }
        config = new YAMLProcessor(file, false, YAMLFormat.EXTENDED);
        try {
            config.load();
        } catch (IOException e) {
            LOGGER.warn("Error loading WEPIF configuration", e);
        }
        List<String> keys = config.getKeys(null);
        config.setHeader(CONFIG_HEADER);

        if (!keys.contains("ignore-nijiperms-bridges")) {
            config.setProperty("ignore-nijiperms-bridges", true);
            isUpdated = true;
        }

        if (!keys.contains("resolvers")) {
            //List<String> resolverKeys = config.getKeys("resolvers");
            List<String> resolvers = new ArrayList<>();
            for (Class<?> clazz : availableResolvers) {
                resolvers.add(clazz.getSimpleName());
            }
            enabledResolvers.addAll(Arrays.asList(availableResolvers));
            config.setProperty("resolvers.enabled", resolvers);
            isUpdated = true;
        } else {
            List<String> disabledResolvers = config.getStringList("resolvers.disabled", new ArrayList<>());
            List<String> stagedEnabled = config.getStringList("resolvers.enabled", null);
            for (Iterator<String> i = stagedEnabled.iterator(); i.hasNext();) {
                String nextName = i.next();
                Class<?> next = null;
                try {
                    next = Class.forName(getClass().getPackage().getName() + "." + nextName);
                } catch (ClassNotFoundException ignored) {
                }

                if (next == null || !PermissionsResolver.class.isAssignableFrom(next)) {
                    LOGGER.warn("WEPIF: Invalid or unknown class found in enabled resolvers: "
                            + nextName + ". Moving to disabled resolvers list.");
                    i.remove();
                    disabledResolvers.add(nextName);
                    isUpdated = true;
                    continue;
                }
                enabledResolvers.add(next.asSubclass(PermissionsResolver.class));
            }

            for (Class<?> clazz : availableResolvers) {
                if (!stagedEnabled.contains(clazz.getSimpleName())
                    && !disabledResolvers.contains(clazz.getSimpleName())) {
                    disabledResolvers.add(clazz.getSimpleName());
                    LOGGER.info("New permissions resolver: "
                        + clazz.getSimpleName() + " detected. "
                        + "Added to disabled resolvers list.");
                    isUpdated = true;
                }
            }
            config.setProperty("resolvers.disabled", disabledResolvers);
            config.setProperty("resolvers.enabled", stagedEnabled);
        }

        if (keys.contains("dinner-perms") || keys.contains("dinnerperms")) {
            config.removeProperty("dinner-perms");
            config.removeProperty("dinnerperms");
            isUpdated = true;
        }
        if (!keys.contains("permissions")) {
            ConfigurationPermissionsResolver.generateDefaultPerms(
                    config.addNode("permissions"));
            isUpdated = true;
        }
        if (isUpdated) {
            LOGGER.info("WEPIF: Updated config file");
            config.save();
        }
        return isUpdated;
    }

## 2. Location on the codebase

- **Package:** `com.sk89q.wepif`
- **Class:** `PermissionsResolverManager`

## 3. Explanation

The `loadConfig` method is an example of the Long Method code smell because it:

1. Contains multiple responsibilities, such as file creation, configuration loading, and property setting.
2. Has a significant amount of code, making it harder to understand and maintain.
3. Includes complex logic for handling configurations and updating properties, increasing cognitive load.

## 4. Refactoring Proposal

To address the Long Method code smell in the `loadConfig` method we should break it down to smaller methods:

1. **`loadConfig`**: will use the new methods instead of the long method.

2. **`createConfigFileIfNotExists`**: will create the configuration file if it does not exist.

3. **`loadConfigFile`**: will load the configuration file using the `YAMLProcessor`.

4. **`setDefaultProperties`**: will set default properties if they are missing in the configuration.

5. **`updateResolvers`**: will update the resolvers based on the configuration.

6. **`updateEnabledAndDisabledResolvers`**: will update the enabled and disabled resolvers based on the configuration.

7. **`removeDeprecatedProperties`**: will remove deprecated properties from the configuration.

8. **`generateDefaultPermissions`**: will generate default permissions if they are missing in the configuration.

# Code Smell 3 - (Feature Envy)

## 1. Code snippet

    public void findResolver() {
        for (Class<? extends PermissionsResolver> resolverClass : enabledResolvers) {
            try {
                Method factoryMethod = resolverClass.getMethod("factory", Server.class, YAMLProcessor.class);

                this.permissionResolver = (PermissionsResolver) factoryMethod.invoke(null, this.server, this.config);

                if (this.permissionResolver != null) {
                    break;
                }
            } catch (Throwable e) {
                LOGGER.warn("Error in factory method for " + resolverClass.getSimpleName(), e);
                continue;
            }
        }
        if (permissionResolver == null) {
            permissionResolver = new ConfigurationPermissionsResolver(config);
        }
        permissionResolver.load();
        LOGGER.info("WEPIF: " + permissionResolver.getDetectionMessage());
    }

## 2. Location on the codebase

- **Package:** `com.sk89q.wepif`
- **Class:** `PermissionsResolverManager`

## 3. Explanation
    
The `findResolver` method exhibits the Feature Envy code smell because it seems more interested in other classes 
functionality rather than its own, this can be seen in the constant use of the `PermissionsResolver` class methods
and `factoryMethod`, using basically none of functionalities of the class it resides in (**PermissionsResolverManager**).

## 4. Refactoring Proposal

To address the Feature Envy code smell in the `findResolver` method, we should refactor it to delegate the responsibility
of finding the resolver to a new class or method that is more closely related to `PermissionsResolver`.

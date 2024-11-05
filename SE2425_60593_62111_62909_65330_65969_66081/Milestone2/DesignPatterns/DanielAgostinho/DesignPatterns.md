# Design Pattern 1 (Singleton)

## 1. Code snippet:

### PermissionsResolverManager.java
    public class PermissionsResolverManager implements PermissionsResolver {

    // Other variables

    private static PermissionsResolverManager instance;

    // Initializes the PermissionsResolverManager instance
    public static void initialize(Plugin plugin) {
        if (!isInitialized()) {
            instance = new PermissionsResolverManager(plugin);
        }
    }

    // Checks if the class is initialized to prevent creation of multiple instances
    public static boolean isInitialized() {
        return instance != null;
    }

    // Other methods

    }

## 2. Class diagram:

![PermissionsResolverManager](PermissionsResolverManager.png)

## 3. Location on the codebase:

- **Package:** `com.sk89q.wepif`
- **Class:** `PermissionsResolverManager`
- **Fields and Methods:**
    - `private static PermissionsResolverManager instance` : Singleton instance.
    - `public static void initialize(Plugin plugin)` : Initializes the singleton instance.
    - `public static boolean isInitialized()` : Checks if the class is initialized.

## 4. Discussion:

This implementation demonstrates the **Singleton pattern** by ensuring that only one instance of
`PermissionsResolverManager` exists throughout the application. The `PermissionsResolverManager`
class contains a private static variable `instance` that holds the single instance of the class.
The `initialize` method checks if the instance is already created using the `isInitialized` method,
and if not, it creates a new instance. This prevents the creation of multiple instances and ensures
that the same instance is used across the application. This design pattern is useful for managing shared
resources or configurations in a centralized manner.

# Design Pattern 2 ...)

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


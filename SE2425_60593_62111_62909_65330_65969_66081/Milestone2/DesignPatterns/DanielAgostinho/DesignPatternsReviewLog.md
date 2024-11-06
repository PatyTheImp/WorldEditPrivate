# Design Pattern 1 - Singleton

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be well identified.
- Maybe you should mention that the default constructor is `protected`, which prevents other objects from using the `new` operator with the **Singleton** class.
- Also, it would be nice to mention the method `getInstance()`, which is how other objects can have access to the `PermissionsResolverManager` instance. 
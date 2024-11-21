# Design Pattern 1 - Factory Pattern

**Reviewer:** Nuno Duarte

**Comments:**

The pattern is well identified but not well implemented, is not a full factory method because the class doesn't have a sole purpose.


# Design Pattern 2 - Iterator Pattern

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be there, but some elements don't seems to be well identified.
- I can't find the `TransformRegionIterator` class identified in the class diagram. Did you mean the `RegionIterator` located in the `com.sk89q.worldedit.regions.iterator`?
- Maybe would be good to identify the `TransformRegion` as the **collection class** (seems to be a `BlockVector3` collection). Also identify the `Region` interface (which the `TransformRegion` implements) as the **collection interface**.
- Finally, it would be nice to identify a **client** class that utilizes the iterator through the colletion. An example is the `BiomeCommands` class, located in the `com.sk89q.worldedit.command` package, that iterates a `Region` object in the `biomeInfo` method.

# Design Pattern 3 - Decorator Pattern

**Reviewer:** Nelson Martins

**Comments:**

- The pattern is well identified, but the class diagram has methods missing, namely: `FlatRegionMaskingFilter` and `apply`.
- In the fields and methods section there are **2 typos** in `apply(BloackVector2 position) throws WolrdEditException`,
  namely: `BloackVector2` and `WolrdEditException` should be `BlockVector2` and `WorldEditException`.
- The discussion `should be more detailed`. It would be nice to explain how the pattern is used and
  how it benefits the codebase.
- The discussion has a random `<br>` tag at the end of the text.

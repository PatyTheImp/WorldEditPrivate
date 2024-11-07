# Design Pattern 1 - Factory Pattern

**Reviewer:** Nuno Duarte

**Comments:**
Factory Method is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. The code resembles the factory pattern because of the 'createProperty' method hides the instantiation logic of various Property subclasses and because of how the method can create multiple types of Property objects (IntegerProperty, BooleanProperty, etc.) based on the provided type. But is not a full factory method because the class doesn't have a sole purpose.

The pattern is well identified but not well implemented. 


# Design Pattern 2 - Iterator Pattern

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be there, but some elements don't seems to be well identified.
- I can't find the `TransformRegionIterator` class identified in the class diagram. Did you mean the `RegionIterator` located in the `com.sk89q.worldedit.regions.iterator`?
- Maybe would be good to identify the `TransformRegion` as the **collection class** (seems to be a `BlockVector3` collection). Also identify the `Region` interface (which the `TransformRegion` implements) as the **collection interface**.
- Finally, it would be nice to identify a **client** class that utilizes the iterator through the colletion. An example is the `BiomeCommands` class, located in the `com.sk89q.worldedit.command` package, that iterates a `Region` object in the `biomeInfo` method.

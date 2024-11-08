# Code Smell 2 - Long Parameter List

**Reviewer -** Guilherme Simões

**Comments -**
This refactor proposal is a SOLID solution to the Long Parameter List code smell since it adheres to the Single Responsibility Principle (SRP) by keeping the context management within a dedicated class,
and it improves the Open/Closed Principle (OCP) by allowing the method signature to remain unchanged even when adding new parameters.
It simplifies method signatures, improves maintainability, and groups logically related parameters into a cohesive object.
Although it adds a small amount of complexity with the introduction of the `ActionContext` class, the benefits far outweigh the drawbacks,
especially in a large and complex system where maintaining clean method signatures is crucial.
This refactoring enhances code quality and aligns well with object-oriented principles like encapsulation and abstraction.

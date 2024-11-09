# Code Smell 2 - Lazy Class

**Reviewer:** Patrícia Costa

**Comments:**

- If the `radii` method truly has a single usage and doesn’t need to be reused in other parts of the codebase, refactoring it as a `private static` method in the class that uses it is a valid approach.
- However, if there’s a possibility of reuse or if it serves as a clear part of a pattern for handling generated annotations, it may be better to keep `Annotations` as a dedicated class.


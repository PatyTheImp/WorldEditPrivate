# Code Smell 1 - Data Class

**Reviewer:** Nelson Martins

**Comments:**

- The code smell was well identified and the explanation is clear, the purpose of the `BaseItem` class seems to be only to store data.
- The proposed refactoring is a good approach to solve the problem.
- Overall, the identification is well done.

# Code Smell 2 - Lazy Class

**Reviewer:** Patrícia Costa

**Comments:**

- If the `radii` method truly has a single usage and doesn’t need to be reused in other parts of the codebase, refactoring it as a `private static` method in the class that uses it is a valid approach.
- However, if there’s a possibility of reuse or if it serves as a clear part of a pattern for handling generated annotations, it may be better to keep `Annotations` as a dedicated class.

# Code Smell 3 - Speculative Generality

**Reviewer:** Nuno Duarte

**Comments:**

- The code smell has been well identified and with just the explanation I can tell straight away what it is. 
- These are two good proposals for refactoring, but as the methods aren't even implemented I think deleting them would be the best option. Well done:)

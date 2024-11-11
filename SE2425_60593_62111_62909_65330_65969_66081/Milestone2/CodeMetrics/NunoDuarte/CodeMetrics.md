# Code Metrics Report: 
## 1. Lines of Code (LOC)
### Project Level
**Calculated Metric**: 73,383 LOC
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

**Calculated Metric:** 4,592 LOC

**Definition**: LOC measures the total number of code lines in a project or package. Blank lines and comments are included, reflecting the full volume of the source code.

**Importance:** This metric provides an overview of the project’s size, helping estimate the required effort and future maintenance needs.

**Potential Issues:** Projects with high LOC may indicate a complex system that could be challenging to maintain.
High LOC may signal large classes or methods, which makes understanding and testing harder.

## 1.2 Non-Commenting Source Statements (NCSS) (Project Level)
### Project Level
**Calculated Metric**: 41,753 NCSS
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

**Calculated Metric**: 1,993 NCSS

**Definition:** NCSS counts only executable lines of code, excluding comments and blank statements. This metric closely tracks the points where executable code appears, offering a more precise view of logical complexity.

**Importance**: NCSS can indicate the effort required to understand critical parts of the code and helps identify where potential simplification can reduce complexity.

**Potential Issues:** High NCSS in specific classes may indicate long methods or overloaded classes.
The metric may also indicate code smells, such as methods or classes with multiple responsibilities.

## 1.3 Number of Concrete Classes (NOCC) (Project Level)
### Project Level
**Calculated Metric:** 1,513 Concrete Classes
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

**Calculated Metric:** 18 Concrete Classes

**Definition:** Counts all concrete (non-abstract) classes in the project or package. This metric helps measure the system's extensibility and modularity.

**Importance:** A high number of concrete classes can signal a project with low code reuse, highlighting the need for improvements in abstraction and reuse of common functionalities.

**Potential Issues:**
A high number of concrete classes can complicate the project structure, making it harder to understand and increasing the likelihood of duplicated logic. It indicates a potential increase in the need for unit and integration tests.

## 1.4 Relationship with Code Smells (Project Level)
These metrics may reflect the presence of classic code smells, such as:

**God Class/Method:** Classes and methods with many LOC or NCSS are typical indicators of responsibility overload.

**Long Method:** Extensive methods indicate low modularity and are difficult to test individually.

**Large Class:** Many concrete classes or classes with high LOC suggest a lack of abstraction and poor separation of responsibilities.

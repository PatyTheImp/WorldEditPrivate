# Code Metrics Report: 
## 1. Lines of Code (LOC)
**Definition**: LOC measures the total number of code lines in a project or package. Blank lines and comments are included, reflecting the full volume of the source code.

**Importance:** This metric provides an overview of the project’s size, helping estimate the required effort and future maintenance needs.

**Potential Issues:** Projects with high LOC may indicate a complex system that could be challenging to maintain.
High LOC may signal large classes or methods, which makes understanding and testing harder.
<img width="529" alt="Captura de ecrã 2024-11-12, às 14 29 59" src="https://github.com/user-attachments/assets/75b379ef-be64-4eda-9122-e262369ed845">

### Project Level
*Calculated Metric*: 73,383 LOC
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

*Calculated Metric:* 4,592 LOC
### Class Level (EditSession)
*Calculated Metric*: 2,440 LOC



## 1.2 Non-Commenting Source Statements (NCSS) (Project Level)
**Definition:** NCSS counts only executable lines of code, excluding comments and blank statements. This metric closely tracks the points where executable code appears, offering a more precise view of logical complexity.

**Importance**: NCSS can indicate the effort required to understand critical parts of the code and helps identify where potential simplification can reduce complexity.

**Potential Issues:** High NCSS in specific classes may indicate long methods or overloaded classes.
The metric may also indicate code smells, such as methods or classes with multiple responsibilities.

<img width="486" alt="Captura de ecrã 2024-11-12, às 14 35 48" src="https://github.com/user-attachments/assets/e4a6bd66-d4fe-41d6-946e-4942314cca7e">

### Project Level
*Calculated Metric*: 41,753 NCSS
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

*Calculated Metric*: 1,993 NCSS
### Class Level (EditSession)
*Calculated Metric*: 1,266 NCSS
## 1.3 Number of Concrete Classes (NOCC) (Project Level)
**Definition:** Counts all concrete (non-abstract) classes in the project or package. This metric helps measure the system's extensibility and modularity.

**Importance:** A high number of concrete classes can signal a project with low code reuse, highlighting the need for improvements in abstraction and reuse of common functionalities.

**Potential Issues:**
A high number of concrete classes can complicate the project structure, making it harder to understand and increasing the likelihood of duplicated logic. It indicates a potential increase in the need for unit and integration tests.

### Project Level
*Calculated Metric:* 1,513 Concrete Classes
### Package Level (com.sk89q.worldedit)
> Note that these values do not include the contents of sub-packages within this package.

*Calculated Metric:* 18 Concrete Classes

## 1.4 Relationship with Code Smells (Project Level)
These metrics may reflect the presence of classic code smells, such as:
**God Class/Method:** Classes and methods with many LOC or NCSS are typical indicators of responsibility overload.

**Long Method:** Extensive methods indicate low modularity and are difficult to test individually.

**Large Class:** Many concrete classes or classes with high LOC suggest a lack of abstraction and poor separation of responsibilities.

**Duplicated Code:** Identifying and refactoring duplicate blocks of code reduce the LOC and NCSS.

## 2. Cognitive Complexity

<img width="847" alt="Captura de ecrã 2024-11-12, às 15 18 33" src="https://github.com/user-attachments/assets/cbbbdfbf-7b64-4951-8570-5cab30a95fb3">

**Definition:** Measures how understandable a piece of code is by evaluating control flow structures such as loops, conditionals, and nesting levels. It focuses on the mental effort required to understand the code, rather than the number of possible paths.

**Importance:** High Cognitive Complexity increases the difficulty of reading, understanding, and maintaining code, leading to a higher likelihood of bugs and issues during development. Lower complexity helps keep code readable, making it easier to debug, refactor, and test.

**Potential Issues:**
Code with high Cognitive Complexity can be challenging to maintain, as developers may struggle to understand the logic.
High complexity increases the risk of errors and the time required to make updates or fix issues.

**Code Smells:**

**- Long Method:** Methods that are overly long tend to be more complex, often indicating that the function is handling too much logic and should be broken down.

**- Deeply Nested Conditionals:** Extensive nesting (e.g., if statements within loops or other conditionals) increases Cognitive Complexity and makes the code harder to follow.

**- Duplicated Code:** Duplicate blocks of code lead to redundancy, making maintenance more difficult, especially when complex logic is repeated across different areas.

**Metric values:**

Class `EditSession` (`com.sk89q.worldedit.world`) - Cognitive Complexity: 381

Class `SelectionCommands` (`com.sk89q.worldedit.command`) - Cognitive Complexity: 202

## 3. Dependency Metrics
### 3.1 Afferent and Efferent Coupling
**Definition:**

*Afferent Coupling (Ca):* The number of other modules that depend on a specific module, indicating how central or critical the module is to the rest of the system.

<img width="584" alt="Captura de ecrã 2024-11-12, às 15 41 54" src="https://github.com/user-attachments/assets/869996aa-4ac1-478f-b945-238cb674a159">

*Efferent Coupling (Ce):* The number of modules that a specific module depends on, indicating how much the module relies on external components or other modules.

**Importance:** Managing dependencies is crucial for maintainability and stability. High afferent coupling can create bottlenecks where a change in one module affects many others. High efferent coupling suggests that a module may be too reliant on external components, increasing the risk of breaking changes.

**Potential Issues:**
High afferent coupling (Ca) means the module is critical, making changes risky because many other modules depend on it.
High efferent coupling (Ce) indicates that the module is fragile, as it depends heavily on other parts of the codebase.

**Code Smells:**

**- God Class:** Modules with very high afferent coupling often contain too much functionality, leading to a God Class that centralizes too many responsibilities.
**- Cyclic Dependencies:** Cycles occur when modules depend on each other, either directly or indirectly, creating a tightly coupled structure that makes refactoring difficult and can lead to unexpected bugs.

**- Feature Envy:** Modules with high efferent coupling often have logic that would be better suited elsewhere, leading to excessive reliance on other modules’ data and functions.

**Metric Values:**


Package `worldedit` - Afferent Coupling: 320 (highly depended upon by many modules)

Package `worldedit` - Efferent Coupling: 107 (relies heavily on multiple external services)

This combination can indicate a God Class or a Cyclic Dependency issue. Such a module both depends on and is depended upon by many other modules, making it hard to maintain.



# Milestone 2 reviews

## Code Metrics Reviews

**Author:** Daniel Agostinho

**Reviewer -** Guilherme Simões

**Comments -** 
  - The metrics are not properly explained. By explaining each metric up front, the report would make it clear how these specific measurements are relate to code complexity and potential design issues.
  - The review would benefit from a more thorough breakdown, specifically identifying classes with extreme values in each metric which may suffer from the smells identified in the next point. Specific examples help see which classes may be "bad" and deserve immediate attention.
  - The review mentions unreliability in some metrics for detecting specific code smells but doesn’t suggest why this might be the case or discuss potential limitations in the metrics themselves. This information would help readers understand the context of the metrics and any limitations in using them exclusively to detect issues.
  - While the review discusses correlations between the metrics and code smells, it doesn't delve deeply into why certain metrics indicate particular code smells. Explaining these relationships would strengthen the argument.

---

**Author:** Guilherme Simões

**Reviewer:** Nuno Duarte

**Comments:**
- For every metric, definition, potential issues and relation to code smells are well explained and justified.
- The report provides valuable insights into the project’s overall structure, with well-balanced MHF and CF indicating good encapsulation and modularity.
- The explanation of the results obtained with the metrics and the problems they can cause is very well done.

---

**Author:** Miguel Flor

**Reviewer -** Daniel Agostinho

**Comments -**
### Number of Methods
The `NOM` metric assessment seems to be fully correct and well explained, there is no correction I would make to this.
It describes the metric well and gives the reader a good understanding of what the metric is and how it can be used.

### Number of Attributes and Methods
The `SIZE2` metric although it doesn't seem to be a good metric to evaluate the codebase, it is well explained and 
analyzed, the only problem I can find with your assessment is that saying a `high size2` is a good metric for finding 
`God Classes`, but it only has a value of 28% percent, which is not that high (even if it is the highest compared to 
other metrics), so it might not be the best metric to find God Classes, this isn't really a problem, 
but it is something to consider.

### Data Abstraction Coupling
The `DAC` seems to be a good metric to evaluate the codebase, and it is well explained and analyzed,
however, high coupling does have `3 outliers`, which may indicate that there are some classes that are too dependent on 
other abstract classes. This may `hinder the flexibility` of the codebase, making it harder to maintain and extend.
Although it might not be a problem, for correction’s sake it `should be removed in trouble spots` where u mention it has 
almost no outliers.
As a small sidenote on line 63 of the file "AS" should be written as "As".

---

**Author:** Nelson Martins

**Reviewer -** Miguel Flor

**Comments -**
### 1. Abstractness
The potential trouble stops are well explain and analyzed.
The relation with the code smells are well explained and identified what code smells are related to a high `A` value and a low `A` value

### 2. Instability
The `I` metric is well identified, and afferent coupling (`Ca`) and efferent coupling (`Ce`) are well explained as well.
In the potential trouble spots are well identified what a low and high `I` value means. Something to consider adding is that in general the project seems to have a high `I` value.
The code smells are well identified and explained.


### 3. Normalized Distance from Main Sequence
The explanation overall is well explained, the relation with graph seems correct to.
The analyses of the graph is good. When referring to the packages that may have problems, it would be good to include some of that packages.
The relation with the code smells are very detailed and well explained.

---

**Author:** Nuno Duarte

**Reviewer:** Patrícia Costa

**Comments:**

- For every metric, the definition, importance and potential issues seem to be well explained and justified.
- Maybe the data visualization for the metrics **LOC** and **NCSS** would make more sense in a class level or method level. That way, you could identify the classes/methods with extreme values as potential trouble spots. 
- Just stating the numbers and not comparing with expected values doesn't tell much. 
- In the relationship between code smells, you should point specific code smells you identified, and relate them with the metrics.

---

**Author:** Patrícia Costa

**Reviewer:** Nelson Martins

**Comments:**

- The **definitions** of the metrics are clear and well explained.
- The **charts** are well-chosen and really help to understand the metrics.
- All the **potential trouble spots** are well identified and explained, as are the classes more problematic,
everything coincides with the charts.
- The relations with code smells seems to be well done, but some of the metrics doesn't have one, I don't know
if it's because some don't have a direct relation, but you did mention some code smells in the potential trouble spots, which is good.
- Overall the document is well done, and the metrics are well explained and justified.

## Code Smells Reviews

**Author:** Daniel Agostinho

### Code Smell 3 - Feature Envy

**Reviewer -** Nuno Duarte

**Comments**
- The code smell was well identified but the explanation could have been a little more specific.
- The refactoring proposal is too generic, a little detail would have been better, for example, how the method could look or in which class it should be.

### Code Smell 2 - Long Method

**Reviewer -** Guilherme Simões

**Comments -**
The Long Method code smell is well identified.
The refactoring proposal for the loadConfig method is well-thought-out and addresses the smell effectively.
By breaking down the original method into smaller, more focused methods, the proposal improves readability, maintainability, and modularity of the code.

### Code Smell 1 - Large Class

**Reviewer** Miguel Flor

**Comments**
- The Large Class code smell is well identified.
- In the explanation is properly justified why the class is considered a code smell
- The refactoring proposal is correct, maybe it could be more detailed.

---

**Author:** Guilherme Simõe

### Code Smell 1 - Data Class

**Reviewer:** Nelson Martins

**Comments:**

- The code smell was well identified and the explanation is clear, the purpose of the `BaseItem` class seems to be only to store data.
- The proposed refactoring is a good approach to solve the problem.
- Overall, the identification is well done.

### Code Smell 2 - Lazy Class

**Reviewer:** Patrícia Costa

**Comments:**

- If the `radii` method truly has a single usage and doesn’t need to be reused in other parts of the codebase, refactoring it as a `private static` method in the class that uses it is a valid approach.
- However, if there’s a possibility of reuse or if it serves as a clear part of a pattern for handling generated annotations, it may be better to keep `Annotations` as a dedicated class.

### Code Smell 3 - Speculative Generality

**Reviewer:** Nuno Duarte

**Comments:**

- The code smell has been well identified and with just the explanation I can tell straight away what it is. 
- These are two good proposals for refactoring, but as the methods aren't even implemented I think deleting them would be the best option. Well done:)

---

**Author:** Miguel Flor

### Code Smells 1 - Long Method

**Reviewer -** Daniel Agostinho

**Comments -**
- Although the explanation is short it gets the point across without missing any key details, the reader can easily
  understand the problem and the implications of the code smell.
- The refactoring proposal is a tad bit too simplistic, it's not very clear how the refactoring would be done. My 
  suggestion is to specify that the method `deleteChunks` shouldn't just be thrown out but used to call the other methods 
  (`readChunkInfo`, `isBeforeTime`, `writeChunkInfo`, `printMessages`) that would handle smaller tasks as 
  to make the code more readable and maintainable.

### Code Smell 2 - Long Parameter List

**Reviewer -** Guilherme Simões

**Comments -**
This refactoring proposal is a SOLID solution to the Long Parameter List code smell since it adheres to the Single Responsibility Principle (SRP) by keeping the context management within a dedicated class,
and it improves the Open/Closed Principle (OCP) by allowing the method signature to remain unchanged even when adding new parameters.
It simplifies method signatures, improves maintainability, and groups logically related parameters into a cohesive object.
Although it adds a small amount of complexity with the introduction of the `ActionContext` class, the benefits far outweigh the drawbacks,
especially in a large and complex system where maintaining clean method signatures is crucial.
This refactoring enhances code quality and aligns well with object-oriented principles like encapsulation and abstraction.

### Code Smell 3 - Duplicate Code

**Reviewer -** Nelson Martins

**Comments -**
- The code smell is well identified, there is indeed duplicate code in the location specified.
- The refactoring proposal is clear and concise, it's easy to understand how the code could be refactored to avoid the 
  duplication.
- Overall, everything seems well done.

---

**Author:** Nelson Martins

### Code Smell 3 - Feature Envy

**Reviewer -** Nuno Duarte

**Comments**
- The code smell was well identified.
- With the explanation and the proposal for refactoring I can get an idea of what it is and how to solve it, although it is a bit vague.


### Code Smell 2 (God Class)

**Reviewer -** Patrícia Costa

**Comments:**

- The smell is well identified but the justification seems a bit lacking.
- While the number of attributes, methods, and lines of code are indicators, they alone don’t prove a class is a **God Class**.
- You should point out that the class violates the **Single Responsibility Principle** and how it violates.

### Code Smell 1 (Long Method)

**Reviewer -** Miguel Flor

**Comments:**

- The smell is well identified.
- The justification seems correct.
- The refactoring proposal is good, I can't find anything to add.

---

**Author:** Nuno Duarte

### Code Smell 1 - Long Method

**Reviewer:** Guilherme Simões

**Comments:**

- The code smell assessment correctly identifies the long method smell.
- The method is long, and it does indeed handle multiple responsibilities, which is a violation of the Single Responsibility Principle (SRP).
- The refactoring proposal directly addresses the issues of long method and SRP violation, promoting better code modularity and clarity.
- While the review suggests breaking the method into four parts, it would benefit from more detail on how the new methods should be designed.

### Code Smell 2 - Speculative Generality

**Reviewer:** Patrícia costa

**Comments:**

- The smell seems to be well identified since the class aparently is not being used anywhere.
- The refactoring looks appropriate.

### Code Smell 3 - Primitive Obsession

**Reviewer:** Daniel Agostinho

**Comments:**

- The smell is well identified and explained.
- The refactoring proposal is appropriate and would improve the code by encapsulating the primitive data types into a 
  new class, thus making the code more maintainable and easier to understand, which is also explained, thus making the
  refactoring proposal clear and easy to understand.
- Needs no corrections, it's fundamentally correct.

---

**Author:** Patrícia Costa

### Code Smell 1 - God Class

**Reviewer** Miguel Flor

**Comments**
- The justification given in the explanation section, is well explained and justified.
- The refactoring proposal is well-thought-out and addresses the smell effectively.

### Code Smell 2 - Feature Envy

**Reviewer** Daniel Agostinho

**Comments**
- The explanation given is clear and in depth. 
- The refactoring suggestion is very thorough and well-thought-out.
- I can see nothing wrong with this review. No changes are needed.

### Code Smell 3 - Long Method

**Reviewer** Nelson Martins

**Comments**

- The code smell is well identified and explained, the method `get` has very lines of code and performs multiple operations.
- The refactoring proposal is the ideal, the regular thing to do to long methods is to break them down into smaller
methods, which is what the author proposes.
- Overall, everything seems right and well explained.

## Design Patterns Reviews

**Author:** Daniel Agostinho

### Design Pattern 1 - Singleton

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be well identified.
- Maybe you should mention that the default constructor is `protected`, which prevents other objects from using the `new` operator with the **Singleton** class.
- Also, it would be nice to mention the method `getInstance()`, which is how other objects can have access to the `PermissionsResolverManager` instance. 

### Design Pattern 2 - Adapter

**Reviewer:** Miguel Flor

**Comments:**

- The Diagram is not very explicit, you should add the classes that are being adapted.
- The design pattern seems to be well identified, and the discussion is clear.

### Design Pattern 3 - Factory

**Reviewer:** Nelson Martins

**Comments:**

- The diagram is very incomplete, it should have all the classes involved in the factory or at least some of them.
- The discussion is clear and well explained, but again it should mention the classes which are being created by the factory.
- Overall, the pattern is well identified but the diagram could be better.

---

**Author:** Guilherme Simões

### Design Pattern 1 - Template Method Pattern

**Reviewer:** Miguel Flor

**Comments:**

- The photo is not visible, for the photos to work you need to upload them to the repository and use the correct path.
- In the discussion, try to explain better why the chosen code relates with the pattern and not only describing how the code works.

### Design Pattern 2 - Iterator Pattern

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be well identified.
- Maybe in the discussion, you should identify the `EditSession` as the client class. Also point how that class creates 
  the iterator (in this case, through a for each loop).

### Design Pattern 3 - Abstract Factory Pattern

**Reviewer:** Daniel Agostinho

**Comments:**

- There is a missing "}" `MaskFactory.java`, `AbstractFactory.java` to close the class.
- The images are not visible, you need to upload them to the repository and use the correct path (see colleagues folder for example).
- The packages section's format is very chaotic to read try using "-" at the start of the line, to organize better.
- The classes section is correct, but it's a mess format wise, the fix is putting the "-" at the start of the line.
- The discussion is good, but you should explain better why the pattern is used and how it benefits the code.

---

**Author:** Miguel Flor

### Design Pattern 1 - Factory Pattern

**Reviewer:** Nuno Duarte

**Comments:**

The pattern is well identified but not well implemented, is not a full factory method because the class doesn't have a sole purpose.


### Design Pattern 2 - Iterator Pattern

**Reviewer:** Patrícia Costa

**Comments:**

- The pattern seems to be there, but some elements don't seems to be well identified.
- I can't find the `TransformRegionIterator` class identified in the class diagram. Did you mean the `RegionIterator` located in the `com.sk89q.worldedit.regions.iterator`?
- Maybe would be good to identify the `TransformRegion` as the **collection class** (seems to be a `BlockVector3` collection). Also identify the `Region` interface (which the `TransformRegion` implements) as the **collection interface**.
- Finally, it would be nice to identify a **client** class that utilizes the iterator through the colletion. An example is the `BiomeCommands` class, located in the `com.sk89q.worldedit.command` package, that iterates a `Region` object in the `biomeInfo` method.

### Design Pattern 3 - Decorator Pattern

**Reviewer:** Nelson Martins

**Comments:**

- The pattern is well identified, but the class diagram has methods missing, namely: `FlatRegionMaskingFilter` and `apply`.
- In the fields and methods section there are **2 typos** in `apply(BloackVector2 position) throws WolrdEditException`,
  namely: `BloackVector2` and `WolrdEditException` should be `BlockVector2` and `WorldEditException`.
- The discussion `should be more detailed`. It would be nice to explain how the pattern is used and
  how it benefits the codebase.
- The discussion has a random `<br>` tag at the end of the text.

---

**Author:** Nelson Martins

### Design Pattern 1 - Façade Pattern

**Reviewer -** Guilherme Simões

**Comments**
  - The assessment of the Facade pattern in the `CLIWorldEdit` class is strong, especially in how it addresses its in simplifying interactions with the WorldEdit CLI subsystem. It captures key benefits of the Facade pattern and ties them back to the implementation in `CLIWorldEdit`.
  - By noting that the Facade pattern hides the complexities of multiple subsystems, the assessment effectively conveys how this pattern benefits clients of `CLIWorldEdit`.
  - Overrall the assessment is very well done, but a final, short conclusion summarizing how the Facade pattern makes `CLIWorldEdit` more usable, modular, and maintainable would tie everything together nicely.


### Design Pattern 2 - Singleton

**Reviewer -** Nuno Duarte

**Comments**

The design pattern is well identified but not well implemented as stated in the analysis. Well explained.

### Design Pattern 3 - Proxy Pattern

**Reviewer -** Daniel Agostinho

**Comments**
- The class diagram is well done and has all the necessary information without being too cluttered.
- The discussion is well done and explains the current implementation thoroughly.
- Overall I think no changes are needed in this design pattern.

---

**Author:** Nuno Duarte

### Design Pattern 1 (Template Pattern)
**Reviewer:** Miguel Flor

**Comments:**

- The pattern seems to be well identified.
- The diagram seems to be well-made.
- In the discussion it's only addressed why the pattern is referred, but would be good to explain how this pattern is beneficial to the project.   

### Design Pattern 2 (Adapter Pattern)
**Reviewer:** Nelson Martins

**Comments:**

- There is indeed an adapter for the objects between the WorldEdit
representations and the minecraft's fabric API representations.
- Overall the pattern is well identified and explained.

### Design Pattern 3 (Command Pattern)
**Reviewer:** Guilherme Simões

**Comments:**

- The analysis correctly identifies that this structure primarily uses the Command pattern.
- The identification of `ToolCommand` as the invoker and `Platform`, `Player`, and `World` as receivers is correct, clearly aligning the pattern components with their respective roles.
- Expanding on how the `@Deprecated` annotations and compatibility interfaces fit into the Command pattern would enrich your analysis.

---

**Author:** Patrícia Costa

### Design Pattern 1 - Singleton

**Reviewer -** Nuno Duarte

**Comments**
This pattern is well identified.
Singleton is a creational design pattern that lets you ensure that a class has only one instance, while providing a global access point to this instance.

### Design Pattern 2 - Observer Pattern

**Reviewer -** Daniel Agostinho

**Comments**
- The Observer pattern is well identified.
- The class diagram is complete and easy to read and thus easy to understand, the only thing missing is the class fields
  that are present in the code snippets.
- The location on the codebase section is almost perfect but, in each class maybe the fields represented in the code 
  snipped should also be included.
- The discussion is well written and explains the pattern well, without any issues.

### Design Pattern 3 - Strategy Pattern

**Reviewer -** Guilherme Simões

**Comments**
The Strategy pattern in this context is well identified.
It has been effectively noted how `RecursiveVisitor` can dynamically change its behavior by swapping out different `Mask` implementations, making it adaptable and extensible.

## Use Cases Reviews

**Author:** Daniel Agostinho

**Reviewer:** Nuno Duarte

**Comments:**
- The descriptions, primary and secondary actors are well identified.
- The diagram is also correct.
- I would suggest removing the generalization of actors, so that its clear that any user can perform all commands.

---

**Author:** Guilherme Simões

**Reviewer:** Miguel Flor

**Comments:**

- Everything seems well done.
- The use cases are well-defined and described.
- The diagram is well done and easy to understand.

---

**Author:** Miguel Flor

**Reviewer:** Patrícia Costa

**Comments:**

- Everything looks ok.
- The descriptions are short but to the point.
- The use case diagram is simple but efficient. 

---

**Author:** Nelson Martins

**Reviewer:** Daniel Agostinho

**Comments:**

- The use cases are well-defined and the primary and secondary actors are well identified.
- The use case diagram is well done, and it is easy to understand the interactions between the actors and the system.

---

**Author:** Nuno Duarte

**Reviewer:** Nelson Martins

**Comments:**

- The use cases seems to be well-defined.
- The primary and secondary actors are well identified.
- The use case diagram is well done, with the generalizations well represented.
- Overall, all seems good.

---

**Author:** Patrícia Costa

**Reviewer -** Guilherme Simões

**Comments -**

- Each use case accurately identifies the main purpose of each command and relevant actors, particularly separating cases where the Game Server is needed versus commands that only affect the User session.
- I would suggest removing the generalization of actors, so that its clear that any user can perform all commands.

# Milestone 3 reviews

**Authors:** Daniel Agostinho and Miguel Flor

## Code implementation

**Reviewer:** Patrícia Costa

**Comments:**

- In the `raise` method in the `com.sk89q.worldedit.command` package, the arguments `World world` and `LocalSession session` are never used. You can eliminate them.
- Everything else seems fine. 

## Use Case: Raise a certain blocks in a region by a specific 

**Reviewer:** Patrícia Costa

**Comments:**

- The main flow shouldn't include the precondition (since it happens *before* the case starts).
- You might want to clarify that the selection must contain blocks of the chosen type for any action to take effect.


## Alternative Flow: InvalidBlock 

**Reviewer:** Patrícia Costa

**Comments:**

- I guess it starts after step 2 (since the user needs to type `//raise` first).
- Secondary actors should be none since the game isn't really affected. 

## Alternative Flow: InvalidRegion

**Reviewer:** Patrícia Costa

**Comments:**

- Like I said before, this is a precondition. It shouldn'd be considered an alternative flow since it happens *before* the case starts. 

## Alternative Flow: InvalidHeight

**Reviewer:** Patrícia Costa

**Comments:**

- Like I said before, it starts after step 2. 
- Again, secondary actors should be none. 

## Other notes

**Reviewer:** Patrícia Costa

**Comments:**

- You are missing the alternative flow of invalid arguments. When are too many or too few arguments or the arguments are in the wrong order. 

## Use Case Diagram

**Reviewer:** Patrícia Costa

**Comments:**

- I don't understand the Edit session use case. I don't think it should be there. 

## Sequence Diagram

**Reviewer:** Nelson Martins

**Comments:**

- The sequence diagram is well done and easy to understand.
- No changes are needed.

## Class Diagram

**Reviewer:** Patrícia Costa

**Comments:**

- `Region` is an interface, so it should be represented as such, not as a class. I also don't understand why you put it there in the first place, since you didn't made any changes to it.
- You also didn't made any changes to the `getHighestTerrainBlock` method. Not sure if you should include it too. 
- The `RegionCommands` class uses objects of the `EditSession` class, but not the other way around. The association should be an arrow pointing from `RegionCommands` to `EditSession`.

## Unit Tests

**Reviewer:** Nelson Martins

**Comments:**

- Some of the tests are with small errors, some steps doesn't match the expected results. Namely, the tests for the 2D and 1D regions in the second point.
- In the "Number of arguments <2 or >2" test, the third step might be wrong as there are exactly 2 arguments, and the expected results only has the possibility of having too many arguments.
- Also in this test, I noticed that if the command is "//raise stone" it will raise 1 block height, it only has one argument but the expected result is not clear about this. You should clarify it in the expected results.
- About the other tests all seems fine and works as expected.

## Updated User Story

**Reviewer:** Nelson Martins

**Comments:**

- The user story is well written and easy to understand.
- The only thing to point is that the reviewers were written in portuguese, but I think it's not a big deal.

---

**Authors:** Guilherme Simões and Nuno Duarte

**Reviewers:** Daniel Agostinho and Miguel Flor

### Code Review
**Comments:**
- The `RegionCommands` code seems to be simple and efficient, although the print message could be improved on.
- The `EditSession` and `CuboidRegion` code works and is well implemented, due to its simplicity I think no changes are needed. 

### Unit Tests Review
**Comments:**
- **3D region:** The test case is relevant and the expected results are correct. After in game testing they actual 
result matches the expected result.
- **3D Region with flags:** The test case is relevant and the expected results are correct. After in game testing 
they actual result matches the expected result.
- **2D Region:** The test case is relevant and the expected results are correct. After in game testing they actual 
result matches the expected result.
- **2D Region with flags:** The test case is relevant and the expected results are correct. After in game testing 
they actual result matches the expected result. But the way the command is used doesn't match it's recommended use, 
in the test case "//edges stone -cnw" is used, but the correct and recommended way to use the command is "//edges -cnw stone",
- **1D Region:** The test case is relevant and the expected results are correct. After in game testing they actual 
result matches the expected result.
- **1D Region with flags:** The test case is relevant and the expected results are correct. After in game testing they 
actual result matches the expected result.
- **Same position:** The test case is relevant and the expected results are correct. After in game testing they actual 
result matches the expected result.
- **Same position with flags:** The test case is relevant and the expected results are correct. 
After in game testing they actual result matches the expected result. But the way the command is used doesn't match it's
recommended use, in the test case "//edges stone -cefnsw" is used, but the correct way to use the command is
"//edges -cefnsw stone", strangely the server output is the same for both commands.
- **Invalid Block:** The test case is relevant and the expected results are correct. After in game testing they 
actual result matches the expected result.
- **Only 1 position chosen:** The test case is relevant and the expected results are correct. After in game testing they 
actual result matches the expected result.
- **Invalid Flag:** The test case is relevant but the expected results are incorrect. Also, the way the command is used 
doesn't match it's recommended use, in the test case "//edges stone -q" is used, but the correct way to use the command 
is "//edges -q stone". After in game testing the actual result matches the expected result, but when the command is used
in the recommended way the server output is "Invalid value for <pattern> (Block name '-p' was not recognized.). 
acceptable values are any pattern. Usage: ``//edges [-cefnsw] <pattern>``".

### Use Case Review

#### Use Case
**Comments:**
- **Fill Edges of a Cuboid Region:** The use case is correct and well explained.

#### Alternative flows
**Comments:**
- **InvalidPattern:** The alternative flow is correct and complete.
- **InvalidRegion:**  The alternative flow is correct and complete.
- **InvalidFlag:**  The alternative flow is correct and complete.


#### Use Case Diagram
**Comments:**
- The use case diagram is a little to simple and could be improved on, maybe specify some dependencies between other functionalities.

#### Sequence Diagram
**Comments:**
- Seems to be correct and to explain the sequence of events from the command usage to the block changes.

#### Class Diagram
**Comments:**
- The class diagram seems to be correct and to represent the classes and their relationships accurately

---

**Authors:** Nelson Martins and Patrícia Costa

**Reviewers:** Guilherme Simões and Nuno Duarte

### Code Review
**Comments:**
- In the `makeAnimals` method of the ``EditSession`` class, the strings should be refactored to better organize the code;
- The code includes most animal variants but is missing the wolf variants.
- We would suggest removing the `-p` flag on the variants command and just inputting an integer in the case of a page specification.
- The `getFrogVariant` and `getCatVariant` in the `FabricAnimal` class are very similar; they could be refactored to be just one method. 

### Unit Tests Review
**Comments:**
- *Add one animal*: The test is relevant but, after setting the second position, the value in the parenthesis is 1, not 100.
- *Add multiple animals, one per block*: The test is relevant but, after setting the second position, the value in the parenthesis is 10, not 100.
- *Tests all animals*: The test is relevant but, after setting the second position, the value in the parenthesis is 10, not 100.
- *Limit of animals per block at once (with chickens)*: The test is relevant but the system prints "24 animals were created", not "100 animals were created".
- *Add baby animals*: The test is relevant but, after setting the second position, the value in the parenthesis is 10, not 100.
- *Add other variants/species of an animal*: The test is relevant but, after setting the second position, the value in the parenthesis is 10, not 100.
- *Mix babies with other variants of an animal*: The test is relevant but, after setting the second position, the value in the parenthesis is 10, not 100.
- *Try adding an animal that doesn't exist*: The test is relevant but, after setting the second position, the value in the parenthesis is 1, not 100.
- *Try adding an animal with a variant that doesn't exists*: The test is relevant but, after setting the second position, the value in the parenthesis is 1, not 100.

### Use Case Review

#### Use Case
**Comments:**
- The use cases are well specified but some of the alternative flows are repeated; They could just be reused between main flows.
- The "Game Server" secondary actor doesn't correspond to anything because the server is assumed as the system.

#### Use Case Diagram
**Comments:**
- The "Game Server" secondary actor doesn't exist but, besides that, the diagram seems accurate.

#### Sequence Diagram
**Comments:**
- The loop representation of the creation of animals is not represented correctly. It should be inside of a loop fragment.
- The `variants` sequence diagram is missing.

#### Class Diagram
**Comments:**
- The class diagram is missing some methods used in the code but overall although it's a bit confusing, it seems to be complete.

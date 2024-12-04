# Review

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

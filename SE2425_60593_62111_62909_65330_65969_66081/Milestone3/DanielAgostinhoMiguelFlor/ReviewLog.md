## Code implementation

**Reviewer:** Patrícia Costa

**Comments:**

- In the `raise` method in the `com.sk89q.worldedit.command` package, the arguments `World world` and `LocalSession session` are never used. You can eliminate them.

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
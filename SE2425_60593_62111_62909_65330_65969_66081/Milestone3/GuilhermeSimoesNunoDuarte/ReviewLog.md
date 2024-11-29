# Review

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
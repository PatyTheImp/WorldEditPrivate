## Use Cases
| Use Case: Summon  animals                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 2                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **Brief Description**:<br>Summons one or more animals inside a specified region.                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **Primary Actors**:<br>User                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Secondary Actors**:<br>Game Server                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| **Preconditions**:<br>1. An area is selected.                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **Main flow**:<br>1. The use case starts when the user types `//animal` in the game chat.  <br>&nbsp;&nbsp;&nbsp;&nbsp;1.1. The system sugests a list of available animal types for the user to select. <br>2. The user types or selects an animal type. <br>3. If nothing else is specified <br> &nbsp;&nbsp;&nbsp;&nbsp;3.1. The system creates one adult animal of the specified type with the default variant. <br> 4. If an integer number is specified <br> &nbsp;&nbsp;&nbsp;&nbsp;4.1. The system creates the specified number of animals. <br> 5. If the `-b` flag is used <br> &nbsp;&nbsp;&nbsp;&nbsp;5.1. The system creates the animal(s) in baby version. <br> 6. If a variant is specified <br> &nbsp;&nbsp;&nbsp;&nbsp;6.1. The system creates the animal(s) in the specified variant.
| **Postconditions**:<br>1. The system creates one or more animals in a random position of the selected area.                                                                                                                                                                                                                                                                                                                                   |
| **Alternative Flows**:<br>InvalidAnimalType<br>InvalidArguments<br>InvalidCount <br>InvalidVariant                                                                                                                                                                                                                                                                                                                                                                                                                                                     |


| Alternative Flow: Summon  animals: InvalidAnimalType                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 2.1                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the chosen animal type is invalid.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid animal type.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 2 of the main flow. <br>2. The system informs the user that the chosen animal type is invalid. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: Summon  animals: InvalidArguments                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 2.2                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the entered number of arguments is invalid and shows the right usage of the command.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid number of arguments or enters them in the wrong order.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 1.1 of the main flow. <br>2. The system informs the user that the entered number of arguments is invalid and shows the right usage of the command. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: Summon  animals: InvalidCount                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 2.3                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the count must be a positive number.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an integer number smaller than 1.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 4 of the main flow. <br>2. The system informs the user that the count must be a positive number. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: Summon  animals: InvalidVariant                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 2.4                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the chosen animal variant is invalid.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid animal variant.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 6 of the main flow. <br>2. The system informs the user that the chosen animal variant is invalid. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Use Case: List variants                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 3                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| **Brief Description**:<br>List the variants of a specified animal type.                                                                                                                                                                                                                                                                                                                                                                                                                  |
| **Primary Actors**:<br>User                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          |
| **Preconditions**:<br>None.                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| **Main flow**:<br>1. The use case starts when the user types `//variants` in the game chat.  <br>&nbsp;&nbsp;&nbsp;&nbsp;1.1. The system sugests a list of available animals for the user to select. <br>2. The user types or selects an animal type. <br>3. If nothing else is specified <br> &nbsp;&nbsp;&nbsp;&nbsp;3.1. The system lists, in the game chat, the available variants of the specified animal. <br>  4. If the `-p` flag is used followed by a page number<br> &nbsp;&nbsp;&nbsp;&nbsp;4.1. The system shows the list begining from the specified page number. 
| **Postconditions**:<br>1. The system shows a list of animal variants in the game chat.                                                                                                                                                                                                                                                                                                                                   |
| **Alternative Flows**:<br>InvalidAnimalType<br>InvalidArguments<br>InvalidPageNumber <br>AnimalWithoutVariants                                                                                                                                                                                                                                                                                                                                                                                                                                                     |


| Alternative Flow: List variants: InvalidAnimalType                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 3.1                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the chosen animal type is invalid.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid animal type.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 2 of the main flow. <br>2. The system informs the user that the chosen animal type is invalid. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: List variants: InvalidArguments                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 3.2                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the entered number of arguments is invalid and shows the right usage of the command.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid number of arguments or enters them in the wrong order.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 1.1 of the main flow. <br>2. The system informs the user that the entered number of arguments is invalid and shows the right usage of the command. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: List variants: InvalidPageNumber                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 3.3                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the page number is invalid.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an invalid page number.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 4 of the main flow. <br>2. The system informs the user that the page number is invalid. |
| **Postconditions:**<br>None.                                                                                                                                  |

| Alternative Flow: List variants: AnimalWithoutVariants                                                                                                                                |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**: 3.4                                                                                                                                                   |
| **Brief Description**:<br>The system informs the user that the chosen animal type doesn't have variants.                                                                      |
| **Primary Actors**:<br>User                                                                                                                                   |
| **Secondary Actors**:<br>None.                                                                                                                          |
| **Preconditions**:<br>1. The user enters an animal type that don't have variants.                                                                            |
| **Alternative flow**:<br>1. The alternative flow begins after step 3 of the main flow. <br>2. The system informs the user that the chosen animal type doesn't have variants. |
| **Postconditions:**<br>None.                                                                                                                                  |

## Use Case Diagram

![Use Case Diagram](UseCaseDiagram.png)

## Sequence Diagram

![Sequence Diagram](SequenceDiagram.png)

## Class Diagram

![Class Diagram](ClassDiagram.png)

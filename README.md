# casino-simulator
Country Toad's CSC207 Group Project

### by Matthew Dahlgren: matt-dahlgren, Jacob Lisogurski: Jac0b-Beep, Pooja Mangra: 04mangra, Andriy Shkvorets: atStarling, Sonia Vaidya: soniavaidya05

Featuring Clean Architecture and SOLID!

## Features
- A functioning app that allows you to play simple Blackjack!
- Assisted Mode that tells you the optimal decision to take during your turn, featuring a complex probability algorithm!
- Learn Mode: to learn how to play Blackjack!
- Emailed Post-Game reports using the email you signed up with!
- A better than average UI!

## App Launching Instructions
- Run with Java 11. Built and Tested with Amazon Corretto 11.0.24.
- Run [Main.java](./src/main/java/app/Main.java)

## Design Process:
In the beginning, we created CRC cards to determine which classes we'd need in the final product. Although we expanded
on these later as our program began to manifest, we developed a UML chart from those cards to help us understand
the relationship between our Use Cases, Interface Adapters, Data Access Objects, APIs, etc. Then, we moved onto
implementation.

Firstly, we began work on our [entities](./src/main/java/entities) to try and get a very basic version of our program
finished. We added all the entities we needed, with the abstract class [player](./src/main/java/entities/Player.java) being inherited by
[UserPlayer](./src/main/java/entities/UserPlayer.java) and [Dealer](./src/main/java/entities/Dealer.java).
Although we initially made a CardFactory and a respective interface for creating the dealer or player's hand, we decided
against it, as it required two new files just to cut 2 lines of code.

After finishing the entities, we moved on to our [use cases](./src/main/java/use_case).
Each *Use Case* corresponds to an action that the player "chooses" to take within the application. While the 
[free play use cases](./src/main/java/use_case/freeplay) are use cases directly relevant to the game play itself. When
a game begins, a Setup Use Case is run which creates the dealer, userPlayer, fetches a new DeckID from the API through
an interface, and fills the empty [GameDataAccessObject](./src/main/java/data_access/GameDataAccessObject.java) which we use to process the gameplay through the hit and stand interactors.

# WE MAY CHANGE THIS, PLEASE UPDATE IF NEEDED, ALSO INCLUDE INFO ON THE FREEPLAY STATE, FREEPLAY CONTROLLER, AND MAYBE PRESENTER WHEN FINISHED!!!
In all 3 of our FreePlay use cases, the output data is always an ArrayList of the links of the images of both the
player's and dealer's hands, which allow the FreePlay presenter (after going through their respective OutputBoundaries),
to display that data visually on the screen. 

[App Builder](./src/main/java/app/AppBuilder.java) combines all our use cases and views into a single JFrame application,
as well as setting all the basic visual aspects such as the dimensions of the launched application, and the icon.

The [Views](./src/main/java/view) visually displays all the different screens, as well as including the individual
design features that we use to construct the views (such as the LabelTextPanel in Lab 5). These views retrieve any use
specific information from their respective ViewModels (which themselves get information from their respective states).
Otherwise, the views contain their use case buttons, whose actionListeners are determined by their respective use cases'
controllers executes with information from the view's respective view model.

# Discuss Game Report, Learning Mode, Assisted Mode, and Probability Stuff!!!

Since our program uses several views and has quite a lot of varying classes, there's still a few classes that weren't 
discussed, but feel free to look at their Java Docs for more information.

## Test Coverage

Below is our test coverage for our program. [INSERT TEST COVERAGE DETAILS HERE ONCE WE'RE FINISHED WITH THEM]

In our tests, we did not test the visual UI elements as they are best manually tested.

## Images
Insert a bunch of images of us doing things with an explanation of what they are


## Other Things to Mention:

As we didn't fully understand Clean Architecture and SOLID; our code was a bit of a mess for the first few weeks. 
For example, the entire FreePlay mode was a single use case before we chose to divide it into the 3 we have now. 
Within the first week, we had developed an atrociously anti-Clean Architecture version of Blackjack which played okay.

## Resource Credits
 Card Images and API Data from the Deck of Cards API - https://deckofcardsapi.com/
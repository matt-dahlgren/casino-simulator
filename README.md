# **Blackjack Simulator**
## Country Toad's CSC207 Group Project

#### by Matthew Dahlgren: matt-dahlgren, Jacob Lisogurski: Jac0b-Beep, Pooja Mangra: 04mangra, Andriy Shkvorets: atStarling, Sonia Vaidya: soniavaidya05

**1** [Game Modes and Features:](#game-modes-and-features)
- [Features](#features)
- [Player Account](#player-account)
- [Assisted Mode](#assisted-mode)
- [Game Report](#game-report)

**2** [App Launching Instructions:](#app-launching-instructions)

**3**[Design Process](#design-process)

**4** [Explanation of Classes (in General)](#explanation-of-classes-in-general)

**5** [Test Coverage](#test-coverage)

**6** [Images](#images)

**7** [Contribution](#contribution)

**8** [Resource Credits](#resource-credits)

## Game Modes and Features
### Features
- A functioning app that allows you to play simple Blackjack!
- Assisted Mode that tells you the optimal decision to take during your turn, featuring a complex probability algorithm!
- Learn Mode: to learn how to play Blackjack!
- Emailed Post-Game reports using the email you signed up with!
- A better than average UI!

### Player Account
Signing up for an account allows you create a unique, password protected account on the application. Your
account will have a username and password and will be linked to your email address so you can receive reports
about your Blackjack gameplay experience. Once you're done playing, you can log out. This allows you to keep your saved
games separate from others who may be sharing your device. 

### Learn Mode
Learn Mode allows you to learn about how to play BlackJack. You can toggle between different concepts, like the 
objective of the game, how dealing works, and what kinds of moves you can do. You can navigate to Learn Mode from the 
Main Menu of the game. The aim of this mode is to be as inclusive as possible of players with different 
levels of expertise!

### Assisted Mode
Assisted Mode allows you to play Blackjack in a zero risk environment with active suggestions on whether hitting or
standing is a better move for the player to make in the board's current state. This allows experienced blackjack players
to train themselves, and giving less experienced players exposure to a new game with assistance. 

### Game Report
The game report allows you to see the quality of all your moves in Assisted Mode, and to compare that against the 
quality of the potential moves they didn't take (i.e. if the player hit, they can see the quality of that move, 
as well as the move quality of standing instead). The player can view game reports of any of their assisted games, 
allowing them to track their progress. They can also recieve emails with the game reports, to keep for their own 
reference.

## App Launching Instructions
- Run with Java 22. Built and Tested with Amazon Corretto 11.0.24 on macOS Sequoia, Windows 10, and Windows 11.
- Utilize Maven to install needed dependacies.
- Run [Main.java](./src/main/java/app/Main.java)
  
- Luckily, the main issues that arise are with Maven. If the app refuses to build, navigate to Maven in your 
Setting's Build Tools, select "Runner" and then tick the checkbox to delegate IDE build/run actions to Maven.
Also, consider reloading your pom.xml file if you have issues.
- Another problem that may arise is with the [Account Info](./src/main/java/user_data/Account_Info) incorrectly saving data,
please delete the empty entires in the file, and things should work okay!

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
an interface, and fills the empty [GameDataAccessObject](./src/main/java/data_access/GameDataAccessObject.java) which 
we use to process the gameplay through the hit and stand interactors.

In all 3 of our FreePlay use cases, the output data is always an ArrayList of the links of the images of both the
player's and dealer's hands, which allow the FreePlay presenter (after going through their respective OutputBoundaries),
to display that data visually on the screen. To simplify the application, we condensed the view into only two to make
things easier to update, without needing to refresh everything all at once.

## Explanation of Classes (in General)
[App Builder](./src/main/java/app/AppBuilder.java) combines all our use cases and views into a single JFrame application, 
and sets the dimensions, icon, etc. of the app. By using an App Builder, we can implement our use cases and views using
the *Open-closed principle* of SOLID, saving us loads of headaches by being able to add one thing at a time. Here begins
the complex set of interface adapters existing between the user and the use cases themselves, adhering strictly to
*Clean Architecture*. No direct contact to the use cases or entities are made here.

The [Views*](./src/main/java/view) another "frameworks and drivers" of *Clean Architecture*, they, more or less, just
display the data they receive, create the screen's GUI, and utilise controllers to create functioning buttons. Through
their respective view models, who extend the functionality of the Views by acting as a medium between them and the
state, which is responsible for storing the mutable objects needed for creating the display.

*Note that there are also design elements used for visuals, like LabelTextPanel from Lab 5.

The Controllers are the real backbone of the application, facilitating communication between the views and use case
interactors. After executing a controller method from the view with whatever input information is needed, the method
is parsed through a interface adapter medium before landing at the use case interactor. By doing this process, we
implement *all 5 principles of SOLID,* making our code much more readable, module, cleaner, and easier to implement
gradually. Once the input data arrives at the interactors, we can program the actual use cases, implementing all the
application business rules we need.

The Presenters are the opposite of the controllers, they take the output data from the interactors and proceed
to update the view model and subsequently notify the view that new changes have been made.

Although this entire process could be done through solely a single view, the disobedience to Clean Architecture and
SOLID would make it very difficult to run without having the entire program assembled all at once. As well as
making it a nightmare to independently test sections of code, make modifications, or understanding what the code
is actually doing.

## Contribution
As this was an assignment done for our CSC207 course and was a demonstration of what we could achieve given the course
material, public contributions are closed. However, if you wish to make a fork, you can! Nothing special is needed
except the requirements listed in the app launching instructions. We would recommend making nice and clean merge
requests for when you work on that fork, so that your main branch doesn't accidentally end up broken. Always use
SOLID and Clean Architecture in adherence to our existing code! Ensure that merged contributions actually expand on
the app rather than break it down.


## Resource Credits
 Card Images and API Data from the Deck of Cards API - https://deckofcardsapi.com/

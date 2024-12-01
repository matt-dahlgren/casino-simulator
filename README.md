# casino-simulator
Country Toad's CSC207 Group Project

### by Matthew Dahlgren: matt-dahlgren, Jacob Lisogurski: Jac0b-Beep, Pooja Mangra: 04mangra, Andriy Shkvorets: atStarling, Sonia Vaidya: soniavaidya05

Featuring Clean Architecture and SOLID!

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

After finishing the entities, we moved on to our [use cases](./src/main/java/use_case). As we didn't fully understand
Clean Architecture and SOLID; our code was a bit of a mess. For example, the entire FreePlay mode was a single use case,
before we chose to divide it into the 3 we have now. Within the first week, we had developed an atrociously
anti-Clean Architecture version of Blackjack which worked successfully.
Each *Use Case* corresponds to an action that the player "chooses" to take within the application. While the *


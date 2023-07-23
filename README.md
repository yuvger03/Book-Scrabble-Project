# Book Scrabble Project 
Link to github-https://github.com/yuvger03/clientSide

Link for the presentation of Desktop App and flow explanation: https://www.youtube.com/watch?v=0Rmro8iu6vs

A Desktop app which simulates a Book Scrabble game played by a host and up to three guests.

### Collaborators
This program was developed by three student, Shira Kaufman, Noa Kohan and Yuval Gerber, CE students from Bar-Ilan university, Israel.

### Code Design:
Book Scrabble Project has been programmed by the MVVM design with client-server TCP/IP communication, as we used Java's data binding mechanism,and fxml.


## Play the game

### Installation
1. Downloads the project by git clone form https://github.com/yuvger03/clientSide.git.
2. Go to the pom.xml file (this file is used to store the dependencies of the Maven project) and make sure that Maven has all the dependencies, otherwise-downloads it.
3. Build the project using your IDE.

### How to run the game?
1. Run the StartHost.java to start a new game, this creates an instanse of MyServer and creates a new host player.
The host listens on a random port, game supports local players only (socket connection created on "localhost" and random port)
2. Run the StartGuest.java to enter to the game as guests.
3. One of the players should be the host and also be named so (The Host will determine when to start the game and when to end it).
  
<img width="513" alt="image" src="https://github.com/yuvger03/clientSide/assets/56202649/7ea44fca-429e-4424-bfc7-741a55cee71e">

4. After the host insert name "host" and clicks on the button Start as Host he should check what is the port of the game using the port buuton,
   and the guests will enter this port and click start game.(eg. like Kahoot, the host knows the "game id" and publish it offline to other players)
   
   <img width="405" alt="image" src="https://github.com/yuvger03/clientSide/assets/56202649/e957802e-2352-4b30-bcbf-3e7f0559ca02">

5.  All gusets will enter to game, the host can check who joined the game by clicking the button check players.
6.  The host will start the game after clicking the start button, only then, all players will get tiles from bag and will have the option to play.
7.  Enjoy the game!
8.  IMPORTANT: we added a txt file that consist all combinations of letters(eg. A, B, AB, AA, BB .. ) so we can demonstrate the game flow without having hard life to find words that exists in Harry Potter or other dicts.


## PART 3
#### How to save a game
1. Download MongoDB Community Server and connect to a MongoDB deployment with the URI mongodb://localhost:27017
2. Create a Database named mydb, in this database create a collection named games.
3. While playing the game the host can determine when to end and save the game to the Database using the end and save button.



#### How to resume an existing game
1. Run the StartHost.java to start a new game.
2. Run the StartGuest.java to enter to the game as guests.
3. The names of the players will stay the same.
4. After the host clicks on the button Host he should check what is the port of the game
   and the guests will enter this port.
5. The host will enter the old port's number to the resume game Text Field and will click the resume game button.
6. Now the game will start from the point it ended.

## Documentation

The Documentation and the UML class diagram of the project is available at the https://github.com/yuvger03/clientSide/tree/master/documantation directory

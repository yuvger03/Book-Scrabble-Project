# Book Scrabble Project 
Link to github-https://github.com/yuvger03/clientSide

Link for the presentation of Desktop App-https://youtu.be/9PXQ427QCPU

A Desktop app which simulates a Book Scrabble game played by a host and up to three guests.

## Collaborators
This program was developed by three student, Shira Kaufman, Noa Kohan and Yuval Gerber, CE students from Bar-Ilan university, Israel.

## Code Design:
Book Scrabble Project has been programmed by the MVVM design with client-server TCP/IP communication, as we used Java's data binding mechanism,and fxml.


# Play the game

## Installation
1. Downloads the project by git clone form https://github.com/yuvger03/clientSide.git.
2. Go to the pom.xml file (this file is used to store the dependencies of the Maven project) and make sure that Maven has all the dependencies, otherwise-downloads it.
3. Build the project using your IDE.

## How to run the game?
1. Run the helloAplication Main up to 4 times for a single game
2. One of the players should be the host and also be named so (The Host will determine when to start the game and when to end it).

3. After the host clicks on the button Host he should check what is the port of the game
   and the guests will enter this port.
4.  All the gusets will enter to game and will wait the host to start it.
5.  Enjoy the game!


### How to save a game
1. Download MongoDB Community Server and connect to a MongoDB deployment with the URI mongodb://localhost:27017
2. Create a Database named mydb, in this database create a collection named games.
3. While playing the game the host can determine when to end and save the game to the Database using the end and save button.



### How to resume an existing game
1. Run the helloAplication Main up to 4 times for a single game
2. The names of the players will stay the same.
3. After the host clicks on the button Host he should check what is the port of the game
   and the guests will enter this port.
4. The host will enter the old port's number to the resume game Text Field and will press the resume game button.
5. Now the game will start from the point it ended.
   

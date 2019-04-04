# Silicon-Data

GRP-COSC2635 2D
---------------
Silicon
-------

updated 040419

Readme

Latest version of Silicon

We have now incorporated sounds for the buttons and one
of Kira's music tracks (shift M to play from the game board).

Game keyboard shortcuts:

From the titlescreen:

S to start a new game

L to load a game (need save_game.txt in data folder)

H to see high scores

Escape to quit

From the game board:

Shift B to buy a card

Shift A to attack another card

Shift R to convert money to research

Shift F to toggle full screen/windowed mode

Shift S to save the game

Shift M to play the music track

Escape to return to the title screen


-Important resources for the game are kept in a zip file
   called 'resources.zip'
   
-Latest version is kept in a jar file on the Trello board (Assignment2 card)

To compile and run the latest version from the command line:
------------------------------------------------------------
-ensure that your system is using JRE8
(type 'java -version' to check the runtime version)

-unzip the resources folder to your local folder:

-ensure images folder holds relevant image files including the cards folder

-ensure data folder holds Game.css, GameBoard.css and high_scores.txt files

-save_game.txt will appear in the data folder if a save game is made during
   the game

-copy all of the following java files to the folder:
AttackRoll.java
Card.java
DisplaySetting.java
Envelope.java
GameBoard.java
GameControl.java
GameRules.java
GameState.java
LoadGame.java
Location.java
Player.java
PlayerMove.java
SiliconGame.java
Tone.java
Twain.java

-At the command prompt type:
javac SiliconGame.java

-To run the game type:
java SiliconGame

How to import and run in eclipse:
---------------------------------
-create a new project (make sure it is set use JRE 8)

-Either import the java files into your project's src folder
or copy and paste them to the src folder

-ensure images, sound, data folders are inside the bin folder

-alter the file paths in the code to the following values:

alter file path used in GameState.saveGame() to
	File file = new File("bin/data/save_game.txt");

alter file path used in LoadGame.loadGame() to
	File file = new File("bin/data/save_game.txt");


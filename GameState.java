import javafx.scene.image.Image;

// GRP-COSC2635 Michael Power s3162668
// The GameState class is intended to embody the current state of
// play in the card game - data is managed by the GameControl class.

public class GameState
{
   private Player[] players;
   private Card[] deck;
   private int gameRound;
   private int playerTurn;
   Image cardFlipSide;
   
   public GameState(int numberOfPlayers, int deckSize)
   {
	   // Initialise main variables for the game
	   players = new Player[numberOfPlayers];
	   deck = new Card[deckSize];
	   gameRound = 1;
	   // Point the playerTurn variable to the start of the players array
	   playerTurn = 0;
   }
   
   Player[] getPlayers()
   {
	   return players;
   }
   
   Card[] getDeck()
   {
	   return deck;
   }
   
   int getGameRound()
   {
	   return gameRound;
   }
   
   int getPlayerTurn()
   {
	   return playerTurn;
   }
   
   void addPlayer(int playerNumber, Player player)
   {
	   try
	   {
		   players[playerNumber] = player;
	   }catch(Exception ex)
	   {
		   System.out.println("Unable to add player to the game.");
	   }
   }
   
   void setDeck(Card[] deck)
   {
      this.deck = deck;
   }
   
}

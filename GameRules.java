
// GRP-COSC2635 Michael Power s3162668
// The following class is intended to encapsulate aspects of the
// game rules. The variables/methods of this class represent a
// reference point for the GameController class in managing the
// game.
public class GameRules
{
   final private int NUMBER_OF_PLAYERS = 4;
   final private int NUMBER_OF_CARDS = 54;
   
   public GameRules()
   {
	   
   }
   
   int getNumberOfPlayers()
   {
	   return NUMBER_OF_PLAYERS;
   }
   
   int getNumberOfCards()
   {
	   return NUMBER_OF_CARDS;
   }
}

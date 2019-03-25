
// GRP-COSC2635 Michael Power s3162668
// The GameControl class acts as a bridge between the data of the card
// game and the various display functions. It manages the flow of
// activity during the game.

public class GameControl
{
   private GameState gameState;
   private GameRules gameRules;
	
   public GameControl()
   {
	   gameRules = new GameRules();
	   
	   //newGame();
   }
   
   void newGame()
   {
	   gameState = new GameState(gameRules.getNumberOfPlayers(),
	      gameRules.getNumberOfCards());
	   
	   initialisePlayers();
	   
	   // Set up a deck of cards to be used in the gameState for
	   // testing purposes.
	   Card[] deck = gameState.getDeck();
	   deck = initialiseDeck(deck);
	   shuffleDeck(deck);
	   gameState.setDeck(deck);
   }
   
   GameState getGameState()
   {
	   return gameState;
   }
   
   // This method is used to initialise the players for the game.
   // Configured for testing purposes.
   void initialisePlayers()
   {
	   for(int i = 0; i < gameRules.getNumberOfPlayers(); i++)
	   {
		   Player player = new Player();
		   player.setName("Player" + (i + 1));
		   gameState.addPlayer(i, player);
	   }
   }

   // This method is used to initialise the cards for the game.
   // Configured for testing purposes. Assumes a deck of 54 cards
   // while in testing phase.
   Card[] initialiseDeck(Card[] deck)
   {
      if(deck.length != 54)
      {
         System.out.println("Cannot set up the deck - wrong size.");
         return deck;
      }
	  
	  // Set up names for each card
	  String[] suits = new String[]{" of diamonds", " of clubs",
	     " of spades", " of hearts"};
	   
	  for(int i = 0; i < 4; i++)
	  {
		 deck[0 + i * 13] = new Card("Ace" + suits[i]);
		 deck[1 + i * 13] = new Card("Two" + suits[i]);
		 deck[2 + i * 13] = new Card("Three" + suits[i]);
		 deck[3 + i * 13] = new Card("Four" + suits[i]);
		 deck[4 + i * 13] = new Card("Five" + suits[i]);
	     deck[5 + i * 13] = new Card("Six" + suits[i]);
		 deck[6 + i * 13] = new Card("Seven" + suits[i]);
		 deck[7 + i * 13] = new Card("Eight" + suits[i]);
		 deck[8 + i * 13] = new Card("Nine" + suits[i]);
		 deck[9 + i * 13] = new Card("Ten" + suits[i]);
		 deck[10 + i * 13] = new Card("Jack" + suits[i]);
		 deck[11 + i * 13] = new Card("Queen" + suits[i]);
		 deck[12 + i * 13] = new Card("King" + suits[i]);
	  }
	  deck[52] = new Card("Joker1");
	  deck[53] = new Card("Joker2");
	  
	  return deck;
   }
   
   // Shuffles the deck of Cards
   Card[] shuffleDeck(Card[] deck)
   {
	   Card tempCard;
	   for(int i = 0; i < deck.length; i++)
	   {
		   tempCard = deck[i];
		   int randomCard = (int)(Math.random() * deck.length);
		   deck[i] = deck[randomCard];
		   deck[randomCard] = tempCard;
		   
	   }
	   
	   return deck;
   }
   
   // The following method prints out the card names
   void printCardNames(Card[] deck)
   {
	   for(Card card: deck)
	   {
		   System.out.println(card.getName());
	   }
   }
}



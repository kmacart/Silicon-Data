
// GRP-COSC2635 Michael Power s3162668
// The PlayerMove class is intended to model a single player's
// move in the card game. The player can buy a card and place
// in at a location on the card table, can attempt a take-over
// of another player's card or can choose to convert money
// into research.
public class PlayerMove
{
   // The three types for moveType are 'Buy Card', 'Attack Card' and 'Research'
   private String moveType;
   private Card card;
   private int cost;
   private int research;
   
   // The following three constructors represent the three different move
   // types.
   public PlayerMove(String moveType, Card card, int cost)
   {
	   this.moveType = moveType;
	   this.card = card;
	   this.cost = cost;
   }
   
   public PlayerMove(String moveType)
   {
	   this.moveType = moveType;
   }
   
   String getMoveType()
   {
	   return moveType;
   }
   
   int getCost()
   {
	   return cost;
   }
   
   Card getCard()
   {
	   return card;
   }
   
   int getResearch()
   {
	   return research;
   }
}

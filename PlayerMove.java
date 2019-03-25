
// GRP-COSC2635 Michael Power s3162668
// The PlayerMove class is intended to model a single player's
// move in the card game. The player can buy a card and place
// in at a location on the card table, can attempt a take-over
// of another player's card or can choose to convert money
// into research.
public class PlayerMove
{
   private String moveType;
   private Location location;
   private int cost;
   private Card attackCard;
   private int research;
   
   // The following three constructors represent the three different move
   // types.
   public PlayerMove(String moveType, Location location, int cost)
   {
	   System.out.println("PlayerMove object was created.");
	   this.moveType = moveType;
	   this.location = location;
	   this.cost = cost;
   }
   
   public PlayerMove(String moveType, Card attackCard)
   {
	   this.attackCard = attackCard;
   }
   
   public PlayerMove(String moveType, int research)
   {
	   this.research = research;
   }
   
   String getMoveType()
   {
	   return moveType;
   }
   
   Location getLocation()
   {
	   return location;
   }
   
   int getCost()
   {
	   return cost;
   }
   
   Card getAttackCard()
   {
	   return attackCard;
   }
   
   int getResearch()
   {
	   return research;
   }
}

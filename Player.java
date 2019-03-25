import java.util.ArrayList;

// GRP-COSC2635 Michael Power s3162668
// The player class is used to represent a player in the card game
// whether human or computer controlled.

public class Player
{
   private String name;
   private boolean human;
   private int money = 0;
   private int research = 0;
   private int moduleLevel = 0;
   private ArrayList<Card> cards = new ArrayList<Card>();
   
   public Player()
   {
	   
   }
   
   String getName()
   {
	   return name;
   }
   
   boolean getHuman()
   {
	   return human;
   }
   
   int getMoney()
   {
	   return money;
   }
   
   int getResearch()
   {
	   return research;
   }
   
   int getModuleLevel()
   {
	   return moduleLevel;
   }
   
   Card getPlayerCard(int index)
   {
	   return cards.get(index);
   }
   
   void setName(String name)
   {
	   this.name = name;
   }
   
   void setMoney(int money)
   {
	   this.money = money;
   }
   
   void setResearch(int research)
   {
	   this.research = research;
   }
   
   void upgradeModuleLevel()
   {
	   moduleLevel++;
   }
   
   void addCard(Card newCard)
   {
	   cards.add(newCard);
   }
}



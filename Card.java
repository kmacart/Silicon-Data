import javafx.scene.image.Image;

// GRP-COSC2635 Michael Power s3162668
// Card class used to represent the details of a
// a card such as name and revenue. Also holds a
// location value in relation to the card table.
public class Card
{
   private String name;
   private int revenue = 0;
   private int research = 0;
   private String cardType = "default";
   private Image display;
   private Location location = new Location();
   
   public Card(String name)
   {
	   this.name = name;
	   
	   try
	   {
          display = new Image("file:images/card_default.png");
	   } catch (Exception ex)
	   {
		   System.out.println("Failed to load card image - check " +
	          "file system");
	   }
   }
   
   String getName()
   {
	   return name;
   }
   
   int getRevenue()
   {
	   return revenue;
   }
   
   int getResearch()
   {
	   return research;
   }
   
   String getCardType()
   {
	   return cardType;
   }
   
   Image getImage()
   {
	   return display;
   }
   
   Location getLocation()
   {
	   return location;
   }
   
}
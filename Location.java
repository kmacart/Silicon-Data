
// GRP-COSC2635 Michael Power s3162668
// Location class used to represent the location of
// a card on the card table.
public class Location
{
   private int xCoord = 500;
   private int yCoord = 380;
   private boolean horizontal = true;
   
   public Location()
   {
	   
   }
   
   int getXCoord()
   {
	   return xCoord;
   }
   
   int getYCoord()
   {
	   return yCoord;
   }
   
   boolean getHorizontal()
   {
	   return horizontal;
   }
   
   void setXCoord(int xCoord)
   {
	   this.xCoord = xCoord;
   }
   
   void setYCoord(int yCoord)
   {
	   this.yCoord = yCoord;
   }
   
   void setHorizontal(boolean horizontal)
   {
	   this.horizontal = horizontal;
   }
}

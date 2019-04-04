import javafx.stage.Screen;

public class DisplaySetting
{
   private double screenWidth;
   private double screenHeight;
   private double fullWidth;
   private double fullHeight;
   private double widthRatio;
   private double heightRatio;
   
   public DisplaySetting()
   {
	   screenWidth = SiliconGame.DEFAULT_SCREEN_WIDTH;
	   screenHeight = SiliconGame.DEFAULT_SCREEN_HEIGHT;
	   
	   fullWidth = Screen.getPrimary().getVisualBounds().getWidth();
	   fullHeight = Screen.getPrimary().getVisualBounds().getHeight();
	   
	   widthRatio = fullWidth / screenWidth;
	   heightRatio = fullHeight / screenHeight;
	   
	   
   }
   
   double getScreenWidth()
   {
	   return screenWidth;
   }
   
   double getScreenHeight()
   {
	   return screenHeight;
   }
   
   double getFullWidth()
   {
	   return fullWidth;
   }
   
   double getFullHeight()
   {
	   return fullHeight;
   }
   
   double getWidthRatio()
   {
	   return widthRatio;
   }
   
   double getHeightRatio()
   {
	   return heightRatio;
   }
}

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


// GRP-COSC2635 2D
//
// SILICON - A JavaFX GAME BY:
// Clark Lavery (mentor)
// Evert Visser (s3727884)
// Duncan Baxter (s3737140)
// Kira Macarthur (s3742864)
// Dao Kun Nie (s3691571)
// Michael Power (s3162668)
// John Zealand-Doyle (s3319550)
//
// SiliconGame is the main class of the game that extends from the
// Application class. It includes the GameControl object that
// manages the flow of the game.

public class SiliconGame extends Application
{
	private Stage primaryStage;
	private static GameControl gameControl;
	private DisplaySetting display;
	private SettingsScreen settings;
	
	// Display related constants and variables are initialised below;
	final static int DEFAULT_SCREEN_WIDTH = 972;
	final static int DEFAULT_SCREEN_HEIGHT = 648;
	
	private StackPane pane;
	private LoadGame loader = new LoadGame();
	private boolean gameLoaded;
	private Label loadMessage = new Label();
	
	public void start(Stage stage)
	{
		display = new DisplaySetting();
		settings = new SettingsScreen(stage);
		
		primaryStage = stage;
		gameControl = new GameControl(this, primaryStage);
		
		// Set values to determine window width and height.
		primaryStage.setWidth(DEFAULT_SCREEN_WIDTH);
		primaryStage.setHeight(DEFAULT_SCREEN_HEIGHT);
		primaryStage.setResizable(false);
		primaryStage.centerOnScreen();
        primaryStage.setTitle("Silicon");
		
		// Setup the title screen and set the stage
		primaryStage.setScene(titleScene(this));
		
		// The following command will remove the window decoration
		// primaryStage.initStyle(StageStyle.UNDECORATED);
		
		// Show stage/window and ensure primaryStage is active
		primaryStage.show();
		primaryStage.toFront();
   }
	
   // The following method sets up the title screen at the beginning
   // of the game
   Scene titleScene(SiliconGame game)
   {
	   pane = new StackPane();
	   // The program must find the frigate image on the file system
	   try
	   {
	       Image backGround = new Image("images/titleScreen_alternative.jpg");
	       ImageView imageView = new ImageView(backGround);
	       imageView.setFitWidth(DEFAULT_SCREEN_WIDTH);
	       imageView.setFitHeight(DEFAULT_SCREEN_HEIGHT);
	       pane.getChildren().add(imageView);
	   } catch (Exception ex)
	   {
	       System.out.println("Unable to load image - check file system.");
	   }
	   
	   
	   VBox titleButtons = new VBox(18);
	   titleButtons.setAlignment(Pos.CENTER);
	   titleButtons.setTranslateY(50.0);
	   titleButtons.setMinSize(420, 480);
	   titleButtons.setMaxSize(420, 480);
	   
	   // The buttons represent user options at the beginning of the game
	   Button newGame = new Button("Start New Game");
	   titleButtons.getChildren().add(newGame);
	   Button loadGame = new Button("Load Game");
	   titleButtons.getChildren().add(loadGame);
	   Button highScores = new Button("High Scores");
	   titleButtons.getChildren().add(highScores);
	   Button settings = new Button("Settings");
	   titleButtons.getChildren().add(settings);
	   Button credits = new Button("Credits");
	   titleButtons.getChildren().add(credits);
	   Button quitGame = new Button("Quit Game");
	   titleButtons.getChildren().add(quitGame);
	   
	   // Assign actions to each of the title screen buttons
	   newGame.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   gameLoaded = false;
		   loadMessage.setText("");
		   GameBoard gameBoard = new GameBoard(game, primaryStage,
				      gameControl, gameLoaded);
	   });
	   loadGame.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   loader.resetData();
		   loadMessage.setText(loader.loadData());
		   loader.createGame(game, primaryStage, gameControl);
	   });
	   highScores.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   loadMessage.setText("");
		   primaryStage.setScene(highScoreScene(game));
	   });
	   settings.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   this.settings.showSettings(pane);
	   });
	   credits.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   primaryStage.setScene(creditsScene(game));
	   });
	   quitGame.setOnAction(e ->
	   {
		   new Thread(new Tone(262, 100)).start();
		   primaryStage.close();
	   });
	   
	   titleButtons.getChildren().add(loadMessage);
	   pane.getChildren().add(titleButtons);
	   Scene scene = new Scene(pane);
	   scene.getStylesheets().add("data/Game.css");
	   
	   scene.setOnKeyPressed(e ->
	   {
		   if(e.getCode() == KeyCode.ESCAPE ||
		      e.getCode() == KeyCode.Q)
		   {
			   quitGame.fire();
		   } else if(e.getCode() == KeyCode.S)
		   {
			   newGame.fire();
		   } else if(e.getCode() == KeyCode.L)
		   {
			   loadGame.fire();
		   } else if(e.getCode() == KeyCode.H)
		   {
			   highScores.fire();
		   }
	   });
	   
	   return scene;
   }
   
   // The following method sets up a scene to display the high
   // score table
   Scene highScoreScene(SiliconGame game)
   {
	   StackPane pane = new StackPane();
	   
	   // Attempt to load the high score background
	   try
	   {
	       Image backGround = new Image("images/sunset.jpg");
	       ImageView imageView = new ImageView(backGround);
	       imageView.setFitWidth(DEFAULT_SCREEN_WIDTH);
	       imageView.setFitHeight(DEFAULT_SCREEN_HEIGHT);
	       pane.getChildren().add(imageView);
	   } catch (Exception ex)
	   {
	       System.out.println("Unable to load image - check file system.");
	       ex.printStackTrace();
	   }
	   
	   
	   
	   VBox highScores = new VBox(50);
	   highScores.setAlignment(Pos.CENTER);
	   highScores.setMinWidth(300);
	   highScores.setMinHeight(300);
	   highScores.setMaxSize(300, 500);
	   highScores.setStyle("-fx-background-color: transparent;"
	   		+ "-fx-border-style: solid;" +
			   "-fx-padding: 0px;" +
	   		   "-fx-border-width: 0;" +
			   "-fx-border-radius: 0");
	   
	   String high_score_data = "default";
	   try
	   {
		   InputStream newFile =
		      ClassLoader.getSystemResourceAsStream("data/high_scores.txt");
		   Scanner scanner = new Scanner(newFile);
		   high_score_data = scanner.nextLine();
		   scanner.close();

	   String[] h_values = high_score_data.split(",");
	   
	   Label highScore1 = new Label(h_values[0] + " " + h_values[2]);
	   highScore1.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(highScore1);
	   Label highScore2 = new Label(h_values[3] + " " + h_values[5]);
	   highScore2.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(highScore2);
	   Label highScore3 = new Label(h_values[6] + " " + h_values[8]);
	   highScore3.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(highScore3);
	   Label highScore4 = new Label(h_values[9] + " " + h_values[11]);
	   highScore4.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(highScore4);
	   Label highScore5 = new Label(h_values[12] + " " + h_values[14]);
	   highScore5.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(highScore5);
	   }catch(Exception ex)
	   {
	      System.out.println("Unable to load high scores - file error");
	      Label error = new Label("Unable to load high scores - file error");
	      error.setStyle("-fx-font: 20 Arial");
	      highScores.getChildren().add(error);
	   }
	   
	   Button returnMainMenu = new Button("Return to Main Menu");
	   returnMainMenu.setStyle("-fx-font: 20 Arial");
	   highScores.getChildren().add(returnMainMenu);
	   returnMainMenu.setOnAction(e->
	   {
		  new Thread(new Tone(262, 100)).start();
	      primaryStage.setScene(titleScene(game));
	   });
	   
	   pane.getChildren().add(highScores);
	   Scene scene = new Scene(pane);
	   scene.getStylesheets().add("data/Game.css");
	   
	   scene.setOnKeyPressed(e ->
	   {
		   if(e.getCode() == KeyCode.ESCAPE)
		   {
			   returnMainMenu.fire();
		   }
	   });
	   return scene;
   }
   
   Scene creditsScene(SiliconGame game)
   {
	   StackPane pane = new StackPane();
	   
	   try
	   {
	       Image backGround = new Image("images/credits.jpg");
	       ImageView imageView = new ImageView(backGround);
	       imageView.setFitWidth(DEFAULT_SCREEN_WIDTH);
	       imageView.setFitHeight(DEFAULT_SCREEN_HEIGHT);
	       pane.getChildren().add(imageView);
	   } catch (Exception ex)
	   {
	       System.out.println("Unable to load image - check file system.");
	       ex.printStackTrace();
	   }
	   
	   Button returnMainMenu = new Button("Return to Main Menu");
	   returnMainMenu.getStylesheets().add("data/Game.css");
	   returnMainMenu.setStyle("-fx-font: 20 Arial");
	   returnMainMenu.setAlignment(Pos.CENTER);
	   returnMainMenu.setTranslateY(220.0);
	   returnMainMenu.setOnAction(e->
	   {
		  new Thread(new Tone(262, 100)).start();
	      primaryStage.setScene(titleScene(game));
	   });
	   
	   pane.getChildren().add(returnMainMenu);
	   
	   Scene scene = new Scene(pane);
	   
	   return scene;
   }
   
   DisplaySetting getDisplaySetting()
   {
	   return display;
   }
   
   SettingsScreen getSettings()
   {
	   return settings;
   }
   
   boolean getGameLoaded()
   {
	   return gameLoaded;
   }
   
   void setGameLoaded(boolean gameLoaded)
   {
	   this.gameLoaded = gameLoaded;
   }
   
   // The main method launches the program
   public static void main(String[] args)
   {
      Application.launch(args);
   }
}

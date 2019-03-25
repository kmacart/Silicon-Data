
/*
 * SILICON - A JavaFX GAME BY:
 * -	Clark Lavery (mentor)
 * -	Evert Visser (s3727884)
 * -	Duncan Baxter (s3737140)
 * -	Kira Macarthur (s3742864)
 * -	Dao Kun Nie (s3691571)
 * -	Michael Power (s3162668)
 * -	John Zealand-Doyle (s3319550)
 */

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/*
 * Top-level Class for our Game
 */

public class Game extends Application {
    Stage stage = null; // Primary stage as supplied by Java VM as a parameter to start()
    Scene scene = null; // Scene for primary stage (has JavaFX CSS style-sheet)
    StackPane root = null; // Root node for scene
    public static Node screen[] = { null, null, null, null, null, null }; // Nodes for each game screen
    // screen[0] --> Main menu
    // screen[1] -->
    // screen[2] --> High scores
    // screen[3] -->
    // screen[4] -->
    // screen[5] -->
    public static Boolean close = false;
    GraphicsContext gc = null; // Graphics Context for drawing on primary Stage
    String oldHint = ""; // Storage: former "press ESC key" message
    KeyCombination oldKey = KeyCombination.NO_MATCH; // Storage: former exit-from-FullScreen key(s)

    /*
     * JavaFX Application thread automatically calls start() method. The parameter
     * Stage stage is our top-level window, then Scene scene, StackPane root, and
     * finally an array of Nodes screen[] (one for each menu or other game screen).
     * This method is quite short: it just creates the GUI.
     * 
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage stage) {
	this.stage = stage;
	// Set the Title & Style for our primary Stage
	stage.setTitle("Silicon");
	stage.initStyle(StageStyle.TRANSPARENT);

	// Create a Canvas for the GameBoard and store its graphics context
	// Canvas canvas = new Canvas();
	// gc = canvas.getGraphicsContext2D();

	// Create a StackPane for the root node (gets background from .css file)
	root = new StackPane();
	// Create a Scene with the StackPane as root node, and no background fill
	scene = new Scene(root, null);
	// Load the JavaFX CSS style definitions into scene (affects all roots)
	scene.getStylesheets().add("file:src/Game.css");
	// Add the Scene to the primary Stage
	stage.setScene(scene);
	// Create the main menu
	mainMenu();
	stage.show();	
    }

    /*
     * mainMenu() method: If we have not yet created the main menu, create a VBox as
     * the main menu node, draw the logo and create all the buttons, and store the
     * handle to the VBox as screen[0]. If we have already created the main menu,
     * remove the existing node and replace it with screen[0].
     */
    private void mainMenu() {
	if (screen[0] == null) {
	    // Create a VBox for the main menu node (style is in Game.css)
	    VBox vb = new VBox();

	    // Load the logo into an ImageView and add it to the VBox
	    ImageView iv = new ImageView("file:src/images/Silicon-logo.png");
	    vb.getChildren().add(iv);

	    // Create and add the Buttons for our main menu
	    createMainMenuButtons(vb);

	    // Add the VBox to the root node and signal that we need a layout pass
	    root.getChildren().add(vb);
	    vb.needsLayoutProperty();
	    screen[0] = vb;
	} else {
	    // Replace current screen with main menu screen
	    root.getChildren().removeAll();
	    root.getChildren().add(screen[0]);
	}
    }

    // Buttons
    private void createMainMenuButtons(VBox vb) {
	Button btCreate = new Button("Create Game");
	Button btScores = new Button("High Scores");
	Button btLoad = new Button("Load Game");
	Button btSave = new Button("Save Game");
	Button btSettings = new Button("Settings");
	Button btExit = new Button("Exit");
	vb.getChildren().addAll(btCreate, btScores, btLoad, btSave, btSettings, btExit);

	/*
	 * Use our tone generator to play "middle C" for 1/10 of a second on mouse
	 * press.
	 */
	btCreate.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btScores.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btLoad.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btSave.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btSettings.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btExit.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());

	// Create and register each button's ActionEvent handler.
	btCreate.setOnAction(ae -> {
	    enterFullScreen();
	    System.out.println("Process Create");
	});

	btScores.setOnAction(ae -> {
	    System.out.println("Process High Scores");
	    root.getChildren().remove(screen[0]);
	    screen[2] = HighScores.highScoreScreen(root);
	});

	btLoad.setOnAction(ae -> {
	    System.out.println("Process Load");
	});

	btSave.setOnAction(ae -> {
	    System.out.println("Process Save");
	});

	btSettings.setOnAction(ae -> {
	    System.out.println("Process Settings");
	});

	btExit.setOnAction(ae -> {
	    System.out.println("Process Exit");
	    leaveFullScreen();
	    System.exit(0);
	});
    }

    /*
     * Methods to enter and leave FullScreen mode. Save and restore the on-screen
     * hint. Save and restore the key combination (usually ESC). Disable use of the
     * ESC key to leave FullScreen.
     */
    protected void enterFullScreen() {
	oldHint = stage.getFullScreenExitHint();
	stage.setFullScreenExitHint("");
	oldKey = stage.getFullScreenExitKeyCombination();
	stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
	stage.setFullScreen(true);
    }

    protected void leaveFullScreen() {
	stage.setFullScreenExitHint(oldHint);
	stage.setFullScreenExitKeyCombination(oldKey);
	stage.setFullScreen(false);
    }

    /*
     * The usual "main" method - this code is only executed on platforms that lack
     * full JavaFX support.
     */
    public static void main(String[] args) {
	Application.launch(args);
    }
}
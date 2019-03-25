import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// GRP-COSC2635 Michael Power s3162668
// The GameBoard class represents the main game area. It is
// initiated when the user requests to play a new game. The data for
// this scene is encapsulated in order to limit complication with
// coding in the SiliconGame class

public class GameBoard {
    private Scene scene;
    private Group group;
    private Stage primaryStage;
    private GameControl gameControl;
    private Card[] deck;

    // Variable used to represent views of the deck
    private int selectedCard;
    private ImageView selectedCardView;
    private Location selectedCardLocation;
    private boolean selectToggle = false;

    private int mainDeckXCoord = 600;
    private int mainDeckYCoord = 150;

    private double cardXOffset = 45.0;
    private double cardYOffset = 35.0;
    private double cardHorizontalXEdge = 0.0;
    private double cardVerticalYEdge = 13.0;
    private double stageLeftOffset = 120.0;
    private double stageDownOffset = 130.0;

    public GameBoard(Stage stage, GameControl gC) {
	// Import references to the main stage and GameControl object
	primaryStage = stage;
	gameControl = gC;
	gameControl.newGame();
	deck = gameControl.getGameState().getDeck();
	primaryStage.setScene(sceneSetup());
    }

    Scene sceneSetup() {
	StackPane pane = new StackPane();

	// Attempt to load background image
	try {
	    Image image = new Image("file:images/background.jpg");
	    ImageView view = new ImageView(image);
	    view.fitWidthProperty().bind(primaryStage.widthProperty());
	    view.fitHeightProperty().bind(primaryStage.heightProperty());
	    pane.getChildren().add(view);
	} catch (Exception ex) {
	    System.out.println("Unable to load image from file - check folder.");
	}

	group = new Group();
	group.setManaged(false);
	pane.getChildren().add(group);

	// Display the deck of cards on the table in order to allow
	// the test player to move around cards. Left click to pick
	// up a card and right click to change the orientation
	// from horizontal to vertical
	ImageView[] cardViews = new ImageView[deck.length];
	Location[] cardLocations = new Location[deck.length];
	for (int i = 0; i < deck.length; i++) {
	    cardLocations[i] = deck[i].getLocation();
	    cardLocations[i].setXCoord(mainDeckXCoord);
	    cardLocations[i].setYCoord(mainDeckYCoord);
	    cardViews[i] = new ImageView(deck[i].getImage());
	    if (!cardLocations[i].getHorizontal())
		cardViews[i].setRotate(90);
	    cardViews[i].setX(cardLocations[i].getXCoord());
	    cardViews[i].setY(cardLocations[i].getYCoord());
	    // Add each card to the cardGroup
	    group.getChildren().add(cardViews[i]);
	}

	// Point to the ImageView and Location on top of the deck
	selectedCard = 0;
	selectedCardView = cardViews[selectedCard];
	selectedCardLocation = cardLocations[selectedCard];
	// Ensure selected card is on top of the others
	group.getChildren().remove(cardViews[selectedCard]);
	group.getChildren().add(cardViews[selectedCard]);

	// The first card is given an EventHandler
	selectedCardView.setOnMouseClicked(e -> {
	    // Check if the left button was pressed
	    if (e.getButton().equals(MouseButton.PRIMARY)) {
		handleLeftClick();
		if (!selectToggle) {
		    // Organise EventHandlers for the next cards
		    manageMouseClick(cardViews);
		}
	    }

	    // Check if the right mouse button was pressed and
	    // that the current card has already been clicked on
	    if (selectToggle && e.getButton().equals(MouseButton.SECONDARY)) {
		handleRightClick();
	    }
	});

	// Add a button that will allow us to return to the main screen
	// during the testing phase of the project.
	Button returnButton = new Button("Return to Main Menu");
	returnButton.setStyle("-fx-font: 20 Arial");
	returnButton.setOnAction(e -> {
	    primaryStage.setScene(SiliconGame.titleScene());
	});
	returnButton.setTranslateY(primaryStage.getHeight() / 3.0);
	pane.getChildren().add(returnButton);

	scene = new Scene(pane);
	return scene;
    }

    // This method manages the assigning of a new Event Handler to a card view
    void manageMouseClick(ImageView[] cardViews) {
	cardViews[selectedCard].setOnMouseClicked(null);
	selectedCard++;

	// If we have not reached the end of the deck then make a
	// new Event Handler for the next card.
	if (selectedCard < deck.length) {
	    selectedCardLocation = deck[selectedCard].getLocation();
	    selectedCardView = cardViews[selectedCard];

	    // Ensure new selected card is on the top
	    group.getChildren().remove(selectedCardView);
	    group.getChildren().add(selectedCardView);

	    selectedCardView.setOnMouseClicked(e -> {
		if (e.getButton().equals(MouseButton.PRIMARY)) {
		    handleLeftClick();
		    if (!selectToggle) {
			// The method is recursively called for each new card
			manageMouseClick(cardViews);
		    }

		}

		// Ensure right mouse button only working if the new
		// card has already been clicked on
		if (selectToggle && e.getButton().equals(MouseButton.SECONDARY)) {
		    handleRightClick();
		}
	    });

	} else {
	    // The last card has been deselected.
	    SiliconGame.dataLabel.setText("No more cards to choose.");
	    SiliconGame.data2Label.setText("");
	}
    }

    // Method for handling the left click of a card
    void handleLeftClick() {
	if (selectToggle) {
	    // Card was deselected
	    scene.setOnMouseMoved(null);
	    SiliconGame.dataLabel.setText("Left Button clicked");
	    SiliconGame.data2Label.setText("Card deselected");

	    try {
		// Add a new ImageView representing the flip side of the card
		// showing the name of the card.
		selectedCardLocation.setXCoord((int) selectedCardView.getX());
		selectedCardLocation.setYCoord((int) selectedCardView.getY());

		selectedCardView = new ImageView(new Image("images/card_blank.png"));
		if (!selectedCardLocation.getHorizontal())
		    selectedCardView.setRotate(90);
		selectedCardView.setX(selectedCardLocation.getXCoord());
		selectedCardView.setY(selectedCardLocation.getYCoord());
		// After determining the location and orientation, the ImageView
		// can be added to the cardGroup
		group.getChildren().add(selectedCardView);

		// Create a label depicting the name of the card
		// to be placed upon the new ImageView.
		Label cardLabel = new Label();
		cardLabel.setText(deck[selectedCard].getName());
		cardLabel.setTranslateX(selectedCardLocation.getXCoord() + 10.0);
		cardLabel.setTranslateY(selectedCardLocation.getYCoord() + 30.0);
		if (!selectedCardLocation.getHorizontal())
		    cardLabel.setRotate(90);
		group.getChildren().add(cardLabel);

	    } catch (Exception ex) {
		System.out.println("Unable to load image from file - check folder.");
	    }

	    selectToggle = false;
	} else {
	    // Card was selected
	    SiliconGame.dataLabel.setText("Left Button clicked");
	    SiliconGame.data2Label.setText(deck[selectedCard].getName() + " selected");
	    group.getChildren().remove(selectedCardView);
	    group.getChildren().add(selectedCardView);
	    // Now create another EventHandler that detects mouse
	    // movement - card coordinates are plotted to follow the
	    // mouse cursor
	    scene.setOnMouseMoved(event -> {
		// Setting limits for card movement to the table.
		double xVal = event.getX() - cardXOffset;
		double yVal = event.getY() - cardYOffset;
		if (selectedCardLocation.getHorizontal()) {
		    if (xVal < cardHorizontalXEdge)
			xVal = cardHorizontalXEdge;
		    if (xVal > SiliconGame.DEFAULT_SCREEN_WIDTH - stageLeftOffset)
			xVal = SiliconGame.DEFAULT_SCREEN_WIDTH - stageLeftOffset;
		    selectedCardView.setX(xVal);
		    if (yVal < 0.0)
			yVal = 0.0;
		    if (yVal > SiliconGame.DEFAULT_SCREEN_HEIGHT - (stageDownOffset - 13.0))
			yVal = SiliconGame.DEFAULT_SCREEN_HEIGHT - (stageDownOffset - 13.0);
		    selectedCardView.setY(yVal);
		} else {
		    if (xVal < -13.0)
			xVal = -13.0;
		    if (xVal > SiliconGame.DEFAULT_SCREEN_WIDTH - (stageLeftOffset - 13.0))
			xVal = SiliconGame.DEFAULT_SCREEN_WIDTH - (stageLeftOffset - 13.0);
		    selectedCardView.setX(xVal);
		    if (yVal < cardVerticalYEdge)
			yVal = cardVerticalYEdge;
		    if (yVal > SiliconGame.DEFAULT_SCREEN_HEIGHT - stageDownOffset)
			yVal = SiliconGame.DEFAULT_SCREEN_HEIGHT - stageDownOffset;
		    selectedCardView.setY(yVal);
		}
	    });
	    selectToggle = true;
	}
    }

    void handleRightClick() {
	if (selectedCardLocation.getHorizontal()) {
	    SiliconGame.dataLabel.setText("Right Button clicked");
	    SiliconGame.data2Label.setText("Switch to vertical");
	    selectedCardLocation.setHorizontal(false);
	    selectedCardView.setRotate(90);
	} else {
	    SiliconGame.dataLabel.setText("Right Button clicked");
	    SiliconGame.data2Label.setText("Switch to horizontal");
	    selectedCardLocation.setHorizontal(true);
	    selectedCardView.setRotate(0);
	}
    }
}

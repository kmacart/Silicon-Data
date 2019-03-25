import java.io.File;
import java.util.Scanner;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class HighScores {
    
    static VBox highScoreScreen(StackPane root) {
	// Create a VBox to hold the logo, text area and button
	VBox vb = new VBox();
	root.getChildren().add(vb);

	// Load the logo into an ImageView and create the text area
	ImageView iv = new ImageView("file:src/images/Silicon-logo.png");
	TextArea ta = new TextArea();
	ta.setEditable(false);

	// Populate the text area with our high-score data
	String high_score_data;
	try {
	    Scanner scanner = new Scanner(new File("src/data/high_scores.txt"));
	    high_score_data = scanner.nextLine();
	    scanner.close();
	    String[] h_values = high_score_data.split(",");
	    for (int i = 0; i < 5; i++) {
		ta.appendText(h_values[i * 3] + "\t" + h_values[i * 3 + 2] + "\n");
	    }
	} catch (Exception ex) {
	    System.err.println("Unable to load high scores - file error");
	    ta.setText("Unable to load high scores - file error");
	}

	// Create the "Close" Button - returns to main menu (screen[0])
	Button btClose = new Button("Close");
	btClose.setOnMousePressed(me -> new Thread(new Tone(262, 100)).start());
	btClose.setOnAction(e -> {
	    System.out.println("Process Close High Scores screen");
	    root.getChildren().remove(Game.screen[2]);
	    root.getChildren().add(Game.screen[0]);
	});

	// Add the logo, text area and button to the VBox
	vb.getChildren().addAll(iv, ta, btClose);
	vb.needsLayoutProperty();
	return vb;
    }
}
package View;



import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverScene extends Scene {

	public GameOverScene(String timer, double width, double height, boolean hasWon, Controller controller) {
		super(new Pane(), width, height);
		VBox pane = new VBox();
		pane.setAlignment(Pos.CENTER);
		if(hasWon) {
			pane.setBackground(new Background(new BackgroundFill(Color.rgb(0, 176, 80), null, null)));
			Label wonLabel = new Label("You beat the level!");
			wonLabel.setFont(Font.font("Verdana" , FontWeight.BOLD, 50));
			Button nextLevelButton = new Button("next level");
			Button menuButton = new Button("to menu");
			menuButton.setOnAction(e -> controller.loadMenu());
			nextLevelButton.setOnAction(e -> controller.loadLevel());
			pane.getChildren().addAll(wonLabel, nextLevelButton, menuButton);
		}
		else {
			pane.setBackground(new Background(new BackgroundFill(Color.rgb(198, 0, 43), null, null)));
			Label gameOver = new Label("Game Over!");
			Label timerLabel = new Label(timer);
			Button menuButton = new Button("to menu");
			menuButton.setOnAction(e -> controller.loadMenu());
			gameOver.setFont(Font.font("Verdana" , FontWeight.BOLD, 50));
			timerLabel.setFont(Font.font("Verdana" , FontWeight.BOLD, 50));
			timerLabel.setTextFill(Color.WHITE);
			pane.getChildren().addAll(gameOver, timerLabel, menuButton);
			
		}
		setRoot(pane);
	}

}

package View;



import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverScene extends Scene {

	public GameOverScene(String timer, double width, double height) {
		super(new Pane(), width, height);
		VBox pane = new VBox();
		Label gameOver = new Label("Game Over!");
		Label timerLabel = new Label(timer);
		gameOver.setFont(Font.font("Verdana" , FontWeight.BOLD, 50));
		timerLabel.setFont(Font.font("Verdana" , FontWeight.BOLD, 50));
		timerLabel.setTextFill(Color.WHITE);
		pane.setBackground(new Background(new BackgroundFill(Color.rgb(198, 0, 43), null, null)));
		pane.setAlignment(Pos.CENTER);
		pane.getChildren().addAll(gameOver, timerLabel);
		setRoot(pane);
	}

}

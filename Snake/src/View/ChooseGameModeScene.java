package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ChooseGameModeScene extends Scene {

	public ChooseGameModeScene(Controller controller) {
		super(new Pane(), 500, 500);
		VBox rows = new VBox();
		HBox cols = new HBox();
		
		Label snake = new Label("Snake");
		Button freePlayButton = new Button("freeplay");
		Button playLevelsButton = new Button("play levels");
		Button levelEditorButton = new Button("level editor");
		
		snake.setFont(Font.font(50));
		freePlayButton.setPrefSize(100, 50);
		playLevelsButton.setPrefSize(100, 50);
		levelEditorButton.setPrefSize(100, 50);
		
		freePlayButton.setOnAction(e -> controller.loadFreePlay());
		
		freePlayButton.setTooltip(new Tooltip("Play the basic game, without any walls or levels"));
		cols.getChildren().addAll(playLevelsButton, levelEditorButton);
		rows.getChildren().addAll(snake, freePlayButton, cols);
		
		rows.setAlignment(Pos.CENTER);
		cols.setAlignment(Pos.CENTER);
		rows.setSpacing(100);
		cols.setSpacing(100);
		
		rows.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
		Background buttonBack = new Background(new BackgroundFill(Color.rgb(94, 173, 131), null, null));
		Border buttonBorder = new Border(new BorderStroke(Color.rgb(159, 184, 72), BorderStrokeStyle.DOTTED, null, new BorderWidths(2)));
		
		freePlayButton.setBackground(buttonBack);
		playLevelsButton.setBackground(buttonBack);
		levelEditorButton.setBackground(buttonBack);
		freePlayButton.setBorder(buttonBorder);
		playLevelsButton.setBorder(buttonBorder);
		levelEditorButton.setBorder(buttonBorder);
		setRoot(rows);
	}

}

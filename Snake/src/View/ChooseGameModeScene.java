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
		HBox cols1 = new HBox();
		HBox cols2 = new HBox();
		Label snake = new Label("Snake");
		Button freePlayButton = new Button("freeplay");
		Button playRandomLevelsButton = new Button("play random levels");
		Button playSelectLevelButton = new Button("select a level");
		Button levelEditorButton = new Button("level editor");
		
		snake.setFont(Font.font(50));
		freePlayButton.setPrefSize(150, 75);
		playRandomLevelsButton.setPrefSize(150, 75);
		levelEditorButton.setPrefSize(150, 75);
		playSelectLevelButton.setPrefSize(150, 75);
		
		freePlayButton.setOnAction(e -> controller.loadFreePlay());
		playRandomLevelsButton.setOnAction(e -> controller.loadLevel());
		playSelectLevelButton.setOnAction(e -> controller.loadSelectedLevel());
		
		freePlayButton.setTooltip(new Tooltip("Play the basic game, without any walls or levels"));
		cols1.getChildren().addAll(playSelectLevelButton, freePlayButton);
		cols2.getChildren().addAll(playRandomLevelsButton, levelEditorButton);
		rows.getChildren().addAll(cols1, cols2);
		
		rows.setAlignment(Pos.CENTER);
		cols1.setAlignment(Pos.CENTER);
		cols2.setAlignment(Pos.CENTER);
		rows.setSpacing(100);
		cols1.setSpacing(100);
		cols2.setSpacing(100);
		
		rows.setBackground(new Background(new BackgroundFill(Color.CADETBLUE, null, null)));
		
		Background buttonBack = new Background(new BackgroundFill(Color.rgb(94, 173, 131), null, null));
		Border buttonBorder = new Border(new BorderStroke(Color.rgb(159, 184, 72), BorderStrokeStyle.DOTTED, null, new BorderWidths(2)));
		
		freePlayButton.setBackground(buttonBack);
		playRandomLevelsButton.setBackground(buttonBack);
		playSelectLevelButton.setBackground(buttonBack);
		levelEditorButton.setBackground(buttonBack);
		
		freePlayButton.setBorder(buttonBorder);
		playRandomLevelsButton.setBorder(buttonBorder);
		playSelectLevelButton.setBorder(buttonBorder);
		levelEditorButton.setBorder(buttonBorder);
		
		setRoot(rows);
	}

}

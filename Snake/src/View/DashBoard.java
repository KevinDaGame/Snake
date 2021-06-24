package View;

import Controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class DashBoard extends HBox {

	private Controller controller;
	private Button playPauseButton;
	private Button exitButton;
	private Slider speedSlider;
	private Label playTimeLabel;
	
	public DashBoard(Controller controller) {
		this.controller = controller;
		
		setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, null, null)));
		playPauseButton = new Button("Play");
		exitButton = new Button("Exit");
		playPauseButton.setOnAction(e -> handlePlayPause());
		exitButton.setOnAction(e -> controller.exit());
		
		getChildren().addAll(playPauseButton, exitButton);
	}

	private void handlePlayPause() {
		if(playPauseButton.getText().equals("Play")) {
			playPauseButton.setText("Pause");
			controller.play();
		}
		else {
			playPauseButton.setText("Play");
			controller.pause();
		}
	}

}

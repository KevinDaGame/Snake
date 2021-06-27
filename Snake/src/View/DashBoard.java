package View;

import Controller.Controller;
import javafx.geometry.Pos;
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
		setPrefHeight(50);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		setAlignment(Pos.CENTER);
		setSpacing(20);
		playPauseButton = new Button("Play");
		exitButton = new Button("Exit");
		speedSlider = new Slider();
		playTimeLabel = new Label(controller.getGame().getTimer());
		speedSlider.setMin(1);
		speedSlider.setMax(12);
		speedSlider.setShowTickLabels(true);
		speedSlider.setShowTickMarks(true);
		speedSlider.setMinorTickCount(0);
		speedSlider.setMajorTickUnit(1);
		speedSlider.setMouseTransparent(true);
		speedSlider.setPrefSize(250, 40);
		playTimeLabel.setPrefSize(80, 40);
		playPauseButton.setPrefSize(80, 30);
		exitButton.setPrefSize(80, 30);
		speedSlider.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY.darker(), null, null)));
		playTimeLabel.setAlignment(Pos.CENTER);
		playTimeLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY.darker(), null, null)));
		playPauseButton.setOnAction(e -> handlePlayPause());
		exitButton.setOnAction(e -> controller.exit());
		
		getChildren().addAll(playPauseButton, exitButton, speedSlider, playTimeLabel);
	}
	
	public void update() {
		speedSlider.setValue(controller.getGame().getDifficulty());
		playTimeLabel.setText(controller.getGame().getTimer());
	
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

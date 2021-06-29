package View;

import Controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class EditorDashBoard extends HBox {
	private Button exitButton;
	private Button saveButton;
	private Button clearButton;
	private TextField timeInput;
	private Label label;
	private Controller controller;
	
	public EditorDashBoard(Controller controller) {
		this.controller = controller;
		setPrefHeight(50);
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		setAlignment(Pos.CENTER);
		setSpacing(20);
		exitButton = new Button("Exit");
		saveButton = new Button("Save");
		clearButton = new Button("Clear");
		label = new Label("Input time to survive in the field:");
		timeInput = new TextField();
		exitButton.setPrefSize(80, 30);
		saveButton.setPrefSize(80, 30);
		clearButton.setPrefSize(80, 30);
		saveButton.setOnAction(e -> saveLevel());
		clearButton.setOnAction(e -> controller.loadLevelEditor());
		exitButton.setOnAction(e -> controller.exit());
		getChildren().addAll(exitButton, saveButton, clearButton, label, timeInput);
	}

	private void saveLevel() {
		try {
			if(Integer.valueOf(timeInput.getText()) > 0) {
				controller.saveLevel(Integer.valueOf(timeInput.getText()));
				return;
			}			
		}
		catch (NumberFormatException e) {
		}
		timeInput.clear();
		timeInput.setText("Invalid time input!");
	}
}

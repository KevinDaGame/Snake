package View;

import Controller.Controller;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

	public GameScene(Controller controller, DrawPane drawPane) {
		super(new Pane());
		BorderPane root = new BorderPane();
		setRoot(root);
		root.setCenter(drawPane);
		root.setBottom(new DashBoard(controller));
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			//TODO handle this
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case UP:
					System.out.println("up");
					break;

				default:
					break;
				}
				
			}
		});
	}
}

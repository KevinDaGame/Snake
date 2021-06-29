package View;

import Controller.Controller;
import Model.Direction;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

	public GameScene(Controller controller, DrawPane drawPane, HBox dashBoard) {
		super(new Pane());
		BorderPane root = new BorderPane();
		setRoot(root);
		root.setCenter(drawPane);
		root.setBottom(dashBoard);
		this.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			//TODO handle this
			public void handle(KeyEvent event) {
				Direction direction = null; 
				switch (event.getCode()) {
				case UP:
					direction = Direction.UP;
					break;
				case DOWN:
					direction = Direction.DOWN;
					break;
				case LEFT:
					direction = Direction.LEFT;
					break;
				case RIGHT:
					direction = Direction.RIGHT;
					break;
				default:
					break;
				}
				
				if(direction != null) {
					controller.getGame().getSnake().getBodyParts().get(0).setDirection(direction);					
				}
				
			}
		});
	}
}

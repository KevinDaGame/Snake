package View;

import Controller.Controller;
import Model.Direction;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {

	public GameScene(Controller controller, DrawPane drawPane, DashBoard dashBoard) {
		super(new Pane());
		BorderPane root = new BorderPane();
		setRoot(root);
		root.setCenter(drawPane);
		root.setBottom(dashBoard);
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			//TODO handle this
			public void handle(KeyEvent event) {
				Direction direction = null; 
				switch (event.getCode()) {
				case UP:
					System.out.println("up");
					direction = Direction.UP;
					break;
				case DOWN:
					System.out.println("down");
					direction = Direction.DOWN;
					break;
				case LEFT:
					System.out.println("left");
					direction = Direction.LEFT;
					break;
				case RIGHT:
					System.out.println("right");
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

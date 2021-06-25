package View;

import java.util.ArrayList;

import Model.BodyPart;
import Model.Game;
import Model.Snake;
import Model.Spot;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class DrawPane extends Pane {
	private Canvas canvas;
	private Game game;
	public DrawPane(Game game) {
		canvas = new Canvas(760, 600);
		this.game = game;
		draw();
//		Image image = new Image(this.getClass().getResourceAsStream("/bear.png"), 40, 40, true, false);
//		gc.drawImage(image, 40, 40);
		getChildren().add(canvas);
	}
	
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		drawBackGround();
		drawSpots();
		drawSnake();
	}
	
	private void drawSpots() {
		ArrayList<Spot> spots = game.getSpots();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(Spot spot: spots) {
			Image image = new Image(this.getClass().getResourceAsStream("/" + spot.getImg() + ".png"), 40, 40, true, false);
			gc.drawImage(image, spot.getX() * 40, spot.getY() * 40);
		}
	}
	
	private void drawSnake() {
		Snake snake = game.getSnake();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(BodyPart bodyPart: snake.getBodyParts()) {
			if(bodyPart.getX() == snake.getX() && bodyPart.getY() == snake.getY()) {
				gc.setFill(Color.RED);
			}
			else {
				gc.setFill(Color.ORANGE);
			}
			gc.fillOval(bodyPart.getX() * 40, bodyPart.getY() * 40, 40, 40);
		}
	}

	private void drawBackGround() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		for(int x = 0; x < 19; x++) {
			for(int y = 0; y < 15; y++) {
				if(x % 2 == 0 && y % 2 == 0) {
					gc.setFill(Color.BLACK);
				}
				else if(x % 2 == 0 || y % 2 == 0) {
					gc.setFill(Color.rgb(50, 50, 50));
				}
				else {
					gc.setFill(Color.rgb(100, 100, 100));
				}
				gc.fillRect(x * 40, y * 40, 40, 40);
			}
		}
	}
}

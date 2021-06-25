package Model;

import javafx.scene.input.KeyCode;

public class BodyPart {
	private int x;
	private int y;
	private Direction direction;
	private Direction oldDirection;
	
	public BodyPart(int x, int y, Direction direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.oldDirection = null;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;

		
	}
	
	public void updateDirection(Direction direction) {
		oldDirection = this.direction;
		this.direction = direction;
	}
	
	public void move() {
		switch (direction) {
		case UP:
			setY(getY() - 1);
			break;
		case DOWN:
			setY(getY() + 1);
			break;
		case LEFT:
			setX(getX() - 1);
			break;
		case RIGHT:
			setX(getX() + 1);
			break;
		default:
			break;
		}
	}

	private void setY(int y) {
		this.y = y;
	}
	
	private void setX(int x) {
		this.x = x;
	}

	public Direction getDirection() {
		return direction;
	}

	public Direction getOldDirection() {
		return oldDirection;
	}

	public void setOldDirection(Direction direction) {
		oldDirection = null;
		
	}
}

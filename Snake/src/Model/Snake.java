package Model;

import java.util.ArrayList;

public class Snake {
	private ArrayList<BodyPart> bodyParts;
	private int x;
	private int y;
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		bodyParts = new ArrayList<>();
		startSnake();
	}
	
	private void startSnake() {
		for(int i = 0; i < 5; i++) {
			bodyParts.add(new BodyPart(x - i, y, Direction.RIGHT));
		}
	}

	public ArrayList<BodyPart>getBodyParts() {
		return bodyParts;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void move() {
		for(int i = 0; i < bodyParts.size(); i++) {
			if(i == 0) {
				bodyParts.get(i).move();
				setX(bodyParts.get(i).getX());
				setY(bodyParts.get(i).getY());
			}
			else {
				if(canMove(bodyParts.get(i))) {
					bodyParts.get(i).move();
					if(bodyParts.get(i - 1).getOldDirection() != null) {
						bodyParts.get(i).updateDirection(bodyParts.get(i - 1).getOldDirection());
						bodyParts.get(i - 1).setOldDirection(null);
					}
					else {
						bodyParts.get(i).updateDirection(bodyParts.get(i - 1).getDirection());
					}
				}
			}
		}
	}
	private void setY(int y) {
		this.y = y;
		
	}

	private void setX(int x) {
		this.x = x;
		
	}

	/**
	 * check if the given part can move. This will come in handy when parts are added and multiple are on a single spot
	 * @param bodyPart
	 * @return
	 */
	private boolean canMove(BodyPart bodyPart) {
		int x = bodyPart.getX();
		int y = bodyPart.getY();
		switch (bodyPart.getDirection()) {
		case UP:
			y -= 1;
			break;
		case DOWN:
			y += 1;
			break;
		case LEFT:
			x -= 1;
			break;
		case RIGHT:
			x += 1;
			break;
			

		default:
			break;
		}
		for(BodyPart checkPart: bodyParts) {
			if(x == checkPart.getX() && y == checkPart.getY()) {
				return false;
			}
		}
		return true;
	}

	public void addPart() {
		BodyPart lastPart = bodyParts.get(bodyParts.size() - 1);
		bodyParts.add(new BodyPart(lastPart.getX(), lastPart.getY(), lastPart.getDirection()));
	}

	public void setBodyParts(ArrayList<BodyPart> newSnake) {
		bodyParts = newSnake;
		
	}
}

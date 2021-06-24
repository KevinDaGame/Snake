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
			bodyParts.add(new BodyPart(x - i, y));
		}
	}

	public ArrayList<BodyPart>getBodyParts() {
		return bodyParts;
	}
	
	public int getX() {
		return x;
	}
	
	public int gety() {
		return y;
	}
}

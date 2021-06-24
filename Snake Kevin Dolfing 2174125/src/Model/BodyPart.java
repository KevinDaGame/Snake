package Model;

public class BodyPart {
	private int x;
	private int y;
	
	public BodyPart(int x, int y) {
		this.x = x;
		this.y = y;
		System.out.println("new bodypart at x = " + x + " and y = " + y);
	}
	
	public int getX() {
		return x;
	}
	
	public int gety() {
		return y;
	}
}

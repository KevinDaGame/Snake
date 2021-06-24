package Model;

public class Spot {
	private Marker type;
	private int x;
	private int y;
	public Spot(Marker type, int x , int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int gety() {
		return y;
	}
	
	public String getImg() {
		switch (type) {
		case BEAR:
			return "bear";
		case FIRE:
			return "fire";
		case MOUSE:
			return "mouse";

		default:
			return "";
		}
	}
}

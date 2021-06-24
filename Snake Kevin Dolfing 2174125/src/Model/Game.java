package Model;

import java.util.ArrayList;

public class Game {
	ArrayList<Spot> spots;
	Snake snake;
	
	public Game() {
		snake = new Snake(6, 4);
		spots = new ArrayList<>();
		spots.add(new Spot(Marker.BEAR, 0, 0));
		spots.add(new Spot(Marker.FIRE, 0, 1));
		spots.add(new Spot(Marker.MOUSE, 0, 2));
	}

	public ArrayList<Spot> getSpots() {
		return spots;
	}

	public Snake getSnake() {
		return snake;
	}
}

package Model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Game {
	private ArrayList<Spot> spots;
	private Snake snake;
	private int level;
	private long lastCheckTime;
	private int timeRunning;
	public Game() {
		level = 1;
		lastCheckTime = System.currentTimeMillis();
		timeRunning = 0;
		snake = new Snake(6, 4);
		spots = new ArrayList<>();
		spots.add(new Spot(Marker.BEAR, 0, 0));
		spots.add(new Spot(Marker.FIRE, 0, 1));
		spots.add(new Spot(Marker.MOUSE, 0, 2));
	}

	
	public void levelUp() {
		if(level < 12) {
			level++;
		}
	}
	public ArrayList<Spot> getSpots() {
		return spots;
	}

	public Snake getSnake() {
		return snake;
	}


	public int getLevel() {
		return level;
	}


	public void updateTime() {
		timeRunning += System.currentTimeMillis() - lastCheckTime;
		lastCheckTime = System.currentTimeMillis();
		
	}

	public void startTime() {
		lastCheckTime = System.currentTimeMillis();
	}
	public String getTimer() {
		String minutes = String.valueOf(TimeUnit.MILLISECONDS.toMinutes(timeRunning));
		String seconds = String.valueOf(TimeUnit.MILLISECONDS.toSeconds(timeRunning - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(timeRunning))));
		String miliseconds = String.valueOf(timeRunning - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(timeRunning)));
		
		if(minutes.length() == 1) {
			minutes = "0" + minutes;
		}
		if(seconds.length() == 1) {
			seconds = "0" + seconds;
		}
		if (miliseconds.length() == 2) {
			miliseconds = "0" + miliseconds;
		}
		if (miliseconds.length() == 1) {
			miliseconds = "00" + miliseconds;
		}
		return String.valueOf(minutes + ":" + seconds + ":" + miliseconds);
	}
}

package Model;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Controller.Controller;

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
		spots.add(new Spot(Marker.BEAR, 10, 6));
		spots.add(new Spot(Marker.FIRE, 10, 7));
		spots.add(new Spot(Marker.MOUSE, 0, 2));
	}

	public void levelUp() {
		if (level < 12) {
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
		String seconds = String.valueOf(TimeUnit.MILLISECONDS
				.toSeconds(timeRunning - TimeUnit.MINUTES.toMillis(TimeUnit.MILLISECONDS.toMinutes(timeRunning))));
		String miliseconds = String
				.valueOf(timeRunning - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(timeRunning)));

		if (minutes.length() == 1) {
			minutes = "0" + minutes;
		}
		if (seconds.length() == 1) {
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

	public boolean checkEvents() {
		if (snake.getX() > 18 || snake.getX() < 0 || snake.getY() > 14 || snake.getY() < 0) {

			return false;
		}
		for (Spot spot : spots) {
			if (spot.getX() == snake.getX() && spot.getY() == snake.getY()) {
				if (spot.getType() == Marker.FIRE) {
					return false;
				} else if (spot.getType() == Marker.BEAR) {
					int newLength = (int) Math.floor((double) snake.getBodyParts().size() / 2);

					ArrayList<BodyPart> newSnake = new ArrayList<>();
					for (int i = 0; i < snake.getBodyParts().size(); i++) {
						if (i < newLength) {
							newSnake.add(snake.getBodyParts().get(i));
						}

					}
					snake.setBodyParts(newSnake);
					if (snake.getBodyParts().size() < 5) {
						return false;
					}
				} else if (spot.getType() == Marker.MOUSE) {
					for (int i = 0; i < 5; i++) {
						snake.addPart();
					}
				}
			}
		}

		return true;
	}
}

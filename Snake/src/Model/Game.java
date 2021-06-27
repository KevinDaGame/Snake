package Model;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Game {
	private ArrayList<Spot> spots;
	private Snake snake;
	private int difficulty;
	private long lastCheckTime;
	private int timeRunning;
	private final int StartSpotCount = 5;
	private int timeToWin;
	public Game() {
		initGame();
	}

	public Game(File file) {
		
	}
	
	
	public void initGame() {
		difficulty = 1;
		lastCheckTime = System.currentTimeMillis();
		timeRunning = 0;
		snake = new Snake(6, 4);
		spots = new ArrayList<>();
		generateStartSpots();
	}
	
	private void generateStartSpots() {
		for (int i = 0; i < StartSpotCount; i++) {
			generateSpot();
		}

	}

	public void generateSpot() {
		Random random = new Random();
		boolean found = false;
		while (!found) {
			int x = random.nextInt(19);
			int y = random.nextInt(15);
			if (isClear(x, y)) {
				int type = random.nextInt(3);
				if (type == 0) {
					spots.add(new Spot(Marker.BEAR, x, y));
				} else if (type == 1) {
					spots.add(new Spot(Marker.FIRE, x, y));
				} else if (type == 2) {
					spots.add(new Spot(Marker.MOUSE, x, y));
				}
				found = true;
			}
		}

	}

	public void difficultyUp() {
		if (difficulty < 12) {
			difficulty++;
		}
	}

	public ArrayList<Spot> getSpots() {
		return spots;
	}

	public Snake getSnake() {
		return snake;
	}

	public int getDifficulty() {
		return difficulty;
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
					spots.remove(spot);
					return false;
				} else if (spot.getType() == Marker.BEAR) {
					spots.remove(spot);
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
					return true;
				} else if (spot.getType() == Marker.MOUSE) {
					spots.remove(spot);
					for (int i = 0; i < 5; i++) {
						snake.addPart();
					}
					return true;
				}
			}
		}
		for (int i = 1; i < snake.getBodyParts().size(); i++) {
			if (snake.getBodyParts().get(i).getX() == snake.getX()
					&& snake.getBodyParts().get(i).getY() == snake.getY()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * when trying to put a spot on the field, check if it is safe (empty spot &
	 * snake isn't going to instantly run into it)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isClear(int x, int y) {
		for (Spot spot : spots) {
			if (spot.getX() == x && spot.getY() == y) {
				return false;
			}
		}

		if (snake.getX() - 2 < x && snake.getX() + 2 > x && snake.getY() - 2 < y && snake.getY() + 2 > y) {
			return false;
		}
		for (BodyPart part : snake.getBodyParts()) {
			if (part.getX() == x && part.getY() == y) {
				return false;
			}
		}
		return true;
	}
}

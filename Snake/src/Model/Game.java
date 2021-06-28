package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Controller.Controller;

public class Game {
	private ArrayList<Spot> spots;
	private Snake snake;
	private int difficulty;
	private long lastCheckTime;
	private int timeRunning;
	private final int StartSpotCount = 5;
	private long timeToWin;
	private Controller controller;
	private boolean failedToLoad;
	
	public Game(Controller controller) {
		this.controller = controller;
		difficulty = 1;
		lastCheckTime = System.currentTimeMillis();
		timeRunning = 0;
		snake = new Snake(6, 4);
		spots = new ArrayList<>();
		generateStartSpots();
	}

	public Game(Controller controller, File file) {
		this.controller = controller;
		difficulty = 1;
		lastCheckTime = System.currentTimeMillis();
		timeRunning = 0;
		spots = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String value;
			while((line = reader.readLine()) != null) {
				if(line.startsWith("time: ")) {
					value = line.substring(6);
					
					try {						
						timeToWin = TimeUnit.SECONDS.toMillis(Long.valueOf(value));
					}
					catch (NumberFormatException e) {
						controller.showLoadFileError("At field time, value " + value + " is not a valid input");
						fail();
						return;
					}
				}
				else if(line.startsWith("wall: ")) {
					value = line.substring(6);
					System.out.println(value);
					boolean secondNumber = false;
					String x = "";
					String y = "";
					for(int i = 0; i < value.length(); i++) {
						char c = value.charAt(i);
						if(c == ',') {
							secondNumber = true;
						}
						else if(Character.isDigit(c)) {
							if(!secondNumber) {
								x += c;
							}
							else {
								y += c;
							}
						}
						else if(c == ' ') {
							
						}
						else {
							controller.showLoadFileError("the input " + c + " is not a valid input");
							fail();
							return;
						}
					}
					if(Integer.valueOf(x) < 0 || Integer.valueOf(x) > 18 || Integer.valueOf(y) < 0|| Integer.valueOf(y) > 14) {
						controller.showLoadFileError("coordinates out of bounds: x = " + x + " and y = " + y );
						fail();
						return;
					}
					spots.add(new Spot(Marker.WALL, Integer.valueOf(x), Integer.valueOf(y)));
					System.out.println("x = " + x + " y = " + y);
				}
			}
		} catch (FileNotFoundException e) {
			controller.showLoadFileError("Could not find file");
			fail();
			return;
		} catch (IOException e) {
			controller.showLoadFileError("File cannot be read");
			fail();
			return;
		}
		if(timeToWin == 0) {
			controller.showLoadFileError("Time to win not found in file");
			fail();
			return;
		}
		if(snake == null) {
			snake = new Snake(6, 4);
		}
		generateStartSpots();
		
		for(BodyPart part: snake.getBodyParts()) {
			for(Spot spot: spots) {
				if(part.getX() == spot.getX() && part.getY() == spot.getY()) {
					controller.showLoadFileError("The snake spawned on top of a wall!");
					fail();
					return;
				}
			}
		}
	}
	
	private void generateStartSpots() {
		for (int i = 0; i < StartSpotCount; i++) {
			generateSpot();
		}

	}
	
	private void fail() {
		failedToLoad = true;
	}
	
	public boolean hasFailed() {
		return failedToLoad;
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
				if (spot.getType() == Marker.FIRE || spot.getType() == Marker.WALL) {
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

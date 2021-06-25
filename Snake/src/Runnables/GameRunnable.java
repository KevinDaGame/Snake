package Runnables;

import java.util.Random;

import Controller.Controller;
import javafx.application.Platform;

public class GameRunnable implements Runnable {

	private Controller controller;
	private final int defaultDelay = 1300;
	private final int stepDelay = 100;
	private final int addsToLevelUp = 5;
	private int movesSinceLastAdded;
	private boolean running;

	public GameRunnable(Controller controller) {
		this.controller = controller;
		running = true;
		movesSinceLastAdded = 0;
	}

	@Override
	public void run() {
		Random random = new Random();
		while (running) {
			try {
				System.out.println(defaultDelay - (controller.getGame().getLevel() * stepDelay));
				Thread.sleep(defaultDelay - (controller.getGame().getLevel() * stepDelay));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(random.nextInt(10) < movesSinceLastAdded) {
				movesSinceLastAdded = 0;
				if(controller.getGame().getSnake().getBodyParts().size() % 5 == 0) {
					controller.getGame().levelUp();
				}
				controller.getGame().getSnake().addPart();
			}
			controller.getGame().getSnake().move();
			movesSinceLastAdded++;
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					controller.getGame().updateTime();
					controller.getDashBoard().update();
					controller.getDrawPane().draw();
				}
			});
		}

	}

	public void stop() {
		running = false;
	}

}

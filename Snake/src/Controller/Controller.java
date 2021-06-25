package Controller;
import java.util.Random;

import Model.Game;
import Runnables.GameRunnable;
import View.DashBoard;
import View.DrawPane;
import View.GameOverScene;
import View.GameScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Controller extends Application {
	private Game game;
	private DrawPane drawPane;
	private GameRunnable gameRunnable;
	private DashBoard dashBoard;
	private Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		game = new Game();
		drawPane = new DrawPane(game);
		dashBoard = new DashBoard(this);
		GameScene scene = new GameScene(this, drawPane, dashBoard);
		stage.setScene(scene);
		stage.setTitle("PROG4 ASS Snake - Kevin Dolfing");
		stage.setResizable(false);
		stage.show();
	

	}
	
	
	public void exit() {
		Platform.exit();
	}
	
	public void play() {
		getGame().startTime();
		gameRunnable = new GameRunnable(this);
		Thread thread = new Thread(gameRunnable);
		thread.setDaemon(true);
		thread.start();
		
	}
	
	/**
	 * called when pause button is clicked
	 */
	public void pause() {
		gameRunnable.stop();	
	}
	
	/**
	 * called when snake dies
	 */
	public void endGame() {
		stage.setScene(new GameOverScene(game.getTimer(), stage.getScene().getWidth(), stage.getScene().getHeight()));
		gameRunnable.stop();
	}
	
	public Game getGame() {
		return game;
	}
	public DrawPane getDrawPane() {
		return drawPane;
	}
	
	public DashBoard getDashBoard() {
		return dashBoard;
	}

}

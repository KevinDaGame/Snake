package Controller;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.util.Random;

import javax.swing.filechooser.FileNameExtensionFilter;

import Model.Game;
import Runnables.GameRunnable;
import View.ChooseGameModeScene;
import View.DashBoard;
import View.DrawPane;
import View.GameOverScene;
import View.GameScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		ChooseGameModeScene scene = new ChooseGameModeScene(this);
		stage.setScene(scene);
		stage.setTitle("PROG4 ASS Snake - Kevin Dolfing");
		stage.setResizable(false);
		stage.show();
	}

	private void initGameScene() {
		drawPane = new DrawPane(game);
		dashBoard = new DashBoard(this);
		GameScene scene = new GameScene(this, drawPane, dashBoard);
		stage.setScene(scene);
	}

	public void loadFreePlay() {
		game = new Game();
		initGameScene();
	}

	public void loadLevel() {
		Random random = new Random();
		File levelDir = new File(getClass().getResource("/Levels").getFile());
		File[] levels = levelDir.listFiles();
		int index = random.nextInt(levels.length);
		game = new Game(levels[index]);
	}

	public void showLoadFileError() {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Error loading file");
		error.setTitle("File error");
		error.setContentText("This file can't be read. Please check for corruption");
		error.show();
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

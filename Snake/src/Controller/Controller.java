package Controller;

import java.io.File;
import java.util.Random;

import Model.Game;
import Runnables.GameRunnable;
import View.ChooseGameModeScene;
import View.DashBoard;
import View.DrawPane;
import View.EditorDashBoard;
import View.GameOverScene;
import View.GameScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Controller extends Application {
	private Game game;
	private DrawPane drawPane;
	private GameRunnable gameRunnable;
	private DashBoard dashBoard;
	private EditorDashBoard editorDashBoard;
	private Stage stage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		loadMenu();
		stage.setTitle("PROG4 ASS Snake - Kevin Dolfing");
		stage.setResizable(false);
		stage.show();
	}

	public void loadMenu() {
		ChooseGameModeScene scene = new ChooseGameModeScene(this);
		stage.setScene(scene);

	}

	private void initGameScene(boolean editor) {
		GameScene scene;
		if (editor) {
			drawPane = new DrawPane(game, this, true);
			editorDashBoard = new EditorDashBoard(this);
			scene = new GameScene(this, drawPane, editorDashBoard);
		} else {
			drawPane = new DrawPane(game, this, false);
			dashBoard = new DashBoard(this);
			scene = new GameScene(this, drawPane, dashBoard);
		}
		stage.setScene(scene);
	}

	public void loadFreePlay() {
		game = new Game(this, false);
		initGameScene(false);
	}

	public void loadLevelEditor() {
		game = new Game(this, true);
		initGameScene(true);
	}

	public void loadLevel() {
		Random random = new Random();
		File levelDir = new File(getClass().getResource("/Levels").getFile());
		File[] levels = levelDir.listFiles();
		int index = random.nextInt(levels.length);
		game = new Game(this, levels[index]);
		if (!game.hasFailed()) {
			initGameScene(false);
		}
	}

	public void loadSelectedLevel() {
		File level;
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(getClass().getResource("/Levels").getFile()));
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text files", "*.txt");
		chooser.getExtensionFilters().add(filter);
		level = chooser.showOpenDialog(stage);
		if (level != null) {
			game = new Game(this, level);
			if (!game.hasFailed()) {
				initGameScene(false);
			}
		}
	}

	public void showLoadFileError(String errorString) {
		Alert error = new Alert(AlertType.ERROR);
		error.setHeaderText("Error loading file");
		error.setTitle("File error");
		error.setContentText("This file can't be read. " + errorString);
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
		if (game.getTimeToWin() > 0 && game.getTimeRunning() >= game.getTimeToWin()) {
			stage.setScene(new GameOverScene(game.getTimer(), stage.getScene().getWidth(), stage.getScene().getHeight(),
					true, this));
		} else {
			stage.setScene(new GameOverScene(game.getTimer(), stage.getScene().getWidth(), stage.getScene().getHeight(),
					false, this));
		}
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

	public void addWall(int x, int y) {
		if(x >= 0 && x < 19 && y >=0 && y < 15) {			
			System.out.println("add");
			game.addWall(x, y);
			getDrawPane().draw();
		}
	}

	public void removeWall(int x, int y) {
		if(x >= 0 && x < 19 && y >=0 && y < 15) {	
			System.out.println("remove");
			game.removeWall(x, y);
			getDrawPane().draw();			
		}
	}

	public void saveLevel(int time) {
		File level;
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(getClass().getResource("/Levels").getFile()));
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text files", "*.txt");
		chooser.getExtensionFilters().add(filter);
		level = chooser.showSaveDialog(stage);
		if(level != null) {
			game.saveLevel(level, time);
			loadMenu();
		}
	}

}

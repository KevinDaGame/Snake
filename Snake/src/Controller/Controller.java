package Controller;
import Model.Game;
import View.DrawPane;
import View.GameScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Controller extends Application {
	Game game;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		game = new Game();
		GameScene scene = new GameScene(this, new DrawPane(game));
		stage.setScene(scene);
		stage.setTitle("PROG4 ASS Snake - Kevin Dolfing");
		stage.setResizable(false);
		stage.show();

	}
	
	
	public void exit() {
		Platform.exit();
	}
	public void play() {
		
		
	}
	public void pause() {
		
		
	}

}

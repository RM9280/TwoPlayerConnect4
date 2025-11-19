
// Elaina Wamhoff and Rebecca Mantione

import java.net.Socket;
import javafx.application.Application;
import javafx.stage.Stage;

// creates and starts player GUI
public class Client extends Application {
	
	public static void main(String[] args) {
		launch();
	}
	
	public void start(Stage stage) {
		StartScene s = new StartScene(stage);
		stage.setTitle("Connect 4");
		stage.show();
	}
}
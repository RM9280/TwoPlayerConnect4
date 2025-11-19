//
//// Elaina Wamhoff and Rebecca Mantione
//
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.Socket;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//
//// creates intro screen for game
//public class StartScene {
//
//	private Scene scene;				// new scene
//	private static Socket connection;	// connection established to server
//	private static int playerNumber;	// number representing if client is player 1 or 2
//	
//	// StartScene constructor
//	public StartScene(Stage stage) {
//		Label title = new Label("CONNECT 4");
//		title.setFont(new Font(60));
//		title.setAlignment(Pos.CENTER);
//		
//		// establishes connection with server and starts GameScene
//		Button start = new Button("Start!");
//		start.setMinWidth(200);
//		start.setAlignment(Pos.CENTER);
//		start.setOnAction(e -> {
//			String[] settings = SettingsScene.getSettings();
//			String hostName = settings[0];
//			int LISTENING_PORT = Integer.parseInt(settings[1]);
//			try {
//				connection = new Socket(hostName, LISTENING_PORT);
//				BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				String temp = incoming.readLine();
//				playerNumber = Integer.parseInt(temp);
//			} catch (Exception l) {
//				l.printStackTrace();
//			}
//			GameScene g = new GameScene(stage);
//		});
//		
//		// starts SettingsScene
//		Button settings = new Button("Settings");
//		settings.setMinWidth(200);
//		settings.setAlignment(Pos.CENTER);
//		settings.setOnAction(e -> {
//			SettingsScene s = new SettingsScene(stage);
//		});
//		
//		VBox root = new VBox(title, start, settings);
//		root.setAlignment(Pos.CENTER);
//		
//		scene = new Scene(root, 350, 350);
//		stage.setScene(scene);
//	}
//	
//	// connection getter
//	public static Socket getSocket() {
//		return connection;
//	}
//	
//	// playerNumber getter
//	public static int getPlayerNumber() {
//		return playerNumber;
//	}
//	
//}
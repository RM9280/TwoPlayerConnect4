
// Elaina Wamhoff and Rebecca Mantione

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

// creates and starts game
public class GameScene {
	
	private GraphicsContext g;						// graphics to create board
	private Canvas c;								// primary canvas for game
	private Label message = new Label();			// label that changes telling when to take turn
	private int[] playerCoordinates = new int[2];	// player coordinates selected
	private int[] oppoCoordinates = new int[2];		// opposition coordinates received
	private BufferedReader incoming;				// reads from server
	private PrintWriter outgoing;					// sends to server
	
	// GameScene constructor
	public GameScene(Stage stage) {
		try {
			incoming = new BufferedReader(new InputStreamReader(StartScene.getSocket().getInputStream()));
			outgoing = new PrintWriter(StartScene.getSocket().getOutputStream());
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		c = new Canvas(350, 300);
		g = c.getGraphicsContext2D();
		g.setFill(Color.YELLOW);
		g.fillRect(0, 0, 350, 300);
		g.setStroke(Color.BLACK);
		for(int i = 0; i <= 350; i += 50) {
			for(int j = 0; j <= 250; j += 50) {
				g.strokeOval(i, j, 50, 50);
			}
		}
		BorderPane root = new BorderPane(c);
		root.setStyle("-fx-border-width: 4px; -fx-border-color: #444");
		message.setFont(new Font(40));
		message.setAlignment(Pos.CENTER);
		Label memo = new Label();
		if(StartScene.getPlayerNumber() == 1) {
			message.setText("YOUR TURN!");
			memo.setText("Click on the circle where you would like to play, press PLAY!...");
		} else {
			message.setText("Press PLAY!");
			memo.setText("Press PLAY!, Click on the circle where you would like to play...");
		}
		VBox top = new VBox(message, memo);
		root.setTop(top);
		Button main = new Button("Return to home.");
		main.setMinWidth(175);
		main.setOnAction(e -> new StartScene(stage));
		Button play = new Button("PLAY!");
		play.setMinWidth(175);
		HBox menu = new HBox(main, play);
		root.setBottom(menu);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
		// players turn
		c.setOnMousePressed(e -> {
			translateMove(e);
			draw(true);
			message.setText("Press PLAY!");
			sendMove();
		});
		
		// oppositions turn
		play.setOnAction(e -> {
			receiveMove();
			draw(false);
			message.setText("YOUR TURN!");
		});	
	}
	
	// receives oppositions move from server
	private void receiveMove() {
		try {	
			oppoCoordinates[0] = Integer.parseInt(incoming.readLine());
			oppoCoordinates[1] = Integer.parseInt(incoming.readLine());	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// sends players move to server
	private void sendMove() {
		try {
			outgoing.println(playerCoordinates[0]);
			outgoing.println(playerCoordinates[1]);
			outgoing.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// draws new move on board
	private void draw(boolean player) {
		if(player == true) {
			g.setFill(Color.RED);
			g.fillOval(playerCoordinates[0], playerCoordinates[1], 50, 50);
		} else {
			g.setFill(Color.BLUE);
			g.fillOval(oppoCoordinates[0], oppoCoordinates[1], 50, 50);
		}
	}
	
	// translates mouse coordinates to coordinates of circle to be drawn
	private void translateMove(MouseEvent e) {
		int X;
		int Y;
		int row;
    	int col;
		
		X = (int)e.getX();
		Y = (int)e.getY();
    	if(X < 50) 
    		col = 0;
    	else if(X < 100)
    		col = 1;
    	else if(X < 150)
    		col = 2;
    	else if(X < 200)
    		col = 3;
    	else if(X < 250)
    		col = 4;
    	else if(X < 300)
    		col = 5;
    	else
    		col = 6;
    	
    	if(Y < 50) 
    		row = 0;
    	else if(Y < 100)
    		row = 1;
    	else if(Y < 150)
    		row = 2;
    	else if(Y < 200)
    		row = 3;
    	else if(Y < 250)
    		row = 4;
    	else 
    		row = 5;
    	
    	playerCoordinates[0] = col * 50;
    	playerCoordinates[1] = row * 50;
	}
}



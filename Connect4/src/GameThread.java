
// Elaina Wamhoff and Rebecca Mantione

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// creates connections to both players and establishes communication between them
public class GameThread extends Thread {
	
	private static Socket player1;			// connection to player 1
	private static Socket player2;			// connection to player 2
	private static BufferedReader in1;		// reader from player 1 connection
	private static BufferedReader in2;		// reader from player 2 connection
	private static PrintWriter out1;		// writer to player 1 connection
	private static PrintWriter out2;		// writer to player 2 connection
	
	// GameThread constructor
	public GameThread(Socket player1, Socket player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	// creates readers and writers to each player, sends and receives game moves to each other
	public void run() {
		try {
			System.out.println("Player 1: Connection from " +  this.player1.getInetAddress().toString());
		} catch(Exception e) {
			System.out.println("Player 1: Error on connection with: " + this.player1.getInetAddress().toString() + ": " + e);
		}
		try {
			System.out.println("Player 2: Connection from " +  this.player2.getInetAddress().toString());
		} catch(Exception e) {
			System.out.println("Player 2: Error on connection with: " + this.player2.getInetAddress().toString() + ": " + e);
		}
		
		String p1X = null;
		String p1Y = null;
		String p2X = null;
		String p2Y = null;
		try {
			in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			out1 = new PrintWriter(player1.getOutputStream());
			in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
			out2 = new PrintWriter(player2.getOutputStream());
			
			out1.println("1");
			out2.println("2");
			out1.flush();
			out2.flush();
			
			System.out.println("readers and writers connected");
	
			while(true) {
				p1X = in1.readLine();
				p1Y = in1.readLine();
				
				out2.println(p1X);
				out2.println(p1Y);
				out2.flush();
				
				p2X = in2.readLine();
				p2Y = in2.readLine();
				
				out1.println(p2X);
				out1.println(p2Y);
				out1.flush();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


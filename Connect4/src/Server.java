
// Elaina Wamhoff and Rebecca Mantione

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// opens listener for two clients to play connect 4 and sends them to new GameThread 
public class Server {
	
	private static int LISTENING_PORT = 32007;
	
	public static void main(String[] args) {
		ServerSocket listener;
		Socket player1;
		Socket player2;
        try {
            listener = new ServerSocket(LISTENING_PORT);
            System.out.println("Listening on port " + LISTENING_PORT);
            while (true) {
                player1 = listener.accept(); 
                player2 = listener.accept();
                GameThread game = new GameThread(player1, player2);
                game.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
	}
}
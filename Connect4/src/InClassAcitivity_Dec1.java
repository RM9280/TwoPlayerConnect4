

	import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.PrintWriter;
	import java.net.ServerSocket;
	import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

	public class InClassAcitivity_Dec1 {

		InClassAcitivity_Dec1() {

		}

		public static void main(String[] args) {
			
			try { // Create the input stream.
				Scanner scanner = new Scanner(new File("Project6.xml"));
				String LISTENING_PORT = null;
				String IP_Address = null;
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine().trim();
					if (line.equals("<LISTENING_PORT>")) {
						LISTENING_PORT = scanner.nextLine().trim();
					}
					if (line.equals("<IP_Address>")) {
						IP_Address = scanner.nextLine().trim();
					}
				}
				System.out.println(LISTENING_PORT + " " + IP_Address);
			} catch (FileNotFoundException e) {
				System.out.println("Can't find file data.dat!");
			}
		}

	}



// Elaina Wamhoff and Rebecca Mantione

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SettingsScene {
    private TextField hostText; // Field for entering the username.
    private TextField portText; // Field for entering the password.
//    private ArrayList<Account> accounts; // List of user accounts.
    private Label statusLbl; // Label to display login status.

    /**
     * Initializes the LoginScene with a list of accounts.
     */
    public SettingsScene(Stage stage) {
    	Label title = new Label("Settings Scene");
		title.setFont(new Font(60));
		title.setAlignment(Pos.CENTER);
//        this.accounts = accounts;

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 500, 300);
        
        statusLbl = new Label();
        root.setTop(title);
        root.setAlignment(title, Pos.TOP_CENTER);
        root.setBottom(statusLbl);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        Label host = new Label("Host");
        Label port = new Label("Port");

        hostText = new TextField();
        portText = new TextField();

        gridPane.add(host, 0, 0);
        gridPane.add(hostText, 1, 0);
        gridPane.add(port, 0, 1);
        gridPane.add(portText, 1, 1);

        root.setCenter(gridPane);
        BorderPane.setMargin(gridPane, new Insets(20,20,20,20));
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(e -> new StartScene(stage));
        
        Button save = new Button("Save");
        save.setOnAction(e -> {
        	save();
        	StartScene s = new StartScene(stage);
        });

        gridPane.add(cancelBtn, 1, 2);
        gridPane.add(save, 2, 2);
        stage.setScene(scene);
    }

    /**
     * Returns listening port and IP address in XML file
     */
    public static String[] getSettings() {
		String[] settings = new String[2];
    	try {
    		Scanner scanner = new Scanner(new File("settings.xml"));
    		while (scanner.hasNextLine()) {
    			String line = scanner.nextLine().trim();
    			if (line.equals("<LISTENING_PORT>")) {
    				settings[0] = scanner.nextLine().trim();
    			}
    			if (line.equals("<IP_Address>")) {
    				settings[1] = scanner.nextLine().trim();
    			}
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return settings;
    }
    
    /**
     * Saves new settings to XML file
     */
    public void save() {
    	try {
    		PrintWriter writer = new PrintWriter("settings.xml");
        	String host = hostText.getText();
        	System.out.println(host);
        	String portTest = portText.getText();
    		if (portTest.equals("")) {
    			writer.println("<Settings>");
				writer.println("<IP_Address>");
				writer.println("localhost");
				writer.println("</IP_Address>");
				writer.println("<LISTENING_PORT>");
				writer.println("32007");
				writer.println("</LISTENING_PORT>");
				writer.println("</Settings>");
				writer.flush();
				writer.close();
    		}
    		if (host.equals("")) {
    			writer.println("<Settings>");
				writer.println("<IP_Address>");
				writer.println("localhost");
				writer.println("</IP_Address>");
				writer.println("<LISTENING_PORT>");
				writer.println("32007");
				writer.println("</LISTENING_PORT>");
				writer.println("</Settings>");
				writer.flush();
				writer.close();
    		}else {
    		int port = Integer.valueOf(portText.getText());
        	System.out.println(port);
    				writer.println("<Settings>");
    				writer.println("<IP_Address>");
    				writer.println(host);
    				writer.println("</IP_Address>");
    				writer.println("<LISTENING_PORT>");
    				writer.println(port);
    				writer.println("</LISTENING_PORT>");
    				writer.println("</Settings>");

    				writer.flush();
    				writer.close();
    		}
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}


// Elaina Wamhoff and Rebecca Mantione

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

//Tests the getSettings() method in SettingsScene to make sure it is reading in correctly
public class Settings_JUnit {

	@Test
	void test() {
		String[] settings = new String[2];
		settings = SettingsScene.getSettings();
		String check = settings[0] + " " + settings[1];
		System.out.println(settings[0] + " " + settings[1]);
		assert check.equals("32007 localhost");

	}

}

package org.usfirst.frc.team4534.robot.util;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertySheetLoader {

	private static final Properties parsePropertiesString(String string) {
		final Properties p = new Properties();
		try {
			p.load(new StringReader(string));
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static final Properties parseProperties(String path) {
		try {
			Properties p = parsePropertiesString(readFile(path));
			return p;
		} catch (IOException e) {
			return null;
		}
	}

	public static final String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}

}

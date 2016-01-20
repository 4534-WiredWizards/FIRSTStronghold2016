package org.usfirst.frc.team4534.robot.util;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertySheetLoader {
	
	public static final Properties parseProperties(String string) {
		final Properties p = new Properties();
		try {
			p.load(new StringReader(string));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

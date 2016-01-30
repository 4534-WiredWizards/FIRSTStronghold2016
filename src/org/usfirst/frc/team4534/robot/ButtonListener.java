package org.usfirst.frc.team4534.robot;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;

public interface ButtonListener {
	
	public void onButtonPress(Button button, double value);
	
}

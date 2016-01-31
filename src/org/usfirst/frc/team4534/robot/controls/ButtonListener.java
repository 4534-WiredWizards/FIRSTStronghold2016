package org.usfirst.frc.team4534.robot.controls;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;

public interface ButtonListener {
	
	public void onButtonPress(Button button, double value);
	public void onButtonRelease(Button button);
	
}

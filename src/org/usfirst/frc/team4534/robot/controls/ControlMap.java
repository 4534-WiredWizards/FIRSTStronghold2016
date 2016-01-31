package org.usfirst.frc.team4534.robot.controls;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;
import org.usfirst.frc.team4534.robot.ControlSystem.ButtonLiteral;

public interface ControlMap {
	
	public ButtonLiteral convertButton(Button button);
	
}

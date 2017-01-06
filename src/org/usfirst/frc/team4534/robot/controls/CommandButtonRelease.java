package org.usfirst.frc.team4534.robot.controls;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;

import edu.wpi.first.wpilibj.command.Command;

@Deprecated
public class CommandButtonRelease implements ButtonListener {

	private final Button button;
	private final Command command;
	private boolean isPressed = false;
	
	public CommandButtonRelease(Button button, Command command) {
		this.button = button;
		this.command = command;
	}

	@Override
	public void onButtonPress(Button button, double value) {
		if (button.equals(this.button)) {
			isPressed = true;
		}
	}
	
	@Override
	public void onButtonRelease(Button button) {
		if (button.equals(this.button) && isPressed) {
			isPressed = false;
			command.start();
		}
	}

}

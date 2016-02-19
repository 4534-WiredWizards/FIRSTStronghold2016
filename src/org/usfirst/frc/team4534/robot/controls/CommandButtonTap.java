package org.usfirst.frc.team4534.robot.controls;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;

import edu.wpi.first.wpilibj.command.Command;

public class CommandButtonTap implements ButtonListener {

	private final Button button;
	private final Command command;
	private boolean isPressed = false;

	public CommandButtonTap(Button button, Command command) {
		this.button = button;
		this.command = command;
	}

	@Override
	public void onButtonPress(Button button, double value) {
		if (button.equals(this.button) && !isPressed) {
			isPressed = true;
			command.start();
		}
	}

	@Override
	public void onButtonRelease(Button button) {
		if (button.equals(this.button)) {
			isPressed = false;
		}
	}

}

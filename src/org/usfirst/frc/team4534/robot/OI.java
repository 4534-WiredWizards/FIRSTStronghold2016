package org.usfirst.frc.team4534.robot;

import org.usfirst.frc.team4534.robot.ControlSystem.Button;
import org.usfirst.frc.team4534.robot.commands.AimAndShoot;
import org.usfirst.frc.team4534.robot.commands.CenterAngle;
import org.usfirst.frc.team4534.robot.commands.CenterAnglePID;
import org.usfirst.frc.team4534.robot.commands.CenterDistance;
import org.usfirst.frc.team4534.robot.commands.CenterDistancePID;
import org.usfirst.frc.team4534.robot.commands.IntakeBall;
import org.usfirst.frc.team4534.robot.commands.LowGoal;
import org.usfirst.frc.team4534.robot.commands.Shoot;
import org.usfirst.frc.team4534.robot.controls.CommandButton;
import org.usfirst.frc.team4534.robot.controls.CommandButtonTap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick stick;

	public OI() {
		stick = new Joystick(0);
		ControlSystem.addButtonListener(new CommandButtonTap(Button.AIM_SHOOT, new LowGoal()));
		ControlSystem.addButtonListener(new CommandButtonTap(Button.SHOOT, new LowGoal()));
		ControlSystem.addButtonListener(new CommandButton(Button.INTAKE, new IntakeBall()));
		ControlSystem.addButtonListener(new CommandButton(Button.LOW_GOAL, new LowGoal()));
		//ControlSystem.addButtonListener(new CommandButtonTap(Button.RIGHT_CLICK, new ArmToggle()));
		
		
		/*new JoystickButton(stick, 1).whileHeld(new DriveStraight(-.4));
		new JoystickButton(stick, 2).whileHeld(new DriveStop());
		new JoystickButton(stick, 4).whileHeld(new DriveStraight(.4));
		ControlSystem.addButtonListener(new CommandButton(Button.SHOOT, new DriveStraight(-0.4)));*/
		SmartDashboard.putNumber("TurnTo", 0);
		ControlSystem.addButtonListener(new CommandButton(Button.START, new CenterAngle()));
		ControlSystem.addButtonListener(new CommandButton(Button.SELECT, new CenterDistance()));
	}

	public Joystick getJoystick() {
		return stick;
	}
}

// // CREATING BUTTONS
// One type of button is a joystick button which is any button on a
// joystick.
// You create one by telling it which joystick it's on and which button
// number it is.
// Joystick stick = new Joystick(port);
// Button button = new JoystickButton(stick, buttonNumber);

// There are a few additional built in buttons you can use. Additionally,
// by subclassing Button you can create custom triggers and bind those to
// commands the same as any other Button.

// // TRIGGERING COMMANDS WITH BUTTONS
// Once you have a button, it's trivial to bind it to a button in one of
// three ways:

// Start the command when the button is pressed and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenPressed(new ExampleCommand());

// Run the command while the button is being held down and interrupt it once
// the button is released.
// button.whileHeld(new ExampleCommand());

// Start the command when the button is released and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenReleased(new ExampleCommand());
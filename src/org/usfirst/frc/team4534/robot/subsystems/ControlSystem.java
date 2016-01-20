package org.usfirst.frc.team4534.robot.subsystems;

import java.util.HashMap;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ControlSystem extends Subsystem {

	public final RobotDrive robotDrive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);

	private static final HashMap<String, Double> buttonValues = new HashMap<String, Double>();

	public ControlSystem(int startingMap) {

	}

	@Override
	protected void initDefaultCommand() {

	}

	public void drive(Joystick joystick) {
		//Robot.drivetrain.arcadeDrive(forward, rotate);
	}

	// speedfinal = ((speedi / (prec + 1)) * (fast +1)) / 2

	public static double getspeed(double forward, double back) {
		return forward - back;
	}

	public static final double calcSpeed(double speedI, double prec, double fast) {
		return (((speedI / (prec + 1)) * (fast + 1)) / 2);
	}

	/**
	 * Gets the value of a button by name as a value between 0 and 1. To check if a button is pressed. Names are: moveForward, moveBackward, turnRight, turnLeft, shoot.
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final double getButton(String buttonName) {
		return 0;
	}
	
	/**
	 * Similar to {@link getButton()} but for things that are more suited to being boolean values. For example: shoot
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final boolean getButtonIsPressed(String buttonName) {
		return false; // TODO: create ControlSystem.getButtonIsPressed
	}

}

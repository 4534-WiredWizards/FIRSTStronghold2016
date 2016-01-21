package org.usfirst.frc.team4534.robot;

import java.util.HashMap;
import java.util.Properties;

import org.usfirst.frc.team4534.robot.util.PropertySheetLoader;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

public class ControlSystem {

	private final RobotDrive robotDrive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);

	private static final HashMap<String, Double> buttonValues = new HashMap<String, Double>();
	
	private static Properties prop;

	public ControlSystem(int startingMap) {
		prop = PropertySheetLoader.parseProperties("sheets/0.txt");
	}

	public static double getspeed(double forward, double back) {
		return forward - back;
	}

	public static final double calcSpeed(double speedI, double prec, double fast) {
		return (((speedI / (prec + 1)) * (fast + 1)) / 2);
	}

	/**
	 * Gets the value of a button by name as a value between 0 and 1. To check if a button is pressed. Names are: moveForward, moveBackward, turnRight, turnLeft, precision, speedBoost, shoot.
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final double getButton(String buttonName) {
		Joystick joy = Robot.oi.getJoystick();
		switch(buttonName) {
		}
		return 0;
	}
	
	/**
	 * Similar to {@link ControlSystem#getButton(String)} but for things that are more suited to being boolean values. For example: shoot
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final boolean getButtonIsPressed(String buttonName) {
		Joystick joystick = Robot.oi.getJoystick();
		return false; // TODO: create ControlSystem.getButtonIsPressed(String)
	}

}

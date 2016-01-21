package org.usfirst.frc.team4534.robot;

import java.util.HashMap;
import java.util.Properties;

import org.usfirst.frc.team4534.robot.util.PropertySheetLoader;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.Timer;

/**
 * Convenience methods for accessing the joystick(s). Only a few requirements
 * for using it though. 1: {@link ControlSystem#init() must be called before
 * anything else can be, 2: update must be called periodically.}
 * 
 * @author Brandon Dyer
 *
 */
public class ControlSystem {

	private static final HashMap<String, String> buttonMap = new HashMap<String, String>();
	private static Properties prop;
	private static Joystick joystick;
	private static double rumbleTime;

	/**
	 * This NEEDS to be called before any other ControlSystem methods are called
	 * to avoid a {@link NullPointerException}
	 */
	public static void init() {
		joystick = Robot.oi.getJoystick();
		prop = PropertySheetLoader.parseProperties("sheets/0.txt");
		rumbleTime = 0;
		oldTime = Timer.getFPGATimestamp();
		update();
	}

	private static double oldTime;

	/**
	 * This method NEEDS to be called periodically.
	 */
	public static void update() {
		double newTime = Timer.getFPGATimestamp();
		double delta = newTime - oldTime;
		{
			// update rumble
			rumbleTime -= delta;
			if (rumbleTime < 0)
				rumbleTime = 0;
			if (rumbleTime > 0) {
				joystick.setRumble(RumbleType.kLeftRumble, 1);
				joystick.setRumble(RumbleType.kRightRumble, 1);
			} else {
				joystick.setRumble(RumbleType.kLeftRumble, 0);
				joystick.setRumble(RumbleType.kRightRumble, 0);
			}
		}
		oldTime = newTime;
	}

	/**
	 * Converts two axis into one.
	 * 
	 * @param forward
	 * @param back
	 * @return
	 */
	private static double getspeed(double forward, double back) {
		return forward - back;
	}

	/**
	 * Gives the speed based on a function: f(s,p,f) = ((s / (p + 1)) * (f + 1))
	 * / 2 . Assuming the speed value is one, the button to move forward is
	 * fully pressed and the button to move backward is fully depressed, then
	 * the following statements are true: If prec = 0 and fast = 0 then the
	 * speedF is half, if prec = 1 and fast = 0 then the speedF is quarter, if
	 * prec = 0 and fast = 1 then the speedF is full, if prec = 1 and fast = 1
	 * then the speedF is half. "speedF" is f(s, p, f).
	 * 
	 * @param speedI
	 *            the speed axis {-1 <= speedI <= 1}
	 * @param prec
	 *            the precision value {0 <= prec <= 1}
	 * @param fast
	 *            the fast value {0 <= fast <= 1}
	 * @return f(s, p, f)
	 */
	public static final double calcSpeed(double speedI, double prec, double fast) {
		return (((speedI / (prec + 1)) * (fast + 1)) / 2);
	}

	/**
	 * Gets the value of a button by name as a value between 0 and 1. To check
	 * if a button is pressed. Names are: moveForward, moveBackward, turnRight,
	 * turnLeft, precision, speedBoost, shoot.
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final double getButton(String buttonName) {
		switch (buttonName) {
		}
		return 0;
	}

	/**
	 * Similar to {@link ControlSystem#getButton(String)} but for things that
	 * are more suited to being boolean values. For example: shoot
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final boolean getButtonIsPressed(String buttonName) {
		return false; // TODO: create ControlSystem.getButtonIsPressed(String)
	}

	/**
	 * Gets the value of a literal button by name. For example: A, B, X, Y,
	 * LeftTrigger, RightTrigger, StickLeftX, StickLeftY, StickRightX,
	 * StickRightY, LeftBumper, RightBumper, LeftButton, RightButton, Start,
	 * Select
	 * 
	 * @param button
	 * @return
	 */
	private static final double getButtonLiteral(String button) {
		double n;
		switch (button.trim().toLowerCase()) {
		case "a":

		default:
			n = 0;
		}
		return n;
	}

	/**
	 * Makes the joystick rumble for a specific amount of time.
	 * 
	 * @param seconds
	 *            number of seconds to rumble
	 */
	public static final void rumbleTimeSet(double seconds) {
		rumbleTime = seconds;
	}

	public static final void rumbleTimeAdd(double seconds) {
		rumbleTime += seconds;
	}

	public static final void rumbleKill() {
		rumbleTime = 0;
	}

}

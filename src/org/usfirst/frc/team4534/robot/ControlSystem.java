package org.usfirst.frc.team4534.robot;

import java.util.LinkedList;

import org.usfirst.frc.team4534.robot.controls.ButtonListener;
import org.usfirst.frc.team4534.robot.controls.ControlMap;
import org.usfirst.frc.team4534.robot.controls.maps.DefaultMap;
import org.usfirst.frc.team4534.robot.util.Maths;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * Convenience methods for accessing the joystick(s). Only a few requirements
 * for using it though. 1: {@link ControlSystem#init() must be called before
 * anything else can be, 2: update must be called periodically.}
 * 
 * @author Brandon Dyer
 *
 *         Note from Sam: I hate this class
 */
public class ControlSystem {

	private static double rumbleTime;
	private static double[] currentJoyX, currentJoyY;
	private static final int numJoysticks = 2; // 1 indexed, not zero indexed
	private static final LinkedList<ButtonListener> buttonListeners = new LinkedList<ButtonListener>();
	private static final LinkedList<ControlMap> controlMaps = new LinkedList<ControlMap>();
	private static int currentMap = 0;
	
	@SuppressWarnings("unused")
	private static boolean isSelectPressed = false;

	/**
	 * This NEEDS to be called before any other ControlSystem methods are called
	 * to avoid a {@link NullPointerException}
	 */
	public static void init() {
		loadMaps();
		rumbleTime = 0;
		oldTime = Timer.getFPGATimestamp();
		currentJoyX = new double[numJoysticks];
		currentJoyY = new double[numJoysticks];
		for(int i = 0; i < numJoysticks; i++) {
			currentJoyX[i] = 0;
			currentJoyY[i] = 0;
		}
		update(); // must be last
	}

	public static void loadMaps() {
		addControlMap(new DefaultMap());
		// addControlMap(new RacingMap());
	}

	private static double oldTime;

	/**
	 * This method NEEDS to be called periodically.
	 */
	public static void update() {
		double newTime = Timer.getFPGATimestamp();
		double delta = newTime - oldTime;
		{
			/* update rumble */ {
				rumbleTime -= delta;
				if (rumbleTime < 0)
					rumbleTime = 0;
				if (rumbleTime > 0) {
					// joystick.setRumble(RumbleType.kLeftRumble, 1);
					// joystick.setRumble(RumbleType.kRightRumble, 1);
				} else {
					// joystick.setRumble(RumbleType.kLeftRumble, 0);
					// joystick.setRumble(RumbleType.kRightRumble, 0);
				}
			}
			/* update acceleration */ {
				// The threshold is the minimum value of the current joy values
				final double threshold = 0.55;
				for (int i = 0; i < numJoysticks; i++) {
					// Linearly interpolate the current joy values
					currentJoyY[i] = Maths.lerp(currentJoyY[i], getMoveAxisY(), 1 - Math.pow(.1, delta));
					currentJoyX[i] = getMoveAxisX();//Maths.lerp(currentJoyX[i], getMoveAxisX(), 1 - Math.pow(.1, delta));
					// Reverse clamp the current joy values based on the raw
					// axis
					if (getMoveAxisY() >= threshold && currentJoyY[0] < threshold)
						currentJoyY[i] = threshold;
					if (getMoveAxisY() <= -threshold && currentJoyY[0] > -threshold)
						currentJoyY[i] = -threshold;
					if (getMoveAxisX() >= threshold && currentJoyX[0] < threshold)
						currentJoyX[i] = threshold;
					if (getMoveAxisX() <= -threshold && currentJoyX[0] > -threshold)
						currentJoyX[i] = -threshold;
				}
				if (getButtonLiteral(ButtonLiteral.SELECT) > 0.5) {
					currentMap++;
				}
				for (Button b : Button.values()) {
					if (getButtonIsPressed(b)) {
						callButton(b, getButton(b));
					} else {
						callButtonRelease(b);
					}
				}
				/*
				 * if (getButtonLiteral(ButtonLiteral.SELECT) > 0.5 &&
				 * !isSelectPressed) { isSelectPressed = true; //currentMap++; }
				 * else if (getButtonLiteral(ButtonLiteral.SELECT) <= 0.5) {
				 * isSelectPressed = false; }
				 */
			}
		}
		oldTime = newTime;
	}

	public static void killAccel() {
		currentJoyY[0] = 0;
		currentJoyX[0] = 0;
	}

	private static final double scale = 3;

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
	 * @param turbo
	 *            the fast value {0 <= fast <= 1}
	 * @return f(s, p, f)
	 */
	public static final double calcSpeed(double speedI, double prec, double turbo) {
		return ((((speedI * scale) / (prec + scale)) * (turbo + scale)) / (scale + 1));
	}

	public static final double getMoveAxisAccelX() {
		return currentJoyX[0];
	}

	public static final double getMoveAxisAccelY() {
		return currentJoyY[0];
	}

	public static final double getMoveAxisX(int joy) {
		switch (joy) {
		case 1:
			return ControlSystem.calcSpeed(
					getButtonLiteral(ButtonLiteral.STICK_RIGHT_RIGHT)
							- getButtonLiteral(ButtonLiteral.STICK_RIGHT_LEFT),
					getButton(Button.PRECISION), getButton(Button.TURBO));
		case 0:
		default:
			return ControlSystem.calcSpeed(getButton(Button.TURN_RIGHT) - getButton(Button.TURN_LEFT),
					getButton(Button.PRECISION), getButton(Button.TURBO));
		}
	}

	public static final double getMoveAxisX() {
		return getMoveAxisX(0);
	}

	public static final double getMoveAxisY(int joy) {
		switch (joy) {
		case 1:
			return ControlSystem.calcSpeed(
					getButtonLiteral(ButtonLiteral.STICK_RIGHT_UP) - getButtonLiteral(ButtonLiteral.STICK_RIGHT_DOWN),
					getButton(Button.PRECISION), getButton(Button.TURBO));
		case 0:
		default:
			return ControlSystem.calcSpeed(getButton(Button.MOVE_FORWARD) - getButton(Button.MOVE_BACKWARD),
					getButton(Button.PRECISION), getButton(Button.TURBO));
		}
	}

	public static final double getMoveAxisY() {
		return getMoveAxisY(0);
	}

	/**
	 * Gets the value of a button by name as a value between 0 and 1. To check
	 * if a button is pressed.
	 * 
	 * @param buttonName
	 *            the name of the button
	 * 
	 * @return value of the button
	 */
	public static final double getButton(Button button) {
		double value = getButtonLiteral(controlMaps.get(currentMap % controlMaps.size()).convertButton(button));
		return value;
	}

	public static enum Button {
		MOVE_FORWARD, MOVE_BACKWARD, TURN_RIGHT, TURN_LEFT, PRECISION, TURBO, SHOOT, AIM_SHOOT, INTAKE, LEFT_ARM_TOGGLE, RIGHT_ARM_TOGGLE, ARMS_DOWN, ARMS_UP, LEFT_CLICK, RIGHT_CLICK, START, SELECT, LOW_GOAL
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
	public static final boolean getButtonIsPressed(Button button) {
		return getButton(button) > 0.5;
	}

	/**
	 * Gets the value of a literal button by name.
	 * 
	 * @param button
	 * @return
	 */
	public static final double getButtonLiteral(ButtonLiteral button) {
		Joystick j = Robot.oi.getJoystick();
		double n = 0;
		switch (button) {
		case A:
			n = j.getRawButton(1) ? 1 : 0;
			break;
		case B:
			n = j.getRawButton(2) ? 1 : 0;
			break;
		case LEFT_BUMPER:
			n = j.getRawButton(5) ? 1 : 0;
			break;
		case STICK_LEFT_CLICK:
		case LEFT_BUTTON:
			n = j.getRawButton(9) ? 1 : 0;
			break;
		case LEFT_TRIGGER:
			n = j.getRawAxis(2);
			break;
		case RIGHT_BUMPER:
			n = j.getRawButton(6) ? 1 : 0;
			break;
		case STICK_RIGHT_CLICK:
		case RIGHT_BUTTON:
			n = j.getRawButton(10) ? 1 : 0;
			break;
		case RIGHT_TRIGGER:
			n = j.getRawAxis(3);
			break;
		case START:
			n = j.getRawButton(8) ? 1 : 0;
			break;
		case SELECT:
			n = j.getRawButton(7) ? 1 : 0;
			break;
		case STICK_LEFT_UP:
			n = j.getRawAxis(1) > 0 ? j.getRawAxis(1) : 0;
			break;
		case STICK_LEFT_DOWN:
			n = j.getRawAxis(1) < 0 ? -j.getRawAxis(1) : 0;
			break;
		case STICK_RIGHT_UP:
			n = j.getRawAxis(5) > 0 ? j.getRawAxis(5) : 0;
			break;
		case STICK_RIGHT_DOWN:
			n = j.getRawAxis(5) < 0 ? -j.getRawAxis(5) : 0;
			break;
		case STICK_LEFT_RIGHT:
			n = j.getRawAxis(0) > 0 ? j.getRawAxis(0) : 0;
			break;
		case STICK_LEFT_LEFT:
			n = j.getRawAxis(0) < 0 ? -j.getRawAxis(0) : 0;
			break;
		case STICK_RIGHT_RIGHT:
			n = j.getRawAxis(4) > 0 ? j.getRawAxis(4) : 0;
			break;
		case STICK_RIGHT_LEFT:
			n = j.getRawAxis(4) < 0 ? -j.getRawAxis(4) : 0;
			break;
		case X:
			n = j.getRawButton(3) ? 1 : 0;
			break;
		case Y:
			n = j.getRawButton(4) ? 1 : 0;
			break;
		default:
			n = 0;
			break;
		}
		return n;
	}

	public static enum ButtonLiteral {
		A, B, X, Y, LEFT_TRIGGER, RIGHT_TRIGGER, STICK_LEFT_UP, STICK_LEFT_DOWN, STICK_LEFT_LEFT, STICK_LEFT_RIGHT, STICK_RIGHT_UP, STICK_RIGHT_DOWN, STICK_RIGHT_LEFT, STICK_RIGHT_RIGHT, LEFT_BUMPER, RIGHT_BUMPER, LEFT_BUTTON, RIGHT_BUTTON, START, SELECT, STICK_RIGHT_CLICK, STICK_LEFT_CLICK;
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

	public static final void addButtonListener(ButtonListener listener) {
		buttonListeners.add(listener);
	}

	private static final void callButton(Button button, double value) {
		for (ButtonListener l : buttonListeners) {
			l.onButtonPress(button, value);
		}
	}

	private static final void callButtonRelease(Button button) {
		for (ButtonListener l : buttonListeners) {
			l.onButtonRelease(button);
		}
	}

	public static final void addControlMap(ControlMap map) {
		if (!controlMaps.contains(map)) {
			controlMaps.add(map);
		}
	}

}

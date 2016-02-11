package org.usfirst.frc.team4534.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	public static int leftMotor = 1; // PWM1
	public static int rightMotor = 0; // PWM0
	public static int ballIntake = 2; // PWM2
	public static int shooter = 3; // PWM3
	public static int ballHandlerLimit = 0; // DIO0
	public static int compressorAnalogPort = 4;

	// Sensors
	// Digital Input Output (DIO) pins on the MXP port are the value they are
	// listed at, plus 10
	// e.g. if it says DIO0 on the MXP, it's referenced as DIO10 in the code

	// Encoders
	public static int leftEncoderA = 10; // MXPDIO0 / DIO10
	public static int leftEncoderB = 11; // MXPDIO1 / DIO11
	public static int leftEncoderX = 12; // MXPDIO2 / DIO12
	public static int rightEncoderA = 13; // MXPDIO3 / DIO13
	public static int rightEncoderB = 14; // MXPDIO4 / DIO14
	public static int rightEncoderX = 15; // MXPDIO5 / DIO15

	// Gyroscope
	public static int gyroscope = 0; // AI0

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

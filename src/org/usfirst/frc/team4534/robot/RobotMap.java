package org.usfirst.frc.team4534.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// PWM
	
	public static int leftMotor = 1; // PWM1
	public static int rightMotor = 0; // PWM0
	public static int ballIntake = 2; // PWM2
	public static int armMotor = 3; // PWM3

	
	// CAN
	
	public static int PCM = 1; //CAN ID 1
	public static int shooter = 2; //CAN ID 2
	public static int shooter2 = 3; // CAN ID 3

	// Pneumatics
	
	public static int leftArm = 0; //PCM location
	public static int rightArm = 1;	//PCM location
	public static double solenoidDelay = 2;
	public static double approachDelay = 2;
	
	// Sensors
	// Digital Input Output (DIO) pins on the MXP port are the value they are
	// listed at, plus 10
	// e.g. if it says DIO0 on the MXP, it's referenced as DIO10 in the code

	// Ball handler
	public static int ballHandlerLimit = 0; // DIO0
	
	// Arms
	public static int armDownLimit = 7; // DIO7
	public static int armUpLimit = 8; // DIO8
	
	// Encoders
	public static int leftEncoderA = 1; // DIO1
	public static int leftEncoderB = 2; // DIO2
	public static int leftEncoderX = 3; // DIO3
	public static int rightEncoderA = 4; // DIO4
	public static int rightEncoderB = 5; // DIO5
	public static int rightEncoderX = 6; // DIO6

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

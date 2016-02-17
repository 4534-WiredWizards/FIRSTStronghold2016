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

	public static int leftMotor = 1;
	public static int rightMotor = 0;
	public static int ballIntake = 2;
	public static int shooter = 2; //CAN ID
	public static int shooter2 = 3;
	public static int ballHandlerLimit = 0;
	public static int compressorAnalogPort = 4;
	public static int PCM = 1; //CAN ID
	public static int leftArm = 0; //PCM location
	public static int rightArm = 1;	//PCM location
	public static double solenoidDelay = 2;
	public static double approachDelay = 2;
	
	
	

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}

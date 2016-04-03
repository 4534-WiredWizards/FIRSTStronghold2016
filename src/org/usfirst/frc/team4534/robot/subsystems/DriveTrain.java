package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;
import org.usfirst.frc.team4534.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends Subsystem {

	private RobotDrive drive;

	// create instance of motor-configuration based robot drive
	public DriveTrain() {
		
		drive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor); 
		//LiveWindow.addActuator("DriveTrain", "Left Motor", );
	}

	// enable arcade drive operation of the drivetrain

	/**
	 * Drives the robot via a single joystick in arcade driving mode.
	 * 
	 * @author Tom Sanford & Benjamin Davis
	 * @param forward
	 *            the value of motion. Between -1 & 1.
	 * @param rotate
	 *            the value of rotation. Between -1 & 1.
	 */
	public void arcadeDrive(double forward, double rotate) {
		drive.arcadeDrive(forward, rotate, true);
	}

	/**
	 * Drives the robot without turning.
	 * 
	 * @author Benjamin Davis
	 * @param speed
	 *            Amount of power given to motors, ranging from -1 (Full speed
	 *            BACKWARD) to +1 (Full speed FORWARD).
	 */
	public void straightDrive(double speed) {
		drive.drive(-speed, 0);
	}
	
	public RobotDrive getRobotDrive() {
		return this.drive;
	}
	// sets motor speed to 0 on each motor

	/**
	 * Stops the wheels. Often a good idea.
	 * 
	 * @author Tom Sanford
	 */
	public void stop() {
		drive.arcadeDrive(0, 0);
	}

	public void turn(double value) {
		drive.arcadeDrive(0.0, value);
	}
	
	// Set the default command for a subsystem here.
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

}

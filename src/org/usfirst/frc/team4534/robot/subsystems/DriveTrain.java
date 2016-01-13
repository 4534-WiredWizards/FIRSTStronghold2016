package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private RobotDrive drive;
	
	// create instance of motor-configuration based robot drive
	public DriveTrain() {
		drive = new RobotDrive(0, 1);
	}
	
	// enable tank drive operation of the drivetrain
	public void tankDrive(Joystick joy) {
		drive.tankDrive(joy.getY(),joy.getRawAxis(4));
	}
	
	// sets motor speed to 0 on each motor
	public void stop() {
		drive.tankDrive(0, 0);
	}
	// Set the default command for a subsystem here.
	public void initDefaultCommand() {
		// setDefaultCommand(new MySpecialCommand());		
	}
}

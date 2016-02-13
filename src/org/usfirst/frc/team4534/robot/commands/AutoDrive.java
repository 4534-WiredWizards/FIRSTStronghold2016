package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	@SuppressWarnings("unused")
	private double duration;
	private double speed;
	private double rotate;
	
	
	/**
	 * Drives the robot using fixed values.
	 * @author Benjamin Davis
	 * @param duration Duration of driving, in seconds.
	 * @param speed Speed of driving: a positive value corresponds to forward motion, a negative value corresponds to backward motion. can be from -1 to 1.
	 * @param rotate Amount of rotation
	 */
	public AutoDrive(double duration, double speed, double rotate) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.duration = duration;
		this.speed = speed;
		this.rotate = rotate;
		setTimeout(duration);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		Robot.drivetrain.arcadeDrive(speed, rotate);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (isTimedOut());
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.straightDrive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

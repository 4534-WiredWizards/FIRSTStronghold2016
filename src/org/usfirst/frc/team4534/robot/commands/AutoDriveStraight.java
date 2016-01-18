package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoDriveStraight extends Command {
	private Timer timer;
	private double duration;
	private double speed;
	
	/**
	 * Drives the robot in a straight line.
	 * @author Benjamin Davis
	 * @param duration Duration of driving, in seconds.
	 * @param speed Speed of driving: a positive value corresponds to forward motion, a negative value corresponds to backward motion. can be from -1 to 1.
	 */
	public AutoDriveStraight(double duration, double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		this.duration = duration;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	/**
	 * Called repeatedly when this Command is scheduled to run
	 */
	protected void execute() {
		Robot.drivetrain.straightDrive(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (timer.hasPeriodPassed(duration));
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		timer.reset();
		Robot.drivetrain.straightDrive(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

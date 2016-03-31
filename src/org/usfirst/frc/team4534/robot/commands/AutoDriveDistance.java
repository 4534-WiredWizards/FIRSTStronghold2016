package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Drives the robot a distance, equal to the distance parameter, in inches.
 *@param distance in inches, how far the robot will travel.
 */
public class AutoDriveDistance extends Command {
private Encoder encoder;
private double distance;
    public AutoDriveDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.drivetrain);
    	this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	encoder = Robot.encoder;
    	encoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (encoder.getDistance() < distance) {
    		Robot.drivetrain.arcadeDrive(.7, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (encoder.getDistance() >= distance);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

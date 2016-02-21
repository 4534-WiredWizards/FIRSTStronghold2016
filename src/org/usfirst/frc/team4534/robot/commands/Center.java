package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Center extends Command {

	private double speed;
	
    public Center() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.jetsonvision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// set LEDs to signify
    	speed = -Robot.jetsonvision.getCurrentTuple().getCenter();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	speed = Robot.jetsonvision.getCurrentTuple().getCenter();
    	double workingSpeed = speed;
    	if(workingSpeed == -999) {
    		// didn't get a read
    		this.cancel();
    		return;
    	}
    	
    	// invert it to make it turning instructions for the robot
    	workingSpeed = -workingSpeed;
    	
    	if(workingSpeed < 0.3) workingSpeed = 0.3;
    	
    	Robot.drivetrain.turn(workingSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(speed) < 0.05 || Math.abs(speed) == -999;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToggleBrake extends Command {

    public ToggleBrake() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.lifter.getBrake() > .5) {
			Robot.lifter.setBrake(0);
		} else {
			Robot.lifter.setBrake(1);
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraight extends Command {
	
//	private boolean isExecuted = false;
	
    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
//    	System.out.println("create");
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
//    	System.out.println("initialize");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println("execute");
    	Robot.drivetrain.straightDrive(.4);
//    	isExecuted = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
//    	System.out.println("isFinished");
//        return isExecuted;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	System.out.println("end");
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
//    	System.out.println("interupted");
    	end();
    }
}

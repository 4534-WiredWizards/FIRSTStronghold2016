package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPortcullis extends CommandGroup {

    public AutoPortcullis() {
    	requires(Robot.drivetrain);
    	requires(Robot.armpneumatics);
    	
    	addParallel(new ArmsDown(0));
    	addParallel(new AutoDriveStraight(1, .4));
    	addSequential(new ArmsUp(2));
    	addSequential(new AutoDriveStraight(2, .4));// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Initiating AutoPortcullis");
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

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPortcullis extends CommandGroup {

    public AutoPortcullis() {
    	requires(Robot.drivetrain);
    	requires(Robot.armpneumatics);
    	System.out.println("Initiating AutoPortcullis");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addParallel(new ArmsDown(0));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	
    	if (Robot.armpneumatics.readLeft() != false || Robot.armpneumatics.readRight() != false){
    		addSequential(new ArmsDown(RobotMap.solenoidDelay));
    	}
    	
    	addSequential(new ArmsUp(RobotMap.solenoidDelay));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (!Robot.isAuto) {
    		Robot.arduinocomm.writeString("i");
    	}
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

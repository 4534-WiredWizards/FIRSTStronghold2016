package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightArmToggle extends Command {

    public RightArmToggle() {
    	requires(Robot.armpneumatics);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("RightArmToggle Called!");
    	if (Robot.armpneumatics.readRight() == true){
    		Robot.armpneumatics.retractRight();
    	} else if (Robot.armpneumatics.readRight() == false){
        	Robot.armpneumatics.extendRight();
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
    	System.out.println("RightArmToggle Finished!");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

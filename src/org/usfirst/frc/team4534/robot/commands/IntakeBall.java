package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBall extends Command {

    public IntakeBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballhandler);
    	setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arduinocomm.writeString("l");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(!Robot.ballhandler.getLowerHandlerLimit() && !Robot.ballhandler.getUpperHandlerLimit()) {
        	Robot.ballhandler.setIntake(.60);
        	Robot.ballhandler.setShooter(-0.2);
    	}
    	if(Robot.ballhandler.getLowerHandlerLimit() && !Robot.ballhandler.getUpperHandlerLimit()) {
    		// do nothing, we're ready
    	}
    	if(Robot.ballhandler.getUpperHandlerLimit()) {
    		// move it down until it no longer contacts
    		Robot.ballhandler.setIntake(-0.30);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.ballhandler.getLowerHandlerLimit() && !Robot.ballhandler.getUpperHandlerLimit()) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ballhandler.stop();
    	Robot.arduinocomm.writeString("v");
    	Robot.arduinocomm.writeString("i");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

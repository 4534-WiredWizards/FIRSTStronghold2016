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
    	arduinocomm.writeString("l");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballhandler.setIntake(.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.ballhandler.getHandlerLimit() || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ballhandler.stop();
    	arduinocomm.writeString("v");
    	arduinocomm.writeString("i");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

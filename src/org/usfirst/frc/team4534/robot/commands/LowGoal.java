package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowGoal extends Command {

    public LowGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballhandler);
    	setTimeout(2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballhandler.setIntake(-1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(!Robot.ballhandler.getLowerHandlerLimit() && !Robot.ballhandler.getUpperHandlerLimit()) {
    		Robot.arduinocomm.writeString("i");
    		Robot.arduinocomm.writeString("r");
        	if (Robot.allianceColor == DriverStation.Alliance.Blue) {
    			// In the blue alliance
    			System.out.print("BLUE alliance");
    			Robot.arduinocomm.writeString("n");
    		} else if (Robot.allianceColor == DriverStation.Alliance.Red) {
    			// In the red alliance
    			Robot.arduinocomm.writeString("r");
    			System.out.print("RED alliance");
    		}
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

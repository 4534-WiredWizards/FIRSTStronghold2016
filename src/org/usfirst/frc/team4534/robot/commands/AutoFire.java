package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *Fires the boulder by reversing the ball intake motor and 
 *pushing the boulder into the (spun up) shooter.
 */
public class AutoFire extends Command {

    public AutoFire() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballhandler);
    	setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arduinocomm.writeString("s");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballhandler.set(.80);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ballhandler.stop();
    	Robot.arduinocomm.writeString("i");
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

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

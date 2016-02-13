package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
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
    	if (Robot.armpneumatics.readRight() == DoubleSolenoid.Value.kForward){
    		Robot.armpneumatics.retractRight();
    	} else if (Robot.armpneumatics.readRight() == DoubleSolenoid.Value.kReverse){
        	Robot.armpneumatics.extendRight();
    	} else if (Robot.armpneumatics.readRight() == DoubleSolenoid.Value.kOff){
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveRotate extends Command {

	private double speed;
    public AutoDriveRotate(double duration, double speed) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
		this.speed = speed;
    	setTimeout(duration);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arduinocomm.writeString("c");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.arcadeDrive(0, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

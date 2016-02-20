package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArms extends Command {

	private double amount;
	private double duration;
	
	/**
	 * 
	 * @param speed
	 * @param duration
	 */
    public MoveArms(double speed, double duration){
    	requires(Robot.arms);
    	this.duration = duration;
    	this.amount = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(duration);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arms.move(amount);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut() || (amount > 0 ? Robot.arms.checkArmsUp() : Robot.arms.checkArmsDown());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arms.stopMovement();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAngle extends PIDCommand {

	private double targetAngle;
	
    public TurnAngle(double angle) {
    	super("TurnAngle", 1, 0.5, 0.1);
    	requires(Robot.drivetrain);
    	requires(Robot.gyroscope);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.targetAngle = Robot.gyroscope.getValue() + angle;
    	this.getPIDController().setSetpoint(this.targetAngle);
    	this.getPIDController().setAbsoluteTolerance(3);
    	//this.getPIDController().setContinuous();
    	this.getPIDController().setOutputRange(0, 0.4);
    	
    	//this.setTimeout(3);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return this.getPIDController().onTarget() || this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.gyroscope.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.drivetrain.turn(output);
		SmartDashboard.putNumber("PID Set", output);
	}
}

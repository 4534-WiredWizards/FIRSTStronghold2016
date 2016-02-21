package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDController.Tolerance;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends PIDCommand {

	private double targetAngle;
	
    public TurnToAngle(double angle) {
    	super("TurnAngle", 0.2, 0.01, 0);
    	requires(Robot.drivetrain);
    	requires(Robot.gyroscope);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//Robot.gyroscope.reset();
    	this.setInputRange(0.0, 360.0);
    	this.getPIDController().setContinuous();
    	this.targetAngle = angle;
    	System.out.println(angle);
    	System.out.println(targetAngle);
    	//System.out.println(this.targetAngle);
    	//this.getPIDController().setAbsoluteTolerance(3);
    	//this.getPIDController().setPercentTolerance(0.0000000001);
    	this.getPIDController().setOutputRange(-0.55, 0.55);
    	this.setSetpoint(targetAngle);
    	this.getPIDController().setToleranceBuffer(100);
    	//this.setTimeout(3);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	targetAngle = SmartDashboard.getNumber("TurnTo");
    	this.setSetpoint(targetAngle);
    	
    	System.out.println(this.getPIDController().getAvgError());
    	SmartDashboard.putNumber("Avg Err",this.getPIDController().getAvgError());
    	//System.out.println(this.getPIDController().onTarget());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean r = this.getPIDController().getAvgError() < 3;
    	//return r || this.isTimedOut();
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return Robot.gyroscope.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.drivetrain.turn(-output);
		SmartDashboard.putNumber("PID Set", -output);
	}
}

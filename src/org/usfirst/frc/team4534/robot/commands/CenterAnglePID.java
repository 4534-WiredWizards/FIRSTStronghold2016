package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterAnglePID extends PIDCommand {

	private final double TARGET_ANGLE = 0.0;
	private double[] angleBuffer;
	private int aBufferIndex;
	private final int A_BUFFER_SIZE = 20; // 1 indexed
	private double averageAngle;	
	private boolean fullAveraged;
	
    public CenterAnglePID() {
    	super("CenterAnglePID", 0.2, 0.01, 0);
    	requires(Robot.drivetrain);
    	requires(Robot.jetsonvision);
    	
    	//this.setTimeout(3);
    	this.fullAveraged = false;
    	
    	this.angleBuffer = new double[A_BUFFER_SIZE];
    	this.aBufferIndex = 0;
    	for(;aBufferIndex < A_BUFFER_SIZE;aBufferIndex++) {
    		this.angleBuffer[aBufferIndex] = 0;
    	}
    	aBufferIndex = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.jetsonvision.update();
    	Robot.jetsonvision.update();
    	this.setInputRange(-1.0, 1.0);
    	this.getPIDController().setOutputRange(-0.75, 0.75);
    	this.getPIDController().setToleranceBuffer(100);
    	this.setSetpoint(TARGET_ANGLE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.jetsonvision.update();
    	//TARGET_DISTANCE = SmartDashboard.getNumber("TurnTo");
    	
    	this.angleBuffer[aBufferIndex] = getPosition();
    	aBufferIndex++;
    	aBufferIndex %= 20;
    	
    	if(aBufferIndex == 19) this.fullAveraged = true;
    	
    	double sum = 0;
    	for(int i = 0; i < A_BUFFER_SIZE; i++) {
    		sum += angleBuffer[i];
    	}
    	this.averageAngle = sum / A_BUFFER_SIZE;
    	
    	//System.out.println(this.getPIDController().getAvgError());
    	SmartDashboard.putNumber("Avg Err",this.getPIDController().getAvgError());
    	//System.out.println(this.getPIDController().onTarget());
    	System.out.println(Math.abs(getPosition() - getSetpoint()));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean r = this.fullAveraged && Math.abs(this.averageAngle - getSetpoint()) < 1;
    	//return r || this.isTimedOut();
    	return r;
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
		Robot.jetsonvision.update();
		return Robot.jetsonvision.getCurrentTuple().getCenter();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.drivetrain.turn(output);
		SmartDashboard.putNumber("PID Set", output);
	}
}

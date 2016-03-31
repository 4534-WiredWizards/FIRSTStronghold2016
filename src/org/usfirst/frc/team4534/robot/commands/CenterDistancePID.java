package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDController.Tolerance;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterDistancePID extends PIDCommand {

	private final double TARGET_DISTANCE = 171.0;
	private double[] distanceBuffer;
	private int dBufferIndex;
	private final int D_BUFFER_SIZE = 20; // 1 indexed
	private double averageDistance;	
	private boolean fullAveraged;
	
    public CenterDistancePID() {
    	super("CenterDistancePID", 0.2, 0.01, 0);
    	requires(Robot.drivetrain);
    	requires(Robot.jetsonvision);
    	
    	//this.setTimeout(3);
    	this.fullAveraged = false;
    	
    	this.distanceBuffer = new double[D_BUFFER_SIZE];
    	this.dBufferIndex = 0;
    	for(;dBufferIndex < D_BUFFER_SIZE;dBufferIndex++) {
    		this.distanceBuffer[dBufferIndex] = 0;
    	}
    	dBufferIndex = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.jetsonvision.update();
    	Robot.jetsonvision.update();
    	this.setInputRange(0.0, 300.0);
    	this.getPIDController().setOutputRange(-0.75, 0.75);
    	this.getPIDController().setToleranceBuffer(100);
    	this.setSetpoint(TARGET_DISTANCE);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TARGET_DISTANCE = SmartDashboard.getNumber("TurnTo");
    	
    	this.distanceBuffer[dBufferIndex] = getPosition();
    	dBufferIndex++;
    	dBufferIndex %= 20;
    	
    	if(dBufferIndex == 19) this.fullAveraged = true;
    	
    	double sum = 0;
    	for(int i = 0; i < D_BUFFER_SIZE; i++) {
    		sum += distanceBuffer[i];
    	}
    	this.averageDistance = sum / D_BUFFER_SIZE;
    	
    	//System.out.println(this.getPIDController().getAvgError());
    	SmartDashboard.putNumber("Avg Err",this.getPIDController().getAvgError());
    	//System.out.println(this.getPIDController().onTarget());
    	System.out.println(Math.abs(getPosition() - getSetpoint()));

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean r = this.fullAveraged && Math.abs(this.averageDistance - getSetpoint()) < 1;
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
		return Robot.jetsonvision.getCurrentTuple().getDistance();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		Robot.drivetrain.arcadeDrive(-output,0.0);
		SmartDashboard.putNumber("PID Set", -output);
	}
}

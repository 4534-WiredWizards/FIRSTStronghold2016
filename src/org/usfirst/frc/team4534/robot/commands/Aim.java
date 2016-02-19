package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.subsystems.JetsonVision;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Aims the robot toward the nearest high goal.
 */


public class Aim extends Command {
	JetsonVision.VisionTuple visionTuple;
	double rotationTolerance = .1;
	double rotationAdjust = .35;
	double distanceTarget = 60;
	double distanceTolerance = 3;
	double distanceAdjust = .1;
	double rotate = 0;
	double move = 0;
	double timeOut = 6;
	boolean rotationOkay = false;
	boolean distanceOkay = false;
	
    public Aim() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.jetsonvision);
    	requires(Robot.drivetrain);
    	setTimeout(timeOut);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	visionTuple = Robot.jetsonvision.getCurrentTuple();
    	rotationOkay = false;
    	distanceOkay = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	visionTuple = Robot.jetsonvision.getCurrentTuple();
    	if (visionTuple.getAngle() > rotationTolerance || visionTuple.getAngle() < -rotationTolerance){
    		rotate = (visionTuple.getAngle() * rotationAdjust);
    	} else {
    		rotationOkay = true;
    		rotate = 0;
    	}
    	if (visionTuple.getDistance() > distanceTarget + distanceTolerance || visionTuple.getDistance() < distanceTarget - distanceTolerance){
    		move = ((visionTuple.getDistance() - distanceTarget) * distanceAdjust);
    	} else {
    		distanceOkay = true;
    		move = 0;
    	}
    	Robot.drivetrain.arcadeDrive(move, rotate);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (rotationOkay && distanceOkay) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

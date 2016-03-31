package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.ControlSystem;
import org.usfirst.frc.team4534.robot.ControlSystem.Button;
import org.usfirst.frc.team4534.robot.ControlSystem.ButtonLiteral;
import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CenterAngle extends Command {

	private double speed;

	public CenterAngle() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.jetsonvision);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// TODO: set LEDs to signify
		Robot.jetsonvision.update();
		speed = -Robot.jetsonvision.getCurrentTuple().getCenter();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.jetsonvision.update();
		speed = Robot.jetsonvision.getCurrentTuple().getCenter();
		SmartDashboard.putNumber("Center", speed);
		double workingSpeed = speed;
		if (workingSpeed == -999) {
			// didn't get a read
			this.cancel();
			return;
		}
		workingSpeed *= 0.85;
		if (Math.abs(workingSpeed) != workingSpeed) {
			workingSpeed -= 0.15;
		} else {
			workingSpeed += 0.15;
		}
		
		// invert it to make it turning instructions for the robot
		workingSpeed = -workingSpeed;

		// if(workingSpeed < 0.5) workingSpeed = 0.5;

		Robot.drivetrain.turn(workingSpeed);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(speed) < 0.005 || speed == -999;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

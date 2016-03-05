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
public class CenterDistance extends Command {

	private double difference;
	private final double TARGET = 178.0;

	public CenterDistance() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.jetsonvision);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// TODO: set LEDs to signify
		Robot.jetsonvision.update();
		difference = TARGET - Robot.jetsonvision.getCurrentTuple().getDistance();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.jetsonvision.update();
		difference = TARGET - Robot.jetsonvision.getCurrentTuple().getDistance();
		if(difference == TARGET - (-999)) {
			// didn't get a read
			this.cancel();
			return;
		}
		
		// now calculate the speed of movement, ranging from 1.0 to -1.0
		double speed = difference / 40.0;
		
		// constrain it from -1.0 to 1.0
		speed = Math.max(Math.min(speed, 1.0), -1.0);
		SmartDashboard.putNumber("CenterDistance", speed);
		
		speed *= 0.55;
		if (Math.abs(speed) != speed) {
			speed -= 0.45;
			// constrain it to 0.6
			speed = Math.max(speed, -0.6);
		} else {
			speed += 0.45;
			// constrain it to 0.6
			speed = Math.min(speed, 0.6);
		}
		
		// constrain it to 0.6
		speed = Math.min(speed, 0.6);
		
		// invert it to make it turning instructions for the robot
		//speed = -speed;

		// if(workingSpeed < 0.5) workingSpeed = 0.5;

		Robot.drivetrain.arcadeDrive(speed, 0.0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(difference) < 2 || (TARGET - (-999)) == difference;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

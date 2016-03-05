package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoChevalDeFrise extends CommandGroup {

	public AutoChevalDeFrise() {
		requires(Robot.drivetrain);
		requires(Robot.arms);
		System.out.println("Initiating AutoChevalDeFrise");

		if (Robot.isAuto) {
			//addParallel(new MoveArms(0.5, 1.5));
			addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
		}
		if (!Robot.arms.readLeft() || !Robot.arms.readRight()) {
			addSequential(new ExtendArms());
		}

		addSequential(new MoveArms(-0.5,1.5));
		addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
}

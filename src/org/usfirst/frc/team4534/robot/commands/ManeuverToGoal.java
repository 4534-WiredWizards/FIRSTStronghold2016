package org.usfirst.frc.team4534.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ManeuverToGoal extends CommandGroup {
	int startPosition;
	int goal;

	public ManeuverToGoal(int startPosition, int goal) {
		this.startPosition = startPosition;
		this.goal = goal;

		if (goal == 1) {
			switch (startPosition) {
				case 1:
					addSequential(new TurnToAngle(0));
					addSequential(new AutoDriveDistance(75.1));
					addSequential(new TurnToAngle(30));
					addSequential(new AutoDriveDistance(163.8));
					break;
				case 2:
					addSequential(new TurnToAngle(326));
					addSequential(new AutoDriveDistance(90));
					addSequential(new TurnToAngle(30));
					addSequential(new AutoDriveDistance(163.8));
					break;
				case 3:
					addSequential(new TurnToAngle(307));
					addSequential(new AutoDriveDistance(126));
					addSequential(new TurnToAngle(30));
					addSequential(new AutoDriveDistance(163.8));
					break;
				case 4:
					addSequential(new TurnToAngle(297));
					addSequential(new AutoDriveDistance(169));
					addSequential(new TurnToAngle(30));
					addSequential(new AutoDriveDistance(163.8));
					break;
				case 5:
					addSequential(new TurnToAngle(290));
					addSequential(new AutoDriveDistance(216));
					addSequential(new TurnToAngle(30));
					addSequential(new AutoDriveDistance(163.8));
					break;
			}
			

		} else if (goal == 2) {
			switch (startPosition) {
			case 1:
				addSequential(new TurnToAngle(0));
				addSequential(new AutoDriveDistance(75.1));
				addSequential(new TurnToAngle(30));
				addSequential(new AutoDriveDistance(163.8));
				break;
			case 2:
				addSequential(new TurnToAngle(326));
				addSequential(new AutoDriveDistance(90));
				addSequential(new TurnToAngle(30));
				addSequential(new AutoDriveDistance(163.8));
				break;
			case 3:
				addSequential(new TurnToAngle(307));
				addSequential(new AutoDriveDistance(126));
				addSequential(new TurnToAngle(30));
				addSequential(new AutoDriveDistance(163.8));
				break;
			case 4:
				addSequential(new TurnToAngle(297));
				addSequential(new AutoDriveDistance(169));
				addSequential(new TurnToAngle(30));
				addSequential(new AutoDriveDistance(163.8));
				break;
			case 5:
				addSequential(new TurnToAngle(290));
				addSequential(new AutoDriveDistance(216));
				addSequential(new TurnToAngle(30));
				addSequential(new AutoDriveDistance(163.8));
				break;
		}
		
		} else if (goal == 0) {
			addSequential(new AutoDriveDistance(6));
		}

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}

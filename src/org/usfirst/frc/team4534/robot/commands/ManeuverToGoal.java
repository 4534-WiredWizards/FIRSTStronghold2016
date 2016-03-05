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

		if (goal != 0) {
			if (goal == 1) {
				addSequential(new AutoDrive(startPosition-1, .5, (startPosition-1)*-1));
			}
			if (goal == 2) {
				addSequential(new AutoDrive(startPosition-3.5, .5, (startPosition-3.5) * -.1));
			}
			if (goal == 3) {
				addSequential(new AutoDrive(6-startPosition, .5, (6-startPosition)*.1));
			}
			

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

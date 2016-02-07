package org.usfirst.frc.team4534.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoughTerrain extends CommandGroup {

	public AutoRoughTerrain() {

		System.out.println("Initiating AutoRoughTerrain");
		
		addSequential(new AutoDriveStraight(3, .2));
		addSequential(new AutoDriveRotate(1, .2));
		addSequential(new AutoDriveStraight(1, .2));
		addSequential(new AutoDriveRotate(1, -.2));

	}
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRoughTerrain extends CommandGroup {

	public AutoRoughTerrain() {

		System.out.println("Initiating AutoRoughTerrain");
		Robot.arduinocomm.writeString("c");
		
    	requires(Robot.drivetrain);
    	
    	if (Robot.isAuto){
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	addSequential(new AutoDriveStraight(3, .4));

	}
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRockWall extends CommandGroup{

    public AutoRockWall() {
    	requires(Robot.drivetrain);
    	System.out.println("Initiating AutoRockWall");
		
    	if (Robot.isAuto){
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	addSequential(new AutoDriveStraight(3, .4));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}

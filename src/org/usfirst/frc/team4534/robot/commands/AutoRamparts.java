package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRamparts extends CommandGroup {

    public AutoRamparts() {
    	requires(Robot.drivetrain);
    	System.out.println("Initiating AutoRamparts");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	addSequential(new AutoDriveStraight(3, .4));
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}

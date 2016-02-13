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
    	requires(Robot.armpneumatics);
    	System.out.println("Initiating AutoChevalDeFrise");
		
    	if (Robot.isAuto){
    		addParallel(new ArmsUp(0));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	if (Robot.armpneumatics.readLeft() != true ||
    	  Robot.armpneumatics.readRight() != true){
    	    addSequential(new ArmsUp(RobotMap.solenoidDelay));
    	}
    	addSequential(new ArmsDown(RobotMap.solenoidDelay));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}

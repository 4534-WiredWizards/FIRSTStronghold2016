package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDrawbridge extends CommandGroup {

    public AutoDrawbridge() {
    	requires(Robot.drivetrain);
    	requires(Robot.armpneumatics);
    	System.out.println("Initiating AutoDrawbridge");
    	Robot.arduinocomm.writeString("c");
		
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (Robot.isAuto){
    		addParallel(new ArmsUp(0));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	if (Robot.armpneumatics.readLeft() != true || Robot.armpneumatics.readRight() != true){
    	    addSequential(new ArmsUp(RobotMap.solenoidDelay));
    	}
    	addParallel(new ArmsDown(0));
    	addParallel(new AutoDriveStraight(.5, -.4));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));
    }
}
package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoPortcullis extends CommandGroup {

    public AutoPortcullis() {
    	requires(Robot.drivetrain);
    	requires(Robot.arms);
    	System.out.println("Initiating AutoPortcullis");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addParallel(new MoveArms(-0.5,1.5));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	
    	if (Robot.arms.readLeft() != false || Robot.arms.readRight() != false){
    		addSequential(new ExtendArms());
    	}
    	
    	addSequential(new MoveArms(0.75,2));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));// Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (!Robot.isAuto) {
    		Robot.arduinocomm.writeString("i");
    	}
    }

}

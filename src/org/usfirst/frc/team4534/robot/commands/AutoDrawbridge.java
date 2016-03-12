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
    	requires(Robot.arms);
    	System.out.println("Initiating AutoDrawbridge");
    	Robot.arduinocomm.writeString("c");
		
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (Robot.isAuto){
    		addParallel(new MoveArms(0.5,1.5));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	if (Robot.arms.readLeft() != true || Robot.arms.readRight() != true){
    	    addSequential(new ExtendArms());
    	}
    	addParallel(new MoveArms(-0.35, 1.5));
    	addParallel(new AutoDriveStraight(.5, -.4));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	if (!Robot.isAuto) {
    		Robot.arduinocomm.writeString("i");
    	}
    }
}
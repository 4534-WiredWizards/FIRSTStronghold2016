package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;
import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSallyPort extends CommandGroup {

    public AutoSallyPort() {
    	requires(Robot.drivetrain);
    	requires(Robot.arms);
    	System.out.println("Initiating AutoSallyPort");
		
    	if (Robot.isAuto){
    		addParallel(new MoveArms(0.5,1.5));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	if (Robot.arms.readLeft() != true || Robot.arms.readRight() != true){
    	    addSequential(new ExtendArms());
    	}
    	addParallel(new MoveArms(-0.5, 1));
    	addSequential(new AutoDriveStraight(.25, -.4));
    	addSequential(new AutoDriveRotate(1, -.4));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, -.4));
    	addSequential(new AutoDriveRotate(1, .4));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}

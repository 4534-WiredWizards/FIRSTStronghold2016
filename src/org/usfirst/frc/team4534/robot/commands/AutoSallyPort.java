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
    	requires(Robot.armpneumatics);
    	System.out.println("Initiating AutoSallyPort");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addParallel(new ArmsUp(0));
    		addParallel(new AutoDriveStraight(RobotMap.approachDelay, .4));
    	}
    	if (Robot.armpneumatics.readLeft() != true || Robot.armpneumatics.readRight() != true){
    	    addSequential(new ArmsUp(RobotMap.solenoidDelay));
    	}
    	addParallel(new RightArmToggle());
    	addSequential(new AutoDriveStraight(.25, -.4));
    	addSequential(new AutoDriveRotate(1, -.4));
    	addSequential(new AutoDriveStraight(RobotMap.approachDelay, -.4));
    	addSequential(new AutoDriveRotate(1, .4));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
}

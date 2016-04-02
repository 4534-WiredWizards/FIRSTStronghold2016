package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLowBar extends CommandGroup {

    public AutoLowBar() {
    	requires(Robot.drivetrain);
    	System.out.println("Initiating AutoMoat");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addSequential(new AutoDriveDistance(72));
    	}
    	addSequential(new AutoDriveDistance(72, .5));
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (!Robot.isAuto) {
    		Robot.arduinocomm.writeString("i");
    	}
    }

}

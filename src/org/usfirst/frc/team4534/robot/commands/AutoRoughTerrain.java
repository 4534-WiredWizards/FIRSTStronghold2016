package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoughTerrain extends CommandGroup {
    
    public  AutoRoughTerrain() {
    	requires(Robot.drivetrain);
    	System.out.println("Initiating AutoRoughTerrain");
    	Robot.arduinocomm.writeString("c");
		
    	if (Robot.isAuto){
    		addParallel(new AutoDriveDistance(72));
    	}
    	addSequential(new AutoDriveStraight(3, .4));
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (!Robot.isAuto) {
    		Robot.arduinocomm.writeString("i");
    	}
    }
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
}

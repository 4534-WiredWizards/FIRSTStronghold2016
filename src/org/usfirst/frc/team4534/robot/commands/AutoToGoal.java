package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoToGoal extends CommandGroup{
	private Encoder encoder;
	public AutoToGoal(int defense){
		encoder = Robot.rightEncoder;
		requires(Robot.drivetrain);
		
			addSequential(new AutoDriveDistance(60));
			switch(defense){
			case 1:
				addSequential(new TurnToAngle(90));
				addSequential(new AutoDriveDistance(147));
				break;
			case 2: 
				addSequential(new TurnToAngle(90));
				addSequential(new AutoDriveDistance(97));
				break;
			case 3:
				addSequential(new TurnToAngle(90));
				addSequential(new AutoDriveDistance(47));
				break;
			case 4:
				addSequential(new TurnToAngle(270));
				addSequential(new AutoDriveDistance(47));
				break;
			case 5: 
				addSequential(new TurnToAngle(270));
				addSequential(new AutoDriveDistance(97));
			}
			
			addSequential(new TurnToAngle(0));
	}
	

}

package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ControlSystem extends Subsystem {
	
	public final RobotDrive robotDrive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
	
	public ControlSystem(int startingMap) {
		
	}

	@Override
	protected void initDefaultCommand() {
		
	} 
	
	//speedfinal = ((speedi / (prec + 1)) * (fast +1)) / 2
	
}

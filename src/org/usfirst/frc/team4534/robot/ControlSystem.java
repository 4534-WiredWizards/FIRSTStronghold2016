package org.usfirst.frc.team4534.robot;

import edu.wpi.first.wpilibj.RobotDrive;

public class ControlSystem {
	
	public final RobotDrive robotDrive = new RobotDrive(RobotMap.leftMotor, RobotMap.rightMotor);
	
	public ControlSystem(int startingMap) {
		
	}
	
	//speedfinal = ((speedi / (prec + 1)) * (fast +1)) / 2
	
}

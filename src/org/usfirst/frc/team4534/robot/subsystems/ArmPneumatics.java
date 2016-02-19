package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmPneumatics extends Subsystem {

	Solenoid pistonLeft;
	Solenoid pistonRight;
	
	public ArmPneumatics(){
		pistonLeft = new Solenoid(RobotMap.PCM, RobotMap.leftArm);
		pistonRight = new Solenoid(RobotMap.PCM, RobotMap.rightArm);
		
	}
	
	protected void initDefaultCommand() {
			
	}
	
	public void extendLeft(){
		pistonLeft.set(true);
	}
	
	public void extendRight(){
		pistonRight.set(true);
	}
	
	public void retractLeft(){
		pistonLeft.set(false);
	}
	
	public void retractRight(){
		pistonRight.set(false);
	}
	
	
	
	public boolean readLeft() {
		return pistonLeft.get();
	}
	
	public boolean readRight() {
		return pistonRight.get();
	}

}

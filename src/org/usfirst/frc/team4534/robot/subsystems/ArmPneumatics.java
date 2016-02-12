package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmPneumatics extends Subsystem {

	DoubleSolenoid pistonLeft;
	DoubleSolenoid pistonRight;
	
	public ArmPneumatics(){
		pistonLeft = new DoubleSolenoid(RobotMap.PCM, RobotMap.leftArmForward, RobotMap.leftArmBackward);
		pistonRight = new DoubleSolenoid(RobotMap.PCM, RobotMap.rightArmForward, RobotMap.rightArmBackward);
		
	}
	
	protected void initDefaultCommand() {
			
	}
	
	public void extendLeft(){
		pistonLeft.set(DoubleSolenoid.Value.kForward);
	}
	
	public void extendRight(){
		pistonRight.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retractLeft(){
		pistonLeft.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void retractRight(){
		pistonRight.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void offLeft(){
		pistonLeft.set(DoubleSolenoid.Value.kOff);
	}
	public void offRight(){
		pistonRight.set(DoubleSolenoid.Value.kOff);
	}
	
	public void offBoth(){
		pistonLeft.set(DoubleSolenoid.Value.kOff);
		pistonRight.set(DoubleSolenoid.Value.kOff);

	}

}

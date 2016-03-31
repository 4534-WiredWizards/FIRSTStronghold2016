package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Arms extends Subsystem {

	Solenoid pistonLeft;
	Solenoid pistonRight;
	Victor armMotor;
	DigitalInput lowerLimit;
	DigitalInput upperLimit;
	
	public enum ArmSide {
		LEFT,
		RIGHT
	}
	
	public Arms(){
		pistonLeft = new Solenoid(RobotMap.PCM, RobotMap.leftArm);
		pistonRight = new Solenoid(RobotMap.PCM, RobotMap.rightArm);
		armMotor = new Victor(RobotMap.armMotor);
		lowerLimit = new DigitalInput(RobotMap.armDownLimit);
		upperLimit = new DigitalInput(RobotMap.armUpLimit);
		LiveWindow.addActuator("Arms", "Left Piston", pistonLeft);
		LiveWindow.addActuator("Arms", "Right Piston", pistonRight);
		LiveWindow.addActuator("Arms", "Arm Motor", armMotor);
		LiveWindow.addSensor("Arms", "Lower Limit Switch", lowerLimit);
		LiveWindow.addSensor("Arms", "Upper Limit Switch", upperLimit);
	}
	
	protected void initDefaultCommand() {
//		setDefaultCommand(new MoveArmsWithJoystick());
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
	
	public boolean checkArmsUp() {
		return upperLimit.get();
	}
	
	public boolean checkArmsDown() {
		return lowerLimit.get();
	}

	public void move(double speed) {
		armMotor.set(speed);
	}
	
	public void stopMovement() {
		armMotor.set(0);
	}
	
}

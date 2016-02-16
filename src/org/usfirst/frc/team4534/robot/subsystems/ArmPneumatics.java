package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmPneumatics extends Subsystem {
	Solenoid piston1;
	Solenoid piston2;
	
	public ArmPneumatics(){
		piston1 = new Solenoid(0);
		piston2 = new Solenoid(1);
		
	}
	
	protected void initDefaultCommand() {
			
	}
	
	public void extend1(){
		piston1.set(true);
	}
	
	public void extend2(){
		piston2.set(true);
	}
	
	public void retract1(){
		piston1.set(false);
	}
	
	public void retract2(){
		piston2.set(false);
	}

}

package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmPneumatics extends Subsystem {

	DoubleSolenoid piston1;
	DoubleSolenoid piston2;
	
	public ArmPneumatics(){
		piston1 = new DoubleSolenoid(1, 2, 3);
		piston2 = new DoubleSolenoid(1, 4, 5);
		
	}
	
	protected void initDefaultCommand() {
			
	}
	
	public void extend1(){
		piston1.set(DoubleSolenoid.Value.kForward);
	}
	
	public void extend2(){
		piston2.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retract1(){
		piston1.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void retract2(){
		piston2.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void off1(){
		piston1.set(DoubleSolenoid.Value.kOff);
	}
	public void off2(){
		piston2.set(DoubleSolenoid.Value.kOff);
	}
	
	public void offBoth(){
		piston1.set(DoubleSolenoid.Value.kOff);
		piston2.set(DoubleSolenoid.Value.kOff);

	}

}

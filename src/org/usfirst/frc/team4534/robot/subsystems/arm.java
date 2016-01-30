package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
/**
 *Manipulates the arm
 *@author Charlotte
 */
public class arm extends Subsystem {
    private DoubleSolenoid brace;
    private DoubleSolenoid main;
    public arm(){
    	brace = new DoubleSolenoid(RobotMap.braceForward, RobotMap.braceReverse);
    	main = new DoubleSolenoid(RobotMap.mainForward, RobotMap.mainReverse);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
  
    	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


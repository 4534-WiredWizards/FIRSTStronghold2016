package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4534.robot.RobotMap;
//import org.usfirst.frc.team4534.robot.commands.Lift;

/**
 *
 */
/*
public class Lifter extends Subsystem {
	private Talon lifter;
	private Servo brake;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Lifter () {
		lifter = new Talon(RobotMap.lifterMotor);
		brake = new Servo(RobotMap.lifterServo);
	}
	public void setLifter(double speed) {
		lifter.set(speed);
	}
	public void stopLifter() {
		lifter.set(0);
	}
	public void toggleBrake() {
		if (brake.get() > .5) {
			brake.set(0);
		} else {
			brake.set(1);
		}
	}
	public double getBrake() {
		return brake.get();
	}
	
	public void setBrake(double position) {
		brake.set(position);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new Lift());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
*/

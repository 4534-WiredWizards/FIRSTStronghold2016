package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4534.robot.RobotMap;
import org.usfirst.frc.team4534.robot.commands.BIStop;

import edu.wpi.first.wpilibj.TalonSRX;
/**
 *Ball Intake Device: use this subsystem to manipulate the ball intake device.
 *
 */
public class BallIntake extends Subsystem {
    
	private TalonSRX ballIntake;
	// Ball Intake may be abbreviated BI.
	public BallIntake(){
		ballIntake = new TalonSRX(RobotMap.ballIntake);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/**
	 * Use this to set the motor speed for the BI motor.
	 * @param speed A value between 1 and -1 that determines how fast the motor will run.
	 */
	public void set(double speed){
		ballIntake.set(speed);
	}
	public void stop() {
		ballIntake.set(0);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new BIStop());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


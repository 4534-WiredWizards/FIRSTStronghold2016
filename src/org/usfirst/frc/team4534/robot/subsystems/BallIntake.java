package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
/**
 *Ball Intake Device: use this subsystem to manipulate the ball intake device.
 *
 */
public class BallIntake extends Subsystem {
    
	private TalonSRX ballIntake;
	
	public BallIntake(){
		ballIntake = new TalonSRX(RobotMap.ballIntake);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void set(double speed){
		ballIntake.set(speed);
	}
	public void stop() {
		ballIntake.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


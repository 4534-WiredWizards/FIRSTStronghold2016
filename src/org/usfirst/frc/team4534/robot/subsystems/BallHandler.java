package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team4534.robot.RobotMap;
import org.usfirst.frc.team4534.robot.commands.BallHandlerStop;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;
/**
 *Ball Handling Device: use this subsystem to manipulate the ball handler.
 *Includes Talon SRX for Shooter, Victor 888 for Intake, and limit switch for limit...
 *
 */
public class BallHandler extends Subsystem {
    
	private Victor ballIntake;
	private TalonSRX shooter;
	private DigitalInput ballHandlerLimit;
	// Ball Handler may be referred to as 'Handler'.
	public BallHandler(){
		ballIntake = new Victor(RobotMap.ballIntake);
		shooter = new TalonSRX(RobotMap.shooter);
		ballHandlerLimit = new DigitalInput(RobotMap.ballHandlerLimit);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/**
	 * Use this to set the motor speed for the BI motor.
	 * @param intakeSpeed A value between 1 and -1 that determines how fast the Intake motor will run.
	 * @param shooterSpeed A value between 1 and -1 sets how fast the Shooter motor will run
	 */
	public void set(double intakeSpeed, double shooterSpeed){
		ballIntake.set(intakeSpeed);
		shooter.set(shooterSpeed);
	}
	public void stop() {
		ballIntake.set(0);
		shooter.set(0);
	}
	public boolean getHandlerLimit(){
		return ballHandlerLimit.get();
	}
    public void initDefaultCommand() {
    	setDefaultCommand(new BallHandlerStop());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


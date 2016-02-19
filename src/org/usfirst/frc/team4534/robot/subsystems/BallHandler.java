package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team4534.robot.RobotMap;
import org.usfirst.frc.team4534.robot.commands.BallHandlerStop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
/**
 *Ball Handling Device: use this subsystem to manipulate the ball handler.
 *Includes Talon SRX for Shooter, Victor 888 for Intake, and limit switch for limit...
 *
 */
public class BallHandler extends Subsystem {
    
	private Victor ballIntake;
	private CANTalon shooter;
	private CANTalon shooter2;
	
	private DigitalInput ballHandlerLimit;
	// Ball Handler may be referred to as 'Handler'.
	public BallHandler(){
		ballIntake = new Victor(RobotMap.ballIntake);
		shooter = new CANTalon(RobotMap.shooter);
		shooter2 = new CANTalon(RobotMap.shooter2);
		ballHandlerLimit = new DigitalInput(RobotMap.ballHandlerLimit);
		LiveWindow.addActuator("BallHandler", "1", shooter);
		LiveWindow.addActuator("BallHandler", "2", shooter2);
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	/**
	 * Use this to set the motor speed for the BI motor.
	 * @param intakeSpeed A value between 1 and -1 that determines how fast the Intake motor will run.
	 * @param shooterSpeed A value between 1 and -1 sets how fast the Shooter motor will run
	 */
	public void set(double intakeSpeed, double shooterSpeed){
		ballIntake.set(-intakeSpeed);
		shooter.set(-shooterSpeed);
		shooter2.set(shooterSpeed);
	}
	/**
	 * Sets the shooter motors
	 * @param rate How fast? -1 to 1
	 */
	public void setShooter(double rate){
		shooter.set(-rate);
		shooter2.set(rate);
		
	}
	/**
	 * Sets the intake motor speed.
	 * @param rate How fast? -1 to 1
	 */
	public void setIntake(double rate){
		ballIntake.set(-rate);
	}
	public void stop() {
		ballIntake.set(0);
		shooter.set(-0);
		shooter2.set(0);
		
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


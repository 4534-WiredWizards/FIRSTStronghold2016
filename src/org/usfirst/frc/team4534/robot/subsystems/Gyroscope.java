package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class Gyroscope extends Subsystem implements PIDSource {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private ADXRS450_Gyro gyro;
	
	public Gyroscope() {
		this.gyro = new ADXRS450_Gyro();
		this.gyro.setPIDSourceType(PIDSourceType.kDisplacement);
		LiveWindow.addSensor("Gyroscope", "Gyro", gyro);
	}
	
	public void reset() {
		this.gyro.reset();
	}
	
	public double getValue() {
		return this.gyro.getAngle();
	}
	
	public double pidGet() {
		return this.gyro.pidGet();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		this.gyro.setPIDSourceType(PIDSourceType.kDisplacement);
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return this.gyro.getPIDSourceType();
	}
}


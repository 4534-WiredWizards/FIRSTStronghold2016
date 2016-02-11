package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveEncoder extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public enum EncoderSide {
		LEFT(0),
		RIGHT(1);
		
		public int value;
		
		private EncoderSide(int v) {
			this.value = v;
		}
	}
	
	private Encoder encoder;
	
	public DriveEncoder(RobotDrive drive, DriveEncoder.EncoderSide side) {
		super(1,0.1,0.01);
		
		// make the encoder
		switch(side) {
			case LEFT:
				encoder = new Encoder(RobotMap.leftEncoderA,RobotMap.leftEncoderB,RobotMap.leftEncoderX);
				break;
			case RIGHT:
				encoder = new Encoder(RobotMap.rightEncoderA,RobotMap.rightEncoderB,RobotMap.rightEncoderX);
				break;
		}
		
		encoder.setDistancePerPulse((1.0/2048/2)*28);

		// set the PID tolerance to say... eh, 200 cycles
		setAbsoluteTolerance(200);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static int convertInchesToPulses(double inches) {
    	return (int) (inches / ((1.0/2048/2)*28));
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		
	}
}


package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class JetsonVision extends Subsystem {
    
	public JetsonVision.VisionTuple visionTuple;
	
	public JetsonVision() {
		// instantiate with dummy data for now
		this.visionTuple = new JetsonVision.VisionTuple(5, -10.0);
	}
	
	public class VisionTuple {
		public double getDistance() {
			return distance;
		}



		public void setDistance(double distance) {
			this.distance = distance;
		}



		public double getAngle() {
			return angle;
		}



		public void setAngle(double angle) {
			this.angle = angle;
		}



		private double distance;
		private double angle;
		
		public VisionTuple(double x, double theta) {
			this.distance = x;
			this.angle = theta;
		}
		
		
		
		public String toString() {
			return "("+this.distance+","+this.angle+")";
		}
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public JetsonVision.VisionTuple getCurrentTuple() {
		return this.visionTuple;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


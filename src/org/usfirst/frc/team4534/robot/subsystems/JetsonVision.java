package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 */
public class JetsonVision extends Subsystem implements LiveWindowSendable {

	public JetsonVision.VisionTuple visionTuple;
	private ITable table;
	private NetworkTable visionTable;

	public JetsonVision() {
		// instantiate with dummy data for now
		this.visionTuple = new JetsonVision.VisionTuple(-999,-999);
		this.visionTable = NetworkTable.getTable("vision");
		
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
			return "(" + this.distance + "," + this.angle + ")";
		}
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public JetsonVision.VisionTuple getCurrentTuple() {
		return this.visionTuple;
	}

	public void update() {
		System.out.println("Update Called");
		
		this.visionTuple.setAngle(visionTable.getNumber("angle",-999));
		this.visionTuple.setDistance(visionTable.getNumber("distance",-999));
		
		System.out.println("New vision tuple: "+this.visionTuple.toString());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void initTable(ITable subtable) {
		table = subtable;
		updateTable();
	}

	public ITable getTable() {
		return table;
	}

	public void updateTable() {
		if (table != null) {
			table.putString("Current Tuple", this.visionTuple.toString());
		}
	}

	@Override
	public void startLiveWindowMode() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stopLiveWindowMode() {
		// TODO Auto-generated method stub

	}
}

package org.usfirst.frc.team4534.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

/**
 *
 */
public class JetsonVision extends Subsystem implements LiveWindowSendable {

	private static final int CACHE_MISSED_NUM = 3;
	public JetsonVision.VisionTuple visionTuple;
	private ITable table;
	private NetworkTable visionTable;
	private int missedCount = 0;

	public JetsonVision() {
		// instantiate with dummy data for now
		this.visionTuple = new JetsonVision.VisionTuple(-999, -999, -999);
		this.visionTable = NetworkTable.getTable("vision");
		LiveWindow.addSensor("JetsonVision","Jetson",this);

	}

	public class VisionTuple {
		public double getDistance() {
			return distance;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public double getCenter() {
			return center;
		}

		public void setCenter(double center) {
			this.center = center;
		}

		public double getAngle() {
			return angle;
		}

		public void setAngle(double angle) {
			this.angle = angle;
		}

		private double distance;
		private double angle;
		private double center;

		public VisionTuple(double x, double theta, double center) {
			this.distance = x;
			this.angle = theta;
			this.center = center;
		}

		public String toString() {
			return "(" + this.distance + "," + this.angle + "," + this.center + ")";
		}
		
		public boolean isDefault() {
			return this.distance == -999 && this.angle == -999 && this.center == -999;
		}
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public JetsonVision.VisionTuple getCurrentTuple() {
		return this.visionTuple;
	}

	public void update() {
		System.out.println("Update Called");

		JetsonVision.VisionTuple newTuple = new JetsonVision.VisionTuple(-999, -999, -999);
		
		newTuple.setAngle(visionTable.getNumber("angle", -999));
		newTuple.setDistance(visionTable.getNumber("distance", -999));
		newTuple.setCenter(visionTable.getNumber("center",-999));
		
		if(newTuple.isDefault()) {
			missedCount++;
		} else {
			this.visionTuple = newTuple;
		}
		
		if(missedCount > CACHE_MISSED_NUM) {
			missedCount = 0;
			this.visionTuple = newTuple;
		}

		System.out.println("New vision tuple: " + this.visionTuple.toString());
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

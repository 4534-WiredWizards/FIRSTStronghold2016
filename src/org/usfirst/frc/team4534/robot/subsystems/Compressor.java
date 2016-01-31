package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Compressor extends Subsystem {

	AnalogInput pressureSensor;
	Compressor compressor;

	private static final double maxPressure = 3;

	public Compressor() {
		pressureSensor = new AnalogInput(4);
		compressor = new Compressor();
	}

	protected void initDefaultCommand() {

	}

	public void start() {
		compressor.start();

	}

	public boolean isPressurized() {
		if (Robot.isReal()) {
			return maxPressure <= pressureSensor.getVoltage();
		} else {
			return true;
		}
	}
}

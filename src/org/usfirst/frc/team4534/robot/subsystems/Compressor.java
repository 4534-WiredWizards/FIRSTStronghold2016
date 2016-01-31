package org.usfirst.frc.team4534.robot.subsystems;

import org.usfirst.frc.team4534.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Compressor extends Subsystem {

	AnalogInput pressureSensor;
	Compressor compressor;

	private static final double maxPressure = 3;

	public Compressor() {
		pressureSensor = new AnalogInput(RobotMap.compressorAnalogPort);
		compressor = new Compressor();
	}

	protected void initDefaultCommand() {

	}

	public void start() {
		compressor.start();

	}

	public boolean isPressurized() {
		return maxPressure <= pressureSensor.getVoltage();
	}
}

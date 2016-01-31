package org.usfirst.frc.team4534.robot;

import java.util.LinkedList;

import org.usfirst.frc.team4534.robot.commands.AutoDriveRotate;
import org.usfirst.frc.team4534.robot.commands.AutoDriveStraight;
import org.usfirst.frc.team4534.robot.commands.DriveStop;
import org.usfirst.frc.team4534.robot.subsystems.BallHandler;
import org.usfirst.frc.team4534.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4534.robot.util.MillisecondTimer;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public static DriveTrain drivetrain;
	// Command auto;
	CommandGroup autonomousCommands;
	public static BallHandler ballhandler;
	public static BuiltInAccelerometer accelerometer;

	public LinkedList<SendableChooser> steps = new LinkedList<SendableChooser>();
	private static int numSteps = 5; //How many steps we will have in the auto routine
	
	// public SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("robotInit");

		drivetrain = new DriveTrain();
		ballhandler = new BallHandler();
		oi = new OI();
		accelerometer = new BuiltInAccelerometer();
		//step1 = new SendableChooser();
		//step1.addDefault("Wait", new DriveStop());
		//step1.addObject("Straight One Second", new AutoDriveStraight(1, .35));
		//step1.addObject("Turn 1 Second", new AutoDriveRotate(1, .35));
		//SmartDashboard.putData(step1.toString() + "choice:", step1);
		if (steps.isEmpty()){
		for(int i = 0; i < numSteps; i++){
			steps.add(new SendableChooser());
			System.out.println("Made Sendable Chooser #" + i);
		}
		}

		for (int q = 0; q < steps.size(); q++) { // This loop adds functions to
													// each Auto Chooser.
			steps.get(q).addDefault("Wait", new DriveStop());
			steps.get(q).addObject("Straight One Second", new AutoDriveStraight(1, .35));
			steps.get(q).addObject("Turn 1 Second", new AutoDriveRotate(1, .35));
			SmartDashboard.putData(steps.get(q).toString() + "choice:", steps.get(q));
			System.out.println("Put #" + q + " Data to SmartDashboard");
		}

		/*
		for (SendableChooser autoSteps : steps) {         //This was a
			autoSteps.addDefault("Wait", new DriveStop());
			autoSteps.addObject("Straight One Second", new AutoDriveStraight(1, .35));
			autoSteps.addObject("Turn 1 Second", new AutoDriveRotate(1http://stackoverflow.com/questions/287903/enums-in-javascript, .35));
			SmartDashboard.putData(autoSteps.toString() + "choice:", autoSteps);
		}
		*/
		// autoChooserSetup(step1);
		// autoChooserSetup(step2);
		// autoChooserSetup(step3);
		// autoChooserSetup(step4);
		// autoChooserSetup(step5);

		ControlSystem.init();
		ControlSystem.rumbleTimeSet(1);

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(ballhandler);
		// SmartDashboard.putData((NamedSendable) oi);

		// instantiate the command used for the autonomous period
		autonomousCommands = new CommandGroup();

		// autoChooser = new SendableChooser();
		// autoChooser.addObject("Drive Straight", new Autonomous());

		// SmartDashboard.putData("Auto Mode", autoChooser);
		SmartDashboard.putData(Scheduler.getInstance());
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (autonomousCommands != null) {
			if (autonomousCommands.isRunning()) {
				autonomousCommands.cancel();
			}
		}
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommands != null) {
			if (autonomousCommands.isRunning()) {
				autonomousCommands.cancel();
			}
		}
		for (int e = 0; e < steps.size(); e++) {
			autonomousCommands.addSequential((Command) steps.get(e).getSelected());
			System.out.println("Command #" + e + " Added: " + ((Command) steps.get(e).getSelected()).getName());
		}

		// auto = (Command) step1.getSelected();
		// autonomousCommands.addSequential((Command) step1.getSelected());
		// autonomousCommands.addSequential((Command) step2.getSelected());
		// autonomousCommands.addSequential((Command) step3.getSelected());
		// autonomousCommands.addSequential((Command) step4.getSelected());
		// autonomousCommands.addSequential((Command) step5.getSelected());

		if (autonomousCommands != null) {
			// autonomousCommand = (Command) autoChooser.getSelected();
			autonomousCommands.start();
			System.out.println("Auto Started!");
		if(accelerometer.getZ() >= 2){
			autonomousCommands.cancel();
			System.out.println("Accelometer value greater than 2.");
		}
		
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommands != null) {
			//while(!autonomousCommands.isCanceled()){
				autonomousCommands.cancel();
			//}
		}
		System.out.println("Beginning Teleop!");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		if (autonomousCommands != null) {
			//while(!autonomousCommands.isCanceled()){
				autonomousCommands.cancel();
			//}
			}
		//auto.cancel();
		System.out.println("DISABLED!");

	}

	/**
	 * This function is called periodically during operator control
	 */
	
	public void teleopPeriodic() {
		System.out.println("is running...");
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Joy Y", oi.stick.getY());
		SmartDashboard.putNumber("Joy X", oi.stick.getX());
		SmartDashboard.putNumber("AcelY", ControlSystem.getMoveAxisAccelY());
		SmartDashboard.putNumber("AcelX", ControlSystem.getMoveAxisAccelX());
		SmartDashboard.putNumber("Accelerometer", accelerometer.getZ());
		ControlSystem.update();
		SmartDashboard.putNumber("Teleop Millisecond Delay", MillisecondTimer.getDifference());
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	private void autoChooserSetup(SendableChooser chooser) {
		/*
		chooser = new SendableChooser();
		chooser.addDefault("Wait", new DriveStop());
		chooser.addObject("Straight One Second", new AutoDriveStraight(1, .35));
		chooser.addObject("Turn 1 Second", new AutoDriveRotate(1, .35));
		SmartDashboard.putData(chooser.toString() + "choice:", chooser);
		*/
	}
}
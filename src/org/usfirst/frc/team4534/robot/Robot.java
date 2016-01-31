package org.usfirst.frc.team4534.robot;

import org.usfirst.frc.team4534.robot.commands.AutoDriveStraight;
import org.usfirst.frc.team4534.robot.commands.Autonomous;
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
	Command autoDefenseChoice;
	SendableChooser autoDefense;
	SendableChooser autoStartPos;
	SendableChooser autoGoal;
	
	public static BallHandler ballhandler;
	public static BuiltInAccelerometer accelerometer;

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
		
		autoDefense = new SendableChooser();
		autoDefense.addDefault("No Defense", new DriveStop());
		autoDefense.addObject("Portcullis", new DriveStop());
		autoDefense.addObject("Cheval de Frise", new DriveStop());
		autoDefense.addObject("Sally Port", new DriveStop());
		autoDefense.addObject("Drawbridge", new DriveStop());
		autoDefense.addObject("Rough Terrain", new Autonomous());
		autoDefense.addObject("Rock Wall", new DriveStop());
		autoDefense.addObject("Moat", new DriveStop());
		autoDefense.addObject("Ramparts", new DriveStop());
		autoDefense.addObject("Secret Passage", new AutoDriveStraight(1, .35));
		SmartDashboard.putData("Auto Defense", autoDefense);
		
		//Right Now, only autoDefense will be used.
		autoStartPos = new SendableChooser();
		autoStartPos.addObject("1 (Low Bar)", new DriveStop());
		autoStartPos.addObject("2", new DriveStop());
		autoStartPos.addObject("3", new DriveStop());
		autoStartPos.addObject("4", new DriveStop());
		autoStartPos.addObject("5", new DriveStop());
		autoStartPos.addDefault("6 (Secret Passage)", new DriveStop());
		SmartDashboard.putData("Auto Position", autoStartPos);
		//Right Now, this does nothing
		autoGoal = new SendableChooser();
		autoGoal.addObject("High Left", new DriveStop());
		autoGoal.addObject("High Center", new DriveStop());
		autoGoal.addObject("High Right", new DriveStop());
		autoGoal.addObject("Low Left", new DriveStop());
		autoGoal.addObject("Low Center", new DriveStop());
		autoGoal.addObject("Low Right", new DriveStop());
		autoGoal.addDefault("NO Shooting", new DriveStop());
		SmartDashboard.putData("Auto Goal", autoGoal);

		ControlSystem.init();
		ControlSystem.rumbleTimeSet(1);

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(ballhandler);
		// SmartDashboard.putData((NamedSendable) oi);

		// instantiate the command used for the autonomous period
		autoDefenseChoice = new CommandGroup();

		// autoChooser = new SendableChooser();
		// autoChooser.addObject("Drive Straight", new Autonomous());

		// SmartDashboard.putData("Auto Mode", autoChooser);
		SmartDashboard.putData(Scheduler.getInstance());
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		if (autoDefenseChoice != null) {
			if (autoDefenseChoice.isRunning()) {
				autoDefenseChoice.cancel();
			}
		}
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autoDefenseChoice != null) {
			if (autoDefenseChoice.isRunning()) {
				autoDefenseChoice.cancel();
			}
		}

		autoDefenseChoice = (Command) autoDefense.getSelected();
		if (autoDefenseChoice != null) {
			// autonomousCommand = (Command) autoChooser.getSelected();
			autoDefenseChoice.start();
			System.out.println("Auto Started!");
		if(accelerometer.getZ() >= 2){
			autoDefenseChoice.cancel();
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
		if (autoDefenseChoice != null) {
			autoDefenseChoice.cancel();
		}
		System.out.println("Beginning Teleop!");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		if (autoDefenseChoice != null) {
			autoDefenseChoice.cancel();
		}
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
}
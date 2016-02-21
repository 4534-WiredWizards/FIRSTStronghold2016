package org.usfirst.frc.team4534.robot;

import org.usfirst.frc.team4534.robot.commands.AutoChevalDeFrise;
import org.usfirst.frc.team4534.robot.commands.AutoDrawbridge;
import org.usfirst.frc.team4534.robot.commands.AutoDriveStraight;
import org.usfirst.frc.team4534.robot.commands.AutoMoat;
import org.usfirst.frc.team4534.robot.commands.AutoPortcullis;
import org.usfirst.frc.team4534.robot.commands.AutoRamparts;
import org.usfirst.frc.team4534.robot.commands.AutoRockWall;
import org.usfirst.frc.team4534.robot.commands.AutoRoughTerrain;
import org.usfirst.frc.team4534.robot.commands.AutoSallyPort;
import org.usfirst.frc.team4534.robot.commands.DriveStop;
import org.usfirst.frc.team4534.robot.commands.ManeuverToGoal;
import org.usfirst.frc.team4534.robot.subsystems.ArmPneumatics;
import org.usfirst.frc.team4534.robot.subsystems.BallHandler;
import org.usfirst.frc.team4534.robot.subsystems.Compressor;
import org.usfirst.frc.team4534.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4534.robot.subsystems.JetsonVision;
import org.usfirst.frc.team4534.robot.util.MillisecondTimer;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;
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
	public static JetsonVision jetsonvision;
	// Command auto;
	Command autoDefenseChoice;
	int autoPositionChoice;
	int autoGoalChoice;
	CommandGroup autonomousRoutine;
	SendableChooser autoDefense;
	SendableChooser autoStartPos;
	SendableChooser autoGoal;
	public static boolean isAuto = false;
	
	public static BallHandler ballhandler;
	public static ArmPneumatics armpneumatics;
	public static Compressor compressor;
	public static BuiltInAccelerometer accelerometer;
	public static SerialPort arduinocomm;
	public DriverStation.Alliance allianceColor;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("robotInit");

		drivetrain = new DriveTrain();
		ballhandler = new BallHandler();
		jetsonvision = new JetsonVision();
		armpneumatics = new ArmPneumatics();
		oi = new OI();
		accelerometer = new BuiltInAccelerometer();
		arduinocomm = new SerialPort(115200, SerialPort.Port.kMXP);
		allianceColor = DriverStation.getInstance().getAlliance();
		
		autoDefense = new SendableChooser();
		autoDefense.addDefault("No Defense", new DriveStop());
		autoDefense.addObject("Portcullis", new AutoPortcullis());
		autoDefense.addObject("Cheval de Frise", new AutoChevalDeFrise());
		autoDefense.addObject("Sally Port", new AutoSallyPort());
		autoDefense.addObject("Drawbridge", new AutoDrawbridge());
		autoDefense.addObject("Rough Terrain", new AutoRoughTerrain());
		autoDefense.addObject("Rock Wall", new AutoRockWall());
		autoDefense.addObject("Moat", new AutoMoat());
		autoDefense.addObject("Ramparts", new AutoRamparts());
		autoDefense.addObject("Approach", new AutoDriveStraight(RobotMap.approachDelay, .4));
		SmartDashboard.putData("Auto Defense", autoDefense);
		
		//Right Now, only autoDefense will be used.
		autoStartPos = new SendableChooser();
		autoStartPos.addObject("1 (Low Bar)", 1);
		autoStartPos.addObject("2", 2);
		autoStartPos.addObject("3", 3);
		autoStartPos.addObject("4", 4);
		autoStartPos.addObject("5", 5);
		SmartDashboard.putData("Auto Position", autoStartPos);
		//Right Now, this does nothing
		autoGoal = new SendableChooser();
		autoGoal.addObject("High Left", 1);
		autoGoal.addObject("High Center", 2);
		autoGoal.addObject("High Right", 3);
		autoGoal.addObject("Low Left", 4);
		autoGoal.addObject("Low Right", 5);
		autoGoal.addDefault("NO Shooting", 0);
		SmartDashboard.putData("Auto Goal", autoGoal);

		ControlSystem.init();
		ControlSystem.rumbleTimeSet(1);

		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(ballhandler);
		// SmartDashboard.putData((NamedSendable) oi);

		// instantiate the command used for the autonsomous period
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
		isAuto = true;
		// schedule the autonomous command (example)
		if (autoDefenseChoice != null) {
			if (autoDefenseChoice.isRunning()) {
				autoDefenseChoice.cancel();
			}
		}

		autoDefenseChoice = (Command) autoDefense.getSelected();
		autoPositionChoice = (int) autoStartPos.getSelected();
		autoGoalChoice = (int) autoGoal.getSelected();
		autonomousRoutine.addSequential(autoDefenseChoice);
		//autonomousRoutine.addSequential(new ManeuverToGoal(autoPositionChoice, autoGoalChoice));
		if (autoDefenseChoice != null) {
			autonomousRoutine.start();
			System.out.println("Auto Started!");
		}
		arduinocomm.writeString("b");
		if (allianceColor == DriverStation.Alliance.Blue) {
			// In the blue alliance
			System.out.print("BLUE alliance");
			arduinocomm.writeString("n");
		} else if (allianceColor == DriverStation.Alliance.Red) {
			// In the red alliance
			arduinocomm.writeString("r");
			System.out.print("RED alliance");
		}
		}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Accelerometer", accelerometer.getZ());
		
		if(accelerometer.getZ() >= 3.0){
			autoDefenseChoice.cancel();
			System.out.println("Accelometer value greater than 2.");
		}

	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autoDefenseChoice != null) {
			autoDefenseChoice.cancel();
		}
		isAuto = false;
		arduinocomm.writeString("i");
		System.out.println("Beginning Teleop!");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		isAuto = false;
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
		if(Timer.getMatchTime() >= 130.0){
			arduinocomm.writeString("z");
		}
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		
	}
}
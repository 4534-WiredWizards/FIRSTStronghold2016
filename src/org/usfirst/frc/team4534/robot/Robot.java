/* if .gitignore is an unstaged file, remove it
By default, EGit automatically adds resources marked as "Derived" to .gitignore. A typical example is the bin folder in a Java project containing the compiled classes.
You can disable this by going to the preferences Team > Git > Projects and deselect "Automatically ignore derived resources by adding them to .gitignore".
*/

package org.usfirst.frc.team4534.robot;

import org.usfirst.frc.team4534.robot.commands.Autonomous;
import org.usfirst.frc.team4534.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4534.robot.subsystems.ExampleSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;

	public static DriveTrain drivetrain;
	Command autonomousCommand;
	public SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drivetrain = new DriveTrain();
		
		oi = new OI();
		// instantiate the command used for the autonomous period
		autoChooser = new SendableChooser();
		autoChooser.addObject("Drive Straight", new Autonomous());
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null) {
			autonomousCommand = (Command) autoChooser.getSelected();
			autonomousCommand.start();
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
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

package org.usfirst.frc.team4534.robot.commands;

import org.usfirst.frc.team4534.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeBall extends Command {

    public IntakeBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.ballhandler);
    	setTimeout(5);
    	System.out.println("yaas");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arduinocomm.writeString("l");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!Robot.ballhandler.getLowerHandlerLimit()){
    		Robot.ballhandler.setIntake(0.60);
    	} else {
    		Robot.ballhandler.setIntake(0.00);
    		Robot.oi.stick.setRumble(Joystick.RumbleType.kRightRumble, 1);
    	}
    	
    	
    	//if(!Robot.ballhandler.getLowerHandlerLimit()) {
        //	Robot.ballhandler.setIntake(.60);
        	//Robot.ballhandler.setShooter(-0.2);
    	//}
    	//if(Robot.ballhandler.getLowerHandlerLimit()) {
    		// do nothing, we're ready
    	//}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; //(Robot.ballhandler.getLowerHandlerLimit()) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ballhandler.stop();
    	Robot.arduinocomm.writeString("v");
    	Robot.arduinocomm.writeString("i");
    	Robot.oi.stick.setRumble(Joystick.RumbleType.kRightRumble, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

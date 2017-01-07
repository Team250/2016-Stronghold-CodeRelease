package org.usfirst.frc250.Stronghold2016.commands.shooter;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DeflectorUpPosition extends Command {

	double targetPosition;
    public DeflectorUpPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	targetPosition = Robot.shooter.findNextPosition(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { //up is negative
    	if (Robot.shooter.getDeflectorPosition() > targetPosition) {
    		Robot.shooter.moveDeflector(Constant.kDEFLECTOR_RAISE_SPEED.get());
    	} else {
    		Robot.shooter.moveDeflector(Constant.kDEFLECTOR_LOWER_SPEED.get());
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.shooter.isDeflectorAtPosition(targetPosition);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.stopDeflector();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

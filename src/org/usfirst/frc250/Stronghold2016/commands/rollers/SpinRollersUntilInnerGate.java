package org.usfirst.frc250.Stronghold2016.commands.rollers;

import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinRollersUntilInnerGate extends Command {

    public SpinRollersUntilInnerGate() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.rollers.spinRollerSlowIn();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.rollers.isInnerPhotoGateBroken();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rollers.stopRoller();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

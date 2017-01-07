package org.usfirst.frc250.Stronghold2016.commands.auto;

import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class _SetOrientation extends Command {

	double offset;
	
    public _SetOrientation(double offset) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.offset = offset;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.miscellany.setHeadingOffset(offset);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

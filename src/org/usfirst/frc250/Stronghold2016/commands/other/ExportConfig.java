package org.usfirst.frc250.Stronghold2016.commands.other;

import org.usfirst.frc250.Stronghold2016.Constant;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ExportConfig extends Command {

    public ExportConfig() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Command running");
    	Constant.exportConfig();
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

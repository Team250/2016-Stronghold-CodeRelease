package org.usfirst.frc250.Stronghold2016.commands.intakeArm;

import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmToSetpoint extends Command {
	
	double position;
	
    public IntakeArmToSetpoint(double targetPosition) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeArm);
    	position = targetPosition;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeArm.enableClosedLoop(true);
    	Robot.intakeArm.setArmPosition(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.intakeArm.isArmOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeArm.enableClosedLoop(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

package org.usfirst.frc250.Stronghold2016.commands.auto;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class _DriveForward extends Command {

	boolean isSlow;

	public _DriveForward(boolean isSlow) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		this.isSlow = isSlow;
	}

	public _DriveForward() {
		this(false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (isSlow) {
			Robot.drivetrain.drive(Constant.kAUTO_DRIVE_SLOW_SPEED.get(), Constant.kAUTO_DRIVE_SLOW_SPEED.get());
		} else {
			Robot.drivetrain.drive(Constant.kAUTO_DRIVE_SPEED.get(), Constant.kAUTO_DRIVE_SPEED.get());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.stopDriving();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

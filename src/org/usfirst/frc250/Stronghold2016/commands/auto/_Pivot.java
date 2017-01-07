package org.usfirst.frc250.Stronghold2016.commands.auto;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.Robot;
import org.usfirst.frc250.Stronghold2016.Utilities;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class _Pivot extends Command {
	double degrees, targetAngle;
	
	public _Pivot(double deltaDegrees) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		this.degrees = deltaDegrees;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		targetAngle = Utilities.formatAngle(Robot.miscellany.getHeading() + degrees);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Utilities.angleDifference(Robot.miscellany.getHeading(),
				targetAngle) > Constant.kHEADING_CORRECTION_TOLERANCE.get()) {
			Robot.drivetrain.drive(-Constant.kAUTO_DRIVE_SPEED.get(), Constant.kAUTO_DRIVE_SPEED.get());
		} else if (Utilities.angleDifference(Robot.miscellany.getHeading(),
				targetAngle) < -Constant.kHEADING_CORRECTION_TOLERANCE.get()) {
			Robot.drivetrain.drive(Constant.kAUTO_DRIVE_SPEED.get(), -Constant.kAUTO_DRIVE_SPEED.get());
		} else {
			Robot.drivetrain.drive(0, 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(Utilities.angleDifference(Robot.miscellany.getHeading(),
				targetAngle)) <= Constant.kHEADING_CORRECTION_TOLERANCE.get();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

package org.usfirst.frc250.Stronghold2016.commands.shooter;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DeflectorToPosition1 extends Command {
	double targetPosition;
	boolean isDashboard, isNear;

	public DeflectorToPosition1(boolean isNear, boolean isDashboard) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
		this.isDashboard = isDashboard;
		this.isNear = isNear;
	}
	
	public DeflectorToPosition1(boolean isNear) {
		this(isNear, false);
	}

	public DeflectorToPosition1() {
		this(false, false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (isDashboard) {
			targetPosition = SmartDashboard.getNumber("Deflector position input")
					+ SmartDashboard.getNumber("Deflector overshoot input");
		} else if (isNear) {
			targetPosition = Constant.kDEFLECTOR_NEAR_SHOOTING_POSITION.get()
					+ Constant.kDEFLECTOR_POSITION_COMPENSATION.get();
		} else {
			targetPosition = Constant.kDEFLECTOR_FAR_SHOOTING_POSITION.get()
					+ Constant.kDEFLECTOR_POSITION_COMPENSATION.get();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.shooter.getDeflectorPosition() > targetPosition) {
			Robot.shooter.moveDeflector(Constant.kDEFLECTOR_LOWER_SPEED.get());
		} else {
			Robot.shooter.moveDeflector(Constant.kDEFLECTOR_RAISE_SPEED.get());
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println(targetPosition + "\t" + Robot.shooter.getDeflectorPosition());
		return Math.abs(targetPosition - Robot.shooter.getDeflectorPosition()) < Constant.kDEFLECTOR_POSITION_TOLERANCE
				.get();
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

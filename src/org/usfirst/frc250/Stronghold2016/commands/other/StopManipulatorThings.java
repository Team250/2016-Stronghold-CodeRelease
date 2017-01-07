package org.usfirst.frc250.Stronghold2016.commands.other;

import org.usfirst.frc250.Stronghold2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StopManipulatorThings extends Command {
    
    public  StopManipulatorThings() {
        requires(Robot.intakeArm);
        requires(Robot.rollers);
        requires(Robot.shooter);
    }

	protected void initialize() {
		Robot.intakeArm.stopArm();
		Robot.shooter.stopWheel();
		Robot.shooter.stopDeflector();
		Robot.rollers.stopRoller();
	}

	protected void execute() {
		
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		
	}
}

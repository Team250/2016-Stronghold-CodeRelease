package org.usfirst.frc250.Stronghold2016.commands.shooter;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DeflectorToPositionWithOvershoot extends CommandGroup {
	
	public DeflectorToPositionWithOvershoot(boolean isNear, boolean isDashboard) {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		addSequential(new DeflectorToPosition1(isNear, isDashboard));
		addSequential(new DeflectorToPosition2(isNear, isDashboard));
	}
	
	public DeflectorToPositionWithOvershoot () {
		this(false, false);
	}
	
	public DeflectorToPositionWithOvershoot (boolean isNear) {
		this(isNear, false);
	}
}

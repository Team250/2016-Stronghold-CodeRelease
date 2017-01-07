package org.usfirst.frc250.Stronghold2016.commands.rollers;

import org.usfirst.frc250.Stronghold2016.commands.intakeArm.IntakeArmToSetpoint;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Eject extends CommandGroup {
    /**
     * Ejects the ball for low goal or whatever. While held. 
     */
    public  Eject() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new IntakeArmToSetpoint(0.2));
    	addSequential(new SpinRollersBackwards());
    }
}

package org.usfirst.frc250.Stronghold2016.commands.auto;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.commands.defenseArm.LowerDefenseArm;
import org.usfirst.frc250.Stronghold2016.commands.defenseArm.RaiseDefenseArmFast;
import org.usfirst.frc250.Stronghold2016.subsystems.DefenseArm;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class PortucullisAuto extends CommandGroup {
    
    public  PortucullisAuto() {
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
    	addSequential(new LowerDefenseArm());
    	addParallel(new _DriveForward(true), Constant.kAUTO_FORWARD_SECONDS.get());
    	addSequential(new WaitCommand(1));
    	addSequential(new RaiseDefenseArmFast());
    }
}

// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc250.Stronghold2016.subsystems;

import org.usfirst.frc250.Stronghold2016.Robot;
import org.usfirst.frc250.Stronghold2016.RobotMap;
import org.usfirst.frc250.Stronghold2016.commands.defenseArm.MoveDefenseArm;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DefenseArm extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final SpeedController armMotor = RobotMap.defenseArmarmMotor;
	private final DigitalInput topLimit = RobotMap.defenseArmtopLimit;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private boolean isAtBottomLimit = false;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		
		setDefaultCommand(new MoveDefenseArm());
	}

	/**
	 * Sets the defense arm motor to the specified speed, checking for the
	 * limits.
	 * 
	 * @param speed
	 *            A speed value from -1 to 1, where positive is moving away from
	 *            the robot and negative is moving towards.
	 */
	public void setArmSpeed(double speed) {
		if (isCurrentHigh() && speed > 0) {
			isAtBottomLimit = true;
			armMotor.set(0);
		} else if ((isAtTopLimit() && speed < 0) || (isAtBottomLimit && speed > 0)) {
			armMotor.set(0);
		} else {
			isAtBottomLimit = false;
			armMotor.set(speed);
		}
	}

	/**
	 * Stops the defense arm.
	 */
	public void stopArm() {
		setArmSpeed(0);
	}

	private boolean isCurrentHigh() {
		/* TODO: Try this with Talon reading instead of PDP */
		return Robot.miscellany.getCurrent(RobotMap.defenseArmChannel) > 4;
	}

	/**
	 * Whether or not the defense arm has hit the top limit.
	 * 
	 * @return Whether the arm is at the top limit.
	 */
	public boolean isAtTopLimit() {
		return !topLimit.get();
	}

	/**
	 * Whether or not the defense arm has "hit" the bottom limit, based on
	 * current draw.
	 * 
	 * @return Whether the arm is at the bottom limit.
	 */
	public boolean isAtBottomLimit() {
		return isAtBottomLimit;
	}
}
package org.usfirst.frc250.Stronghold2016.subsystems;

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Rollers extends Subsystem {
    
	private final SpeedController rollerMotor = RobotMap.rollerrollerMotor;
	private final DigitalInput innerPhotoGate = RobotMap.rollersinnerPhotoGate;
	private final DigitalInput outerPhotoGate = RobotMap.rollersouterPhotoGate;
	
	private boolean rollerSpinning = false;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    /**
	 * Spins the rollers at the speed specified in {@link Constant} as
	 * kINTAKE_ROLLER_SPEED
	 * 
	 * @param isForward
	 *            Whether to spin forward, where forward pulls the ball in.
	 */
	public void spinRoller(boolean isForward) {
		/* TODO: Current draw safety */
		rollerMotor.set(isForward ? Constant.kROLLER_SPEED.get() : Constant.kROLLER_REVERSE_SPEED.get());
		rollerSpinning = true;
	}

	/**
	 * Spins rollers forward to pull in ball.
	 */
	public void spinRoller() {
		spinRoller(true);
	}

	public void spinRollerSlowIn() {
		rollerMotor.set(Constant.kROLLER_SLOW_SPEED.get());
		rollerSpinning = true;
	}

	/**
	 * Stops the rollers.
	 */
	public void stopRoller() {
		rollerMotor.set(0);
		rollerSpinning = false;
	}

	/**
	 * Returns whether or not the rollers are currently spinning.
	 * 
	 * This isn't based on encoder values, just the internal state.
	 * 
	 * @return Whether the rollers are spinning.
	 */
	public boolean isRollerSpinning() {
		return rollerSpinning;
	}

	/**
	 * Returns whether or not the inner photo gate is broken, indicating the
	 * ball is ready to be fired.
	 * 
	 * @return Whether the inner photo gate is broken.
	 */
	public boolean isInnerPhotoGateBroken() {
		return !innerPhotoGate.get();
	}

	/**
	 * Returns whether or not the outer photo gate is broken, indicating the
	 * ball is inside the robot.
	 * 
	 * @return Whether the outer photo gate is broken.
	 */
	public boolean isOuterPhotoGateBroken() {
		return !outerPhotoGate.get();
	}
}


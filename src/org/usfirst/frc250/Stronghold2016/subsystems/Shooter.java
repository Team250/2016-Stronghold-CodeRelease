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

import org.usfirst.frc250.Stronghold2016.Constant;
import org.usfirst.frc250.Stronghold2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private final CANTalon flywheelMotor = RobotMap.shooterflywheelMotor;
	private final CANTalon deflectorMotor = RobotMap.shooterdeflectorMotor;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private boolean isDeflectorAtBottom = false;
	private double[] deflectorSetpoints = { 0, -600, -1200, -1800 };

	public Shooter() {
		flywheelMotor.reverseOutput(true);
		flywheelMotor.setInverted(true);
		flywheelMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		flywheelMotor.changeControlMode(TalonControlMode.Speed);
		flywheelMotor.configNominalOutputVoltage(0, 0);
		flywheelMotor.configPeakOutputVoltage(0, -12);
		flywheelMotor.setProfile(0);
		flywheelMotor.setF(0.03);
		flywheelMotor.setP(0.08);
		flywheelMotor.setI(0);
		flywheelMotor.setD(2);
		flywheelMotor.setCloseLoopRampRate(Constant.kFLYWHEEL_VOLTAGE_RAMP_RATE.get());

		deflectorMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		deflectorMotor.reverseSensor(true);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void println() {
		System.out.println("out: " + flywheelMotor.getOutputVoltage() + " spd: " + flywheelMotor.getSpeed() + " err: "
				+ flywheelMotor.getClosedLoopError() * 60 * 10 / 4096 + " trg: " + flywheelMotor.getSetpoint());
	}

	/**
	 * Spin the flywheel at the speed specified in {@link Constant}.
	 */
	public void spinWheel(double speed) {
		flywheelMotor.set(-speed);
//		 double speed = -SmartDashboard.getNumber("Flywheel input");
//		 flywheelMotor.set(speed);
//		 System.out.println(speed);
	}

	/**
	 * Stop the flywheel.
	 */
	public void stopWheel() {
		flywheelMotor.set(0);
	}

	/**
	 * Gets the current flywheel speed from the encoder.
	 * 
	 * @return The flywheel encoder speed.
	 */
	public double getWheelSpeed() {
		return flywheelMotor.getSpeed();
	}

	public boolean isWheelAtSpeed(double speed) {
		// return Math.abs(flywheelMotor.getClosedLoopError()) <
		// Constant.kFLYWHEEL_SPEED_TOLERANCE.get();
		return Math.abs(getWheelSpeed() - speed) < Constant.kFLYWHEEL_SPEED_TOLERANCE
				.get();
	}

	/**
	 * Move the deflector shield at the specified speed
	 * 
	 * @param speed
	 *            A speed value, from -1 to 1.
	 */
	public void moveDeflector(double speed) {
		// if (isCurrentHigh() && speed > 0) {
		// isAtBottomLimit = true;
		// armMotor.set(0);
		// } else if ((isAtTopLimit() && speed < 0) || (isAtBottomLimit && speed
		// > 0)) {
		// armMotor.set(0);
		// } else {
		// isAtBottomLimit = false;
		// armMotor.set(speed);
		// }
		if (isCurrentHigh() && speed < 0) {
			isDeflectorAtBottom = true;
			zeroDeflector();
			stopDeflector();
		} else if (isDeflectorAtBottom && speed < 0) {
			stopDeflector();
		} else {
			isDeflectorAtBottom = false;
			deflectorMotor.set(speed);
		}
	}
	// 13 ft to back, 1900 encoder
	
	public boolean isDeflectorAtBottom () {
		return isDeflectorAtBottom;
	}

	/**
	 * Stops the deflector shield.
	 */
	public void stopDeflector() {
		deflectorMotor.set(0);
	}

	/**
	 * The current deflector position, based on the encoder value.
	 * 
	 * @return The deflector position, in encoder ticks.
	 */
	public double getDeflectorPosition() {
		return deflectorMotor.getPosition();
	}

	public boolean isDeflectorAtPosition(double position) {
		return Math.abs(getDeflectorPosition() - position) < Constant.kDEFLECTOR_POSITION_TOLERANCE.get();
	}

	public double findNextPosition(boolean up) {
		double currentPosition = getDeflectorPosition();
		double candidate = up ? -9999 : 9999;
		for (int i = 0; i < deflectorSetpoints.length; i++) {
			if (!up) {
				if (deflectorSetpoints[i] > currentPosition + Constant.kDEFLECTOR_POSITION_TOLERANCE.get()
						&& candidate > deflectorSetpoints[i]) {
					candidate = deflectorSetpoints[i];
				}
			} else {
				if (deflectorSetpoints[i] < currentPosition - Constant.kDEFLECTOR_POSITION_TOLERANCE.get()
						&& candidate < deflectorSetpoints[i]) {
					candidate = deflectorSetpoints[i];
				}
			}
		}
		if (candidate > 9000 || candidate < -9000) {
			candidate = currentPosition;
		}
		return candidate;
	}

	public void zeroDeflector() {
		deflectorMotor.setPosition(0);
	}

	public double getDeflectorCurrent() {
		return deflectorMotor.getOutputCurrent();
	}

	private boolean isCurrentHigh() {
		return getDeflectorCurrent() > 1;
	}

}

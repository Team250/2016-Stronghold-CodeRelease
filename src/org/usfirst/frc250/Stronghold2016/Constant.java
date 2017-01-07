package org.usfirst.frc250.Stronghold2016;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Speicifies constant values, allowing for them to be modified without 
 * recompiling by dynamically loading a configuration file.
 */
public enum Constant {
	// Drivetrain
	kDRIVE_RAMP_RATE(12), 
	kDRIVE_MULTIPLIER(1.0), 
	kHEADING_CORRECTION_THRESHOLD(30), 
	kHEADING_CORRECTION_TOLERANCE(3), 
	kHEADING_CORRECTION_SPEED(0.5),
	kAUTO_DRIVE_SPEED(0.8),
	kAUTO_DRIVE_SLOW_SPEED(0.6),
	// Flywheel
	kFLYWHEEL_VOLTAGE_RAMP_RATE(18),
	kFLYWHEEL_VBUS_SPEED(1.0), 
	kFLYWHEEL_FAR_SPEED(4500),
	kFLYWHEEL_NEAR_SPEED(4100),
	kFLYWHEEL_SPEED_TOLERANCE(100),
	// Deflector
	kDEFLECTOR_RAISE_SPEED(0.5), 
	kDEFLECTOR_LOWER_SPEED(-0.5),
	kDEFLECTOR_FAR_SHOOTING_POSITION(2000),
	kDEFLECTOR_NEAR_SHOOTING_POSITION(1700),
	kDEFLECTOR_POSITION_COMPENSATION(200),
	kDEFLECTOR_POSITION_TOLERANCE(100),
	// Rollers
	kROLLER_SPEED(-0.5),
	kROLLER_SLOW_SPEED(-0.2),
	kROLLER_REVERSE_SPEED(1.0),
	// Intake arm
	kINTAKE_ARM_RAISE_SPEED(-0.75),
	kINTAKE_ARM_LOWER_SPEED(0.75),
	kINTAKE_ARM_EJECT_POSITION(1500),
	// Defense arm
	kDEFENSE_ARM_RAISE_SPEED(-0.75), 
	kDEFENSE_ARM_LOWER_SPEED(0.65), 
	kDEFENSE_ARM_FAST_RAISE_SPEED(-1.0),
	// Timing
	kSECONDS_TO_MOVE_BALL_TO_FLYWHEEL(1),
	kSECONDS_FLYWHEEL_TO_SPEED(0.5),
	kAUTO_FORWARD_SECONDS(3),
	// Other
	kDEADBAND_WIDTH(0.1), 
	kBINARY_AXIS_DEADBAND(0.6), 
	kTIPPING_THRESHOLD(10);

	private double value;

	Constant(double value) {
		this.value = value;
	}

	public double get() {
		return this.value;
	}

	private static boolean contains(String s) {
		for (Constant choice : values()) {
			if (choice.name().equals(s)) {
				return true;
			}
		}
		return false;
	}

	public static void update(HashMap<String, Double> newValues) {
		System.out.println("updating");
		for (Iterator<String> iter = newValues.keySet().iterator(); iter.hasNext();) {
			String key = iter.next();
			System.out.println(key);
			if (Constant.contains(key)) {
				System.out.println("changing " + Constant.valueOf(key).value + " to " + newValues.get(key));
				try {
					Constant.valueOf(key).value = newValues.get(key).doubleValue();
				} catch (Exception e) {
					System.out.println("Encountered an error while reading config " + key + ": " + e);
				}
			}
		}
	}
	
	private static String configURI = "/home/lvuser/config.json";

	@SuppressWarnings("unchecked")
	public static void loadConfig() {
		JSONParser parser = new JSONParser();
		try {
			// JSONObject configObject = (JSONObject) parser.parse(new
			// FileReader("/media/sda/config.json"));
			JSONObject configObject = (JSONObject) parser.parse(new FileReader(configURI));
			Constant.update(configObject);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	/* TEST: Config export (SmartDashboard button) */
	@SuppressWarnings("unchecked")
	public static void exportConfig() {
		System.out.println("method running");
		JSONObject obj = new JSONObject();
		for (Constant value : Constant.values()) {
			obj.put(value.name(), value.get());
		}
		System.out.println(obj.toJSONString());
		try {
			FileWriter file = new FileWriter(configURI);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

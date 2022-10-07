package project;

import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot { // MAYBE extends MovePilot

	private String name = "Robot";

	private Map map;
	private MovePilot movePilot;
	private HashMap colorsCalibrated;
	private EV3ColorSensor cs;

	public Pilot(String name, EV3ColorSensor cs) {
		this.name = name;

		this.map = new Map();

		this.movePilot = initPilot();
		this.movePilot.setLinearSpeed(100);
		this.movePilot.setAngularSpeed(100);
		this.movePilot.setLinearAcceleration(2000);
		this.movePilot.setAngularAcceleration(2000);

		this.colorsCalibrated = new HashMap<Color, int[]>();

		this.cs = cs;
		// Maybe EV3TouchSensor
	}

	static MovePilot initPilot() {
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		return new MovePilot(chassis);
	}

	/*
	 * GETTERS & SETTERS
	 */

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCalibratedColor(Color key, int[] value) {
		this.colorsCalibrated.put(key, value);
	}

	public HashMap<Color, int[]> getColorsCalibrated() {
		return this.colorsCalibrated;
	}

}

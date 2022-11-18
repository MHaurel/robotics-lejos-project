package project;

import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot {

	private String name = "Robot";

	private Map map;
	private MovePilot movePilot;
	
	private float bandWidth = 15f; // 1.5 cm
	private int caseWidth = 120; // 12 cm
	private float distanceBetweenSensorWheel = 75f; // 7.5cm

	private int moveSpeed = 75;
	
	public Pilot(String name, EV3ColorSensor cs) {
		this.name = name;

		this.map = new Map();

		this.movePilot = initPilot();
		this.movePilot.setLinearSpeed(this.moveSpeed);
		this.movePilot.setAngularSpeed(100);
		this.movePilot.setLinearAcceleration(100);
		this.movePilot.setAngularAcceleration(100);
	}

	static MovePilot initPilot() {
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		return new MovePilot(chassis);
	}
	
	private void linearTravel(int nbrCases) {
		// Distance = bandWidth + caseWidth
		float distance = this.bandWidth + this.caseWidth;
		this.movePilot.travel(distance * nbrCases);
	}
	
	public void travelAppropriateDirectionDistance(Direction direction, int nbrCases) {
		switch (direction) {
		case UP:
			this.linearTravel(nbrCases);
			break;
			
		case DOWN:
			this.linearTravel(-nbrCases);
			break;
			
		case RIGHT:
			this.movePilot.rotateRight();
			this.linearTravel(nbrCases);
			this.movePilot.rotateLeft();
			break;
			
		case LEFT:
			this.movePilot.rotateLeft();
			this.linearTravel(nbrCases);
			this.movePilot.rotateRight();
			break;
		}
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
	
	public float getBandWidth() {
		return this.bandWidth;
	}
	
	public void setBandWidth(float bw) {
		this.bandWidth = bw;
	}
	
	public int getCaseWidth() {
		return this.caseWidth;
	}
	
	public void setCaseWidth(int cw) {
		this.caseWidth = cw;
	}
	
	public void setMovePilot(MovePilot mp) {
		this.movePilot = mp;
	}
	
	public MovePilot getMovePilot() {
		return this.movePilot;
	}

}

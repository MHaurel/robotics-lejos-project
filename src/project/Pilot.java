package project;

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
	
	private int x, y;
	
	private float bandWidth = 15f; // 1.5 cm
	private int caseWidth = 120; // 12 cm

	private int moveSpeed = 100;
	private int rotationSpeed = 50;
	
	public Pilot(String name, EV3ColorSensor cs) {
		this.name = name;

		if (name == "Jon" ) {
			this.map = new Map();
			x = 0;
			y = 6;
		} else {
			this.map = new Map();
			x = 0;
			y = 5;
		}

		this.movePilot = initPilot();
		this.movePilot.setLinearSpeed(this.moveSpeed);
		this.movePilot.setAngularSpeed(this.rotationSpeed);
		this.movePilot.setLinearAcceleration(100);
		this.movePilot.setAngularAcceleration(100);
	}

	public static MovePilot initPilot() {
		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
		Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
		return new MovePilot(chassis);
	}
	
	public void travelDirectionDistance(Direction direction, int nbrCases) {
		
		float distance = this.bandWidth + this.caseWidth;
		
		switch (direction) {
		case UP:
			this.movePilot.travel(distance * nbrCases, false);
			break;
			
		case DOWN:
			this.movePilot.travel(distance * -nbrCases, false);
			break;
			
		case RIGHT:
			this.movePilot.rotate(86, false); // right
			this.movePilot.travel(distance * nbrCases, false);
			this.movePilot.rotate(-86, false); // left
			break;
			
		case LEFT:
			this.movePilot.rotate(-86, false); // left
			this.movePilot.travel(distance * nbrCases, false);
			this.movePilot.rotate(86, false); // right
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

package project;

import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Pilot {

	private String name = "Robot";

	private Color[][] map;
	private MovePilot movePilot;
	
	private int x, y;
	
	private float bandWidth = 15f; // 1.5 cm
	private int caseWidth = 120; // 12 cm

	private int moveSpeed = 100;
	private int rotationSpeed = 50;
	
	private Status status;
	
	public Pilot(String name, EV3ColorSensor cs) {
		this.name = name;
		
		Map m = new Map();

		if (name == "Jon" ) {
			this.map = m.getMap();
			this.x = 0;
			this.y = 6;
		} else {
			this.map = m.getReversedMap();
			this.x = 0;
			this.y = 5;
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
	
	//using the coordinates of the robot and a color, determines the coordinates of the closest case of this color and travel to it using travelCase(x, y)
	public void travelColor(Color color) {
		int yCase = 0;
		int xCase = 0;
		int distanceCase = Integer.MAX_VALUE;
		int distanceActu = 0;
		
		for (int i = this.map.length - 1; i >= 0; i--) { //allows to prefer the left and down direction to avoid robots collision
			for (int j = 0; j < this.map[i].length; j++) {
				if (this.map[i][j] == color) {
					
					distanceActu = Math.abs(i - this.y) + Math.abs(j - this.x); //Manhattan distance
					if (distanceActu < distanceCase) {
						distanceCase = distanceActu;
						yCase = i;
						xCase = j;
					}
					
				}
			}
		}
		System.out.println("xCase: " + xCase);
		System.out.println("yCase: " + yCase);
		travelCase(xCase, yCase);
	}

	private void travelCase(int x, int y) {
		int travelX = x - this.x;
		int travelY = y - this.y;
		System.out.println("travelX: " + travelX);
		System.out.println("travelY: " + travelY);
		
		if (travelX != 0) {
			Direction dirX = travelX > 0 ? Direction.RIGHT : Direction.LEFT;
			travelDirectionDistance(dirX, Math.abs(travelX));
		}
		
		if (travelY != 0) {
			Direction dirY = travelY > 0 ? Direction.DOWN : Direction.UP;
			travelDirectionDistance(dirY, Math.abs(travelY));
		}
		
		this.x = x;
		this.y = y;
	}
	
	private void travelDirectionDistance(Direction direction, int nbrCases) {
		
		float distance = this.bandWidth + this.caseWidth;
		
		switch (direction) {
		case UP:
			this.movePilot.travel(distance * nbrCases, false);
			break;
			
		case DOWN:
			this.movePilot.travel(distance * -nbrCases, false);
			break;
			
		case RIGHT:
			this.movePilot.rotate(85, false); // turn right
			this.movePilot.travel(distance * nbrCases, false);
			this.movePilot.rotate(-85, false); // turn left
			break;
			
		case LEFT:
			this.movePilot.rotate(-85, false); // turn left
			this.movePilot.travel(distance * nbrCases, false);
			this.movePilot.rotate(85, false); // turn right
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
	
	public MovePilot getMovePilot() {
		return this.movePilot;
	}
	
	public void setMovePilot(MovePilot mp) {
		this.movePilot = mp;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Status getStatus() {
		return this.status;
	}
	
	public void setStatus(Status s) {
		this.status = s;
	}

}

package project;

import java.util.Random;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class ForwardRandomDirection implements Behavior {
	
	private Pilot pilot;
	private ColorManagement cm;
	Random r;
	
	Color currentColor = null;
	
	public ForwardRandomDirection(Pilot pilot, ColorManagement cm) {
		this.pilot = pilot;
		this.cm = cm;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
		// Choose the direction among Direction
		System.out.println("Press RIGHT button to go in a random direction");
		Button.RIGHT.waitForPressAndRelease();
		r = new Random();
		Direction direction = Direction.values()[r.nextInt(Direction.values().length - 2)];
		System.out.println(direction);
		// Call the approrpriate method of pilot
		this.pilot.travelAppropriateDirection(direction);
		
//		this.pilot.getMovePilot().forward();
//		
//		while (pilot.getMovePilot().isMoving()) {
//			this.currentColor = cm.closestColor();
//		}
		
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.pilot.getMovePilot().stop();
	}

}
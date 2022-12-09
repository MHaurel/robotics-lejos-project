package project;

import java.util.Random;

import lejos.hardware.Button;
import lejos.robotics.subsumption.Behavior;

public class ForwardRandomDirection implements Behavior {
	
	private Pilot pilot;
	Random r;
	
	Color currentColor = null;
	
	public ForwardRandomDirection(Pilot pilot) {
		this.pilot = pilot;
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
		System.out.println("Press RIGHT button to go to a random Color");
		Button.RIGHT.waitForPressAndRelease();
		r = new Random();
		Color randomColor = Color.values()[r.nextInt(Color.values().length - 1)];
				
		System.out.println("Random color : " + randomColor);
		
		this.pilot.travelColor(randomColor);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.pilot.getMovePilot().stop();
	}

}
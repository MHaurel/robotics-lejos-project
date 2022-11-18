package project;

import lejos.robotics.subsumption.Behavior;

public class ForwardCheckColor implements Behavior {
	
	private Pilot pilot;
	private ColorManagement cm;
	
	Color currentColor = null;
	
	public ForwardCheckColor(Pilot pilot, ColorManagement cm) {
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
		this.pilot.getMovePilot().forward();
		
		while (pilot.getMovePilot().isMoving()) {
			this.currentColor = cm.closestColor();
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.pilot.getMovePilot().stop();
	}

}

package project;

import lejos.robotics.subsumption.Behavior;

public class ForwardCheckColor implements Behavior {
	
	private Pilot pilot;
	private ColorManagement cm;
	
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
		System.out.println(this.cm.closestColor());
		this.pilot.getMovePilot().forward();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.pilot.getMovePilot().stop();
	}

}

package project;

import lejos.robotics.subsumption.Behavior;

public class Receive implements Behavior {

	private Pilot pilot;
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return this.pilot.getStatus() == Status.RECEIVER;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Wait for other robot's command
		
		// Move the robot
		
		// Update the status of the pilot
		this.pilot.setStatus(Status.SENDER);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}

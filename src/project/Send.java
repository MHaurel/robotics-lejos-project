package project;

import lejos.robotics.subsumption.Behavior;

public class Send implements Behavior {

	private Pilot pilot;
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return this.pilot.getStatus() == Status.SENDER;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Send the command to the robot
		
		// Wait for the other command of the robot
		
		// Update status of the pilot
		this.pilot.setStatus(Status.RECEIVER);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}

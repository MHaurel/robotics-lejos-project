package project;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Stop implements Behavior {
	
	private Arbitrator a;
	private Pilot pilot;
	private EV3ColorSensor cs;
	
	public Stop(Pilot pilot, EV3ColorSensor cs) {
		this.pilot = pilot;
		this.cs = cs;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.LEFT.isDown();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		/*
		 * Stop motors
		 */
		this.pilot.getMovePilot().stop();
		// Close color sensor
		this.cs.close();
		this.a.stop();
		System.exit(0);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	public void setArbi(Arbitrator a) {
		this.a = a;
	}
	
}

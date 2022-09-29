package project;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class ForwardCheckColor implements Behavior {
	
	private MovePilot pilot;
	private EV3ColorSensor cs;
	
	private Color currentColor;
	
	public ForwardCheckColor(MovePilot pilot, EV3ColorSensor cs) {
		this.pilot = pilot;
		this.cs = cs;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.DOWN.isDown(); // temporary
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		this.pilot.forward();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		this.pilot.stop();
	}

	
	
}

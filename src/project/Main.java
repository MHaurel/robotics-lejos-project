package project;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		
		String name = "Arya"; // Depends on the robot
		System.out.println("Hello ! I'm " + name);
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);

		// Using the newly defined class as the pilot (contain a move pilot)
		Pilot pilot = new Pilot(name, cs);
		
		// Instantiating behaviors
		ColorManagement cm = new ColorManagement(cs, pilot);
		cm.init();
		
//		Behavior b1 = new ForwardCheckColor(pilot, cm);
		Behavior b1 = new ForwardRandomDirection(pilot, cm);
		
		Stop bStop = new Stop(pilot, cs);
		
		Behavior[] tab = new Behavior[] {b1, bStop};
		
		Arbitrator arbi = new Arbitrator(tab);
		
		bStop.setArbi(arbi);
		
		// Display message when ready
		System.out.println("I'm ready ! Press DOWN to go on");
		Button.DOWN.waitForPressAndRelease();

		arbi.go();
		
	}
	
}

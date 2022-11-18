package project;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {

	public static void main(String[] args) {
		
		String name = "Arya";
		
//		Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
//		Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
//		
//		Chassis chassis = new WheeledChassis(new Wheel[] {wheel1, wheel2}, WheeledChassis.TYPE_DIFFERENTIAL);
		
		
		
		/*
		 * Set speed & acceleration of the pilot
		 */
//		pilot.setLinearSpeed(100);
//		pilot.setAngularSpeed(100);
//		pilot.setLinearAcceleration(2000);
//		pilot.setAngularAcceleration(2000);
		
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);

		// Using the newly defined class as the pilot (contain a move pilot)
		Pilot pilot = new Pilot(name, cs);
		
		/*
		 * Instantating behaviors
		 */
		
		//Behavior b1 = new ForwardCheckColor(pilot, cs);
		Behavior b1 = new GetColor(cs, pilot);
		
		Stop bStop = new Stop(pilot, cs);
		
		Behavior[] tab = new Behavior[] {b1, bStop};
		
		Arbitrator arbi = new Arbitrator(tab);
		
		bStop.setArbi(arbi);
		
		// Printing message when ready
		System.out.println("I'm ready ! Let's go DOWN");
		Button.DOWN.waitForPressAndRelease();
		System.out.println("Checkpoint press RIGHT");

		arbi.go();
		
	}
	
}

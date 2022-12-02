package project;

import java.io.DataInputStream;
import java.io.InputStream;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;

public class Receive implements Behavior {

	private Pilot pilot;
	
	public Receive(Pilot pilot) {
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return this.pilot.getStatus() == Status.RECEIVER;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Wait for other robot's command
		Color color = this.receiveColor();
		// Move the robot
//		while (this.pilot.getMovePilot().isMoving())
		this.pilot.travelColor(color);
		// Update the status of the pilot
		this.pilot.setStatus(Status.SENDER);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
	private Color receiveColor() {
		try {
			
			BTConnector bt = new BTConnector();
			NXTConnection btc = bt.waitForConnection(100000, NXTConnection.PACKET);

			if (btc !=null) {
	
				InputStream is = btc.openInputStream();
				DataInputStream dis = new DataInputStream(is);
	
				int valeur = dis.read();
	
				dis.close();
				btc.close();
				PlayMusic.playMusic(valeur);
				System.out.println("Color : " + Color.values()[valeur]);
				System.out.println("Press RIGHT");
				Button.RIGHT.waitForPressAndRelease();
				LCD.clear();
				return Color.values()[valeur];
				} else {
					System.out.println("Pas de connexion");
					System.out.println("Press RIGHT");
					Button.RIGHT.waitForPressAndRelease();
					return null;
				}
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

}

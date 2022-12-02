package project;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Random;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;

public class Send implements Behavior {

	private Pilot pilot;
	
	public Send(Pilot pilot) {
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		System.out.println("I'm the receiver !");
		return this.pilot.getStatus() == Status.SENDER;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		// Send the command to the robot
		this.sendRandomColorId();
		// Wait for the other command of the robot
		// Update status of the pilot
		this.pilot.setStatus(Status.RECEIVER);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
	private void sendRandomColorId() {
		
		Button.RIGHT.waitForPressAndRelease();
		
		Random r = new Random();
		int valeur =  r.nextInt(Color.values().length - 1);
		
		try {
			
			BTConnector bt = new BTConnector();
			BTConnection btc = bt.connect("00:16:53:43:37:FC", NXTConnection.PACKET);

			OutputStream os = btc.openOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			System.out.println("\n\nEnvoi");
			
			dos.write(valeur);
			PlayMusic.playMusic(valeur);
			
			dos.flush();
			System.out.println("Color: " + Color.values()[valeur]);
			System.out.println("Press RIGHT");
			Button.RIGHT.waitForPressAndRelease();
			dos.close();
			btc.close();
			LCD.clear();
		} catch (Exception e) {
		}
	}

}

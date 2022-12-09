package project;

import java.io.DataOutputStream;
import java.io.OutputStream;

import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class Emetteur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String connected = "Connected";
		String waiting = "Waiting";
		EV3 ev = LocalEV3.get();
		System.out.println("--"+ev.getName()+"--");
		Button.RIGHT.waitForPressAndRelease();
		try {
			BTConnector bt = new BTConnector();
			BTConnection btc = bt.connect("00:16:53:43:37:FC", NXTConnection.PACKET);

			LCD.clear();
			LCD.drawString(connected, 0, 0);
			LCD.refresh();

			OutputStream os = btc.openOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			System.out.println("\n\nEnvoi");
			int valeur = 256;
			dos.write(valeur);
			dos.flush();
			System.out.println("\nEnvoy�");
			System.out.println("You send value: " + valeur);
			Button.RIGHT.waitForPressAndRelease();
			dos.close();
			btc.close();
			LCD.clear();
			
		} catch (Exception e) {
			
		}
	}

}

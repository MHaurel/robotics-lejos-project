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
			
			//LCD.drawString(waiting, 0, 0);
			//LCD.refresh();
			//droite = 00:16:53:43:4E:26
			//gauche = 00:16:53:43:8E:49
			BTConnector bt = new BTConnector();
			BTConnection btc = bt.connect("00:16:53:43:37:FC", NXTConnection.PACKET);//le premier param�tre est l'adresse du r�cepteur affich� sur l'�cra de l'�metteur apr�s association (pair) bluetooth


			LCD.clear();
			LCD.drawString(connected, 0, 0);
			LCD.refresh();

			//InputStream is = btc.openInputStream();
			OutputStream os = btc.openOutputStream();
			//DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			System.out.println("\n\nEnvoi");
			int valeur = 256;
			dos.write(valeur); // �crit une valeur dans le flux
			dos.flush(); // force l�envoi
			System.out.println("\nEnvoy�");
			//dis.close();
			System.out.println("You send value: " + valeur);
			Button.RIGHT.waitForPressAndRelease();
			dos.close();
			btc.close();
			LCD.clear();
			
		} catch (Exception e) {
		}
	}

}

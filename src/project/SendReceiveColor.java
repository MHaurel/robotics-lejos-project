package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;

public class SendReceiveColor {
	
	public int randomColorId() {
		Random r = new Random();
		return r.nextInt(Color.values().length - 1);
	}
	
	public void sendColorId(int valeur) {
		
		Button.RIGHT.waitForPressAndRelease();
		
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
	
	public Color receiveColorId() {
		
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

}

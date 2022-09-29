package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class GetColor implements Behavior {
	
	// RED, GREEN, BLUE, ORANGE, WHITE, BLACK

	// bandes noires = 1,5 et chaque case = 12
	
	EV3ColorSensor cs;
	int[][] colorCalibrated = new int[6][3];
	
	public GetColor(EV3ColorSensor cs) {
		this.cs = cs;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.RIGHT.isDown();
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		System.out.println("press DOWN");
		int colorCalibrated = 0;
		while (colorCalibrated < 6) {
			// Demande la couleur a afficher
			// Récupère code RGB de la couleur lorsque l'on appuie sur un bouton
			Button.DOWN.waitForPressAndRelease();
			
			while (!Button.DOWN.isDown()) {
				int[] colors = this.getColor();
				System.out.println("RGB = " + " " + colors[0] + " " + colors[1] + " " + colors[2]);
				this.colorCalibrated[colorCalibrated] = colors;
			}

			System.out.println("Just Calibrated color " + colorCalibrated);
			
			colorCalibrated++;
		}
		for(int i = 0; i < 6;i++) {
			for (int j = 0; j < 3;j++) {
				System.out.print(this.colorCalibrated[i][j] + " ");
			}
			System.out.println();
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
		LocalDateTime now = LocalDateTime.now();  
				
		String filename = "calibrated_colors-" + dtf.format(now).toString().split(" ")[0] + "_" + dtf.format(now).toString().split(" ")[1] + ".txt";
				
		System.out.println(filename);
				
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("calibrated_colors", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Write RGB codes here
		String[] colors = {"RED", "GREEN", "BLUE", "ORANGE", "WHITE", "BLACK"};
		for(int i = 0; i < 6; i++) {
			writer.println(colors[i] + " : " + this.colorCalibrated[i][0] + " " + this.colorCalibrated[i][1] + " " + this.colorCalibrated[i][2]);
		}
				
		writer.close();
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
	private int[] getColor() {
		SampleProvider sp = this.cs.getRGBMode();
		float[] sample = new float[3];
		sp.fetchSample(sample, 0);
		int[] colors = new int[3];
		colors[0] = (int)(sample[0] * 255);
		colors[1] = (int)(sample[1] * 255);
		colors[2] = (int)(sample[2] * 255);
		return colors;
	}

	
	
}

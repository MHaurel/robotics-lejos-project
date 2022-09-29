package project;

import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class GetColor implements Behavior {
	
	// BLEU, VERT, ORANGE, ROUGE, BLANC, NOIR

	// bandes noires = 1,5 et chaque case = 12
	
	EV3ColorSensor cs;
	
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
		int colorCalibrated = 0;
		while (colorCalibrated < 6) {
			// Demande la couleur a afficher
			// Récupère code RGB de la couleur lorsque l'on appuie sur un bouton
			Button.DOWN.waitForPressAndRelease();
			
			while (!Button.DOWN.isDown()) {
				int[] colors = this.getColor();
				System.out.println("RGB = " + " " + colors[0] + " " + colors[1] + " " + colors[2]);
			}

			System.out.println("Just Calibrated color " + colorCalibrated++);
			
			colorCalibrated++;
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
	private int[] getColor() {
		this.cs.setCurrentMode("RGB");
		float[] sample = new float[this.cs.sampleSize()];
		this.cs.fetchSample(sample, 0);
		return new int[] {(int)(sample[0] * 255), (int)(sample[3] * 255), (int)(sample[2] * 255)};
	}

	
	
}

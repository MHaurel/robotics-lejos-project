package project;
import java.util.HashMap;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class ColorManagement {

	EV3ColorSensor cs;
	Pilot pilot;
	HashMap<Color, int[]> colorCalibrated;

	public ColorManagement(EV3ColorSensor cs, Pilot pilot) {
		this.cs = cs;
		this.pilot = pilot;
		this.colorCalibrated = new HashMap<Color, int[]>();
	}

	public void init() {
		// TODO Auto-generated method stub
		for (Color color : Color.values()) {
			System.out.println("Press DOWN to begin the calibration of the color: " + color + ". And RIGHT to confirm.");
			Button.DOWN.waitForPressAndRelease();
			
			int[] colors = null;
			
			System.out.println("Press RIGHT to confirm calibration");
			
			while (!Button.RIGHT.isDown()) {
				colors = this.getColor();
				System.out.println("RGB = " + " " + colors[0] + " " + colors[1] + " " + colors[2]);
//				pilot.setCalibratedColor(color, colors);
			}
			
			this.colorCalibrated.put(color, colors);
		}
		
	}

	private int[] getColor() {
		SampleProvider sp = this.cs.getRGBMode();
		float[] sample = new float[3];
		sp.fetchSample(sample, 0);
		int[] colors = new int[3];
		colors[0] = (int) (sample[0] * 255);
		colors[1] = (int) (sample[1] * 255);
		colors[2] = (int) (sample[2] * 255);
		return colors;
	}

	public int closestColor() {
		int[] colorActual = this.getColor();
		double distEucl;
		double distMin = 999;
		int colorId = 0;
		for (int i = 0; i < this.colorCalibrated.size(); i++) {
			distEucl = getEuclideanDistance(this.colorCalibrated.get(Color.values()[i]), colorActual);
			if (distEucl < distMin) {
				distMin = distEucl;
				colorId = i;
			}
		}
		return colorId;
	}

	private double getEuclideanDistance(int[] color1, int[] color2) {
		return Math.sqrt(Math.pow((color1[0] - color2[0]), 2) + Math.pow((color1[1] - color2[1]), 2)
				+ Math.pow((color1[2] - color2[2]), 2));
	}

}

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
	}

	public void init() {
		// TODO Auto-generated method stub
		for (Color color : Color.values()) {
			System.out.println("press DOWN");
			System.out.println("Color to calibrate : " + color);
			// Demande la couleur a afficher
			// Récupère code RGB de la couleur lorsque l'on appuie sur un bouton
			Button.DOWN.waitForPressAndRelease();
			int[] colors = null;
			
			while (!Button.DOWN.isDown()) {
				colors = this.getColor();
				System.out.println("RGB = " + " " + colors[0] + " " + colors[1] + " " + colors[2]);
//				this.colorCalibrated[numColor] = colors;
				pilot.setCalibratedColor(color, colors);
			}
			
			this.colorCalibrated.put(color, colors);

//			System.out.println("Just Calibrated color " + numColor);

//			numColor++;
		}

//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
//		LocalDateTime now = LocalDateTime.now();
//
//		String filename = "calibrated_colors-" + dtf.format(now).toString().split(" ")[0] + "_"
//				+ dtf.format(now).toString().split(" ")[1] + ".txt";
//
//		System.out.println(filename);
//
//		PrintWriter writer = null;
//		try {
//			writer = new PrintWriter("calibrated_colors", "UTF-8");
//		} catch (FileNotFoundException | UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Write RGB codes here
//		String[] colors = { "RED", "GREEN", "BLUE", "ORANGE", "WHITE", "BLACK" };
//		for (int i = 0; i < 6; i++) {
//			writer.println(colors[i] + " : " + this.colorCalibrated[i][0] + " " + 
//						   this.colorCalibrated[i][1] + " " + this.colorCalibrated[i][2]);
//		}
//
//		writer.close();
		
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
			distEucl = getEuclideanDistance(this.colorCalibrated.get(Color.RED), colorActual);
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

package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Test {
	
	static enum Color {
		RED, GREEN, BLUE, ORANGE, WHITE, BLACK
	};

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		// Initialisation array representing a map
		Map map = new Map();
		
		int[][] colorsCalibrated = {
				{255, 0, 0},
				{255, 0, 0},
				{255, 0, 0},
				{255, 0, 0},
				{255, 0, 0},
				{255, 0, 255},
		};
		
		// HashMap linking Colors to int array
		HashMap<Color, int[]> colors = new HashMap<>(); 
		colors.put(Color.RED, colorsCalibrated[0]);
		colors.put(Color.GREEN, colorsCalibrated[1]);
		colors.put(Color.BLUE, colorsCalibrated[2]);
		colors.put(Color.ORANGE, colorsCalibrated[3]);
		colors.put(Color.WHITE, colorsCalibrated[4]);
		colors.put(Color.BLACK, colorsCalibrated[5]);
		
		for (Color i : colors.keySet()) {
			  System.out.println(i + ": " + colors.get(i)[0] + ", " + colors.get(i)[1] + ", " + colors.get(i)[2]);
		}
	}
	
}

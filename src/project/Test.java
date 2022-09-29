package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
	
	enum Color {
		RED, BLUE, GREEN, BLACK, ORANGE, WHITE
	};

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		// Initialisation array representing a map
		Map map = new Map();
		
		// Serializing it
		Color c = Color.BLACK;
		System.out.println(c);
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 5; j++) {
				map.map[i][j] = Color.RED;
			}
		}
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");  
		LocalDateTime now = LocalDateTime.now();  
		
		String filename = "calibrated_colors-" + dtf.format(now).toString().split(" ")[0] + "_" + dtf.format(now).toString().split(" ")[1] + ".txt";
		
		System.out.println(filename);
		
		PrintWriter writer = new PrintWriter("calibrated_colors", "UTF-8");

		// Write RGB codes here
		writer.println("69 6 3"); // R G B
		writer.println("18 38 7");
		
		writer.close();
		
	}
	
}

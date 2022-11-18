package project;


import project.Color;

public class Map {
	
	Color[][] map = {
			{Color.BLUE, Color.BLUE,   Color.BLUE,   Color.BLUE,   Color.BLUE},
			{Color.BLUE, Color.ORANGE, Color.GREEN,  Color.GREEN,  Color.RED},
			{Color.BLUE, Color.ORANGE, Color.BLUE,   Color.GREEN,  Color.BLUE},
			{Color.BLUE, Color.GREEN,  Color.WHITE,  Color.GREEN,  Color.BLUE},
			{Color.BLUE, Color.GREEN,  Color.ORANGE, Color.ORANGE, Color.BLUE},
			{Color.BLUE, Color.GREEN,  Color.GREEN,  Color.GREEN,  Color.BLUE},
			{Color.RED,  Color.GREEN,  Color.BLUE,   Color.BLUE,   Color.BLUE},
	}; 
	
	public Map() {}
	
	public Color[][] getMap() {
		return this.map;
	}
	
	public Color[][] getReversedMap() {
		Color[][] reversedMap = new Color[map.length][map[0].length];
		
		int cpt_i = 0;
		for (int i = map.length - 1; i >= 0; i--) {
			Color[] temp = new Color[map[0].length]; // Temporary row
			int cpt_j = 0;
			for (int j = map[0].length - 1; j >= 0; j--) {
				temp[cpt_j] = map[i][j];
			}
			reversedMap[cpt_i] = temp;
		}
		
		return reversedMap;
	}
	
	public static void main(String[] args) {
		Map map = new Map();
		
		for (int i = 0; i <= map.getMap().length - 1; i++) {
			for (int j = 0; j <= map.getMap()[0].length - 1; j++) {
				System.out.print(map.getMap()[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		System.out.println("");
		System.out.println("===================");
		System.out.println("");
		
		Color[][] reversedMap = map.getReversedMap();
		
		for (int i = 0; i <= reversedMap.length - 1; i++) {
			for (int j = 0; j <= reversedMap[0].length - 1; j++) {
				System.out.println(reversedMap[i][j]);
			}
		}
	}
	
}

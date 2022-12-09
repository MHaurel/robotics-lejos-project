package project;

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
		int line = this.map.length;
		int column = this.map[0].length;
		Color[][] reversedMap = new Color[line][column];
		
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < column; j++) {
				reversedMap[i][j] = this.map[line - i - 1][column - j - 1];
			}
		}
		
		return reversedMap;
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[i].length; j++) {
				result += this.map[i][j] + " ";
			}
			result += System.lineSeparator();
		}
		return result;
	}
	
}

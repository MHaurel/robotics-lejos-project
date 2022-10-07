package project;

import java.io.Serializable;

import project.Test.Color;

public class Map implements Serializable {
	
	enum Color {
		RED, GREEN, BLUE, ORANGE, WHITE, BLACK
	}
	
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
	
}

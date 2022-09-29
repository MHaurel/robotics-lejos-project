package project;

import java.io.Serializable;

import project.Test.Color;

public class Map implements Serializable {

	Color[][] map = new Color[7][5];
	
	public Map() {
		
	}
	
	public Color[][] getMap() {
		return this.map;
	}
	
}

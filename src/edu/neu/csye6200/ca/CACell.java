package edu.neu.csye6200.ca;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CACell.java
 * @version Created at Nov 9, 2016 9:50:31 PM
 */
public class CACell {

	private double color[] = { 0, 0, 0 };

	public CACell() {
	}

	public double[] getColor() {
		return color;
	}

	public void setColor(double color[]) {
		for (int counter = 0; counter < color.length; counter++) {
			this.color[counter] = color[counter];
		}
	}
}

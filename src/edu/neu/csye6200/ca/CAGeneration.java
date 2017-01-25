package edu.neu.csye6200.ca;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CAGeneration.java
 * @version Created at Nov 9, 2016 9:50:40 PM
 */
public class CAGeneration {

	private ArrayList<CACell> Cells_A = new ArrayList<CACell>();
	private Color colors[] = { Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
	private double initiate[] = new double[3];
	private double black[] = new double[3];

	public CAGeneration(int CACells, int color) {
		initiate[0] = colors[color].getRed() * 0.75;
		initiate[1] = colors[color].getGreen() * 0.75;
		initiate[2] = colors[color].getBlue() * 0.75;
		black[0] = colors[color].getRed();
		black[1] = colors[color].getGreen();
		black[2] = colors[color].getBlue();
		for (int counter = 0; counter < CACells; counter++) {
			CACell cell = new CACell();
			if (counter == CACells / 2) {
				cell.setColor(black);
				// System.out.println("Black");
			} else {
				cell.setColor(initiate);
			}
			Cells_A.add(cell);
		}
	}

	public void Set_A(int index, CACell cell) {
		Cells_A.set(index, cell);
	}

	public void clear_A() {
		Cells_A.clear();
	}

	public void Add_A(CACell cell) {
		Cells_A.add(cell);
	}

	public CACell get_A(int index) {
		return Cells_A.get(index);
	}

	public ArrayList<CACell> get_A() {
		return Cells_A;
	}

}

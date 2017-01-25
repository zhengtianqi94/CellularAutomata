package edu.neu.csye6200.ca;

import java.awt.Color;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CARules.java
 * @version Created at Nov 9, 2016 9:50:57 PM
 */
public class CARules {

	private int rule_num = 0;
	private double temp = 0;
	private double red = 0;
	private double green = 0;
	private double blue = 0;
	private double[] cell = new double[3];
	CACell NewCell = new CACell();

	private Color c1 = null;
	private Color c2 = null;
	private Color c3 = null;
	private Color fc = null;
	private Color tc = null;

	public Color getTc() {
		return tc;
	}

	public void setTc(Color tc) {
		this.tc = tc;
	}

	public CARules(int num) {
		this.rule_num = num;
	}

	public CACell Get_By_Rule(CACell cell_1, CACell cell_2, CACell cell_3) {
		c1 = new Color((int) cell_1.getColor()[0], (int) cell_1.getColor()[1], (int) cell_1.getColor()[2]);
		c2 = new Color((int) cell_2.getColor()[0], (int) cell_2.getColor()[1], (int) cell_2.getColor()[2]);
		c3 = new Color((int) cell_3.getColor()[0], (int) cell_3.getColor()[1], (int) cell_3.getColor()[2]);
		switch (rule_num) {
		case 1: {
			if ((c1.getRed() == c2.getRed() && c2.getRed() == c3.getRed())
					&& (c1.getGreen() == c2.getGreen() && c2.getGreen() == c3.getGreen())
					&& (c1.getBlue() == c2.getBlue() && c2.getBlue() == c3.getBlue())) {
				fc = new Color(255, 255, 255);
			} else {
				fc = new Color((int) ((c1.getRed() + c2.getRed() + c3.getRed()) % 255),
						(int) ((c1.getGreen() + c2.getGreen() + c3.getGreen()) % 255),
						(int) ((c1.getBlue() + c2.getBlue() + c3.getBlue()) % 255));
			}
			cell[0] = fc.getRed();
			cell[1] = fc.getGreen();
			cell[2] = fc.getBlue();
			NewCell.setColor(cell);
		}
			break;
		case 2: {
			if ((c1.getRed() == c2.getRed() && c2.getRed() == c3.getRed())
					&& (c1.getGreen() == c2.getGreen() && c2.getGreen() == c3.getGreen())
					&& (c1.getBlue() == c2.getBlue() && c2.getBlue() == c3.getBlue())) {
				fc = new Color(tc.getRed(), tc.getGreen(), tc.getBlue());
			} else {
				fc = new Color((int) (((c1.getRed() + c2.getGreen() + c3.getBlue()) % 255 * Math.random())),
						(int) (((c1.getGreen() + c2.getRed() + c3.getGreen()) % 255 * Math.random())),
						(int) (((c1.getBlue() + c2.getBlue() + c3.getRed()) % 255 * Math.random())));
			}
			cell[0] = fc.getRed();
			cell[1] = fc.getGreen();
			cell[2] = fc.getBlue();
			NewCell.setColor(cell);
		}
			break;
		case 3: {
			red = (c1.getRed() + c2.getRed() + c3.getRed()) % 375;
			green = (c1.getGreen() + c2.getGreen() + c3.getGreen()) % 375;
			blue = (c1.getBlue() + c2.getBlue() + c3.getBlue()) % 375;
			if (red > tc.getRed()) {
				red = tc.getRed() * Math.random() / 2;
			}
			if (green > tc.getGreen()) {
				green = tc.getGreen() * Math.random() / 2;
			}
			if (blue > tc.getBlue()) {
				blue = tc.getBlue() * Math.random() / 2;
			}
			fc = new Color((int) (red), (int) (green), (int) (blue));
			cell[0] = fc.getRed();
			cell[1] = fc.getGreen();
			cell[2] = fc.getBlue();
			NewCell.setColor(cell);
		}
			break;
		}
		return NewCell;
	}
}

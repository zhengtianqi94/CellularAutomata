package edu.neu.csye6200.ca;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CACanvas.java
 * @version Created at Nov 9, 2016 9:51:10 PM
 */
public class CACanvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6427035413313939584L;
	private HashMap<String, ArrayList<CACell>> result = new HashMap<String, ArrayList<CACell>>();
	private ArrayList<CACell> cells = new ArrayList<CACell>();
	private int Time = -1;

	public void setResult(HashMap<String, ArrayList<CACell>> result) {
		// TODO Auto-generated method stub
		for (int counter_1 = 0; counter_1 < result.size(); counter_1++) {
			for (int counter_2 = 0; counter_2 < result.get(String.valueOf(counter_1)).size(); counter_2++) {
				this.result.put(String.valueOf(counter_1), result.get(String.valueOf(counter_1)));
			}
		}
	}

	public void setTime(int time) {
		this.Time = time;
	}

	public CACanvas() {

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (int counter = 0; counter < Time + 1; counter++) {
			cells = result.get(String.valueOf(counter));
			for (int inner_counter = 0; inner_counter < cells.size(); inner_counter++) {
				if ((int) cells.get(inner_counter).getColor()[0] == 0
						&& (int) cells.get(inner_counter).getColor()[1] == 0
						&& (int) cells.get(inner_counter).getColor()[2] == 0) {
					g.setColor(Color.WHITE);
					g.fillRect(inner_counter * 15 + 50, counter * 15, 12, 12);
				} else {
					g.setColor(new Color((int) cells.get(inner_counter).getColor()[0],
							(int) cells.get(inner_counter).getColor()[1],
							(int) cells.get(inner_counter).getColor()[2]));
					g.fillRect(inner_counter * 15 + 50, counter * 15, 12, 12);
				}
			}
		}
	}
}

package edu.neu.csye6200.ca;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CAGenerationSet.java
 * @version Created at Nov 9, 2016 9:50:50 PM
 */
public class CAGenerationSet {

	private Integer Time = 0;
	private Integer CACells = 0;
	private int rule_num = 0;
	private int color = 0;

	private HashMap<String, ArrayList<CACell>> result = new HashMap<String, ArrayList<CACell>>();
	private ArrayList<CACell> cells = null;
	private Color colors[] = { Color.GRAY, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };

	private CACell cell = null;

	public CAGenerationSet(Integer Time, Integer CACells, int color) {
		this.Time = Time;
		this.CACells = CACells;
		this.color = color;
	}

	public void setRule(int num) {
		this.rule_num = num;
	}

	public HashMap<String, ArrayList<CACell>> Generation() {

		CAGeneration CAG = new CAGeneration(CACells, color);
		CARules CAR = new CARules(rule_num);
		CAR.setTc(colors[color]);
		for (int t = 0; t < Time; t++) {
			cells = new ArrayList<CACell>();
			for (int i = 0; i < CACells; i++) {
				if (t == 0) {
					cells.add(CAG.get_A(i));
				}
				if (t != 0) {
					CAG.Set_A(i,
							CAR.Get_By_Rule(result.get(String.valueOf(t - 1)).get((CACells + i - 1) % CACells),
									result.get(String.valueOf(t - 1)).get(i),
									result.get(String.valueOf(t - 1)).get((i + 1) % CACells)));
					cell = new CACell();
					cell.setColor(CAG.get_A(i).getColor());
					cells.add(cell);
				}
			}
			if (t == 0) {
				result.put(String.valueOf(t), cells);
			} else {
				result.put(String.valueOf(t), cells);
			}
		}
		for (int counter = 0; counter < result.size(); counter++) {
			for (int counter_1 = 0; counter_1 < result.get(String.valueOf(counter)).size(); counter_1++) {
				System.out.print(result.get(String.valueOf(counter)).get(counter_1).getColor()[0] + ","
						+ result.get(String.valueOf(counter)).get(counter_1).getColor()[1] + ","
						+ result.get(String.valueOf(counter)).get(counter_1).getColor()[2] + " ");
			}
			System.out.println();
		}
		return result;
	}
}

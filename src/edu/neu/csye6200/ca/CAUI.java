package edu.neu.csye6200.ca;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Tianqi Zheng
 * @NUID 001213047
 * @File CAUI.java
 * @version Created at Nov 9, 2016 9:51:04 PM
 */
public class CAUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4160399609201006615L;

	private Thread thread = null;

	private JFrame frame = null;

	private JPanel mainPanel = null;
	private JPanel TextPanel = null;

	private JButton ButtonStop = null;

	private JButton ButtonStart = null;

	private JButton ButtonPause = null;
	private JButton ButtonContinue = null;

	private JButton Close = new JButton("Exit");

	private CACanvas CACanvas = null;

	private JTextField Cells = null;
	private JTextField Times = null;

	private JComboBox<String> Rules = null;
	private JComboBox<String> Colors = null;

	private Integer Time = 0;
	private Integer CACells = 0;

	private int rule = 0;
	private int color = 0;

	private HashMap<String, ArrayList<CACell>> result = new HashMap<String, ArrayList<CACell>>();
	private ArrayList<CACell> cells = new ArrayList<CACell>();

	public CAUI() {
		initUI();
	}

	public void initUI() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JPanel FinalPanel = new JPanel();
		FinalPanel.setLayout(new GridLayout(3, 1));
		FinalPanel.add(getTextPanel());
		FinalPanel.add(getMainPanel());
		CACanvas = new CACanvas();
		frame.setTitle("Cellular Automation APP");
		frame.setBackground(Color.WHITE);
		frame.setSize(800, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(FinalPanel, BorderLayout.NORTH);
		frame.add(CACanvas);

		frame.setVisible(true);
		frame.setResizable(true);
	}

	public JPanel getMainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		ButtonStop = new JButton("Stop");
		ButtonStart = new JButton("Start");
		ButtonPause = new JButton("Pause");
		ButtonContinue = new JButton("Continue");
		ButtonPause.addActionListener(this);
		ButtonContinue.addActionListener(this);
		Close.addActionListener(this);
		ButtonStart.addActionListener(this);
		ButtonStop.addActionListener(this);
		mainPanel.add(ButtonStart);
		mainPanel.add(ButtonPause);
		mainPanel.add(ButtonContinue);
		mainPanel.add(ButtonStop);
		mainPanel.add(Close);
		return mainPanel;
	}

	public JPanel getTextPanel() {
		TextPanel = new JPanel();
		TextPanel.setLayout(new FlowLayout());
		JLabel text1 = new JLabel();
		JLabel text2 = new JLabel();
		JLabel text3 = new JLabel();
		JLabel text4 = new JLabel();
		text1.setText("Cell Numbers:");
		text2.setText("Generation Times:");
		text3.setText("Select Rule:");
		text4.setText("Select Color:");
		Cells = new JTextField(5);
		Times = new JTextField(5);
		Rules = new JComboBox<String>();
		Rules.addItem("Rule 1");
		Rules.addItem("Rule 2");
		Rules.addItem("Rule 3");
		Colors = new JComboBox<String>();
		Colors.addItem("Gray");
		Colors.addItem("Red");
		Colors.addItem("Blue");
		Colors.addItem("Green");
		Colors.addItem("Yellow");
		Rules.addActionListener(this);
		Colors.addActionListener(this);
		TextPanel.add(text1);
		TextPanel.add(Cells);
		TextPanel.add(text2);
		TextPanel.add(Times);
		TextPanel.add(text3);
		TextPanel.add(Rules);
		TextPanel.add(text4);
		TextPanel.add(Colors);
		return TextPanel;
	}

	private void setResult(HashMap<String, ArrayList<CACell>> result) {
		// System.out.println(result.size());
		for (int counter_1 = 0; counter_1 < result.size(); counter_1++) {
			// System.out.println(result.get(String.valueOf(counter_1)).size());
			for (int counter_2 = 0; counter_2 < result.get(String.valueOf(counter_1)).size(); counter_2++) {
				cells.add(result.get(String.valueOf(counter_1)).get(counter_2));
				// System.out.println(result.get(String.valueOf(counter_2)).size());
			}
			this.result.put(String.valueOf(counter_1), cells);
			cells = new ArrayList<CACell>();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("Start")) {
			if (Cells.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "You must input the starting cells!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (Times.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "You must input the generation times!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else if (rule == 0) {
				JOptionPane.showMessageDialog(null, "You must select a rule for generate!", "ERROR",
						JOptionPane.ERROR_MESSAGE);
			} else {
				this.CACells = Integer.parseInt(Cells.getText());
				this.Time = Integer.parseInt(Times.getText());
				if (this.CACells > 20 && this.Time > 15) {
					frame.setSize(this.CACells * 20, this.Time * 20);
				}
				CAGenerationSet CAGS = new CAGenerationSet(Time, CACells, color);
				CAGS.setRule(rule);

				this.setResult(CAGS.Generation());
				CACanvas.setResult(result);

				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						int counter = 0;
						while (counter < result.size()) {
							for (int counter_1 = 0; counter_1 < result.size(); counter_1++) {
								for (int counter_2 = 0; counter_2 < result.get(String.valueOf(counter_1))
										.size(); counter_2++) {
									CACanvas.setTime(counter_1);
									CACanvas.repaint();
								}
								try {
									Thread.sleep(500);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								counter++;
							}
						}
						System.out.println("Thread End");
					}
				};
				thread = new Thread(runnable);
				thread.start();
			}
		}
		if (e.getSource() == ButtonStop) {
			thread.stop();
		}
		if (e.getSource() == ButtonPause) {
			thread.suspend();
		}
		if (e.getSource() == ButtonContinue) {
			thread.resume();
		}
		if (e.getSource() == Close) {
			frame.setVisible(false);
			System.exit(0);
		}
		if (e.getSource() == Rules) {
			int command = Rules.getSelectedIndex();
			switch (command) {
			case 0: {
				rule = 1;
			}
				break;
			case 1: {
				rule = 2;
			}
				break;
			case 2: {
				rule = 3;
				JOptionPane.showMessageDialog(null, "Try Color Gray & Yellow, that's amazing different!", "INFORMATION",
						JOptionPane.INFORMATION_MESSAGE);
			}
				break;
			}
		}
		if (e.getSource() == Colors) {
			int command = Colors.getSelectedIndex();
			switch (command) {
			case 0: {
				color = 0;
			}
				break;
			case 1: {
				color = 1;
			}
				break;
			case 2: {
				color = 2;
			}
				break;
			case 3: {
				color = 3;
			}
				break;
			case 4: {
				color = 4;
			}
				break;
			}
		}
	}

}

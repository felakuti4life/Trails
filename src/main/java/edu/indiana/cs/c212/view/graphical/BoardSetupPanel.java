package edu.indiana.cs.c212.view.graphical;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BoardSetupPanel extends JPanel implements ChangeListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1496578388560823352L;
	private static int DEFAULT_BOARD_SIZE = 10;
	private static int MAX_SUPPORTED_BOARD_SIZE = 25;
	private static int MIN_SUPPORTED_BOARD_SIZE = 3;
	private static JLabel DEFAULT_LABEL = new JLabel("Board Size");
	private JSlider boardSlider;
	private SpinnerNumberModel spinnerModel;
	private JSpinner boardSpinner;
	
	public BoardSetupPanel(){
		spinnerModel = new SpinnerNumberModel(DEFAULT_BOARD_SIZE,
												MIN_SUPPORTED_BOARD_SIZE,
												MAX_SUPPORTED_BOARD_SIZE,
												1);
		boardSpinner = new JSpinner(spinnerModel);
		
		
		boardSlider = new JSlider(JSlider.HORIZONTAL, 
				MIN_SUPPORTED_BOARD_SIZE, MAX_SUPPORTED_BOARD_SIZE, DEFAULT_BOARD_SIZE);
		boardSlider.addChangeListener(this);
		//set layout
		this.setAlignmentX(RIGHT_ALIGNMENT);
		this.setAlignmentY(BOTTOM_ALIGNMENT);
		this.setBackground(Color.LIGHT_GRAY);
		
		//add components
		this.add(DEFAULT_LABEL);
		this.add(boardSpinner);
		this.add(boardSlider);
		
	}
	
	public int getBoardSize(){
		return boardSlider.getValue();
	}

	
	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource().equals(boardSlider))
			boardSpinner.setValue(boardSlider.getValue());
		
		else if(e.getSource().equals(boardSpinner))
			boardSlider.setValue((Integer) boardSpinner.getValue());
	}
}

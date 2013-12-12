package edu.indiana.cs.c212.view.graphical;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edu.indiana.cs.c212.gameMechanics.PlayerColor;

public class PlayerChoicePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private String name;
	private JLabel label = new JLabel("Player Choice");
	private JRadioButton basicPlayerItem;
	private JRadioButton randomPlayerItem;
	private AbstractButton commandLinePlayerItem;
	private ButtonGroup radioGroup;
	
	public String playerTypeSelected;
	
	public PlayerChoicePanel(String name, PlayerColor color){
		this.name = name;
		String labelColor = (color == PlayerColor.RED) ? "red" : "blue";
		label.setText(name + " (" + labelColor + "):");
		radioGroup = new ButtonGroup();
		basicPlayerItem = new JRadioButton("Basic Player");
	    basicPlayerItem.addActionListener(this);
	    randomPlayerItem = new JRadioButton("Random Player");
	    randomPlayerItem.addActionListener(this);
	    commandLinePlayerItem = new JRadioButton("commandLinePlayer");
	    commandLinePlayerItem.addActionListener(this);
	    
	    radioGroup.add(basicPlayerItem);
	    radioGroup.add(randomPlayerItem);
	    radioGroup.add(commandLinePlayerItem);
	    
	    this.add(label);
	    this.add(basicPlayerItem);
	    this.add(randomPlayerItem);
	    this.add(commandLinePlayerItem);
	    
	    BoxLayout layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
	    this.setLayout(layout);
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPlayerType(){
		return this.playerTypeSelected;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ButtonModel model = radioGroup.getSelection();
		this.playerTypeSelected = (model == null) ? "" : model.getActionCommand();
		
	}
	
	
}

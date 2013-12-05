package edu.indiana.cs.c212.view.graphical;

import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;

public class PlayerChoicePanel extends JPanel {
	private String name;
	private JMenu menu;
	private JMenuItem basicPlayerItem;
	private JMenuItem randomPlayerItem;
	private ActionListener listener;
	
	public PlayerChoicePanel(String name){
		menu = new JMenu();
		basicPlayerItem = new JMenuItem("Basic Player");
	    basicPlayerItem.addActionListener(listener);
	    menu.add(basicPlayerItem);
	    randomPlayerItem = new JMenuItem("Random Player");
	    randomPlayerItem.addActionListener(listener);
	    menu.add(randomPlayerItem);
	}
	
}

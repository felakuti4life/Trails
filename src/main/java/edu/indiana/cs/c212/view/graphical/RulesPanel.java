package edu.indiana.cs.c212.view.graphical;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;

public class RulesPanel extends JPanel {
	
	private static final long serialVersionUID = 1078636271724728185L;
	private static String[] rulesList = {"Standard Rules", "Lose by Connecting Tile", "Overwrite Rules", "Random Move"};
	private static SpinnerListModel rulesModel = new SpinnerListModel(rulesList);
	private JSpinner rulesSpinner;
	
	public RulesPanel(){
		this.rulesSpinner = new JSpinner(rulesModel);
		this.add(rulesSpinner);
		
	}
	
	public String getRules(){
		return (String) this.rulesSpinner.getValue();
	}
}

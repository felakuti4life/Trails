package edu.indiana.cs.c212.view.graphical;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import edu.indiana.cs.c212.board.Board;

public class BoardPanel extends JPanel implements ActionListener, Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590536940878831526L;
	protected Board board;
	protected Point chosenXY;
	
	private List<HexTile> hexTileList = new ArrayList<HexTile>();
	
	public BoardPanel(Board board){
		this.board = board;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

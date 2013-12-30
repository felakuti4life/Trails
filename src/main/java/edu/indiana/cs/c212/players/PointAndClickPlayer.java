package edu.indiana.cs.c212.players;

import java.awt.AWTEvent;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.indiana.cs.c212.board.Board;
import edu.indiana.cs.c212.gameMechanics.Move;
import edu.indiana.cs.c212.gameMechanics.PlayerColor;
//import edu.indiana.cs.c212.view.graphical.HexTile;

public class PointAndClickPlayer extends AbstractPlayer implements AWTEventListener {
    
    private Point input;
    private String playerName = "Pointer and Clicker";
    
    public PointAndClickPlayer (PlayerColor player) {
	super(player);
	setName();
    }
    
    @Override
    public Move getMove(Board board, List<Move> legalMoves) {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	toolkit.addAWTEventListener(this, 0);
	
	//thread sleep?
	try {
		Thread.sleep(400000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Move move = new Move(input.x, input.y);
	
	toolkit.removeAWTEventListener(this);
	return move;
    }
    
    public void setName() {
    	String name = (String)JOptionPane.showInputDialog(
                "What's your name?\n",
                new JTextField(20));
    	
    	if ((name != null) && (name.length() > 0))
    		this.playerName = name;
    		
    }
    
    @Override
    public void eventDispatched (AWTEvent e) {
	input = (Point) e.getSource();
	Thread.currentThread().interrupt();
    }

	@Override
	public String getName() {
		return this.playerName;
	}
}


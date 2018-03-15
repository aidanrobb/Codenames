package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import code.Board;
import code.Location;
import code.ManageTurns;
import gui.GUI;

public class Driver implements Runnable {

	private Board _board;
//	private Location _location;
//	private  ManageTurns _manageTurns;
	private JFrame _window;
	private JPanel _mainPanel;
	
	public Driver(Board b) {
		_board = b; 
//		_location = l;
//		_manageTurns = m;
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		SwingUtilities.invokeLater(new Driver(b));
	}
	@Override
	public void run() {
		_window = new JFrame("CodeNames");
		_mainPanel = new JPanel();
		_window.getContentPane().add(_mainPanel);

//		new GUI(_model, _mainPanel, this);
		
		_window.setVisible(true);
		_window.pack();
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}

}

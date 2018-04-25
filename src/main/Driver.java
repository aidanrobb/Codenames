package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import code.Board;
import code.GreenBoard;
import code.Location;
import code.ManageTurns;
import gui.GUI;

public class Driver implements Runnable{

	/* 
	 * Board for this class to access its methods.
	 */
	private Board _board;
	/* 
	 * Board for this class to access its methods.
	 */
	private GreenBoard _greenBoard;
	
//	private Location _location;
//	private ManageTurns _manageTurns;
	
	/*
	 * JFrame to run game in.
	 */
	private JFrame _window;
	
	/*
	 * JPanel to store functionality of the game in.
	 */
	private JPanel _mainPanel;
	
	/*
	 * Drive to instantiate the board class in main method.
	 */
	public Driver(Board b, GreenBoard g) {
		_board = b; 
		_greenBoard = g;
//		_location = l;
//		_manageTurns = m;
	}
	/*
	 * Drive to instantiate the board class in main method.
	 */
//	public Driver(GreenBoard g) {
//		_greenBoard = g; 
////		_location = l;
////		_manageTurns = m;
//	}
	
	/*
	 * Main method to run program.
	 * 
	 * @param board object to use
	 */
	public static void main(String[] args) {
		Board b = new Board();
		GreenBoard g = new GreenBoard();
		SwingUtilities.invokeLater(new Driver(b, g));
	}
	
	/*
	 * Instantiates the GUI methods and makes it so that the windows and functionality is visible.
	 * 
	 * @param standard String[] args array
	 */
	@Override
	public void run() {
		_window = new JFrame("Code Names");
		_mainPanel = new JPanel();
		_window.getContentPane().add(_mainPanel);
		
		GUI a = new GUI(_board, _mainPanel, this, _greenBoard);
		
		_window.setVisible(true);
		_window.pack();
		_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	}

	/*
	 * updates the JFrame, _window, to the latest version.
	 */
	public void updateJFrame() {
		_window.pack();
		_window.repaint();
	}
}

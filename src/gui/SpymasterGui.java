package gui;

import java.sql.Driver;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.Board;

public class SpymasterGui {

	private Board _board;
	private Driver _driver;
	private JPanel _mainPanel;
	private JPanel _messagePanel;
	private JTextArea msg;
	
	public SpymasterGui(Board b, JPanel mp, Driver d) {
		_driver = d;
		_board = b;
		
		_mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_messagePanel = new JPanel();
		msg = new JTextArea();
		msg.setEditable(false);
		msg.setText("Welcome");
		//FIX LATER
		if(_board.goodClue("") == false) {
			msg.setText("Clue is illegal, Try Again!");
		}
		else {
			msg.setText("Good Clue!");
		}
		
		
	}
}

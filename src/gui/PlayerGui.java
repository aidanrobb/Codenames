package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.Board;

public class PlayerGui {
	private Board _board;
	private main.Driver _driver;
	private JPanel _mainPanel;
	private JPanel _messagePanel;
	private JTextArea msg;
	
	public PlayerGui(Board b, JPanel mp, main.Driver d) {
		_driver = d;
		_board = b;
		
		_mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		_messagePanel = new JPanel();
		_mainPanel.add(_messagePanel);
		msg = new JTextArea();
		_messagePanel.add(msg);
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

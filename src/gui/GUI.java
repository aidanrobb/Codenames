package gui;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import GUIEventHandlers.SwitchTurn;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import code.Board;
import code.ManageTurns;

public class GUI extends JFrame implements ActionListener {
	private Board _board;
	private main.Driver _driver;
	private JPanel _mainPanel;
	private JPanel _messagePanel;
	private JTextArea msg;
	private JPanel _scorePanel;
	private ManageTurns _m = new ManageTurns();
	private JPanel _cardPanel;
	private JPanel _switchPanel;
	private JPanel _filePanel;
	private GetUserInput _getUserInput;
	
	// JMenu stuff
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	

	   public void actionPerformed(ActionEvent ae) { 
	      String choice = ae.getActionCommand(); 
	      if (choice.equals("Start")) {
	    	  	_board.startGame();
	      }
	      else if (choice.equals("Quit")) { 
	         System.exit(0); 
	      } 
	   } 
	
	public GUI(Board b, JPanel mp, main.Driver d) {
		super(""); 

		_driver = d;
		_board = b;
		
		_mainPanel = mp;
		_mainPanel.setLayout(new BoxLayout(_mainPanel, BoxLayout.Y_AXIS));
		
		// File 
//		 setBounds(40,40,600,480); 
//	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	      JMenuBar menuBar = new JMenuBar(); 
	      menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.X_AXIS));
	      menuBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
	      setJMenuBar(menuBar); 
	      JMenu file = new JMenu("File"); 
	      JMenuItem start = new JMenuItem("Start"); 
	      start.addActionListener(this); 
	      file.add(start); 
	      menuBar.add(file); 
	      JMenuItem quit = new JMenuItem("Quit"); 
	      quit.addActionListener(this); 
	      file.add(quit); 
	      _mainPanel.add(menuBar);
		
		
		_messagePanel = new JPanel();
		_mainPanel.add(_messagePanel);
		
		// message panel 
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
		
		//scorePanel
		_scorePanel = new JPanel();
		_scorePanel.setLayout(new FlowLayout());
		JLabel score = new JLabel("10-9");
		_scorePanel.add(score);
		JLabel teamColor = new JLabel("Red Team");
		_scorePanel.add(teamColor);
		
		JButton switchTurn = new JButton("Switch Turn");
//		switchTurn.addActionListener(new SwitchTurn(_m));
		switchTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int turn = _m.switchTurn();
				if(turn == 0) {
					teamColor.setText("Red Team");
				}
				else if(turn == 1){
					teamColor.setText("Blue Team");
				}
			}
		});
		
		_scorePanel.add(switchTurn);
		_mainPanel.add(_scorePanel);
		
		//cardPanel
		_cardPanel = new JPanel();
		_cardPanel.setLayout(new GridLayout(5,5));
		for (int i = 0; i <25; i++) {
			JButton cards = new JButton("Word");
			_cardPanel.add(cards);
			// WE WILL NEED TO ADD ACTION LISTENERS
			// AND BUTTON PROPERTIES, LATER
		}
		_mainPanel.add(_cardPanel);
		
		// switchPanel
		_switchPanel = new JPanel();
		_switchPanel.setLayout(new BoxLayout(_switchPanel, BoxLayout.X_AXIS));
		JButton spymasterButton = new JButton("Spymaster");
		JButton playerButton = new JButton("Player");
		// NEED TO DO ACTIONLISTERS
		_switchPanel.add(spymasterButton);
		_switchPanel.add(playerButton);
		_mainPanel.add(_switchPanel);
		
		// IF BELOW IS UNCOMMENTED, IT BREAKS EVERYTHING!!!
//		_getUserInput.input();
		
		JPanel input = new JPanel();
		_mainPanel.add(input);
		JTextField box = new JTextField(" Enter Clue");
		input.add(box);
		JButton enter = new JButton("Enter");
		input.add(enter);
		String answer = box.getText();
		
		enter.addActionListener(new ActionListener(){
   			public void actionPerformed(ActionEvent e){
      		String getValue = box.getText();
      		boolean clue = _board.goodClue(getValue);
      		if(clue) {
      			msg.setText("Clue is Valid");
      		}
      		else{
      			msg.setText("Clue is invalid");
      		}
			
   		   }
		});
		
		JTextField count = new JTextField(" Enter Count");
		input.add(count);
		JButton enter2 = new JButton("Enter Count");
		input.add(enter2);
		String countAnswer = box.getText();
	}
	
}

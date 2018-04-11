package gui;

import java.awt.Color;
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
import code.Location;
import code.ManageTurns;

public class GUI extends JFrame implements ActionListener {
	private Board _board;
	private main.Driver _driver;
	private JPanel _mainPanel;
	private JPanel _messagePanel;
	private JLabel msg;
	private JPanel _scorePanel;
	private ManageTurns _m = new ManageTurns();
	private JPanel _cardPanel;
	private JPanel _switchPanel;
	private JPanel _filePanel;
	private GetUserInput _getUserInput;
	private boolean spymaster = false;
	private String codeName;
	private JLabel message;
	
	
	
	// JMenu stuff
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;


	public void actionPerformed(ActionEvent ae) { 
		String choice = ae.getActionCommand(); 
		if (choice.equals("Start")) {
			_board = new Board();
			_board.startGame();
			update();
			msg.setText("New Game");
		}
		else if (choice.equals("Quit")) { 
			System.exit(0); 
		} 
	} 

	public GUI(Board b, JPanel mp, main.Driver d) {
		super(""); 

		_driver = d;
		_board = b;
		//		_board.boardLocations(b.randomNames(b.readFile("GameWords.txt")));
		_board.startGame();

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

		// Spymaster/player message panel 
		msg = new JLabel();
		_messagePanel.add(msg);
		msg.setText("Welcome");

		//clue message 
		JPanel _clueMsg = new JPanel(); 
		_mainPanel.add(_clueMsg);
		
		message = new JLabel();
		_clueMsg.add(message);
		message.setText("Start Game");
		
		
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

		JButton cards;
//		for(Location s : _board.getBoard()) {
//			cards = new JButton();
//			String agent = "";
//			if(s.getPerson() == 0) {
//				agent = "RED";
//			}
//			else if(s.getPerson() == 1) {
//				agent = "BLUE";
//			}
//			else if(s.getPerson() == 2) {
//				agent = "CIVILIAN";
//			}
//			else if(s.getPerson() == 3) {
//				agent = "ASSASSIN";
//			}
//			if(spymaster) {
//				cards = new JButton("<html>" + s.getCodename() + "<br/>" + agent + "</html>");
//				update();
//				
//			}
//			else if(!spymaster) {
//				cards = new JButton(s.getCodename());
//				update();
//			}
//			update();
//			_cardPanel.add(cards);
//			ADD ACTIONLISTENER
//		}
		_mainPanel.add(_cardPanel);

		// switchPanel
		_switchPanel = new JPanel();
		_switchPanel.setLayout(new BoxLayout(_switchPanel, BoxLayout.X_AXIS));
		JButton spymasterButton = new JButton("Spymaster");
		spymasterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spymaster = true;
				update();
				//IF COMMENTED OUT DOESN'T WORK 
				msg.setText("Spymaster View");
			}
		});

		JButton playerButton = new JButton("Player");
		playerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				spymaster = false;
				update();
				msg.setText("Player View");
			}
		});
	
		_switchPanel.add(spymasterButton);
		_switchPanel.add(playerButton);
		_mainPanel.add(_switchPanel);

		// IF BELOW IS UNCOMMENTED, IT BREAKS EVERYTHING!!!
		//		_getUserInput.input();

		JPanel input = new JPanel();
		_mainPanel.add(input);
		JTextField box = new JTextField(" Enter Clue", 10);
		input.add(box);
		JButton enter = new JButton("Enter Clue");
		input.add(enter);
		String answer = box.getText();

		enter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String getValue = box.getText();
				boolean clue = _board.goodClue(getValue);
				if(clue) {
					message.setText("Clue is Valid");
					codeName = getValue;
					update();
				}
				else{
					message.setText("Clue is invalid");
					update();
				}
			}
		});

		JTextField count = new JTextField(" Enter Count", 10);
		input.add(count);
		JButton enter2 = new JButton("Enter Count");
		input.add(enter2);
		String countAnswer = box.getText();

//		updateJFrameIfNotHeadless();
	}
	
	public void updateJFrameIfNotHeadless() {
		if (_driver != null) {
			_driver.updateJFrame();
		}
	}
	public void update() {
		_cardPanel.removeAll();
		JButton cards;
		for(Location s : _board.getBoard()) {
			cards = new JButton();
			String agent = "";
			if(s.getPerson() == 0) {
				agent = "RED";
			}
			else if(s.getPerson() == 1) {
				agent = "BLUE";
			}
			else if(s.getPerson() == 2) {
				agent = "CIVILIAN";
			}
			else if(s.getPerson() == 3) {
				agent = "ASSASSIN";
			}
			if(spymaster) {
				cards = new JButton("<html>" + s.getCodename() + "<br/>" + agent + "</html>");
				
			}
			else if(!spymaster) {
				cards = new JButton(s.getCodename());
				JButton j = cards;
				j.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (s.getPerson() == 0) {
							j.setText("RED");
							j.setForeground(Color.RED);
							j.setContentAreaFilled(false);
							j.setOpaque(true);
						}
						else if (s.getPerson() == 1) {
							j.setText("BLUE");
							j.setForeground(Color.BLUE);
							j.setContentAreaFilled(false);
							j.setOpaque(true);
						}
						else if (s.getPerson() == 2) {
							j.setText("CIVILIAN");
							j.setForeground(Color.GRAY);
							j.setContentAreaFilled(false);
							j.setOpaque(true);
						}
						else if (s.getPerson() == 3) {
							j.setText("ASSASSIN");
							j.setForeground(Color.MAGENTA);
							j.setContentAreaFilled(false);
							j.setOpaque(true);
//							_board.Assassin(l, m)
						}
					}
				});
				message.setText(codeName);
			}
			_cardPanel.add(cards);
//			ADD ACTIONLISTENER
			
			updateJFrameIfNotHeadless();
		}
	}

}

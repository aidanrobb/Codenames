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

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import code.Board;
import code.GreenBoard;
import code.Location;
import code.ManageTurns;

public class GUI extends JFrame implements ActionListener {
	private Board _board;
	private GreenBoard _greenBoard = new GreenBoard();
	private main.Driver _driver;
	private main.Driver _greenDriver;
	private JPanel _mainPanel;
	private JPanel _messagePanel;
	private JLabel msg;
	private JPanel _scorePanel;
	private ManageTurns _m = new ManageTurns();
	private JPanel _cardPanel;
	private JPanel _switchPanel;
	private JPanel _filePanel;
	private boolean spymaster = false;
	private String codeName;
	private JLabel message;
	private JPanel input;
	private JLabel teamColor = new JLabel();
	private JLabel score = new JLabel();
	JPanel _clueMsg = new JPanel();
	private JPanel input2;
	private String _count;
	private Boolean twoTeam;
	JButton switchTurn;
	JButton playerButton;
	JButton spymasterButton;
	
	// JMenu stuff
	JMenuBar menuBar;
	JMenu menu, submenu;
	JMenuItem menuItem;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;

	/*
	 * Action performed for the file menu
	 * Checks to see which input is pressed and what methods to run
	 *
	 * @param ActionEvent ae -- to check if the button was clicked
	 */
	public void actionPerformed(ActionEvent ae) { 
		String choice = ae.getActionCommand(); 
		if (choice.equals("2 Team")) {
			_board = new Board();
			_board.startGame();
			score.setText(_board.getRedCount() + " - " + _board.getBlueCount());
			_m = new ManageTurns();
			_m.resetPlayer();
			teamColor.setText("Red Team");
			msg.setText("2 Team");
			message.setText("Welcome");
			spymaster=true;
			twoTeam = true;
			switchTurn.setEnabled(true);
			playerButton.setEnabled(true);
			spymasterButton.setEnabled(true);
			update();
		}
		if (choice.equals("3 Team")) {
			_greenBoard = new GreenBoard();
			_greenBoard.startGame();
			score.setText(_greenBoard.getRedCount() + " - " + _greenBoard.getBlueCount() + " - " + _greenBoard.getGreenCount());
			_m = new ManageTurns();
			_m.resetPlayer();
			teamColor.setText("Red Team");
			msg.setText("3 Team");
			message.setText("Welcome");
			spymaster=true;
			twoTeam = false;
			switchTurn.setEnabled(true);
			playerButton.setEnabled(true);
			spymasterButton.setEnabled(true);
			greenUpdate();
		}
		else if (choice.equals("Quit")) { 
			System.exit(0); 
		} 
	} 

	/*
	 * Class constructor for the GUI class
	 * 
	 * @param Board b -- board to use board class methods
	 * @param JPanel mp -- panel to add everything to
	 * @param Driver d -- driver to run driver class methods
	 */
	public GUI(Board b, JPanel mp, main.Driver d, GreenBoard g) {
		super(""); 
		input = new JPanel();
		input2 = new JPanel();
		_driver = d;
		_board = b;
		_greenBoard = g;
		_greenDriver = new main.Driver(b, g);
		_greenBoard.startGame();
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
		JMenuItem startTwo = new JMenuItem("2 Team"); 
		startTwo.addActionListener(this); 
		file.add(startTwo); 
		menuBar.add(file); 
		JMenuItem startThree = new JMenuItem("3 Team"); 
		startThree.addActionListener(this); 
		file.add(startThree); 
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
		_clueMsg = new JPanel(); 
		_mainPanel.add(_clueMsg);
		
		message = new JLabel();
		_clueMsg.add(message);
		message.setText("Start Game");
		
		
		//scorePanel
		_scorePanel = new JPanel();
		_scorePanel.setLayout(new FlowLayout());
		score = new JLabel(_board.getRedCount() + " - " + _board.getBlueCount());
		_scorePanel.add(score);
		teamColor = new JLabel("Red Team");
		_scorePanel.add(teamColor);

		switchTurn = new JButton("Switch Turn");
		switchTurn.setEnabled(false);
		switchTurn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (twoTeam) {
					_m.switchTurn();
					if(_m.getPlayer() == 0) {
						teamColor.setText("Red Team");
					}
					else if(_m.getPlayer() == 1){
						teamColor.setText("Blue Team");
					}
					spymaster=true;
					update();
				} else {
					_m.switchTurnGreen();
					if(_m.getPlayer() == 0) {
						teamColor.setText("Red Team");
					}
					else if(_m.getPlayer() == 1){
						teamColor.setText("Blue Team");
					}
					else if(_m.getPlayer() == 4){
						teamColor.setText("Green Team");
					}
					spymaster=true;
					greenUpdate();
				}
					
				
			}
		});

		_scorePanel.add(switchTurn);
		_mainPanel.add(_scorePanel);

		//cardPanel
		_cardPanel = new JPanel();
		_cardPanel.setLayout(new GridLayout(5,5));


		_mainPanel.add(_cardPanel);

		// switchPanel
		_switchPanel = new JPanel();
		_switchPanel.setLayout(new BoxLayout(_switchPanel, BoxLayout.X_AXIS));
		spymasterButton = new JButton("Spymaster");
		spymasterButton.setEnabled(false);
		spymasterButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (twoTeam) {
					spymaster = true;
					update();
					//IF COMMENTED OUT DOESN'T WORK 
					msg.setText("Spymaster View");
				} else {
					spymaster = true;
					greenUpdate();
					//IF COMMENTED OUT DOESN'T WORK 
					msg.setText("Spymaster View");
				}
			}
		});

		playerButton = new JButton("Player");
		playerButton.setEnabled(false);
		playerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (twoTeam) {
					spymaster = false;
					update();
					msg.setText("Player View");
				} else {
					spymaster = false;
					greenUpdate();
					msg.setText("Player View");
				}
			}
		});
	
		_switchPanel.add(spymasterButton);
		_switchPanel.add(playerButton);
		_mainPanel.add(_switchPanel);

		// IF BELOW IS UNCOMMENTED, IT BREAKS EVERYTHING!!!
		//		_getUserInput.input();

		

//		updateJFrameIfNotHeadless();
	}
	
	/*
	 * makes sure the JFrame is the latest update from the Driver Class
	 */
	public void updateJFrameIfNotHeadless() {
		if (_driver != null) {
			_driver.updateJFrame();
		}
	}
	/*
	 * makes sure the JFrame is the latest update from the Driver Class that uses a GreenBoard as a parameter
	 */
	public void updateJFrameIfNotHeadlessGreen() {
		if (_greenDriver != null) {
			_greenDriver.updateJFrame();
		}
	}
	
	/*
	 * Checks to see if clue value is valid
	 * Checks to see if count value is valid
	 * Throws message if either are invalid
	 *
	 * Easter Egg is located in this method
	 */
	public void clueStuff() {
		_mainPanel.add(input2);
		JTextField count = new JTextField(" Enter Count", 10);
		input2.add(count);
		JButton enter2 = new JButton("Enter Count");
		input2.add(enter2);
		String countAnswer = count.getText();
		enter2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if (twoTeam) {
					String getValue2 = count.getText();
					int x = Integer.parseInt(getValue2);
					if(x>0 && x<26) {
						message.setText("Count is Valid");
						_count = getValue2;
						update();
						}
					else {	
						message.setText("Count must be between 0 and 25");
					}
				} else {
					String getValue2 = count.getText();
					int x = Integer.parseInt(getValue2);
					if(x>0 && x<26) {
						message.setText("Count is Valid");
						_count = getValue2;
						greenUpdate();
						}
					else {	
						message.setText("Count must be between 0 and 25");
					}
				}
			}
			
		
		
		});
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
				if (getValue.equalsIgnoreCase("bobsaget")) {
					_mainPanel.setBackground(Color.CYAN);
					_scorePanel.setBackground(Color.CYAN);
					_messagePanel.setBackground(Color.CYAN);
					_switchPanel.setBackground(Color.CYAN);
					_cardPanel.setBackground(Color.CYAN);
					input.setBackground(Color.CYAN);
					input2.setBackground(Color.CYAN);
					_clueMsg.setBackground(Color.CYAN);
				}

				if (twoTeam) {
					if(clue) {
						message.setText("Clue is Valid");
						spymaster=false;
						msg.setText("Player View");
						codeName = getValue;
						update();
					}
					else{
						message.setText(_board.getMsg());
						update();
					}
				} else {
					if(clue) {
						message.setText("Clue is Valid");
						spymaster=false;
						msg.setText("Player View");
						codeName = getValue;
						greenUpdate();
					}
					else{
						message.setText(_board.getMsg());
						greenUpdate();
					}
				}
			}
		});

	
	}
	
	/*
	 * Changes the appearance of the button that has been 
	 * selected based upon what is stored in the Location
	 *
	 * @param Location l -- Location value stored in JButton
	 * @param JButton colorButton -- JButton to change appearance
	 * 		based upon its Location's team
	 */
	public void textColor(Location l, JButton colorButton) {
		if (l.getPerson() == 0) {
//			colorButton.setText("RED");
			colorButton.setForeground(Color.RED);
			colorButton.setContentAreaFilled(false);
			colorButton.setOpaque(true);
		}
		else if (l.getPerson() == 1) {
//			colorButton.setText("BLUE");
			colorButton.setForeground(Color.BLUE);
			colorButton.setContentAreaFilled(false);
			colorButton.setOpaque(true);
		}
		else if (l.getPerson() == 2) {
			colorButton.setText("CIVILIAN");
			colorButton.setForeground(Color.GRAY);
			colorButton.setContentAreaFilled(false);
			colorButton.setOpaque(true);
		}
		else if (l.getPerson() == 3) {
			colorButton.setText("ASSASSIN");
			colorButton.setForeground(Color.MAGENTA);
			colorButton.setContentAreaFilled(false);
			colorButton.setOpaque(true);
//			message.setText(_board.Assassin(l, _m));
		}
	}
	
	/*
	 * Clears the panels out and updates the information in the
	 * panels and boxes based upon what the user has inputed
	 */
	public void update() {
		_cardPanel.removeAll();
		JButton cards;
		input.removeAll();
		input2.removeAll();
		if (spymaster) {
			clueStuff();
		}
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
				if (s.getRevealed()==false) {
					cards = new JButton("<html>" + s.getCodename() + "<br/>" + agent + "</html>");
				} else {
					cards = new JButton(agent);
					textColor(s, cards);

				}
				
			}
			else if(!spymaster) {
				if (s.getRevealed()==false) {
					cards = new JButton(s.getCodename());
					JButton j = cards;
					j.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							_board.locationIsValid(s);
							message.setText(_board.winningStateString());

							if (s.getPerson() == 0) {
								j.setText("RED");
								textColor(s, j);
								score.setText(_board.getRedCount() + " - " + _board.getBlueCount());
								j.setEnabled(false);
								if (_m.getPlayer()==1) {
									_m.setPlayer(0);
									spymaster=true;
									msg.setText("Spymaster View");
									teamColor.setText("Red Team");
									message.setText("Blue turn ended - Red Team's Turn");
									update();
								}
//								j.setDisabledIcon(j.getIcon());
							}
							else if (s.getPerson() == 1) {
								j.setText("BLUE");
								textColor(s, j);
								score.setText(_board.getRedCount() + " - " + _board.getBlueCount());
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									spymaster=true;
									msg.setText("Spymaster View");
									teamColor.setText("Blue Team");
									message.setText("Red turn ended - Blue Team's Turn");
									update();
								}

							}
							else if (s.getPerson() == 2) {
								j.setText("CIVILIAN");
								textColor(s, j);
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									msg.setText("Spymaster View");
									teamColor.setText("Blue Team");
									message.setText("Red turn ended - Blue Team's Turn");
									spymaster=true;
									update();
								} else {
									_m.setPlayer(0);
									msg.setText("Spymaster View");
									teamColor.setText("Red Team");
									message.setText("Blue turn ended - Red Team's Turn");
									spymaster=true;
								}

							}
							else if (s.getPerson() == 3) {
								j.setText("ASSASSIN");
								textColor(s, j);
								message.setText(_board.Assassin(s, _m));
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									teamColor.setText("Blue Team");
									spymaster=true;
								} else {
									_m.setPlayer(0);
									teamColor.setText("Red Team");
									spymaster=true;
								}

							}
						}
					});
					message.setText("Clue: "+ codeName +  " " + "Count: " + _count);
				}
				else {
					// ADD COLORS
					cards = new JButton(agent);
					textColor(s, cards);

				}
			}
			_cardPanel.add(cards);
//			ADD ACTIONLISTENER
			
			updateJFrameIfNotHeadless();
		}
	}
	public void greenUpdate() {
		_cardPanel.removeAll();
		JButton cards;
		input.removeAll();
		input2.removeAll();
		if (spymaster) {
			clueStuff();
		}
		for(Location s : _greenBoard.getBoard()) {
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
			else if(s.getPerson() == 4) {
				agent = "GREEN";
			}
			if(spymaster) {
				if (s.getRevealed()==false) {
					cards = new JButton("<html>" + s.getCodename() + "<br/>" + agent + "</html>");
				} else {
					cards = new JButton(agent);
					textColor(s, cards);
					
				}
				
			}
			else if(!spymaster) {
				if (s.getRevealed()==false) {
					cards = new JButton(s.getCodename());
					JButton j = cards;
					j.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent e){
							_greenBoard.locationIsValidGreen(s);
							message.setText(_greenBoard.winningStateStringGreen());
							
							if (s.getPerson() == 0) {
								j.setText("RED");
								textColor(s, j);
								score.setText(_greenBoard.getRedCount() + " - " + _greenBoard.getBlueCount() + " - " 
										+ _greenBoard.getGreenCount());
								j.setEnabled(false);
								if (_m.getPlayer()==1) {
									_m.setPlayer(0);
									spymaster=true;
									msg.setText("Spymaster View");
									teamColor.setText("Red Team");
									message.setText("Blue turn ended");
									greenUpdate();
								}
//								j.setDisabledIcon(j.getIcon());
							}
							else if (s.getPerson() == 1) {
								j.setText("BLUE");
								textColor(s, j);
								score.setText(_greenBoard.getRedCount() + " - " + _greenBoard.getBlueCount() + " - " 
										+ _greenBoard.getGreenCount());
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									spymaster=true;
									msg.setText("Spymaster View");
									teamColor.setText("Blue Team");
									message.setText("Red turn ended");
									greenUpdate();
								}
								
							}
							else if (s.getPerson() == 2) {
								j.setText("CIVILIAN");
								textColor(s, j);
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									msg.setText("Spymaster View");
									teamColor.setText("Blue Team");
									message.setText("Red turn ended");
									spymaster=true;
									greenUpdate();
								} else if (s.getPerson() == 4) {
									j.setText("GREEN");
									textColor(s, j);
									score.setText(_greenBoard.getRedCount() + " - " + _greenBoard.getBlueCount() + " - " 
											+ _greenBoard.getGreenCount());
									j.setEnabled(false);
									if (_m.getPlayer()==0) {
										_m.setPlayer(1);
										spymaster=true;
										msg.setText("Spymaster View");
										teamColor.setText("Blue Team");
										message.setText("Red turn ended");
										greenUpdate();
									}
									
								} else {
									_m.setPlayer(0);
									msg.setText("Spymaster View");
									teamColor.setText("Red Team");
									message.setText("Blue turn ended");
									spymaster=true;
								}
								
							}
							else if (s.getPerson() == 3) {
								j.setText("ASSASSIN");
								textColor(s, j);
								message.setText(_greenBoard.Assassin(s, _m));
								j.setEnabled(false);
								if (_m.getPlayer()==0) {
									_m.setPlayer(1);
									teamColor.setText("Blue Team");
									spymaster=true;
								} else {
									_m.setPlayer(0);
									teamColor.setText("Red Team");
									spymaster=true;
								}
								
							}
						}
					});
					message.setText("Clue: "+ codeName +  " " + "Count: " + _count);
				}
				else {
					// ADD COLORS
					cards = new JButton(agent);
					textColor(s, cards);
					
				}
			}
			_cardPanel.add(cards);
//			ADD ACTIONLISTENER
			
			
			// HMMMMMMMMMMMMMMM????
			updateJFrameIfNotHeadless();
		}
	}

}

package GUIEventHandlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import code.ManageTurns;


// This might be a disaster
public class SwitchTurn implements ActionListener{
	private ManageTurns _mt;
	
	public SwitchTurn(ManageTurns mt) {
		_mt = mt;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		_mt.switchTurn();
	}
}

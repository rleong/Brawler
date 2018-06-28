package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ButtonType;
import view.PanelType;
import view.WindowPanel;

public class PanelSwitch implements ActionListener{
	
	private final WindowPanel panel;
	private PanelType panelState;
	private Boolean gameState = false;
	
	public PanelSwitch(WindowPanel panel, PanelType panelType, ButtonType button) {
		this.panel = panel;
		panelState = panelType;
		switch(button) {
		case START:
			if(panelState.equals(PanelType.MAINPANEL)) {
				panelState = PanelType.CHARACTERPANEL;
			}
			else if (panelState.equals(PanelType.CHARACTERPANEL)) {
				panelState = PanelType.MAPPANEL;
			}
			else if (panelState.equals(PanelType.MAPPANEL)) {
				panelState = PanelType.GAMEPANEL;
				gameState = true;
			}
			else {
				panelState = PanelType.MAINPANEL;
			}
			break;
		case JOIN:
			panelState = PanelType.JOINPANEL;
			break;
		case OPTIONS:
			panelState = PanelType.OPTIONSPANEL;
			break;
		case CANCEL:
			if (panelState.equals(PanelType.MAPPANEL)) {
				panelState = PanelType.CHARACTERPANEL;
			}
			else if (panelState.equals(PanelType.GAMEPANEL)) {
				panelState = PanelType.CHARACTERPANEL;
				gameState = false;
			}
			else {
				panelState = PanelType.MAINPANEL;
			}
			break;
			default:
				panelState = PanelType.MAINPANEL;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		panel.switchPanels(panelState, gameState);
	}

}
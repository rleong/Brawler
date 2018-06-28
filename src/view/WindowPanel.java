package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.ButtonType;
import model.PanelType;

public class WindowPanel {

	// Panel Attributes
	private String panelName;
	private String displayMessage;
	private JTextArea textArea;
	private PanelType panelState;
	
	// Frames and Panels
	private JFrame frame;
	private JPanel windowScreen;
	private JPanel windowOptions;
	
	// Host Information
	
	public WindowPanel(String panelName) {
		//Set all the values
		this.panelName = panelName;
		displayMessage = "Welcome to Brawler! Made by Russell Leong.";
		panelState = PanelType.MAINPANEL;
	}
	
	// ------- Methods ------- 
	
	// Setups Frame
	public void setUp(ActionListener mainStart, ActionListener mainJoin, ActionListener mainOptions,
			ActionListener joinStart, ActionListener joinCancel, ActionListener optionsStart,
			ActionListener optionsCancel, ActionListener charStart, ActionListener charCancel,
			ActionListener mapStart, ActionListener mapCancel, ActionListener gameCancel,
			ActionListener resultCancel, WindowListener exitListener) {
		frame = new JFrame(this.panelName);
		frame.addWindowListener(exitListener);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setupPanels(frame.getContentPane(), mainStart, mainJoin, mainOptions, joinStart, joinCancel,
				optionsStart, optionsCancel, charStart, charCancel, mapStart, mapCancel, gameCancel,
				resultCancel);
		
		refreshPanel();
	}
	
	// Refreshes things and repacks the screen size
	// Used for changing panel screens
	public void refreshPanel() {
		textArea.setText(displayMessage);
		frame.pack();
	}
	
	// Switch Panels
	public void switchPanels(ButtonType button) {
		boolean gameState = false;
		
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
		
		CardLayout temp0 = (CardLayout)(windowOptions.getLayout()),
				temp1 = (CardLayout)(windowScreen.getLayout());
		temp0.show(windowOptions, panelState.toString());
		if(gameState) {
			temp1.show(windowScreen, PanelType.TEXTSCREEN.toString());
		}else {
			temp1.show(windowScreen, PanelType.GAMESCREEN.toString());
		}
		addDisplayMessage(panelState.toString());
		refreshPanel();
	}
	
	// Adds text to the display message
	public void addDisplayMessage(String text) {
		displayMessage = displayMessage + "\n" + text;
		refreshPanel();
	}
	
	// Sets the display message text
	public void setDisplayMessage(String text) {
		displayMessage = text;
		refreshPanel();
	}
	
	// Used to make a nice CardLayout for the frame
	public void setupPanels(Container pane, ActionListener mainStart, ActionListener mainJoin, ActionListener mainOptions,
			ActionListener joinStart, ActionListener joinCancel, ActionListener optionsStart,
			ActionListener optionsCancel, ActionListener charStart, ActionListener charCancel,
			ActionListener mapStart, ActionListener mapCancel, ActionListener gameCancel,
			ActionListener resultCancel) {
		windowScreen = new JPanel(new CardLayout());
		windowOptions = new JPanel(new CardLayout());
		
		//Main Display
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(displayMessage);
		
		
		
		//Bottom Display
		//Main Menu
		JPanel mainMenuPanel = new JPanel();
		JButton startButton = new JButton("Start");
		startButton.addActionListener(mainStart);
		mainMenuPanel.add(startButton);
		JButton joinButton = new JButton("Join");
		joinButton.addActionListener(mainJoin);
		mainMenuPanel.add(joinButton);
		JButton optionsButton = new JButton("Options");
		optionsButton.addActionListener(mainOptions);
		mainMenuPanel.add(optionsButton);
		
		//Join Screen
		JPanel joinPanel = new JPanel();
		JButton joinCancelButton = new JButton("Cancel");
		joinCancelButton.addActionListener(joinCancel);
		joinPanel.add(joinCancelButton);
		JButton joinStartButton = new JButton("Confirm");
		joinStartButton.addActionListener(joinStart);
		joinPanel.add(joinStartButton);
		
		//Options Screen
		JPanel optionsPanel = new JPanel();
		JButton optionsCancelButton = new JButton("Cancel");
		optionsCancelButton.addActionListener(optionsCancel);
		optionsPanel.add(optionsCancelButton);
		JButton optionsStartButton = new JButton("Confirm");
		optionsStartButton.addActionListener(optionsStart);
		optionsPanel.add(optionsStartButton);		
		
		//Character Selection Screen
		JPanel characterPanel = new JPanel();
		JButton charCancelButton = new JButton("Cancel");
		charCancelButton.addActionListener(charCancel);
		characterPanel.add(charCancelButton);
		JButton charStartButton = new JButton("Confirm");
		charStartButton.addActionListener(charStart);
		characterPanel.add(charStartButton);
		
		//Map Selection Screen
		JPanel mapPanel = new JPanel();
		JButton mapCancelButton = new JButton("Cancel");
		mapCancelButton.addActionListener(mapCancel);
		mapPanel.add(mapCancelButton);
		JButton mapStartButton = new JButton("Start");
		mapStartButton.addActionListener(mapStart);
		mapPanel.add(mapStartButton);
		
		//Game Screen
		JPanel gamePanel = new JPanel();
		JButton gameCancelButton = new JButton("Forfeit");
		gameCancelButton.addActionListener(gameCancel);
		gamePanel.add(gameCancelButton);
		
		//Results
		JPanel resultsPanel = new JPanel();
		JButton resultsCancelButton = new JButton("Return to Main Menu");
		resultsCancelButton.addActionListener(resultCancel);
		resultsPanel.add(resultsCancelButton);
		
		//Add everything and set their enums
		windowScreen.add(textArea, PanelType.TEXTSCREEN.toString());
		windowScreen.add(textArea, PanelType.GAMESCREEN.toString());
		windowOptions.add(mainMenuPanel, PanelType.MAINPANEL.toString());
		windowOptions.add(joinPanel, PanelType.JOINPANEL.toString());
		windowOptions.add(optionsPanel, PanelType.OPTIONSPANEL.toString());
		windowOptions.add(characterPanel, PanelType.CHARACTERPANEL.toString());
		windowOptions.add(mapPanel, PanelType.MAPPANEL.toString());
		windowOptions.add(gamePanel, PanelType.GAMEPANEL.toString());
		windowOptions.add(resultsPanel, PanelType.RESULTPANEL.toString());
		
		//Finally, add these cards onto the frame
		pane.add(windowScreen, BorderLayout.PAGE_START);
		pane.add(windowOptions, BorderLayout.CENTER);
		
	}
	
	// Setters
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public void setPanelState(PanelType panelState) {
		this.panelState = panelState;
	}
	
	// Getters
	public String getPanelName() {
		return panelName;
	}
	public PanelType getPanelState() {
		return panelState;
	}
}

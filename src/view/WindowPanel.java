package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.PanelSwitch;
import controller.exitListener;
import model.Player;

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
	private Player hostPlayer;
	
	public WindowPanel(String panelName, Player hostPlayer) {
		//Set all the values
		this.panelName = panelName;
		this.hostPlayer = hostPlayer;
		displayMessage = "Welcome to Brawler! Made by Russell Leong.";
		panelState = PanelType.MAINPANEL;
	}
	
	// ------- Methods ------- 
	
	// Setups Frame
	public void setUp() {
		frame = new JFrame(this.panelName);
		frame.addWindowListener(new exitListener());
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		setupPanels(frame.getContentPane());
		
		refreshPanel();
	}
	
	// Refreshes things and repacks the screen size
	// Used for changing panel screens
	public void refreshPanel() {
		textArea.setText(displayMessage);
		frame.pack();
	}
	
	// Switch Panels
	public void switchPanels(PanelType panelType, Boolean gameStart) {
		CardLayout temp0 = (CardLayout)(windowOptions.getLayout()),
				temp1 = (CardLayout)(windowScreen.getLayout());
		temp0.show(windowOptions, panelType.toString());
		if(gameStart) {
			temp1.show(windowScreen, PanelType.TEXTSCREEN.toString());
		}else {
			temp1.show(windowScreen, PanelType.GAMESCREEN.toString());
		}
		this.panelState = panelType;
		addDisplayMessage(panelType.toString());
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
	public void setupPanels(Container pane) {
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
		startButton.addActionListener(new PanelSwitch(this, PanelType.MAINPANEL, ButtonType.START));
		mainMenuPanel.add(startButton);
		JButton joinButton = new JButton("Join");
		joinButton.addActionListener(new PanelSwitch(this, PanelType.MAINPANEL, ButtonType.JOIN));
		mainMenuPanel.add(joinButton);
		JButton optionsButton = new JButton("Options");
		optionsButton.addActionListener(new PanelSwitch(this, PanelType.MAINPANEL, ButtonType.OPTIONS));
		mainMenuPanel.add(optionsButton);
		
		//Join Screen
		JPanel joinPanel = new JPanel();
		JButton joinCancelButton = new JButton("Cancel");
		joinCancelButton.addActionListener(new PanelSwitch(this, PanelType.JOINPANEL, ButtonType.CANCEL));
		joinPanel.add(joinCancelButton);
		JButton joinStartButton = new JButton("Confirm");
		joinStartButton.addActionListener(new PanelSwitch(this, PanelType.JOINPANEL, ButtonType.START));
		joinPanel.add(joinStartButton);
		
		//Options Screen
		JPanel optionsPanel = new JPanel();
		JButton optionsCancelButton = new JButton("Cancel");
		optionsCancelButton.addActionListener(new PanelSwitch(this, PanelType.OPTIONSPANEL, ButtonType.CANCEL));
		optionsPanel.add(optionsCancelButton);
		JButton optionsStartButton = new JButton("Confirm");
		optionsStartButton.addActionListener(new PanelSwitch(this, PanelType.OPTIONSPANEL, ButtonType.START));
		optionsPanel.add(optionsStartButton);		
		
		//Character Selection Screen
		JPanel characterPanel = new JPanel();
		JButton charCancelButton = new JButton("Cancel");
		charCancelButton.addActionListener(new PanelSwitch(this, PanelType.CHARACTERPANEL, ButtonType.CANCEL));
		characterPanel.add(charCancelButton);
		JButton charStartButton = new JButton("Confirm");
		charStartButton.addActionListener(new PanelSwitch(this, PanelType.CHARACTERPANEL, ButtonType.START));
		characterPanel.add(charStartButton);
		
		//Map Selection Screen
		JPanel mapPanel = new JPanel();
		JButton mapCancelButton = new JButton("Cancel");
		mapCancelButton.addActionListener(new PanelSwitch(this, PanelType.MAPPANEL, ButtonType.CANCEL));
		mapPanel.add(mapCancelButton);
		JButton mapStartButton = new JButton("Start");
		mapStartButton.addActionListener(new PanelSwitch(this, PanelType.MAPPANEL, ButtonType.START));
		mapPanel.add(mapStartButton);
		
		//Game Screen
		JPanel gamePanel = new JPanel();
		JButton gameCancelButton = new JButton("Forfeit");
		gameCancelButton.addActionListener(new PanelSwitch(this, PanelType.GAMEPANEL, ButtonType.CANCEL));
		gamePanel.add(gameCancelButton);
		
		//Results
		JPanel resultsPanel = new JPanel();
		JButton resultsCancelButton = new JButton("Return to Main Menu");
		resultsCancelButton.addActionListener(new PanelSwitch(this, PanelType.RESULTPANEL, ButtonType.CANCEL));
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

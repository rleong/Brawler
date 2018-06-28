package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import model.ButtonType;
import model.PanelType;
import model.Server;
import view.WindowPanel;

public class Client {
	
	private WindowPanel window;
	private Server server;
	
	public Client(WindowPanel window, Server server) {
		
		this.window = window;
		this.server = server;
		
	}
	
	public void setupWindow() {
		
		window.setUp(mainStart, mainJoin, mainOptions, joinStart, joinCancel,
				optionsStart, optionsCancel, charStart, charCancel, mapStart,
				mapCancel, gameCancel, resultCancel, exitListener);
		
	}
	
	// Action Listeners
	
	private ActionListener mainStart = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.START);
			server.startServer();
		}
		
	};
	
	private ActionListener mainJoin = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.JOIN);
		}
		
	};
	
	private ActionListener mainOptions = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.OPTIONS);
		}
		
	};
	
	private ActionListener joinStart = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.START);
		}
		
	};
	
	private ActionListener joinCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
		}
		
	};
	
	private ActionListener optionsStart = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.START);
		}
		
	};
	
	private ActionListener optionsCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
		}
		
	};
	
	private ActionListener charStart = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.START);
		}
		
	};
	
	private ActionListener charCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
			server.stopServer();
		}
		
	};
	
	private ActionListener mapStart = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.START);
		}
		
	};
	
	private ActionListener mapCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
		}
		
	};
	
	private ActionListener gameCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
		}
		
	};
	
	private ActionListener resultCancel = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			window.switchPanels(ButtonType.CANCEL);
		}
		
	};
	
	// Window Listeners
	
	private WindowListener exitListener = new WindowAdapter() {
		
		@Override
		public void windowClosing(WindowEvent arg0) {
			int confirm = JOptionPane.showOptionDialog(null, 
					"Are you sure you want to exit?", "Exit Confirmation", 
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null, null, null);
			if(confirm == 0) {
				server.stopServer();
				System.exit(0);
			}
		}
		
	};

}

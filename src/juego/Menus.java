package juego;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Menus {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menus window = new Menus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Menus() {
		initialize();
	}
	
	private void initialize() {
		frame.setBounds(100, 100, 450, 300);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setFocusable(true);
	    frame.setLocationRelativeTo(null);
	    frame.setBackground(Color.BLACK);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void correr() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menus window = new Menus();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

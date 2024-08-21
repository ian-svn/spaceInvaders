package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    final private static Integer ALTO = 900;
    final private static Integer ANCHO = 700;
    static JFrame frame = new JFrame("Space Invaders");
    static Juego juego = new Juego();

    private Timer timer;
    private NaveInvadida nave;

    public Juego() {
    	nave = new NaveInvadida(ALTO, ANCHO);

        addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				nave.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				nave.keyReleased(e);
			}
        	
        });
        
        timer = new Timer(10, this);
        timer.start();
        setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        ImageIcon cancha = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
        g.drawImage(cancha.getImage(), 0, 0, getWidth(), getHeight(), this);
        
        nave.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        nave.moverse();
        
        
        repaint();
    }

    public static void main(String[] args) {
        juego.setBackground(new Color(0, 0, 0));
        frame.setBackground(new Color(0, 0, 0));
        frame.add(juego);
        frame.setSize(ANCHO, ALTO);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

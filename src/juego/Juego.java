package juego;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 600;
    private static final int ALTO = 700;
    private static JFrame frame = new JFrame("Space Invaders");
    private static Juego juego = new Juego();
    
    private Integer nivel=1;
    private Integer aux=0;
    private Boolean disparo;
    private Timer timer;
    private NaveInvadida nave;
    private ImageIcon fondo;
    private Integer EspMovEnemigos;
    
    private List<NaveInvasora> enemigos = new ArrayList<>();

    public Juego() {
        nave = new NaveInvadida(ANCHO, ALTO);

        fondo = new ImageIcon(getClass().getResource("/gif/fondo.gif"));
        
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
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
        
        if(nivel==1&&aux==0) {
        	for(int x = 0;x<30;x++) {
        		int posX=40;
        		if(x<10) {
        			posX+=48*x;
             	    enemigos.add(new NaveInvasora(posX,30,ANCHO, ALTO));
        		} else if(x<20) {
        			posX+=48*(x-10);
             	    enemigos.add(new NaveInvasora(posX,60,ANCHO, ALTO));
        		} else if(x<30) {
        			posX+=48*(x-20);
             	    enemigos.add(new NaveInvasora(posX,90,ANCHO, ALTO));
        		}
            }
        } else if(nivel==2&&aux==0) {
        	
        } else if(nivel==3&&aux==0) {
        	
        }

        nave.paint(g);
        
        aux=1;
        
        for(int x = 0;x<enemigos.size();x++) {
     	    enemigos.get(x).paint(g);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	for(int x = 0;x<enemigos.size();x++) {
    		enemigos.get(x).moverse();
    	}
        nave.moverse();
        repaint();
    }

    public static void main(String[] args) {
        frame.add(juego);
        frame.setSize(ANCHO, ALTO);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        juego.requestFocusInWindow();
    }
}

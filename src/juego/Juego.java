package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
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
    private Boolean MovimientoHorizontal=true;
    private Boolean MovimientoVertical=false;
    
    
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
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=66*x;
             	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));
        		} else if(x>7&&x<=7*2) {
        			posX+=66*(x-7);
             	    enemigos.add(new NaveInvasora(posX,80,ANCHO, ALTO,this));
        		} else if(x>7*2&&x<=7*3) {
        			posX+=66*(x-7*2);
             	    enemigos.add(new NaveInvasora(posX,120,ANCHO, ALTO,this));
        		} else if(x>7*3&&x<=7*4) {
        			posX+=66*(x-7*3);
             	    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));
        		} else if(x>7*4&&x<=7*5) {
        			posX+=66*(x-7*4);
             	    enemigos.add(new NaveInvasora(posX,200,ANCHO, ALTO,this));
        		}
            }
        } else if(nivel==2&&aux==0) {
        	System.out.println("Ganaste!");

            g.drawString("Ganaste!",250,350);
        }

        nave.paint(g);
        
        aux=1;
        
        for(int x = 0;x<enemigos.size();x++) {
     	    enemigos.get(x).paint(g);
        }
        

    	checkearPasarNivel();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
	 	List<DisparoNaveInvadida> disparos = nave.getDisparos();
	 	for(int x = 0; x < disparos.size();x++) {
	 		for(int y = 0 ; y < enemigos.size();y++) {
	 			disparos.get(x).choque(enemigos.get(y));
			}
		}
	
    	for(int x = 0;x<enemigos.size();x++) {
    		if(MovimientoVertical) {
    			enemigos.get(x).setY(enemigos.get(x).getY()+10);
    		}
    		enemigos.get(x).moverse();
    	}
    	
		MovimientoVertical=false;
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
    
    public Boolean getMovimientoH() {
    	return MovimientoHorizontal;	
    }
    public void setMovimientoH(Boolean MovimientoHorizontal) {
    	this.MovimientoHorizontal = MovimientoHorizontal;
    }
    public Boolean getMovimientoV() {
    	return MovimientoVertical;
    }
    public void setMovimientoV(Boolean MovimientoVertical) {
    	this.MovimientoVertical = MovimientoVertical;
    }
    private void checkearPasarNivel() {
    	int count=0;
    	for(NaveInvasora enemigo : enemigos) {
    		if(enemigo.getVivo()) {
    			count++;
    		}
    	}
    	if(count==0) {
    		nivel++;
    		aux=0;
    	}
    }
    
    private void drawTextWithOutline(Graphics g, String text, int x, int y, Color outlineColor, Color textColor) throws Exception {
    	InputStream is = getClass().getResourceAsStream("/fuentes/NewAmsterdam-Regular.ttf");
        Font win = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
        g.setFont(win);
        
        Color originalColor = g.getColor();
        g.setColor(outlineColor);
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    g.drawString(text, x + i, y + j);
                }
            }
        }

        g.setColor(textColor);
        g.drawString(text, x, y);
        g.setColor(originalColor);
    }

}

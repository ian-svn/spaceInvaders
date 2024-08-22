package juego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class NaveInvadida extends Nave {

    private final Integer vidas = 3;
    private final Integer XVEL = 5;

    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean wPressed = false;

    public NaveInvadida(Integer EspANCHO, Integer EspALTO) {
    	this.EspALTO = EspALTO;
    	this.EspANCHO = EspANCHO;
        x = EspANCHO / 2 - ANCHO/2;
        y = EspALTO-ALTO-ALTO/2-ALTO/8;
        System.out.println("Espalto: " +EspALTO+" eAnc:" + EspANCHO +" Anc:" + ANCHO +" AL:" + ALTO +" Y:" + y  + " X:" + x );
    }

    public NaveInvadida(Integer x,Integer y, Integer EspANCHO, Integer EspALTO) {
		this.x = x;
		this.y = y;
		this.EspANCHO = EspANCHO;
		this.EspALTO = EspALTO;
	}
    
    @Override
    public void paint(Graphics g) {
        ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveDefensora.png"));
        g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
    }

    @Override
    public void moverse() {
        if (aPressed) {
            if (x > 10) {
                x -= XVEL;
            }
        }
        if (dPressed) {
            if (x < EspANCHO-ANCHO-25) { 
                x += XVEL;
            }
        }
        if (wPressed) {
            if (x < EspANCHO-ANCHO-25) { 
                x += XVEL;
            }
        }
    }

    @Override
    public void disparar() {
    	
    }

    @Override
    public void destruirse() {

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) aPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_D) dPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_W) wPressed = true;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) aPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_D) dPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_W) wPressed = false;
    }
    
    

    public Integer getVidas() {
        return vidas;
    }
    
    public Integer getAncho() {
    	return ANCHO;
    }
    public Integer getAlto() {
    	return ALTO;
    }

	public void setPosicionInicial(int i, int j) {
		
	}
    
    
    
}

package juego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class NaveInvadida extends Nave {

    private final Integer vidas = 3;
    private final Integer XVEL = 5;
    
    private boolean aPressed = false;
    private boolean dPressed = false;
    private boolean wPressed = false;
    private Integer temp = 20;
    
    List<DisparoNaveInvadida> disparos = new ArrayList<>();

    public NaveInvadida(Integer EspANCHO, Integer EspALTO) {
    	this.EspALTO = EspALTO;
    	this.EspANCHO = EspANCHO;
        x = EspANCHO / 2 - ANCHO/2;
        y = EspALTO-ALTO-ALTO/2-ALTO/8;
        //System.out.println("Espalto: " +EspALTO+" eAnc:" + EspANCHO +" Anc:" + ANCHO +" AL:" + ALTO +" Y:" + y  + " X:" + x );
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
        for(int x=0;x<disparos.size();x++) {
        	disparos.get(x).paint(g);
        }
    }

    @Override
    public void moverse() {
    	if(temp>0) {
        	temp-=1;
    	}
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
            if (temp==0) {
            	DisparoNaveInvadida disparo = new DisparoNaveInvadida(x,y,EspANCHO,EspALTO,ANCHO,ALTO);
            	disparos.add(disparo);
            	temp=20;
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
    
    public Rectangle2D getBoundsNaveInvadida() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
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
    
    public Integer getEspAncho() {
    	return EspANCHO;
    }
    public Integer getEspAlto() {
    	return EspALTO;
    }

    public List<DisparoNaveInvadida> getDisparos() {
    	return disparos;
    }
    
	public void setPosicionInicial(int i, int j) {
		
	}
    
    
    
}

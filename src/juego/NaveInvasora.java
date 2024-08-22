package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class NaveInvasora extends Nave{
	
	Integer temp=100;
	Boolean ladoAvanzar=true;
	//Integer EspCambioX1;
	//Integer EspCambioX2;
	
	public NaveInvasora(Integer x, Integer y, Integer EspANCHO, Integer EspALTO) {
		super(x, y, EspANCHO, EspALTO);
	}
	
	@Override
	public void paint(Graphics g) {
		ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveenemiga.png"));
		g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
		temp--;
	}
	
	@Override
	public void moverse() {
		if(x<EspANCHO) {
			ladoAvanzar=true;
		} 
		if(temp<=0&&ladoAvanzar) {
			x+=10;
			reset();
		} else if(temp<=0&&!ladoAvanzar) {
			x-=10;
			reset();
		}
		
    }
	//Rectangle2D DisparoNaveInvadidaHitbox = new Rectangle2D.Double(x, y, ANCHO, ALTO);
	@Override
	public void disparar() {
		
	}
	@Override
	public void destruirse() {
		
    	System.out.println("choque");
	}
	
	public void reset() {
		temp=100;
	}
	

    public Rectangle2D getBoundsNaveInvasora() {
        return new Rectangle2D.Double(x, y, 13, 70);
    }
    
    public Boolean getVivo() {
		return vivo;
	}
	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}
}

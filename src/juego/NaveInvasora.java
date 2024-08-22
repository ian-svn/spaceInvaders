package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class NaveInvasora extends Nave{
	
	public NaveInvasora(Integer x, Integer y) {
		super(x,y);
	}
	
	@Override
	public void paint(Graphics g) {
		ImageIcon nave = new ImageIcon(getClass().getResource("ruta(de"));
		g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
	}
	
	@Override
	public void moverse() {
		Rectangle2D DisparoNaveInvadidaHitbox = new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
	@Override
	public void disparar() {
		
	}
	@Override
	public void destruirse() {
		
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

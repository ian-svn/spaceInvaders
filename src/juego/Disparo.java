package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Disparo {
	
	final protected Integer ANCHO=10;
	final protected Integer ALTO=30;
	public Integer velY=8;
	protected Integer x;
	protected Integer y;
	protected Integer EspANCHO; 
	protected Integer EspALTO;
	protected Integer ancho_nave;
	protected Integer alto_nave;
	
	public Disparo() {
		
	}
	public Disparo(Integer x,Integer y) {
		this.x = x;
		this.y = y;
	}
	public Disparo(Integer x,Integer y, Integer EspANCHO, Integer EspALTO,Integer ancho_nave, Integer alto_nave) {
		this.x = x;
		this.y = y;	
		this.EspANCHO = EspANCHO; 
		this.EspALTO = EspALTO; 
		this.ancho_nave = ancho_nave;
		this.alto_nave = alto_nave;
	}
	
	public abstract void moverse();
	
	public abstract void paint(Graphics g);	
	
    public Rectangle2D getBoundsDisparo() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
}

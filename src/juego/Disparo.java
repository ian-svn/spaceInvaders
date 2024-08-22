package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public abstract class Disparo {
	
	final protected Integer ANCHO=2;
	final protected Integer ALTO=5;
	final private Integer VEL_Y=4;
	protected Integer x;
	protected Integer y;
	
	
	public Disparo() {
		
	}
	public Disparo(Integer x,Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public abstract void moverse();
	
	public abstract void paint(Graphics g);	
	
    public Rectangle2D getBoundsDisparo() {
        return new Rectangle2D.Double(x, y, 13, 70);
    }
}

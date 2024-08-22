package juego;

import java.awt.Graphics;

public abstract class Nave {
	final protected Integer ANCHO = 76;
	final protected Integer ALTO = 80;
	Integer EspANCHO;
	Integer EspALTO;
	
	Integer x;
	Integer y;
	
	Boolean vivo;

	public Nave() {
		
	}
	
	public Nave(Integer x,Integer y) {
		this.x = x;
		this.y = y;
	}
	
	public Nave(Integer x,Integer y, Integer EspANCHO, Integer EspALTO) {
		this.x = x;
		this.y = y;
		this.EspANCHO = EspANCHO;
		this.EspALTO = EspALTO;
	}

	public abstract void moverse();

	public abstract void disparar();

	public abstract void destruirse();
	
	public abstract void paint(Graphics g);
}
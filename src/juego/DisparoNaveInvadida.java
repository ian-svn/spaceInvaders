package juego;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class DisparoNaveInvadida extends Disparo{
	
	public DisparoNaveInvadida(Integer x,Integer y, Integer EspANCHO, Integer EspALTO, Integer ancho_nave, Integer alto_nave) {
		super(x,y,EspANCHO,EspALTO,ancho_nave,alto_nave);
	}
	
	@Override
	public void paint(Graphics g) {
		ImageIcon disparo = new ImageIcon(getClass().getResource("/imagenes/disparoDefensor.png"));
		g.drawImage(disparo.getImage(), x+ancho_nave/2-4, y-alto_nave/8, ANCHO, ALTO, null);
		moverse();
	}
	

	@Override
	public void moverse() {
		if(y<=EspALTO) {
			y-=10;
		}
	}
	
	
}

package juego;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class DisparoNaveInvadida extends Disparo{
	
	public DisparoNaveInvadida(Integer x, Integer y) {
		super(x,y);
	}	
	
	@Override
	public void paint(Graphics g) {
		ImageIcon diaparo = new ImageIcon(getClass().getResource("ruta(de"));
		g.drawImage(diaparo.getImage(), x, y, ANCHO, ALTO, null);
	}
	

	@Override
	public void moverse() {
	}
}

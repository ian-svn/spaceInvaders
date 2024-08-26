package juego;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class DisparoNaveInvasora extends Disparo{
	
	Boolean desaparece=false;
	
	public DisparoNaveInvasora(Integer x,Integer y, Integer EspANCHO, Integer EspALTO, Integer ancho_nave, Integer alto_nave) {
		super(x,y,EspANCHO,EspALTO,ancho_nave,alto_nave);
		velY=4;
	}
	
	@Override
	public void paint(Graphics g) {
		if(!desaparece) {
			ImageIcon disparo = new ImageIcon(getClass().getResource("/imagenes/disparoInvasor.png"));
			g.drawImage(disparo.getImage(), x+ancho_nave/2-4, y+alto_nave/6, ANCHO, ALTO, null);
			moverse();
		}
	}
	

	@Override
	public void moverse() {
		if(y>=0&&y<=EspALTO-ALTO&&!desaparece) {
			y+=velY;
		} else if(y>EspALTO-ALTO){
			desaparece=true;
		}
	}
	
	public void choque(NaveInvadida nave) {
		if(nave.getVivo()&&!desaparece) {
			Rectangle2D disparoReact = new Rectangle2D.Double(x, y, ANCHO, ALTO);
			Rectangle2D naveReact = nave.getBoundsNaveInvadida();
			
			if(disparoReact.intersects(naveReact)) {
				nave.destruirse();
				desaparece=true;
			}
		}
	}
	
	public Boolean getDesaparece() {
		return desaparece;
	}
	public void desaparecer() {
		 desaparece=true;
	}
	
	
	public Rectangle2D getBoundsDisparoND() {
		return new Rectangle2D.Double(x+ancho_nave/2-4, y-alto_nave/8, ANCHO, ALTO);
    }
}

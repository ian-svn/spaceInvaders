package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class NaveInvasora extends Nave{
	
	Integer temp=100;

	Integer tempBasta=1000;
	Boolean vivo=true;
	Juego juego;
	Boolean aux;
	
	public NaveInvasora(Integer x, Integer y, Integer EspANCHO, Integer EspALTO, Juego juego) {
		super(x, y, EspANCHO, EspALTO);
		this.juego = juego;
	}
	
	@Override
	public void paint(Graphics g) {
		if(vivo) {
			ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveenemiga.png"));
			g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
			temp--;
		}
		if(x>EspANCHO||y>EspALTO) {
			vivo=false;
		}
		if(y>EspALTO-ALTO) {
			juego.perder();
		}
	}
	
	@Override
	public void moverse() {
		if(vivo&&x>=EspANCHO-ANCHO-ANCHO/4&&juego.getMovimientoH()) {
			juego.setMovimientoH(false);
			juego.setMovimientoV(true);
		} 
		if(vivo&&x<=0&&!juego.getMovimientoH()) {
			juego.setMovimientoH(true);
			juego.setMovimientoV(true);
		}
		if(vivo&&juego.getMovimientoH()) {
			x+=5;
		} else if(vivo&&!juego.getMovimientoH()) {
			x-=5;
		}
		if(temp<=0){
			reset();
		}
		//System.out.println("espAncho: " + (EspANCHO-ANCHO-ANCHO/4) + "temp: "+temp+" moveH: "+ juego.getMovimientoH() +" moveV: " + juego.getMovimientoV() + " x: " + x);
    }
	//Rectangle2D DisparoNaveInvadidaHitbox = new Rectangle2D.Double(x, y, ANCHO, ALTO);
	@Override
	public void destruirse() {
		juego.perkMenos(this);
		vivo=false;
	}
	
	public void reset() {
		temp=100;
	}
	

    public Rectangle2D getBoundsNaveInvasora() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
    
    public Integer getAncho() {
		return ANCHO;
	}
    public Boolean getVivo() {
		return vivo;
	}
	public void setVivo(Boolean vivo) {
		this.vivo = vivo;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
}

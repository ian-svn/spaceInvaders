package juego;

import java.awt.Color;
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
			//g.setColor(Color.red);
			//7g.fillRect(x,y, ANCHO, ALTO);
		}
		if(x>EspANCHO||y>EspALTO) {
			vivo=false;
		}
	}
	
	@Override
	public void moverse() {
		if(vivo){
			if(vivo&&x>=EspANCHO-ANCHO-ANCHO/5) {
				juego.setMovimientoH(false);
				juego.avanzar();
			} 
			if(vivo&&x<=0) {
				juego.setMovimientoH(true);
				juego.avanzar();
			}
			if(vivo&&juego.getMovimientoH()) {
				x+=5;
			} else if(vivo&&!juego.getMovimientoH()) {
				x-=5;
			}

			if(y+ALTO>=EspALTO) {
				juego.perder();
			}
			
			if(temp<=0){
				reset();
			}
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
	
	public void avanzar() {
		y+=6;
	}
}

package juego;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class NaveInvasoraDisparadora extends NaveInvasora{
	
	int tempDisparo=80;
    List<DisparoNaveInvasora> disparos = new ArrayList<>();
	
	public NaveInvasoraDisparadora(Integer x, Integer y, Integer EspANCHO, Integer EspALTO, Juego juego) {
		super(x, y, EspANCHO, EspALTO, juego);
		this.juego = juego;
	}

	@Override
	public void paint(Graphics g) {
		if(vivo) {
			ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveenemigadisparadora.png"));
			g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
			temp--;
			tempDisparo--;
		}
		for(int x=0;x<disparos.size();x++) {
			disparos.get(x).paint(g);
		}
		choque();
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
		if(vivo&&tempDisparo<=0) {
			disparar();
			tempDisparo=80;
		}
		//System.out.println("espAncho: " + (EspANCHO-ANCHO-ANCHO/4) + "temp: "+temp+" moveH: "+ juego.getMovimientoH() +" moveV: " + juego.getMovimientoV() + " x: " + x);
    }
	//Rectangle2D DisparoNaveInvadidaHitbox = new Rectangle2D.Double(x, y, ANCHO, ALTO);
	private void disparar() {
		DisparoNaveInvasora disparo = new DisparoNaveInvasora(x,y,EspANCHO,EspALTO,ANCHO,ALTO);
    	disparos.add(disparo);
		tempDisparo=50;
	}
	@Override
	public void destruirse() {
		vivo=false;
	}
	
	public void reset() {
		temp=100;
	}
	
	public void choque() {
		if(juego.getNaveInvadida().getVidas()>0) {
			for(int x=0;x<disparos.size();x++) {
				if(!disparos.get(x).getDesaparece()) {
					Rectangle2D disparoReact = disparos.get(x).getBoundsDisparo();
					Rectangle2D naveReact = juego.getNaveInvadida().getBoundsNaveInvadida();
					if(disparoReact.intersects(naveReact)){
						juego.getNaveInvadida().vidaMenos();
						disparos.get(x).desaparecer();
						//System.out.println(juego.getNaveInvadida().getVidas()); TODO BORRAR
					}	
				}
			}	
		}
	}
	

    public Rectangle2D getBoundsNaveInvasora() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
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

	public List<DisparoNaveInvasora> getDisparos() {
		return disparos;
	}
}

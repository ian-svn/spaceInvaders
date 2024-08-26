package juego;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class NaveKamikaze extends NaveInvasora{
	private Integer temp=100;
	private int aux=0;
	private Integer xLanzamiento;
	private Integer yLanzamiento;
	private List<Integer> movimientosX = new ArrayList<>();
	private List<Integer> movimientosY = new ArrayList<>();
	
	public NaveKamikaze(Integer x, Integer y, Integer EspANCHO, Integer EspALTO, Juego juego) {
		super(x, y, EspANCHO, EspALTO, juego);
	}

	private Integer recorreX=0;
	private Integer recorreY=0;
	
	@Override
	public void paint(Graphics g) {
		if(vivo) {
			ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveDefensora1.png"));
			g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
			temp--;
			if(temp<=0&&aux==2) {
				try {
					if(recorreY<movimientosY.size()&&juego.getNaveInvadida().getVivo()) {
						x+=movimientosX.get(recorreX);
						recorreX++;
					}
				} catch(IndexOutOfBoundsException e) {y++;}
				try {
					if(recorreY<movimientosY.size()&&juego.getNaveInvadida().getVivo()) {
						y+=movimientosY.get(recorreY);
						recorreY++;
					}
				} catch(IndexOutOfBoundsException e) {y++;}	
			}
		}
	}
	
	@Override
	public void moverse() {
		if(aux==0) {
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
		}
		if(aux==1) {
			int posX=x,posY=y;
			while(!(posX==xLanzamiento&&posY==yLanzamiento)) {
				if(posX<xLanzamiento) {
					posX+=1;
					movimientosX.add(1);
				} else if(posX>xLanzamiento) {
					posX-=1;
					movimientosX.add(-1);
				}
				if(posY<yLanzamiento) {
					posY+=1;
					movimientosY.add(1);
				} else if(posY>yLanzamiento) {
					posY-=1;
					movimientosY.add(-1);
				}
			}
			aux=2;
		}
		if(vivo&&y>=EspALTO-EspALTO/2-EspALTO/8&&aux==0&&juego.getNaveInvadida().getVivo()) {
			xLanzamiento=juego.getNaveInvadida().getX();
			yLanzamiento=juego.getNaveInvadida().getY();
			aux=1;
		}
		//System.out.println("espAncho: " + (EspANCHO-ANCHO-ANCHO/4) + "temp: "+temp+" moveH: "+ juego.getMovimientoH() +" moveV: " + juego.getMovimientoV() + " x: " + x);
    }
	
	public void reset() {
		temp=100;
	}
}

package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 600;
    private static final int ALTO = 700;
    private static JFrame frame = new JFrame("Space Invaders");
    private static Juego juego = new Juego();
    
    //private static BackgroundMusic bgMusic;
    
    private Integer nivel=1;
    private Integer niveles=7;
    private Integer aux=0;
    private Boolean disparo;
    private Timer timer;
    private NaveInvadida nave;
    private ImageIcon fondo;
    private List<ImageIcon> vidas = new ArrayList<>();
    private Boolean MovimientoHorizontal=true;
    private Boolean MovimientoVertical=false;
    
    
    private List<NaveInvasora> enemigos = new ArrayList<>();
    private List<NaveInvasoraDisparadora> enemigosD = new ArrayList<>();

    public Juego() {
        nave = new NaveInvadida(ANCHO, ALTO);

        fondo = new ImageIcon(getClass().getResource("/gif/fondo.gif"));
        
        vidas.add(new ImageIcon(getClass().getResource("/imagenes/vida.png")));
        vidas.add(new ImageIcon(getClass().getResource("/imagenes/vida.png")));
        vidas.add(new ImageIcon(getClass().getResource("/imagenes/vida.png")));
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nave.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                nave.keyReleased(e);
            }
        });

        timer = new Timer(10, this);
        timer.start();
        setFocusable(true);
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

        paintVidas(g);

        checkGanar(g);
        
        generarNivel();
        
        nave.paint(g);
        
        aux=1;
        
        for(int x = 0;x<enemigos.size();x++) {
     	    enemigos.get(x).paint(g);
        }
        for(int x = 0;x<enemigosD.size();x++) {
     	    enemigosD.get(x).paint(g);
        }
        

    	checkearPasarNivel();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
	 	List<DisparoNaveInvadida> disparos = nave.getDisparos();
	 	for(int x = 0; x < disparos.size();x++) {
	 		for(int y = 0 ; y < enemigos.size();y++) {
	 			disparos.get(x).choque(enemigos.get(y));
			}
		}
	 	for(int x = 0;x<enemigosD.size();x++) {
		 	List<DisparoNaveInvasora> disparosInvasores = enemigosD.get(x).getDisparos();
	 		for(int y = 0 ; y < disparosInvasores.size();y++) {
	 			disparos.get(y).choque(enemigos.get(x));
			}
		}
    	
	
    	for(int x = 0;x<enemigos.size();x++) {
    		if(MovimientoVertical) {
    			enemigos.get(x).setY(enemigos.get(x).getY()+10);
    		}
    		enemigos.get(x).moverse();
            nave.choqueNaves(enemigos.get(x));
    	}
    	
    	for(int x = 0;x<enemigosD.size();x++) {
    		if(MovimientoVertical) {
    			enemigosD.get(x).setY(enemigosD.get(x).getY()+10);
    		}
    		enemigosD.get(x).moverse();
            nave.choqueNaves(enemigosD.get(x));
    	}

    	
		MovimientoVertical=false;
        nave.moverse();
        repaint();
    }
    
    public static void main(String[] args) {
        frame.add(juego);
        frame.setSize(ANCHO, ALTO);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);

        //bgMusic = new BackgroundMusic();
        //bgMusic.playMusic("/audio/starwars.wav"); // Cambia la ruta por la ubicación de tu archivo de audio
    
        juego.requestFocusInWindow();
    }
    
    public Boolean getMovimientoH() {
    	return MovimientoHorizontal;	
    }
    public void setMovimientoH(Boolean MovimientoHorizontal) {
    	this.MovimientoHorizontal = MovimientoHorizontal;
    }
    public Boolean getMovimientoV() {
    	return MovimientoVertical;
    }
    public void setMovimientoV(Boolean MovimientoVertical) {
    	this.MovimientoVertical = MovimientoVertical;
    }
    private void checkearPasarNivel() {
    	int count=0;
    	for(NaveInvasora enemigo : enemigos) {
    		if(enemigo.getVivo()) {
    			count++;
    		}
    	}
    	if(count==0&&nivel<=niveles) {
    		nivel++;
    		aux=0;
    		enemigos.clear();
    		enemigosD.clear();
        	nave.getDisparos().clear();
        	nave.reaparecer();
    	}
    }
    
    public void checkGanar(Graphics g) {
    	if(nivel==8) {
    		int tamanioLetra=35;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, tamanioLetra));
            g.drawString("Felicidades. Ganaste !!", ANCHO/4-tamanioLetra, ALTO/2-ALTO/5);
    	} else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24)); 
            g.drawString("Nivel: " + nivel, 10, 30);	
    	}
    	if(nave.getVidas()==0) {
    		int tamanioLetra=35;
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, tamanioLetra));
            g.drawString("Perdiste...", ANCHO/2-tamanioLetra, ALTO/2-ALTO/5);
    	}
    	
    }

    private void paintVidas(Graphics g) {
    	for(int x=0;x<vidas.size();x++) {
        	if(nave.getVidas()>=1&&x==0) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=2&&x==1) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=3&&x==2) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}
        }
    }
    
    public NaveInvadida getNaveInvadida(){
    	return nave;
    }
    
    private void generarNivel() {
    	if(nivel==1&&aux==0) {
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
             	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
             	    enemigos.add(new NaveInvasora(posX,80,ANCHO, ALTO,this));
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
             	    enemigos.add(new NaveInvasora(posX,120,ANCHO, ALTO,this));
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*2)==2|(x-7*2)==6) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveInvasora(posX,200,ANCHO, ALTO,this));
        		}
            }
        } else if(nivel==2&&aux==0) {
        	
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
             	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
                 	    enemigos.add(new NaveInvasora(posX,80,ANCHO, ALTO,this));	
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
        			if((x-7*2)==2|(x-7*2)==6) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,120,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,120,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*3)==2|(x-7*3)==4) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));	
        			}
             	} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveInvasora(posX,200,ANCHO, ALTO,this));
        		}
            }
        } else if(nivel==3&&aux==0) {
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
        			if((x-7)==4||(x-7)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,40,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));	
        			}
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
             	    enemigos.add(new NaveInvasora(posX,80,ANCHO, ALTO,this));
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
        			if((x-7*2)==1||(x-7*2)==7) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,120,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,120,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*3)==1||(x-7*3)==3||(x-7*3)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));	
        			}
             	} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveKamikaze(posX,200,ANCHO, ALTO,this));
        		}
            }
        } else if(nivel==4&&aux==0) {
        	
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
        			if((x-7)==4||(x-7)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,40,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));	
        			}
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
             	    enemigos.add(new NaveKamikaze(posX,80,ANCHO, ALTO,this));
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
        			if((x-7*2)==1||(x-7*2)==7) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,120,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveKamikaze(posX,120,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*3)==1||(x-7*3)==3||(x-7*3)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));	
        			}
             	} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveInvasora(posX,200,ANCHO, ALTO,this));
        		}
            }
        } else if(nivel==5&&aux==0) {
        	
        	for(int x = 0;x<=35;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
        			if((x-7)==4||(x-7)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,40,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));	
        			}
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
             	    enemigos.add(new NaveKamikaze(posX,80,ANCHO, ALTO,this));
             	    if((x-7*2)==1||(x-7*2)==7) {
             	    	enemigos.add(new NaveInvasoraDisparadora(posX,80,ANCHO, ALTO,this));
                	} else {
                	    enemigos.add(new NaveInvasora(posX,80,ANCHO, ALTO,this));	
                	}
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
        			if((x-7*2)==1||(x-7*2)==7||(x-7*2)==4) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,120,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,120,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*3)==1||(x-7*3)==3||(x-7*3)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveKamikaze(posX,160,ANCHO, ALTO,this));	
        			}
             	} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveInvasora(posX,200,ANCHO, ALTO,this));
        		}
            }
        }else if(nivel==6&&aux==0) {
        	for(int x = 0;x<=42;x++) {
        		int posX=1;
        		if(x>0&&x<=7) {
        			posX+=68*x;
        			if((x-7)==4||(x-7)==5) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,40,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveInvasora(posX,40,ANCHO, ALTO,this));	
        			}
        		} else if(x>7&&x<=7*2) {
        			posX+=68*(x-7);
             	    if((x-7*2)==1||(x-7*2)==7) {
             	    	enemigos.add(new NaveInvasoraDisparadora(posX,80,ANCHO, ALTO,this));
                	} else {
                	    enemigos.add(new NaveKamikaze(posX,80,ANCHO, ALTO,this));	
                	}
        		} else if(x>7*2&&x<=7*3) {
        			posX+=68*(x-7*2);
        			if((x-7*2)==1||(x-7*2)==7||(x-7*2)==4) {
        			    enemigos.add(new NaveInvasoraDisparadora(posX,120,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveKamikaze(posX,120,ANCHO, ALTO,this));	
        			}
        		} else if(x>7*3&&x<=7*4) {
        			posX+=68*(x-7*3);
        			if((x-7*3)==1||(x-7*3)==3||(x-7*3)==5) {
        			    enemigos.add(new NaveInvasora(posX,160,ANCHO, ALTO,this));
                 	} else {
                 	    enemigos.add(new NaveKamikaze(posX,160,ANCHO, ALTO,this));	
        			}
             	} else if(x>7*4&&x<=7*5) {
        			posX+=68*(x-7*4);
             	    enemigos.add(new NaveKamikaze(posX,200,ANCHO, ALTO,this));
        		} else if(x>7*5&&x<=7*6) {
        			posX+=68*(x-7*5);
             	    enemigos.add(new NaveInvasora(posX,240,ANCHO, ALTO,this));
        		}
            }
        }
    }
}

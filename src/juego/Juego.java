package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int ANCHO=600;
    private static final int ALTO=700;
    private static JFrame frame = new JFrame("Space Invaders");
    private static Juego juego = new Juego();
    
    //private static BackgroundMusic bgMusic;
    
    private Integer nivel=1;
    private Integer niveles=6;
    private Integer aux=0;
    private Timer timer;
    private NaveInvadida nave;
    private ImageIcon fondo;
    private List<ImageIcon> vidas = new ArrayList<>();
    private Boolean MovimientoHorizontal=true;
    private Boolean MovimientoVertical=false;
    private Integer perkSpawn=20;
    private Boolean pausa=false;

    private List<Perk> perks = new ArrayList<>();
    private List<NaveInvasora> enemigos = new ArrayList<>();
    private List<NaveInvasoraDisparadora> enemigosD = new ArrayList<>();

    public Juego() {
        nave = new NaveInvadida(ANCHO, ALTO);

        fondo = new ImageIcon(getClass().getResource("/gif/fondo.gif"));
        
        for(int x=0;x<5;x++) {
            vidas.add(new ImageIcon(getClass().getResource("/imagenes/vida.png")));
        }
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            	
            }

            @Override
            public void keyPressed(KeyEvent e) {
                nave.keyPressed(e);
                if(pausa) {
                	if (e.getKeyCode() == KeyEvent.VK_R) {
                        reset();
                	}
                    if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        volverMenu();
                    }
                }
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

        paintPerks(g);
        
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
        for(int x = 0;x<perks.size();x++) {
        	perks.get(x).paint(g);
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
    			if(nivel!=5) {
    				enemigos.get(x).setY(enemigos.get(x).getY()+10);		
    			}
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
    	if(count==0&&nivel<niveles) {
    		nivel++;
    		aux=0;
        	nave.getDisparos().clear();
        	nave.reaparecer();
    	}
    }
    
    public void checkGanar(Graphics g) {
		int tamanioLetraTitulo=80;
		int tamanioLetraSubTitulo=20;
    	if(nivel>=6) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, tamanioLetraTitulo));
            g.drawString(" Felicidades", ANCHO/12, ALTO/2-ALTO/5);
            g.drawString("  Ganaste !!", ANCHO/12, ALTO/2-ALTO/10);
            g.setFont(new Font("Arial", Font.BOLD, tamanioLetraSubTitulo));
            g.drawString("Toque la tecla [R] para volver a jugar.", ANCHO/8+ANCHO/16, ALTO-ALTO/3);
            g.drawString("Toque la tecla [ESC] para volver al menu. ", ANCHO/8+ANCHO/16, ALTO-ALTO/4);
            pausa=true;
    	} else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 24)); 
            g.drawString("Nivel: " + nivel, 10, 30);	
    	}
    	if(nave.getVidas()<=0) {
    		nave.explotando();
    		if(!nave.getVivo()) {
    			nivel=0;
        		enemigos.clear();
        		enemigosD.clear();
        		perks.clear();
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, tamanioLetraTitulo));
                g.drawString("Perdiste...", ANCHO/7+ANCHO/16, ALTO/3);
                g.setFont(new Font("Arial", Font.BOLD, tamanioLetraSubTitulo));
                g.drawString("Toque la tecla [R] para volver a jugar.", ANCHO/8+ANCHO/16, ALTO-ALTO/3);
                g.drawString("Toque la tecla [ESC] para volver al menu. ", ANCHO/8+ANCHO/16, ALTO-ALTO/4);
                pausa=true;
    		}
    	}
    	
    }
    
    public void correr() {
    	reset();
    }
    
    public void reset() {
    	frame.dispose();
	    frame = new JFrame("Space Invaders");
	    juego = new Juego();
	    frame.add(juego);
	    frame.setSize(ANCHO, ALTO);
	    frame.setVisible(true);
	    frame.setResizable(false);
	    frame.setFocusable(true);
	    frame.setLocationRelativeTo(null);
	    frame.setBackground(Color.BLACK);
	    
	    juego.requestFocusInWindow();
    }
    
    public void volverMenu() {
    	frame.dispose();
    	Menu menu = new Menu();
    	menu.correr();
    }

    private void paintVidas(Graphics g) {
    	for(int x=0;x<vidas.size();x++) {
        	if(nave.getVidas()>=1&&x==0) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=2&&x==1) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=3&&x==2) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=4&&x==3) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}if(nave.getVidas()>=5&&x==4) {
        		g.drawImage(vidas.get(x).getImage(), ANCHO-ANCHO/8-50*x,  0, 0+ANCHO/10, 0+ALTO/10-ANCHO/85, this);	
        	}
        }
    }
    
    private void paintPerks(Graphics g) {
    	for(int x=0;x<perks.size();x++) {
    		perks.get(x).paint(g);
    	}
    	if(perks.size()>0) {
    		for(int x=0;x<perks.size();x++) {
        		perks.get(x).choque(nave);
        	}	
    	}
    }
    
    public NaveInvadida getNaveInvadida(){
    	return nave;
    }
    
   public void perkMenos(NaveInvasora naveI) {
    	if(perkSpawn<=0) {
    		Perk perk = new Perk(naveI,ANCHO,ALTO);
    		perks.add(perk);
    		perkSpawn=30;
    	}
    	perkSpawn--;
    }
    
    public void perder() {
    	nave.destruirse();
    	pausa=false;
    }
   
    private void generarNivel() {
	    if (aux == 0) {
	        int posX;
	        switch (nivel) {
	            case 1:
	                for (int x = 0; x < 35; x++) {
	                    posX = 1 + 74 * (x % 7);
	                    if (x < 7) {
	                        enemigos.add(new NaveInvasora(posX, 40, ANCHO, ALTO, this));
	                    } else if (x < 14) {
	                        enemigos.add(new NaveInvasora(posX, 80, ANCHO, ALTO, this));
	                    } else if (x < 21) {
	                        enemigos.add(new NaveInvasora(posX, 120, ANCHO, ALTO, this));
	                    } else if (x < 28) {
	                        enemigos.add(new NaveInvasora(posX, 160, ANCHO, ALTO, this));
	                    } else {
	                        enemigos.add(new NaveInvasora(posX, 200, ANCHO, ALTO, this));
	                    }
	                }
	                break;
	            case 2:
	                for (int x = 0; x < 35; x++) {
	                    posX = 1 + 74 * (x % 7);
	                    if (x < 7) {
	                        enemigos.add(new NaveInvasora(posX, 40, ANCHO, ALTO, this));
	                    } else if (x < 14) {
	                        enemigos.add(new NaveInvasora(posX, 80, ANCHO, ALTO, this));
	                    } else if (x < 21) {
	                        if ((x - 14) == 0 || (x - 14) == 6) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 120, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 120, ANCHO, ALTO, this));
	                        }
	                    } else if (x < 28) {
	                        if ((x - 21) == 1 || (x - 21) == 5) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 160, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 160, ANCHO, ALTO, this));
	                        }
	                    } else {
	                        enemigos.add(new NaveInvasora(posX, 200, ANCHO, ALTO, this));
	                    }
	                }
	                break;
	            case 3:
	                for (int x = 0; x < 35; x++) {
	                    posX = 1 + 74 * (x % 7);
	                    if (x < 7) {
	                        if (x == 4 || x == 5) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 40, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 40, ANCHO, ALTO, this));
	                        }
	                    } else if (x < 14) {
	                        enemigos.add(new NaveInvasora(posX, 80, ANCHO, ALTO, this));
	                    } else if (x < 21) {
	                        if ((x - 14) == 1 || (x - 14) == 7) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 120, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 120, ANCHO, ALTO, this));
	                        }
	                    } else if (x < 28) {
	                        if ((x - 21) == 1 || (x - 21) == 3 || (x - 21) == 5) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 160, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 160, ANCHO, ALTO, this));
	                        }
	                    } else {
	                        enemigos.add(new NaveKamikaze(posX, 200, ANCHO, ALTO, this));
	                    }
	                }
	                break;
	            case 4:
	                for (int x = 0; x < 35; x++) {
	                    posX = 1 + 74 * (x % 7);
	                    if (x < 7) {
	                        if (x == 4 || x == 5) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 40, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 40, ANCHO, ALTO, this));
	                        }
	                    } else if (x < 14) {
	                        enemigos.add(new NaveInvasora(posX, 80, ANCHO, ALTO, this));
	                    } else if (x < 21) {
	                        if ((x - 14) == 1 || (x - 14) == 7) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 120, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 120, ANCHO, ALTO, this));
	                        }
	                    } else if (x < 28) {
	                        if ((x - 21) == 1 || (x - 21) == 3 || (x - 21) == 5) {
	                            enemigos.add(new NaveInvasoraDisparadora(posX, 160, ANCHO, ALTO, this));
	                        } else {
	                            enemigos.add(new NaveInvasora(posX, 160, ANCHO, ALTO, this));
	                        }
	                    } else {
	                        enemigos.add(new NaveKamikaze(posX, 200, ANCHO, ALTO, this));
	                    }
	                }
	                break;
	            case 5:
	            	enemigos.add(new NaveJefeFinal(150,10,ANCHO,ALTO,this));
	                break;
	        }
	        aux = 1;
	    }
    }
}

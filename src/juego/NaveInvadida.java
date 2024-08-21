package juego;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

public class NaveInvadida extends Nave {

    private final Integer vidas = 3;
    private final Integer XVEL = 5; // Velocidad aumentada para mejor control

    private boolean aPressed = false;
    private boolean dPressed = false;

    public NaveInvadida(Integer EspANCHO, Integer EspALTO) {
        super(EspANCHO, EspALTO);
        x = 350 - ANCHO;
        y = EspALTO - 50 - ALTO; // Ajustar posición inicial de la nave
    }

    @Override
    public void paint(Graphics g) {
        ImageIcon nave = new ImageIcon(getClass().getResource("/imagenes/naveDefensora1.png"));
        g.drawImage(nave.getImage(), x, y, ANCHO, ALTO, null);
    }

    @Override
    public void moverse() {
        if (dPressed) {
            if (x + ANCHO < EspANCHO) { 
                x += XVEL;
            }
        }
        if (aPressed) {
            if (x > 0) {
                x -= XVEL;
            }
        }
    }

    @Override
    public void disparar() {
        // Implementación de disparo
    }

    @Override
    public void destruirse() {
        // Implementación de destrucción
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) aPressed = true;
        if (e.getKeyCode() == KeyEvent.VK_D) dPressed = true;
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) aPressed = false;
        if (e.getKeyCode() == KeyEvent.VK_D) dPressed = false;
    }

    public Integer getVidas() {
        return vidas;
    }
    
    
    
}

package juego;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Juego extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int ANCHO = 600;
    private static final int ALTO = 720;
    private static JFrame frame = new JFrame("Space Invaders");
    private static Juego juego = new Juego();

    private Timer timer;
    private NaveInvadida nave;
    private ImageIcon gifBackground;

    public Juego() {
        nave = new NaveInvadida(ANCHO, ALTO);

        gifBackground = new ImageIcon(getClass().getResource("/gif/fondo.gif"));
        
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

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
        

        g.drawImage(gifBackground.getImage(), 0, 0, getWidth(), getHeight(), this);

        nave.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        nave.moverse();
        repaint();
    }

    public static void main(String[] args) {
        frame.add(juego);
        frame.setSize(ANCHO, ALTO);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        juego.requestFocusInWindow();
    }
}

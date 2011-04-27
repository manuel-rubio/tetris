package net.bosqueviejo.uned.lsi.poo;

import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Teclado implements KeyListener, ActionListener
{
    private Juego juego;
    private int contador;
    private Timer cron;

    public Teclado( Juego juego ) {
        this.juego = juego;
        contador = 0;
        
        cron = new Timer(1000, this);
        cron.start();
    }
    
    public void actionPerformed(ActionEvent e) {
        contador = 0;
    }

    /**
     * Cuando se presiona una tecla "escribible", se genera la llamada a este m�todo.
     * En este programa no se usar�, pero es necesaria para KeyEvent.
     */
    public void keyTyped ( KeyEvent e ) {
    }

    /**
     * Gestiona las pulsaciones de las teclas. Este evento se usar� para cazar la pulsaci�n
     * de los cursores de direcci�n y desplazar o girar la pieza en juego.
     * 
     * @param e el evento que se genera. En �l vendr� si se ha generado una pulsaci�n de tecla u otra.
     */
    public void keyPressed ( KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                juego.mueveDerPieza();
                break;
            case KeyEvent.VK_LEFT:
                juego.mueveIzqPieza();
                break;
            case KeyEvent.VK_UP:
                if (contador < 4) {
                    juego.giraPieza();
                    contador ++;
                }
                break;
            case KeyEvent.VK_DOWN:
                juego.bajaPieza();
                break;
        }
    }

    public void keyReleased ( KeyEvent e ) {
    }

}

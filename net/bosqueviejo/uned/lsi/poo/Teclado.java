package net.bosqueviejo.uned.lsi.poo;

import javax.swing.Timer;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Se encarga de manejar los eventos del teclado, y controlar
 * las limitaciones impuestas para el giro de la pieza.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Teclado implements KeyListener, ActionListener
{
    private Juego juego;   //<! Lógica del juego. Se usará para indicar los movimientos que indique el teclado.
    private int contador;  //<! El contador de giros máximos por segundo de la pieza.
    private Timer cron;    //<! Reloj que controla los movimientos máximos.

    /**
     * Inicializa el objeto Teclado. Se asigna el juego, se crea el reloj
     * para el contador de giros y se inicializa el contador.
     *
     * @param juego El controlador de lógica del juego.
     */
    public Teclado( Juego juego ) {
        this.juego = juego;
        contador = 0;
        
        cron = new Timer(ZonaJuego.TIME_TURN, this);
        cron.start();
    }

    /**
     * Caputura el evento de tiempo. Inicializa el contador llegado a cumplirse
     * el tiempo indicado.
     *
     * @param e El evento del reloj.
     */
    public void actionPerformed(ActionEvent e) {
        contador = 0;
    }

    /**
     * Cuando se presiona una tecla "escribible", se genera la llamada a este método.
     * En este programa no se usará, pero es necesaria para KeyEvent.
     */
    public void keyTyped ( KeyEvent e ) {
    }

    /**
     * Gestiona las pulsaciones de las teclas. Este evento se usará para cazar la pulsación
     * de los cursores de dirección y desplazar o girar la pieza en juego.
     * 
     * @param e el evento que se genera. En él vendrá si se ha generado una pulsación de tecla u otra.
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
                if (contador < ZonaJuego.MAX_TURNS) {
                    juego.giraPieza();
                    contador ++;
                }
                break;
            case KeyEvent.VK_DOWN:
                juego.bajaPieza();
                break;
        }
    }

    /**
     * Cuando se libera una tecla. En este programa no se usará, pero es necesaria para KeyEvent.
     */
    public void keyReleased ( KeyEvent e ) {
    }

}

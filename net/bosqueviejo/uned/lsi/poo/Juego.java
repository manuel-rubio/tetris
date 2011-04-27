package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * La clase de Juego se encarga de la lógica propia del juego.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Juego implements ActionListener, KeyListener
{
    private Tablero tablero;      //!< Tablero sobre el que se jugará.
    private ZonaJuego zonaJuego;  //!< La Zona de Juego (aplicación o applet)
    private Timer cron;           //!< El generador de eventos de tiempo.
    
    /**
     * Constructor for objects of class Tablero
     * 
     * @param x ancho del tablero.
     * @param y alto del tablero.
     * @param s tamaño del bloque en píxeles.
     */
    public Juego( ZonaJuego zonaJuego )
    {
        tablero = new Tablero(ZonaJuego.X_BLOCKS,ZonaJuego.Y_BLOCKS);
        this.zonaJuego = zonaJuego;
        zonaJuego.setTablero(tablero);
        zonaJuego.teclado(this);
        
        cron = new Timer(ZonaJuego.TIME_FALL, this);
    }
    
    /**
     * Limpia el tablero.
     */
    public void reinicia() {
        tablero.reinicia();
        cron.start();
    }
    
    /**
     * Avanza la pieza abajo en el tablero. Comprueba también si se puede avanzar con la pieza,
     * sino, pregunta si quiere reiniciar el juego y puede terminar o volver a comenzar.
     */
    public void bajaPieza() {
        if (!tablero.colisionAbajo()) {
            tablero.desplazaPieza(0,1);
            zonaJuego.repaint();
        } else {
            tablero.fijaPieza();
            tablero.limpiaLineas();
            tablero.setPieza(Pieza.factory());
            zonaJuego.repaint();
            if (tablero.colisionAbajo()) {
                cron.stop();
                int confirm = zonaJuego.otraVez();
                if (confirm == JOptionPane.YES_OPTION) {
                    reinicia();
                } else {
                    System.exit(0);
                }
            }
        }
    }
    
    /**
     * Se encarga de tomar los eventos del Timer.
     */
    public void actionPerformed(ActionEvent e) {
        bajaPieza();
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
                if (!tablero.colisionDerecha()) {
                    tablero.desplazaPieza(1,0);
                    zonaJuego.repaint();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!tablero.colisionIzquierda()) {
                    tablero.desplazaPieza(-1,0);
                    zonaJuego.repaint();
                }
                break;
            case KeyEvent.VK_UP:
                if (!tablero.colisionGiro()) {
                    tablero.rotarPieza();
                    zonaJuego.repaint();
                }
                break;
            case KeyEvent.VK_DOWN:
                bajaPieza();
                break;
        }
    }

    /**
     * Evento que se sucede cuando se deja de presionar una tecla. En este programa no se usará, pero
     * es necesaria su creación para KeyEvent.
     */
    public void keyReleased ( KeyEvent e ) {
    }
}

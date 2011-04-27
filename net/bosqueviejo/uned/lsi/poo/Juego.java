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
 * La clase de Juego se encarga de la l�gica propia del juego.
 * 
 * @author Manuel �ngel Rubio Jim�nez
 * @version 2011-04-22
 */
public class Juego implements ActionListener
{
    private Tablero tablero;      //!< Tablero sobre el que se jugar�.
    private ZonaJuego zonaJuego;  //!< La Zona de Juego (aplicaci�n o applet)
    private Timer cron;           //!< El generador de eventos de tiempo.
    
    /**
     * Constructor for objects of class Tablero
     * 
     * @param x ancho del tablero.
     * @param y alto del tablero.
     * @param s tama�o del bloque en p�xeles.
     */
    public Juego( ZonaJuego zonaJuego )
    {
        tablero = new Tablero(ZonaJuego.X_BLOCKS,ZonaJuego.Y_BLOCKS);
        this.zonaJuego = zonaJuego;
        zonaJuego.setTablero(tablero);
        zonaJuego.teclado(new Teclado(this));
        
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
     * Avanza la pieza abajo en el tablero. Comprueba tambi�n si se puede avanzar con la pieza,
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

    public void mueveDerPieza() {
        if (!tablero.colisionDerecha()) {
            tablero.desplazaPieza(1,0);
            zonaJuego.repaint();
        }
    }
    
    public void mueveIzqPieza() {
        if (!tablero.colisionIzquierda()) {
            tablero.desplazaPieza(-1,0);
            zonaJuego.repaint();
        }
    }
    
    public void giraPieza() {
        if (!tablero.colisionGiro()) {
            tablero.rotarPieza();
            zonaJuego.repaint();
        }
    }

    public void actionPerformed(ActionEvent e) {
        bajaPieza();
    }
}

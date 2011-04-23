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
 * La Ventana se encarga de la E/S de
 * información.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Juego implements ActionListener, KeyListener
{
    private Tablero tablero;
    private ZonaJuego zonaJuego;
    private Timer cron;
    
    private final static int HEIGHT = 460;
    private final static int WIDTH = 240;

    /**
     * Constructor for objects of class Tablero
     * 
     * @param x ancho del tablero.
     * @param y alto del tablero.
     * @param s tamaño del bloque en píxeles.
     */
    public Juego( int x, int y, int s, ZonaJuego zonaJuego )
    {
        tablero = new Tablero(x,y);
        this.zonaJuego = zonaJuego;
        zonaJuego.setTablero(tablero, s);
        zonaJuego.teclado(this);
        
        cron = new Timer(500, this);
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
    
    public void keyTyped ( KeyEvent e ) {
    }

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
    
    public void keyReleased ( KeyEvent e ) {
    }
}

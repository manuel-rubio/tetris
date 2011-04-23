package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;

/**
 * La Ventana se encarga de la E/S de
 * información.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Tetris extends JFrame implements ZonaJuego
{
    private int s;
    private Tablero tablero;
    
    public final static int HEIGHT = 460;
    public final static int WIDTH = 240;

    /**
     * Constructor for objects of class Tablero
     */
    public Tetris()
    {
        setTitle("Tetris");
        setBounds(0,0,WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    public void setTablero( Tablero tablero, int s ) {
        this.tablero = tablero;
        this.s = s;
    }
    
    /**
     * Dibuja el tablero, con todos los bloques y la pieza activa.
     * 
     * @param g El elemento que permite dibujar.
     */
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // dibuja los cuadros internos
        if (this.tablero != null) {
            Color [][] tablero = this.tablero.getTablero();
            for (int i=0; i<tablero.length; i++) {
                for (int j=0; j<tablero[i].length; j++) {
                    g.setColor(tablero[i][j]);
                    if (tablero[i][j] != Color.BLACK) {
                        g.fillRect((i*s)+20, (j*s)+40, s, s);
                        g.setColor(tablero[i][j].darker());
                        g.fillRect((i*s)+20+(s/4), (j*s)+40+(s/4), s/2, s/2);
                        g.setColor(Color.BLACK);
                    }
                    g.drawRect((i*s)+20, (j*s)+40, s, s);
                }
            }
        }
    }
    
    /**
     * Realiza una pregunta de si quiere volver a jugar.
     * 
     * @return un entero de tipo JOptionPane.YES_OPTION o JOptionPane.NO_OPTION.
     */
    public int otraVez() {
        return JOptionPane.showConfirmDialog(
            this, "Se acabó el juego\n¿Quiere volver a jugar?",
            "Tetris", JOptionPane.YES_NO_OPTION
        );
    }
    
    /**
     * Agrega la clase pasada como parámetro para el control
     * de los eventos de teclado.
     * 
     * @param al La clase que gestionará los eventos de teclado.
     */
    public void teclado( KeyListener al ) {
        addKeyListener(al);
    }
    
    /**
     * Inicia el juego en modo aplicación de escritorio.
     */
    public static void main( String[] args ) {
        Tetris tetris = new Tetris();
        Juego juego = new Juego(10, 20, 20, tetris);
        juego.reinicia();
    }

}

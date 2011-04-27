package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;


/**
 * Se encarga de generar el entorno para su ejecución como
 * aplicación de escritorio.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Tetris extends JFrame implements ZonaJuego
{
    private Tablero tablero;   //!< Tablero de juego.
    private int ysize;         //!< Tamaño de la zona de juego en píxeles en el eje Y.
    private int xsize;         //!< Tamaño de la zona de juego en píxeles en el eje X.

    private BufferStrategy strategy;
    
    /**
     * Constructor for objects of class Tablero
     */
    public Tetris()
    {
        xsize = ZonaJuego.X_BLOCKS * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 2;
        ysize = ZonaJuego.Y_BLOCKS * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 3;

        setTitle("Tetris");
        setBounds(0,0,xsize,ysize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        // genera la estrategia para el doble-buffer
        createBufferStrategy(2);
        strategy = getBufferStrategy();
    }
    
    /**
     * Configura el Tablero sobre el que se jugará. A este tablero se le pedirá la información
     * para redibujar la pantalla cada vez que se requiera.
     * 
     * @param tablero el tablero que se dibujará en la pantalla.
     */
    public void setTablero( Tablero tablero ) {
        this.tablero = tablero;
    }
    
    /**
     * Dibuja el tablero, con todos los bloques y la pieza activa.
     * 
     * @param g El elemento que permite dibujar.
     */
    public void paint(Graphics gr) {
        int s = SIZE_BLOCKS;
        Graphics2D g;

        g = (strategy == null) ? (Graphics2D) gr : (Graphics2D) strategy.getDrawGraphics();
        g.clearRect(0, 0, xsize, ysize);
        
        // dibuja los cuadros internos
        if (this.tablero != null) {
            Color [][] tablero = this.tablero.getTablero();
            for (int i=0; i<tablero.length; i++) {
                for (int j=0; j<tablero[i].length; j++) {
                    g.setColor(tablero[i][j]);
                    if (tablero[i][j] != Color.BLACK) {
                        g.fillRect((i*s)+SIZE_BLOCKS, (j*s)+(SIZE_BLOCKS*2), s, s);
                        g.setColor(tablero[i][j].darker());
                        g.fillRect((i*s)+SIZE_BLOCKS+(s/4), (j*s)+(SIZE_BLOCKS*2)+(s/4), s/2, s/2);
                        g.setColor(Color.BLACK);
                    }
                    g.drawRect((i*s)+SIZE_BLOCKS, (j*s)+(SIZE_BLOCKS*2), s, s);
                }
            }
        }
        if (strategy != null) {
            g.dispose();
            strategy.show();
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
        Juego juego = new Juego(tetris);
        juego.reinicia();
    }

}

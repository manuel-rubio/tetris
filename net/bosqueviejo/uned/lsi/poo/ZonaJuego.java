package net.bosqueviejo.uned.lsi.poo;

import java.awt.event.KeyListener;

/**
 * Interfaz que se emplea para poder crear clases
 * que permitan lanzar el juego desde interfaces tan
 * distintas como un applet en la web, como una aplicaci�n
 * de escritorio... e incluso un m�vil.
 * 
 * @author Manuel �ngel Rubio Jim�nez
 * @version 2011-04-22
 */
public interface ZonaJuego
{
    // Constantes predefinidas para el juego
    public final static int X_BLOCKS = 12;
    public final static int Y_BLOCKS = 25;
    public final static int SIZE_BLOCKS = 20;
    public final static int TIME_FALL = 1000;

    /**
     * M�todo intr�nseco para los JFrame y Applet, que se encarga
     * de redibujar la pantalla llamando al m�todo <em>paint</em>.
     */
    public void repaint();
    
    /**
     * Realiza una pregunta de si quiere volver a jugar.
     * 
     * @return un entero de tipo JOptionPane.YES_OPTION o JOptionPane.NO_OPTION.
     */
    public int otraVez();
    
    /**
     * Configura el Tablero sobre el que se jugar�. A este tablero se le pedir� la informaci�n
     * para redibujar la pantalla cada vez que se requiera.
     * 
     * @param tablero el tablero que se dibujar� en la pantalla.
     */
    public void setTablero( Tablero tablero );
    
    /**
     * Agrega la clase pasada como par�metro para el control
     * de los eventos de teclado.
     * 
     * @param al La clase que gestionar� los eventos de teclado.
     */
    public void teclado( KeyListener al );

}

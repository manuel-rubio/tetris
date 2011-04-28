package net.bosqueviejo.uned.lsi.poo;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;

/**
 * Manejador que permite visualizar la aplicación
 * dentro de un applet en una página web.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Web extends JApplet implements ZonaJuego
{
    private Tablero tablero;   //!< Tablero de juego.
    private int ysize;         //!< Tamaño de la zona de juego en píxeles en el eje Y.
    private int xsize;         //!< Tamaño de la zona de juego en píxeles en el eje X.

    // elementos para el doble-buffer
    private Image offScreen;
    private Graphics bufferGraphics;
    
    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        // this is a workaround for a security conflict with some browsers
        // including some versions of Netscape & Internet Explorer which do 
        // not allow access to the AWT system event queue which JApplets do 
        // on startup to check access. May not be necessary with your browser. 
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        
        xsize = ZonaJuego.X_BLOCKS * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 2;
        ysize = ZonaJuego.Y_BLOCKS * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 2;
    
        setBounds(0,0,xsize,ysize);
        
        offScreen = createImage(xsize, ysize);
        bufferGraphics = offScreen.getGraphics();
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        Juego juego = new Juego(this);
        juego.reinicia();
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
    }

    /**
     * Paint method for applet.
     * 
     * @param  gr the Graphics object for this applet
     */
    public void paint(Graphics gr)
    {
        Graphics g = bufferGraphics;
        int s = SIZE_BLOCKS;
        g.clearRect(0, 0, xsize, ysize);
        
        // dibuja los cuadros internos
        Color [][] tablero = this.tablero.getTablero();
        for (int i=0; i<tablero.length; i++) {
            for (int j=0; j<tablero[i].length; j++) {
                g.setColor(tablero[i][j]);
                if (tablero[i][j] != Color.BLACK) {
                    g.fillRect((i*s)+ZonaJuego.SIZE_BLOCKS, (j*s)+ZonaJuego.SIZE_BLOCKS, s, s);
                    g.setColor(tablero[i][j].darker());
                    g.fillRect((i*s)+ZonaJuego.SIZE_BLOCKS+(s/4), (j*s)+ZonaJuego.SIZE_BLOCKS+(s/4), s/2, s/2);
                    g.setColor(Color.BLACK);
                }
                g.drawRect((i*s)+ZonaJuego.SIZE_BLOCKS, (j*s)+ZonaJuego.SIZE_BLOCKS, s, s);
            }
        }
        gr.drawImage(offScreen,0,0,this);
        if (!hasFocus()) {
            requestFocus();
        }
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }


    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        return "Title: Tetris\nAuthor: Manuel Angel Rubio Jimenez\nEjercicio para POO, UNED. ";
    }


    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
        };
        return paramInfo;
    }

    /**
     * Agrega un listener para el control del teclado.
     * 
     * @param al objeto que controlará los eventos del teclado.
     */
    public void teclado( KeyListener al ) {
        addKeyListener(al);
    }

    /**
     * Pregunta si el juego debe comenzar otra vez. En este caso,
     * como del applet no se puede salir, se mandará a que reinicie el juego de forma
     * reiterada.
     * 
     * @return siempre responde YES_OPTION.
     */
    public int otraVez() {
        return JOptionPane.YES_OPTION;
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
}

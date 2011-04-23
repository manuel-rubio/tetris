package net.bosqueviejo.uned.lsi.poo;

import java.awt.*;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Web extends JApplet implements ZonaJuego
{
    private Tablero tablero;
    private int s;
    
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
    
        // provide any initialisation necessary for your JApplet
        setBounds(0,0,Tetris.WIDTH,Tetris.HEIGHT);
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        Juego juego = new Juego(10, 20, 20, this);
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
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        g.clearRect(0, 0, Tetris.WIDTH, Tetris.HEIGHT);
        
        // dibuja los cuadros internos
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

    public void teclado( KeyListener al ) {
        addKeyListener(al);
    }

    public int otraVez() {
        return JOptionPane.showConfirmDialog(
            this, "Se acab— el juego\nÀQuiere volver a jugar?",
            "Tetris", JOptionPane.YES_NO_OPTION
        );
    }

    public void setTablero( Tablero tablero, int s ) {
        this.tablero = tablero;
        this.s = s;
    }
}

package net.bosqueviejo.uned.lsi.poo;

import java.awt.event.KeyListener;

public interface ZonaJuego
{

    public void repaint();
    
    public int otraVez();
    
    public void setTablero( Tablero tablero, int s );
    
    public void teclado( KeyListener al );

}

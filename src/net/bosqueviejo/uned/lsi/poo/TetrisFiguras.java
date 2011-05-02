package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Color;

public class TetrisFiguras extends JFrame {
	private int ysize;
	private int xsize;
	private Tablero tablero;
	
    private static Class[] piezasTipos = {
        EleIzquierda.class,
        EleDerecha.class,
        Cuadrado.class,
        Recta.class,
        Te.class,
        ZetaIzquierda.class,
        ZetaDerecha.class
    };

	public static void main( String[] args ) {
		Tablero tablero = new Tablero(16, 28);
		Pieza[] piezas = new Pieza[piezasTipos.length];
		
		tablero.reinicia();
		try {
			for (int i=0; i<piezasTipos.length; i++) {
				piezas[i] = (Pieza) piezasTipos[i].newInstance();
				tablero.setPieza(piezas[i]);
				tablero.desplazaPieza(-8,i*4);
				for (int j=0; j<4; j++) {
					tablero.fijaPieza();
					tablero.desplazaPieza(4,0);
					tablero.rotarPieza();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tablero.setPieza(null);
		TetrisFiguras tetris = new TetrisFiguras(tablero);
	}

    public TetrisFiguras( Tablero tablero )
    {
        xsize = 20 * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 2;
        ysize = 30 * ZonaJuego.SIZE_BLOCKS + ZonaJuego.SIZE_BLOCKS * 3;

		this.tablero = tablero;

        setTitle("Tetris");
        setBounds(0,0,xsize,ysize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
	}

    public void paint(Graphics g) {
        int s = ZonaJuego.SIZE_BLOCKS;
        
        // dibuja los cuadros internos
        if (this.tablero != null) {
            Color [][] tablero = this.tablero.getTablero();
            for (int i=0; i<tablero.length; i++) {
                for (int j=0; j<tablero[i].length; j++) {
					int xv = ((i/4) * 4);
					int yv = ((j/4) * 4);
                    g.setColor(tablero[i][j]);
                    if (tablero[i][j] != Color.BLACK) {
                        g.fillRect((i*s)+ZonaJuego.SIZE_BLOCKS+xv, (j*s)+(ZonaJuego.SIZE_BLOCKS*2)+yv, s, s);
                        g.setColor(tablero[i][j].darker());
                        g.fillRect((i*s)+ZonaJuego.SIZE_BLOCKS+(s/4)+xv, (j*s)+(ZonaJuego.SIZE_BLOCKS*2)+(s/4)+yv, s/2, s/2);
                        g.setColor(Color.BLACK);
                    }
                    g.drawRect((i*s)+ZonaJuego.SIZE_BLOCKS+xv, (j*s)+(ZonaJuego.SIZE_BLOCKS*2)+yv, s, s);
                }
            }
        }
    }
}

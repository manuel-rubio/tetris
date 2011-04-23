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
 * El tablero es la clase que se encarga de mantener
 * los bloques en la pantalla, suprime las líneas, y
 * se encarga del redibujado del tablero
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Tablero extends JFrame implements ActionListener, KeyListener
{
    private int x;
    private int y;
    private int s;
    
    private Color[][] tablero;
    private Timer cron;
    
    private Pieza actual;
    private int xp;
    private int yp;
    
    private final static int HEIGHT = 460;
    private final static int WIDTH = 240;

    /**
     * Constructor for objects of class Tablero
     * 
     * @param x ancho del tablero.
     * @param y alto del tablero.
     * @param s tamaño del bloque en píxeles.
     */
    public Tablero( int x, int y, int s )
    {
        this.x = x;
        this.y = y;
        this.s = s;
        
        tablero = new Color[x][y];
        
        setTitle("Tetris");
        setBounds(0,0,WIDTH,HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        
        cron = new Timer(500, this);
        
        addKeyListener(this);
    }
    
    /**
     * Limpia el tablero.
     */
    public void reinicia() {
        for (int i=0; i<x; i++) {
            for (int j=0; j<y; j++) {
                tablero[i][j] = Color.BLACK;
            }
        }
        setPieza(Pieza.factory());
        cron.start();
    }
    
    /**
     * Asigna la pieza activa.
     * 
     * @param actual La pieza que se pasa por parámetro para asignar como activa.
     */
    public void setPieza( Pieza actual ) {
        xp = x / 2;
        yp = 0;
        this.actual = actual;
        repaint();
    }
    
    /**
     * Dibuja el tablero, con todos los bloques y la pieza activa.
     * 
     * @param g El elemento que permite dibujar.
     */
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        
        // dibuja los cuadros internos
        for (int i=0; i<x; i++) {
            for (int j=0; j<y; j++) {
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
        
        // dibuja la pieza
        if (actual != null) {
            int x = (xp*s)+20;
            int y = (yp*s)+40;
            short [][] forma = actual.toma();
            for (int i=0; i<4; i++) {
                for (int j=0; j<4; j++) {
                    if (forma[i][j] == 1) {
                        g.setColor(actual.getColor());
                        g.fillRect(x+(j*s), y+(i*s), s, s);
                        g.setColor(actual.getColor().darker());
                        g.fillRect(x+(j*s)+(s/4), y+(i*s)+(s/4), s/2, s/2);
                        g.setColor(Color.BLACK);
                        g.drawRect(x+(j*s), y+(i*s), s, s);
                    }
                }
            }
        }
    }
    
    /**
     * Se encarga de fijar la pieza al tablero.
     */
    public void fijaPieza() {
        short [][] forma = actual.toma();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    tablero[xp+j][yp+i] = actual.getColor();
                }
            }
        }
        repaint();
    }

    /**
     * Desplaza la pieza de forma relativa a su posición actual,
     * con lo que se pueden dar valores positivos, negativos, 
     * o cero si no se desea desplazar.
     * 
     * @param x desplazamiento en el eje horizontal.
     * @param y desplazamiento en el eje vertical.
     */
    public void desplazaPieza(int x, int y) {
        yp += y;
        xp += x;
        repaint();
    }

    /**
     * Comprueba si la pieza puede desplazarse hacia abajo sin colisionar, y
     * sin salirse, por abajo, del tablero.
     * 
     * @return verdadero si la pieza colisiona y falso si no.
     */
    public boolean colisionAbajo() {
        short [][] forma = actual.toma();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    if (i >= this.y) {
                        return true;
                    }
                    if (yp+i+1 >= this.y || tablero[xp+j][yp+i+1] != Color.BLACK) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Comprueba si avanzando hacia la derecha se puede, comprobando las piezas que haya,
     * y el borde del final del tablero por la derecha.
     * 
     * @return verdadero si hay colisión, falso en caso contrario.
     */
    public boolean colisionDerecha() {
        short [][] forma = actual.toma();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    if (j >= this.x) {
                        return true;
                    }
                    if (xp+j+1 >= this.x || tablero[xp+j+1][yp+i] != Color.BLACK) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Comprueba si avanzando hacia la izquierda se puede, comprobando las piezas que haya,
     * y el borde del final del tablero por la izquierda.
     * 
     * @return verdadero si hay colisión, falso en caso contrario.
     */
    public boolean colisionIzquierda() {
        short [][] forma = actual.toma();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    if (j < 0) {
                        return true;
                    }
                    if (xp+j-1 < 0 || tablero[xp+j-1][yp+i] != Color.BLACK) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Comprueba si al girar la pieza colisiona o no en el tablero, ya sea lateralmente,
     * o hacia abajo.
     * 
     * @return verdadero si colisiona, falso en caso contrario.
     */
    public boolean colisionGiro() {
        short [][] forma = actual.tomaSig();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    if (j < 0 || j >= this.x || i >= this.y) {
                        return true;
                    }
                    if (
                        yp+i >= this.y || xp+j < 0 ||
                        xp+j >= this.x || tablero[xp+j][yp+i] != Color.BLACK
                    ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Avanza la pieza abajo en el tablero. Comprueba también si se puede avanzar con la pieza,
     * sino, pregunta si quiere reiniciar el juego y puede terminar o volver a comenzar.
     */
    public void bajaPieza() {
        if (!colisionAbajo()) {
            desplazaPieza(0,1);
        } else {
            fijaPieza();
            limpiaLineas();
            setPieza(Pieza.factory());
            if (colisionAbajo()) {
                cron.stop();
                int confirm = JOptionPane.showConfirmDialog(
                    this, "Se acabó el juego\n¿Quiere volver a jugar?",
				    "Tetris", JOptionPane.YES_NO_OPTION
				);
				if (confirm == JOptionPane.YES_OPTION) {
				    reinicia();
				} else {
				    System.exit(0);
				}
            }
        }
    }
    
    /**
     * Elimina del tablero las líneas completas.
     */
    public void limpiaLineas() {
        int i, j, k = 0;
        for (i=0; i<y; i++) {
            for (j=0, k=0; j<x; j++) {
                if (tablero[j][i] != Color.BLACK) {
                    k++;
                }
            }
            if (k == x) {
                for (int a=i; a>0; a--) {
                    for (int b=0; b<x; b++) {
                        tablero[b][a] = tablero[b][a-1];
                    }
                }
                for (int a=0; a<x; a++) {
                    tablero[a][0] = Color.BLACK;
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
                if (!colisionDerecha()) {
                    desplazaPieza(1,0);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!colisionIzquierda()) {
                    desplazaPieza(-1,0);
                }
                break;
            case KeyEvent.VK_UP:
                if (!colisionGiro()) {
                    actual.rotar();
                    repaint();
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

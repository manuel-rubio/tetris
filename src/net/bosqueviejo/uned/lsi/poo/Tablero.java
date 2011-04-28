package net.bosqueviejo.uned.lsi.poo;

import java.awt.Color;

/**
 * El tablero es la clase que se encarga de mantener
 * los bloques en la pantalla, suprime las líneas, y
 * se encarga del redibujado del tablero
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Tablero
{
    private int x; //!< Tamaño del tablero en el eje X.
    private int y; //!< Tamaño del tablero en el eje Y.
    
    private Color[][] tablero; //!< Contenido del tablero.
    
    private Pieza actual; //!< La pieza que hay en juego.
    private int xp; //!< Posición en el eje X de la pieza en juego.
    private int yp; //!< Posición en el eje Y de la pieza en juego.
    
    /**
     * Constructor for objects of class Tablero
     * 
     * @param x ancho del tablero.
     * @param y alto del tablero.
     */
    public Tablero( int x, int y )
    {
        this.x = x;
        this.y = y;
        
        tablero = new Color[x][y];
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
    }

    /**
     * Genera el tablero a dibujar y lo retorna.
     * 
     * @return el tablero a representar.
     */
    public Color[][] getTablero() {
        Color[][] tablero = new Color[x][y];
        for (int i=0; i<x; i++) {
            for (int j=0; j<y; j++) {
                tablero[i][j] = this.tablero[i][j];
            }
        }
        if (actual != null) {
            fijaPieza(tablero);
        }
        return tablero;
    }
    
    /**
     * Se encarga de fijar la pieza al tablero.
     */
    public void fijaPieza() {
        fijaPieza(tablero);
    }

    /**
     * Fija la pieza en el tablero pasado como parámetro.
     * 
     * @param tablero El tablero en el que fijar la pieza actual.
     */
    private void fijaPieza( Color[][] tablero ) {
        short [][] forma = actual.toma();
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                if (forma[i][j] == 1) {
                    tablero[xp+j][yp+i] = actual.getColor();
                }
            }
        }
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
    }
    
    /**
     * Rota la pieza actual.
     */
    public void rotarPieza() {
        actual.rotar();
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
}

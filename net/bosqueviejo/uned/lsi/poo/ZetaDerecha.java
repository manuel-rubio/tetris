package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * La clase ZetaDerecha se encarga de mantener los datos para la pieza
 * de tipo ZetaDerecha del juego Tetris.
 * 
 * Las formas que se incluyen son:
 * 
 *  +-   |
 * -+    ++
 *        |
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class ZetaDerecha extends Pieza
{
    private int posicion;
    private short[][][] formas = 
    {
        { 
            { 0, 1, 1, 0 },
            { 1, 1, 0, 0 },
            { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }
        },
        {
            { 1, 0, 0, 0 },
            { 1, 1, 0, 0 },
            { 0, 1, 0, 0 },
            { 0, 0, 0, 0 }

        }
    };

    /**
     * Constructor for objects of class EleDerecha
     */
    public ZetaDerecha() {
        super();
        posicion = 0;
    }
    
    /**
     * Rotación de piezas.
     */
    public void rotar() {
        posicion = (++posicion) % 2;
    }
    
    /**
     * Da la pieza en curso.
     * 
     * @return la pieza en formato de matriz de 4x4.
     */
    public short[][] toma() {
        return formas[posicion];
    }

    /**
     * Da la pieza siguiente.
     * 
     * @return la pieza en formato de matriz de 4x4.
     */
    public short[][] tomaSig() {
        int posicion = (this.posicion+1) % 2;
        return formas[posicion];
    }
}

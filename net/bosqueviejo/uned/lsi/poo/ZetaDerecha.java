package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * La clase ZetaDerecha se encarga de mantener los datos para la pieza
 * de tipo ZetaDerecha del juego Tetris.
 * 
 * Las formas que se incluyen son:
 * <pre>
 *  +-   |
 * -+    ++
 *        |
 * </pre>
 * @author Manuel �ngel Rubio Jim�nez
 * @version 2011-04-22
 */
public class ZetaDerecha extends Pieza
{

    /**
     * Obtiene las formas para la pieza. La cantidad de formas
     * depender� de las rotaciones que tenga la pieza.
     * 
     * Se genera un vector de rotaciones, cada rotaci�n es una
     * matriz de 4x4 que contiene en 0 y 1 la representaci�n de
     * la pieza para esa rotaci�n.
     * 
     * @return un vector de matrices que representa la pieza.
     */
    protected short[][][] getFormas() {
        short[][][] formas = 
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
        return formas;
    }
    
    /**
     * Toma el n�mero de rotaciones que tiene la pieza en s�.
     * Esta es la dimensi�n del vector de matrices que retorna
     * el m�todo <em>getFormas</em>.
     * 
     * @return el n�mero de rotaciones.
     */
    protected int getRotaciones() {
        return 2;
    }
}

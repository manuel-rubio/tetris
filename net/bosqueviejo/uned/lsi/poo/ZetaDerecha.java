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
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class ZetaDerecha extends Pieza
{

    /**
     * Obtiene las formas para la pieza. La cantidad de formas
     * dependerá de las rotaciones que tenga la pieza.
     * 
     * Se genera un vector de rotaciones, cada rotación es una
     * matriz de 4x4 que contiene en 0 y 1 la representación de
     * la pieza para esa rotación.
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
     * Toma el número de rotaciones que tiene la pieza en sí.
     * Esta es la dimensión del vector de matrices que retorna
     * el método <em>getFormas</em>.
     * 
     * @return el número de rotaciones.
     */
    protected int getRotaciones() {
        return 2;
    }
}

package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * La clase Cuadrado se encarga de mantener los datos para la pieza
 * de tipo Cuadrado del juego Tetris.
 * 
 * Las formas que se incluyen son:
 * 
 * ++
 * ++
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public class Cuadrado extends Pieza
{
    protected short[][][] getFormas() {
        short[][][] formas = 
        {
            {
                { 1, 1, 0, 0 },
                { 1, 1, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
            }
        };
        return formas;
    }
    
    protected int getRotaciones() {
        return 1;
    }
}

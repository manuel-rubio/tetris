package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;

/**
 * La clase Te se encarga de mantener los datos para la pieza
 * de tipo Te del juego Tetris.
 * 
 * Las formas que se incluyen son:
 * 
 * |   -+-    |    |
 * +-   |    -+   -+-
 * |          |
 * 
 * @author Manuel �ngel Rubio Jim�nez
 * @version 2011-04-22
 */
public class Te extends Pieza
{
    protected short[][][] getFormas() {
        short[][][] formas = 
        {
            { 
                { 1, 0, 0, 0 },
                { 1, 1, 0, 0 },
                { 1, 0, 0, 0 },
                { 0, 0, 0, 0 }
            },
            {
                { 1, 1, 1, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
            },
            { 
                { 0, 1, 0, 0 },
                { 1, 1, 0, 0 },
                { 0, 1, 0, 0 },
                { 0, 0, 0, 0 }
            },
            {
                { 0, 1, 0, 0 },
                { 1, 1, 1, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 0 }
            }
        };
        return formas;
    }
    
    protected int getRotaciones() {
        return 4;
    }
}

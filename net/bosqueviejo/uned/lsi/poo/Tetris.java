package net.bosqueviejo.uned.lsi.poo;

import javax.swing.JFrame;
import java.awt.Color;

public class Tetris
{
    public static void main( String[] args ) {
        Tablero tablero = new Tablero(10, 20, 20);
        tablero.reinicia();
    }
}

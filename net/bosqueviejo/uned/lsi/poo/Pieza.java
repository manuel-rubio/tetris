package net.bosqueviejo.uned.lsi.poo;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

/**
 * Base que deben contener todas las piezas de Tetris.
 * 
 * @author Manuel Ángel Rubio Jiménez
 * @version 2011-04-22
 */
public abstract class Pieza
{
    protected Color color;  //!< Color de la pieza
    
    /**
     * Nombre de las clases que se pueden emplear para factory.
     */
    private static Class[] piezas = {
        EleIzquierda.class,
        EleDerecha.class,
        Cuadrado.class,
        Recta.class,
        Te.class,
        ZetaIzquierda.class,
        ZetaDerecha.class
    };
    
    /**
     * Colores que se pueden usar para la creación de las piezas.
     */
    private static Color[] colores = {
        Color.RED, Color.BLUE, Color.GREEN,
        Color.ORANGE, Color.YELLOW
    };
    
    /**
     * Generador de números aleatorios.
     */
    private static Random random = new Random(new Date().getTime());

    /**
     * Constructor de la clase. Genera un color de forma
     * aleatoria para la pieza.
     */
    public Pieza() {
        color = colores[random.nextInt(colores.length)];
    }

    /**
     * Rotación de piezas.
     */
    abstract public void rotar();
    
    /**
     * Toma la posición actual en formato de matriz de 4x4.
     * 
     * @return matriz de enteros cortos de 4x4 con 0 ó 1, según donde hay pieza.
     */
    abstract public short[][] toma();
    
    /**
     * Toma la posicion actual en formato de matriz de la siguiente posicion.
     * 
     * @return matriz de enteros cortos de 4x4 con 0 ó 1, según donde hay pieza.
     */
    abstract public short[][] tomaSig();

    /**
     * Toma el color de la pieza actual.
     * 
     * @return el color de la pieza.
     */
    public Color getColor() {
        return color;
    }
    
    /**
     * Genera una pieza aleatoriamente y con color aleatorio.
     * 
     * @return la pieza generada.
     */
    public static Pieza factory() {
        Class tipoPieza = piezas[random.nextInt(piezas.length)];
        Pieza pieza;
        try {
            pieza = (Pieza) tipoPieza.newInstance();
        } catch (Exception ie) {
            pieza = new EleIzquierda();
        }
        return pieza;
    }
}

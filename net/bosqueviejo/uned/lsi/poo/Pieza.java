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
    private int posicion; //!< Posición de rotación de la pieza.
    
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
     * Generador de números aleatorios.
     */
    private static Random random = new Random(new Date().getTime());

    /**
     * Constructor de la clase. Genera un color de forma
     * aleatoria para la pieza.
     */
    public Pieza() {
        posicion = 0;
    }

    /**
     * Rotación de piezas.
     */
    public void rotar() {
        posicion = (++posicion) % getRotaciones();
    }
    
    /**
     * Da la pieza en curso.
     * 
     * @return la pieza en formato de matriz de 4x4.
     */
    public short[][] toma() {
        return getFormas()[posicion];
    }

    /**
     * Da la pieza siguiente.
     * 
     * @return la pieza en formato de matriz de 4x4.
     */
    public short[][] tomaSig() {
        int posicion = (this.posicion+1) % getRotaciones();
        return getFormas()[posicion];
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
        } catch (Exception e) {
            e.printStackTrace();
            pieza = new EleIzquierda();
        }
        return pieza;
    }
    
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
    protected abstract short[][][] getFormas();
    
    /**
     * Toma el número de rotaciones que tiene la pieza en sí.
     * Esta es la dimensión del vector de matrices que retorna
     * el método <em>getFormas</em>.
     * 
     * @return el número de rotaciones.
     */
    protected abstract int getRotaciones();

    /**
     * Toma el color de la pieza actual.
     * 
     * @return el color de la pieza.
     */
    public abstract Color getColor();
}

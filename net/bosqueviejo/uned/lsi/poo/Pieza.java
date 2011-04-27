package net.bosqueviejo.uned.lsi.poo;

import java.awt.Color;
import java.util.Date;
import java.util.Random;

/**
 * Base que deben contener todas las piezas de Tetris.
 * 
 * @author Manuel �ngel Rubio Jim�nez
 * @version 2011-04-22
 */
public abstract class Pieza
{
    private int posicion; //!< Posici�n de rotaci�n de la pieza.
    
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
     * Generador de n�meros aleatorios.
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
     * Rotaci�n de piezas.
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
     * Toma el color de la pieza actual.
     * 
     * @return el color de la pieza.
     */
    abstract public Color getColor();
    
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
     * depender� de las rotaciones que tenga la pieza.
     * 
     * Se genera un vector de rotaciones, cada rotaci�n es una
     * matriz de 4x4 que contiene en 0 y 1 la representaci�n de
     * la pieza para esa rotaci�n.
     * 
     * @return un vector de matrices que representa la pieza.
     */
    protected abstract short[][][] getFormas();
    
    /**
     * Toma el n�mero de rotaciones que tiene la pieza en s�.
     * Esta es la dimensi�n del vector de matrices que retorna
     * el m�todo <em>getFormas</em>.
     * 
     * @return el n�mero de rotaciones.
     */
    protected abstract int getRotaciones();
}

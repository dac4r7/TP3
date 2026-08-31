/*
 

*/
package lab.tp3.EJER6;
import java.util.HashMap;
import lab.tp3.EJER6.Libros.Libro;

/**
 *
 * @author Diego Adrian Cesarin
 */
public interface IEntradaSalida {
 
    public void guardarDatos(HashMap<Integer,Libro> biblioteca );
    public HashMap<Integer,Libro> leerDatos();
}

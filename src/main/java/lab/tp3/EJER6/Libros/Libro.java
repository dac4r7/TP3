/*
  */
package lab.tp3.EJER6.Libros;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author diego
 */
public interface Libro {   //metodos a implementar para cada libro
  
    public String mostrarCapitulos();
    
    public String verResumen();
    public String verResumenCorto();
   
    public void agregarStock(int cantidad);
    public int getStock();
    public void sacarStock(int cantidad);
    public int getId();
    public String getTitulo() ;
    public String getAutores();
    public String getISBN() ;  
 
}

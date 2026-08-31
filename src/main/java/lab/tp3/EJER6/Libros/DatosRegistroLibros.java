/*
  */
package lab.tp3.EJER6.Libros;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lab.tp3.EJER6.FormatoLibro;

/**
 *
 * @author Diego Adrian Cesarin
 */
public abstract class DatosRegistroLibros implements Serializable {//registra todos los atributos
   protected final int id; 
   protected  String titulo = null;
   protected  String autores = null;
   protected  String editorial = null;
   protected  String anioedicion = null;
   protected String formato = null;
   protected  String ISBN = null;
   FormatoLibro  formatolibro = null;
   Set<String> capitulos = null;
   protected int existencias ;
   protected Categorias categoria = null;//ingenieria,ciencias_naturales,ciencias_sociales,
                                  // ciencia_ficción, romance, misterio, juveniles , policiales
   protected Tematica tematica = null ;  //ingenieria,novela
   
    public DatosRegistroLibros( int id,String titulo,
                                String autores,
                                String editorial,
                                String anioedicion, 
                                String ISBN,
                                FormatoLibro formatolibro,
                                int existencias) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
        this.anioedicion = anioedicion;
        this.ISBN = ISBN;
        this.formatolibro = formatolibro;
        this.capitulos = new HashSet<>();     
        this.existencias = existencias;
     
    }  
}

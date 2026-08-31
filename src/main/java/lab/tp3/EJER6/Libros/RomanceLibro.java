/*
 

*/
package lab.tp3.EJER6.Libros;

import java.io.Serializable;
import java.util.Objects;
import lab.tp3.EJER6.Libros.Libro;
import lab.tp3.EJER6.Libros.DatosRegistroLibros;
import java.util.Set;
import lab.tp3.EJER6.FormatoLibro;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class RomanceLibro extends DatosRegistroLibros implements Libro , Serializable{

    public RomanceLibro(int id ,String titulo, String autores,
                        String editorial, String anioedicion, 
                        String ISBN, 
                        FormatoLibro formatolibro, 
                        int existencias) {
        
    super( id,titulo, autores, editorial, anioedicion, 
           ISBN, formatolibro, existencias);
     this.tematica = Tematica.novela;
     this.categoria = Categorias.romance;
    }

    @Override
    public String mostrarCapitulos() {
    StringBuilder sb = new StringBuilder();
        sb.append("=====TITULO: " + this.titulo+"======="+"\n"  
                  +"===== CAPITULOS ====================="+"\n");
        if(this.getCapitulos()!= null)
        for(String c : this.capitulos){
            sb.append(c);
        }
       return sb.toString();
    }

    @Override
    public String verResumen() {
       StringBuilder sb = new StringBuilder();
       sb.append("==> TITULO: " + getTitulo()+"\n");
       sb.append("==> CATEGORIA: "+this.categoria.toString()+"\n");
       sb.append("==> AUTOR: "+getAutores()+"\n");
       sb.append("==> EDITORIAL: "+getEditorial()+"\n");
       sb.append("==> EDICION: "+getAnioedicion()+"\n");
       sb.append("==> ISBN: "+getISBN()+"\n");
       sb.append("==> STOCK: "+getExistencias()+"\n");
       return sb.toString();
    }

    @Override
    public void agregarStock(int cantidad) {
        this.existencias += cantidad;
      }

    @Override
    public String getTitulo() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.titulo);
      //  return titulo;
        return sb.toString();
    }

    @Override
    public String getAutores() {
        return autores;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getAnioedicion() {
        return anioedicion;
    }

    public String getFormato() {
        return formato;
    }

    @Override
    public String getISBN() {
        return ISBN;
    }

    public FormatoLibro getFormatolibro() {
        return formatolibro;
    }

    public Set<String> getCapitulos() {
        return capitulos;
    }

    public Tematica getTematica() {
        return tematica;
    }

    public int getExistencias() {
        return existencias;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    @Override
    public int getId() {
        return id;
    }    

    @Override
    public String verResumenCorto() {
  StringBuilder sb = new StringBuilder();
       sb.append("==TITULO>" + getTitulo()+"=STOCK>"+this.getExistencias()+"=CATEGORIA>"+this.categoria.toString()+"=\n");
       sb.append("==AUTOR>" + getAutores() + "==EDITORIAL>" + getEditorial() + "===========\n");  
        return sb.toString();
    }  
    
    @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Libro)) return false;
    Libro l = (Libro) o;
    return this.getId() == l.getId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }

    @Override
    public int getStock() {
   return this.existencias;
    }

    @Override
    public void sacarStock(int cantidad) {
      if(cantidad<=this.existencias){
          this.existencias -= cantidad;
      }else{
          System.out.println("NO HAY SUFICIENTE STOCK!");
      }
    }
 
}

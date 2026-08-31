/*

  PERSISTE LA INFORMACION DE LA LIBRERIA
  CON EL STOCK DE LIBROS

*/
package lab.tp3.EJER6;

import lab.tp3.EJER6.Libros.Libro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author Diego Adrian Cesarin
 */

public class RegistrosDAO implements IEntradaSalida{
    
    private ObjectInputStream datosentrada = null;
    private ObjectOutputStream datossalida = null;
    private final String rutaalarchivo = "src/main/resources/biblioteca.dat";//ruta al archivo

    public RegistrosDAO() {
                
    }

      //Agrega y guarda los datos contenidos en la reserva actual a los del archivo   
    @Override
    public void guardarDatos(HashMap<Integer,Libro> biblioteca)  {
        HashMap<Integer,Libro> b = null;
        try{
         b = leerDatos();   //se leen los datos desde el archivo
        }catch(Exception e){System.out.println("Error al leer Archivo");
        }
        if (b != null) //se unifican los datos de reservas en un solo Set  
        {
            b.putAll(biblioteca); 
        }
        //cliente = clientesleidos;
        try {
            datossalida = new ObjectOutputStream(new FileOutputStream(this.rutaalarchivo));
            datossalida.writeObject(biblioteca);                      //se guardan todos los datos

            System.out.println(">>NUEVOS LIBROS GUARDADOS<<");
        } catch (IOException ioe) {
            System.out.println("--ERROR-NO-SE-PUDO-GUARDAR-EL-NUEVO-REGISTRO---");
        }
      if (datossalida != null) {       //se cierra salida    
        try{
          datossalida.close();   //se leen los datos desde el archivo
        }catch(Exception e){System.out.println("Error al Cerrar Flujo de Salida");
        e.printStackTrace();
        }        
        }
    }

    // lee los datos conenidos en el archivo  
    @Override
    public HashMap<Integer,Libro> leerDatos() {
        HashMap<Integer,Libro> b= new HashMap<>();
        File a = new File(this.rutaalarchivo);         //existe el archivo y no esta corrupto?
        if (!a.exists() || a.length() == 0) {
            return b;      //no existe se devuelve un set vacio
        }
         
        try {
            this.datosentrada = new ObjectInputStream(new FileInputStream(this.rutaalarchivo));
            b = (HashMap<Integer,Libro> ) datosentrada.readObject();//se leen los datos

            System.out.println("----LIBROS--LEIDOS-DESDE-EL-ARCHIVO----");
        } catch (IOException e) {
            System.out.println("--ERROR EN LA LECTURA DEL ARCHIVO--");
              e.printStackTrace();
            return  b ;
        }catch (ClassNotFoundException e) {
            System.out.println("--ERROR DURANTE LA LECTURA DEL ARCHIVO--");
              e.printStackTrace();
            return b;
        } 
        finally {
            if (this.datosentrada != null) {     //se cierra la entrada abierta
               try{
                    datossalida.close();   //se leen los datos desde el archivo
                  }catch(Exception e){System.out.println("Error al Cerrar Flujo de Salida");
                  }
            }
        }
        return b;
    }    
}

/*
Lee los datos de un archivo y los deserializa 

*/
package lab.tp3.EJER4;

import java.io.EOFException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class AgendaLeerReg {
    
 private ArrayList<Registro> registros = null;
 private ObjectInputStream datosentrada = null; //Stream para los datos leidos
 private String rutaalarchivo = "src/main/resources/Agenda.ser";//ruta por defecto
 
 public AgendaLeerReg( String rutaalarchivo, ArrayList<Registro> registros) throws ClassNotFoundException, IOException{
     //si el archivo existe lee los registros si no existe toma los registros 
     //que se pasan por constructor     
      this.rutaalarchivo = rutaalarchivo;
      this.registros = registros;
 }
 
 public void abrir() throws FileNotFoundException, IOException {//lee los datos del archivo los guarda en datosentrada
   
      datosentrada = new ObjectInputStream( new FileInputStream(rutaalarchivo)) ;
      
    }
  
 public void leerEImprimir() throws ClassNotFoundException{//lee he imprime los registros en pantalla
   ArrayList<Registro> registros = null;
   
   if( datosentrada != null ){
    try{
        registros = (ArrayList<Registro>) datosentrada.readObject();
        if(registros.isEmpty()){
        System.out.println("**NO HAY REGISTROS EN LA AGENDA**"); 
        }else{
            for(Registro r : registros){
             System.out.println(r.toString());
            }
          }
        }catch(EOFException eof){
         //  System.out.println("**FIN DE LECTURA DEL REGISTRO**");
        }catch(IOException e){
            System.out.println("**ERROR EN LA LECTURA DEL ARCHIVO**");
        }
   }
  
 }
 //Este metodo solo lee el archivo carga los datos serializados y los devuelve
 //se utiliza en el constructor de Menu para leer los datos si ya existen en un archivo
 public  ArrayList<Registro> leerArchivo(String rutaalarchivo) throws ClassNotFoundException, IOException{//lee he imprime los registros
   ArrayList<Registro> registros = null;
   if( this.datosentrada == null ){
    this.abrir();
   }
    try{ 
        registros = (ArrayList<Registro>) datosentrada.readObject();  //lee el contenido del archivo
       }catch(EOFException eof){       
       }catch(IOException e){
            System.out.println("**ERROR EN LA LECTURA DEL ARCHIVO**");
            e.printStackTrace();
       }   
    return registros;
 }
 
   //buscamos un registro por el numero o por el apellido 
 public void buscar(String dato) {
     boolean encontrado = false, condicion=false ;
     int numero=-1;
     Registro r; 
     
     try{//verifico si la busqueda es por numero
        numero = Integer.parseInt(dato);
            condicion =true;
     }catch(NumberFormatException nfe){
         System.out.println("--BUSQUEDA POR APELLIDO--");  
     }
     
     try{
     registros = leerArchivo(this.rutaalarchivo);
     if(registros!= null){
       Iterator<Registro> it = registros.iterator();
         
       while(it.hasNext()){
           r = it.next();
       if( numero == r.getNumeroderegistro() && condicion){
               System.out.println("==SE ENCONTRO Nro de REGISTRO==");
               System.out.println(r.toString());
               encontrado = true;
        }else if( !condicion && dato.compareToIgnoreCase(r.getApellido())==0){
               System.out.println("==SE ENCONTRO EL APELLIDO ==");
               System.out.println(r.toString());
               encontrado = true;
        }else if((registros.size()-1 == registros.lastIndexOf(r)) && !encontrado){
              System.out.println("--NO SE ENCONTRO EL REGISTRO--");
          }
       } 
     }
     }catch(ClassNotFoundException cne){
         System.out.println("-ERROR DE ESCRITURA NO SE ENCONTRO LA CLASSPATH-");
     }catch(IOException ioe){
         System.out.println("--ERROR AL LEER REGISTROS--");
     }
  }
 
 public void cerrar() throws IOException{ 
     if(datosentrada != null){
         datosentrada.close();
     }
   //  System.out.println("**ARCHIVO CERRADO CON EXITO**");
 }

    public ObjectInputStream getDatosentrada() {
        return datosentrada;
    }
 
}

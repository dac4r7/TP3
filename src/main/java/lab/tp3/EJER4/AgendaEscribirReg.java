/*
Serializa los registros y los escribe en un archivo
*/
package lab.tp3.EJER4;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class AgendaEscribirReg {
  
    private ObjectOutputStream datossalida = null;//Stream de salida de datos
    private String rutaalarchivo = "src/main/resources/Agenda.ser";//ruta por defecto
    private ArrayList<Registro> agenda = null;
   
  public AgendaEscribirReg( String rutaalarchivo,  ArrayList<Registro> registros ) throws ClassNotFoundException, IOException{  
      this.rutaalarchivo = rutaalarchivo ;
      agenda = registros;
   }
    
  
  public void abrir() throws FileNotFoundException, IOException, ClassNotFoundException{
      
        this.leer(); //toma los datos de archivo para rescatarlos               
                     //nuevo archivo para escribir
        datossalida = new ObjectOutputStream( new FileOutputStream(this.rutaalarchivo));         
  
    }
    
  public void ingresarDatos() throws ClassNotFoundException, IOException{//crea un nuevo registro
      int n;
      System.out.println("=== NUEVO REGISTRO ===");
      System.out.print("== INGRESE NOMBRE > ");    
      String nombre = leerTeclado();
      System.out.print("\n== INGRESE APELLIDO > ");
      String apellido = leerTeclado();
      System.out.print("\n== INGRESE TELEFONO DE LINEA > ");
      String telefonoLinea = leerTeclado();
      System.out.print("\n== INGRESE TELEFONO MOVIL > ");
      String telefonoMovil = leerTeclado();
      System.out.print("\n== INGRESE TELEFONO DE TRABAJO > ");
      String telefonoTrabajo = leerTeclado();
      System.out.print("\n== INGRESE EMAIL > ");
      String email = leerTeclado();
      System.out.print("\n== INGRESE EL LUGAR DEL PRIMER CONTACTO > ");
      String lugarDeContacto = leerTeclado();
      
      
      this.leer();//se actualizan los datos leidos del archivo
      
      //n va a tomar el valor del último registro entre los que hay + 1
      if(agenda.getLast().getNumeroderegistro() == agenda.size()+1){
          n = agenda.getLast().getNumeroderegistro()+1;
      }else{
          n = agenda.size()+1;
      }
      //nuevo registro
      Registro nuevoregistro = new Registro(n,nombre,apellido,telefonoLinea,telefonoMovil,telefonoTrabajo,email,lugarDeContacto); 
  
      this.agenda.add(nuevoregistro); //agregamos el nuevo registro 
           try{
            escribir(this.agenda);  //se escribe el nuevo registro
               System.out.println("==SE REGISTRARON LOS DATOS EXITOSAMENTE==");
           }catch(IOException ioe){
               System.out.println("--ERROR AL GUARDAR DATOS--");
                       ioe.printStackTrace();
           }
  }
  
 //lee datos(para actualizar this.agenda), busca, borra(si encuentra) y guarda
  public void borrar(int numerodereg) throws ClassNotFoundException, IOException{
       boolean encontrado = false;
   
         this.leer();   //se actualiza this.agenda con los datos del archivo
         if(this.agenda.size() == 0)
           { System.out.println("--NO HAY REGISTROS QUE BORRAR--");
             return;}
      
         Iterator<Registro> it = this.agenda.iterator();
          Registro r ;
         while(it.hasNext()){
             r = it.next();         
          if(r.getNumeroderegistro() == numerodereg ){         
              System.out.println("====SE ENCONTRO REGISTRO====>> Nombre: "+
                                 r.getNombre() + " Apellido: "+r.getApellido());
              System.out.println("DESEA ELIMINARLO ? s:SI n:NO");
              if( validarRespuesta()){
                    it.remove();
                  System.out.println("========REGISTRO REMOVIDO=======");
               try{
                   escribir(this.agenda);  //escribe con la agenda actualizada
                  }catch(IOException ioe){
                  System.out.println("--ERROR DURANTE LA ACTUALIZACION DE LA AGENDA--");                  
                  }
               }                      
          }else if((agenda.size()-1 == agenda.lastIndexOf(r)) && !encontrado  ){
              System.out.println("--NO SE ENCONTRO EL REGISTRO--"); 
          }
     }       
  }
  //crea un OuputStream y guarda los datos que se pasan
  private void escribir(ArrayList<Registro> agenda) throws FileNotFoundException, IOException{  
    datossalida = new ObjectOutputStream( new FileOutputStream(this.rutaalarchivo));         
    try{ datossalida.writeObject(agenda);//se guarda en el archivo
       }catch(IOException ioe){
       System.out.println("--NO SE PUDO ESCRIBIR LA AGENDA--");                  
       } 
  }
  
  //lee y retorna los datos leidos desde el archivo
  public void leer() throws ClassNotFoundException{
   try{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.rutaalarchivo));
        this.agenda = (ArrayList<Registro>) in.readObject(); 
        if(this.agenda != null){
        System.out.println("========AGENDA LEIDA========"); }
       }catch(EOFException eof){
          // System.out.println("**FIN DE LECTURA DEL REGISTRO**");
        }catch(IOException e){
            System.out.println("========AGENDA CREADA========");
            this.agenda = new ArrayList<Registro>();
        }  
 }
  
  private String leerTeclado(){//lee datos ingresados por teclado y debuelve un string
      String dato = "";
      try{//flujo de datos de entrada sincronized
          InputStreamReader inputsr = new InputStreamReader(System.in);
          BufferedReader flujoDE = new BufferedReader(inputsr);
          dato = flujoDE.readLine();          
      }catch(IOException ioe){
          System.out.println("--ERROR : "+ioe.getMessage());
      }
      return dato;
  }
  
  public boolean validarRespuesta(){   //Evalua si una respuesta es S o N
         Scanner d = new Scanner(System.in);
       boolean verificar = false, respuesta = false;
       String dato;
    while(!verificar){
       try{
           dato = d.nextLine();
           if(dato.equalsIgnoreCase("s")){
               respuesta= true;
               verificar=true;
           }else if(dato.equalsIgnoreCase("n")){
                respuesta = false;
                verificar = true;
           }else{
               throw new InputMismatchException("Solo puede ingresar s(para SI) o n(para NO)");
           }
       }catch(InputMismatchException ime){
           System.out.println(ime.getMessage());
       }     
      }
       return respuesta;      
    }
 
  
  public void cerrar() throws IOException{ 
    if(datossalida != null){ datossalida.close();
     }   
 }
  
}

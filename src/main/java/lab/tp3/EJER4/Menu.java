/*
Deberá contar con un menú para registrar, eliminar, buscar un registro y para mostrar todos.
  */
package lab.tp3.EJER4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class Menu {
    
    Scanner dato = new Scanner(System.in);
    ArrayList<Registro> Agenda = null;
    AgendaEscribirReg escribirAgenda= null;//objeto para escribir en la agenda
    AgendaLeerReg leerAgenda = null;//objeto para leer en la agenda
    private final String rutaalarchivo = "src/main/resources/Agenda.ser";//ruta por defecto
    
    Menu() throws FileNotFoundException, IOException, ClassNotFoundException{   
      this.Agenda = new ArrayList<>();
      this.leerAgenda = new AgendaLeerReg(rutaalarchivo,Agenda);
      this.escribirAgenda = new AgendaEscribirReg(rutaalarchivo,Agenda); 
                     
    }
    
    public void menuDeOpciones() throws ClassNotFoundException, IOException{
        
        int opcion = 0;      
        do{
            System.out.println("=========AGENDA=========");
            System.out.println("1 => REGISTRAR");
            System.out.println("2 => ELIMINAR");
            System.out.println("3 => MOSTRAR REGISTROS");
            System.out.println("4 => BUSCAR UN REGISTRO");
            System.out.println("5 => SALIR");
            System.out.println("========================");
            opcion = verificarEleccion(); 
            ejecutarOpcion(opcion);
        }while( opcion!=5 );
                
    }
    
   private int verificarEleccion(){        
    boolean ingresovalido = false; 
    int opcion = 0;      
    while (!ingresovalido ) {
     try{ System.out.print("OPCION=> ");
          opcion = dato.nextInt();
          if(opcion < 1 || opcion >5){
           throw new InputMismatchException();
           } ingresovalido = true;    
        }catch(InputMismatchException e){
            System.out.println("=Debe Ingresar un NUMERO Valido(1 al 5)==");
             dato.nextLine();  //limpia buffer
        }catch(Exception e){
            System.out.println("Error en la verificacion");
            dato.nextLine();
         }       
       }
       return opcion; 
    }
    
    private void ejecutarOpcion(int opcion) throws ClassNotFoundException, IOException{
       
        switch (opcion) {
            case 1://REGISTRAR                
                escribirAgenda.abrir();
                escribirAgenda.ingresarDatos();
                escribirAgenda.cerrar();             
               
                break;
            case 2://borra un registro
                try{
               
                dato.nextLine();
                System.out.println("> INGRESE EL NUMERO DE REGISTRO QUE DESEA BORRAR <");
                String num = dato.nextLine().trim();  //quitamos los espacios        
                if(!num.matches("\\d+")){//Se valida que solo haya numeros dentro de num
                 throw new InputMismatchException();
                }          
                escribirAgenda.borrar(Integer.parseInt(num));//si num no es un numero->NumberFormatException
                escribirAgenda.cerrar();
                }catch( NumberFormatException e){
                 System.out.println("**DEBE INGRESAR UN NUMERO COMO NUMERO DE REGISTRO**");
                }catch(InputMismatchException ime){
                   System.out.println("**DEBE INGRESAR UN NUMERO**");   
                }
             
                break;
            case 3://muestra los registros
                
                leerAgenda.abrir();
                leerAgenda.leerEImprimir();
                leerAgenda.cerrar();             
           
                break;
            case 4://Busca un registro
               
                leerAgenda.abrir();
                dato.nextLine();
                System.out.println(">INGRESE APELLIDO O Nro DE REGISTRO<");                
                String ape = dato.nextLine().trim();//quitamos los espacios  
                leerAgenda.buscar( ape );
                leerAgenda.cerrar();
               
                break;   
            case 5://SALIR
                  System.out.println("Saliendo");
                break;
        }     
    }
}

/*
CLASE Menu 

*/
package lab.tp3.EJER1;

import java.util.Scanner;

/**
 *
 * @author Diego A Cesarin
 */
public class Menu {
    
    ManejoNumeros manejonumeros = null;
    ManejoAlumnos manejoalumnos = null;
    
    Menu( ManejoNumeros manejonumeros ,  ManejoAlumnos manejoalumnos){
     this.manejoalumnos = manejoalumnos;
     this.manejonumeros = manejonumeros;
     }   
        
    public void opcionesMenu( ) {
      boolean salir = false, ingresovalido = false; 
      int opcion;
      Scanner o = new Scanner(System.in);       
       while(!salir){
           System.out.println("ELIJA UNA DE LAS SIGUIENTES OPCIONES"); 
           System.out.println("0.Salir                         " );
           System.out.println("1.Agregar un entero a la Lista     "+ "5.Agregar un Alumno a la Lista" );
           System.out.println("2.Sacar un entero de la Lista      "+ "6.Sacar un Alumno de la Lista");
           System.out.println("3.Mostrar la Lista de Enteros      " +"7.Mostrar la lista de Alumnos" );
           System.out.println("4.Buscar un numero Entero en Lista "  +"8.Buscar un Alumno dentro de la Lista" );
           System.out.print("Seleccion>> ");
           
           while(!ingresovalido){
           try{     
            opcion = o.nextInt();
            while(opcion < 0 || opcion >8 ){//validacion
                 System.out.println("Seleccione una Opcion Valida del 0 a 8 .");   
                 opcion = o.nextInt();
            }
             switch(opcion){
               case 0 -> salir = true;
               case 1 -> this.manejonumeros.agregarEntero( manejonumeros.getListadeenteros() ) ;
               case 2 -> this.manejonumeros.sacarEntero( manejonumeros.getListadeenteros() );
               case 3 -> this.manejonumeros.mostrarListaEnteros( manejonumeros.getListadeenteros());
               case 4 -> this.manejonumeros.buscarNumero( manejonumeros.getListadeenteros() );
               case 5 -> this.manejoalumnos.agregarAlumno( manejoalumnos.getListadealumnos() );
               case 6 -> this.manejoalumnos.retirarAlumno(  manejoalumnos.getListadealumnos() );
               case 7 -> this.manejoalumnos.mostrarAlumnos(  manejoalumnos.getListadealumnos() );
               case 8 -> this.manejoalumnos.buscarAlumno(  manejoalumnos.getListadealumnos() );            
           }    ingresovalido = true;
            }catch(Exception e){
               System.out.println(" Ingrese una opcion Valida un numero de 0 al 8");
               o.nextLine();
           }
          }
          ingresovalido = false; 
          o.nextLine();
       }                
     }  
}

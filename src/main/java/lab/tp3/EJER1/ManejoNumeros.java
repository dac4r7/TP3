/*
 
clase manejoNumeros se encarga de las operaciones con numeros dentro de la lista 

*/
package lab.tp3.EJER1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class ManejoNumeros {
    
    private ArrayList<Integer> listadeenteros = null;

    public ManejoNumeros(ArrayList<Integer> listadeenteros) {
        this.listadeenteros = listadeenteros;
    }

    public ArrayList<Integer> getListadeenteros() {
        return listadeenteros;
    }
      
    
      static void agregarEntero(ArrayList<Integer> listaenteros){
        boolean validacion = false;
        Scanner dato = new Scanner(System.in);
        int numero;       
       while(!validacion){           
          try{ System.out.print("INGRESE UN NUMERO ENTERO>> ");    
               numero = dato.nextInt();             
               listaenteros.add(numero); 
               validacion = true;
          }catch(Exception e){
               System.out.println("Solo se pueden agregar NUMEROS ENTEROS ");
               dato.nextLine();
           }          
          }  
    }
    
    static void sacarEntero(ArrayList<Integer> listaenteros){
         boolean validacion = false,encontrado = false;
        Scanner dato = new Scanner(System.in);
        int numero;
          while(!validacion){
             System.out.print("INGRESE UN NUMERO ENTERO>> ");
          try{     
               numero = dato.nextInt();
               if(listaenteros.remove((Integer)numero)){//devuelve true si se encontro
                 System.out.println("<<El numero se encontro y elimino con exito>>");
               }else{
                   System.out.println("<El numero no fue encontrado>");
               }
               validacion = true;
          }catch(Exception e){
               System.out.println("Solo se pueden quitar NUMEROS ENTEROS ");
               dato.nextLine();
           }
          }   
    }
    
    static void mostrarListaEnteros(ArrayList<Integer> lista){
        System.out.println("LISTA DE ENTEROS");
        for(int n : lista){
            System.out.print(">>" + n);
        }
        System.out.println("");
    }
    
    static void buscarNumero( ArrayList<Integer> lista ){
        boolean validacion = false;
        Scanner dato = new Scanner(System.in);
        int numero,i=0;
          while(!validacion){
             System.out.print("INGRESE UN NUMERO ENTERO>> ");
           try{     
               numero = dato.nextInt();
               while( i < lista.size() && lista.get(i)!= numero){
                i++;              
               } if(i != lista.size()){
                 System.out.print("Numero encontrado en la posicion>> " + i+"\n");
               }else{System.out.println("<NUMERO NO ENCONTRADO>");              }
               validacion = true;
               }catch(Exception e){
                  System.out.println("Solo pueden ser NUMEROS ENTEROS ");
                  dato.nextLine();
               }
          }      
    }
    
}

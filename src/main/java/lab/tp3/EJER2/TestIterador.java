/*
 
*/
package lab.tp3.EJER2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class TestIterador { 
    
   
    //  Scanner numero = new Scanner(System.in); 
    
   public TestIterador(){
    }

    public void ejemplodeIterador() {
        ArrayList<Integer> listaenteros = new ArrayList<>();
            
        System.out.println("SE AGREGAN ELEMENTOS RANDOM A LA LISTA ");
        for(int i = 0 ; i < 10 ; i++){
            listaenteros.add((int)(Math.random()*20+1));
        }
        //solo permite recorrer el Arraylist y remover elementos
        Iterator<Integer> it = listaenteros.iterator();  //sacamos una foto a la lista
        // se itera con los items que existen en este momento
        
        System.out.println("SE RECORRE LA LISTA :");
        while(it.hasNext()){
            int n = it.next();
            System.out.print(n+"  ");
            if(n<10){  it.remove();}
        }
        System.out.println("\nELIMINAMOS LOS MENORES A 10 Y RECORREMOS LA LISTA");
        it = listaenteros.iterator();//como se actualizo la lista de elementos actualizamos el iterador
        while(it.hasNext()){
            System.out.print(it.next()+"  ");
        }
        
        //Se utiliza ListIterator para agregar , quitar , recorrer el Arraylist
        System.out.println("\nAHORA SE AGREGAN NUEVOS ELEMENTOS : 100-200-300-400-500");
        ListIterator<Integer> listit = listaenteros.listIterator();
        
        listit.add(100);
        listit.add(200);
        listit.add(300);
        listit.add(400);
        listit.add(500);
        
        System.out.println("\nCOMO AGREGUE ELEMENTOS AL PRINCIPIO LA LISTA QUEDO EN EL ");
        System.out.println("ULTIMO ELEMENTO AL IR HACIA ATRAS VUELVO AL ELEMENTO 0");
        while(listit.hasPrevious()){
            //recorrer el indice hacia atras 
            int indiceprev = listit.previousIndex();
            int n = listit.previous();
            System.out.println("Elemento indice : "+ indiceprev + " Elemento :"+ n);
        }
        System.out.println("DEL ELEMENTO 0 VOY HASTA EL FINAL");
        while(listit.hasNext()){
            //recorrer el indice hacia atras 
            int indicenext = listit.nextIndex();
            int n = listit.next();
            System.out.println("Elemento indice : "+ indicenext + " Elemento :"+ n);
        }
        System.out.println("INGRESE UN ELEMENTO A BUSCAR EN LA LISTA ");
        int numero = validarNumero();
        listit = listaenteros.listIterator();
        while( listit.hasNext()){
            int n = listit.next();
            if(n == numero){
                System.out.println("Se encontro el numero "+ n +" en el indice :" + listit.nextIndex());
            }
            
        }
    }
    
     static int validarNumero( ){
      Scanner dato = new Scanner(System.in);
      boolean validar = false;
      int numero = 0;  
        System.out.print("Ingrese un numero"+ ">> ");
      
      while(!validar){
        try{
          numero = dato.nextInt();
          validar = true;
         }catch(Exception e){
            System.out.println("TIENE QUE INGRESAR UN NUMERO"); 
            dato.next(); //se limpia el ingreso del dato incorrecto  
         }     
      }
      return numero;
    }
     
   }


/*

clase manejoAlumnos se encarga de las operaciones con los alumnos dentro de la lista 
 */
package lab.tp3.EJER1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class ManejoAlumnos {
    
    ArrayList<Alumno> listadealumnos = null;

    public ManejoAlumnos(ArrayList<Alumno> listadealumnos ) {
      this.listadealumnos = listadealumnos;
    }   

    public ArrayList<Alumno> getListadealumnos() {
        return listadealumnos;
    }
    
     public void agregarAlumno( ArrayList<Alumno> listaalumnos ){
       boolean validacion = false;
        Scanner dato = new Scanner(System.in);
        String ingreso ;
        int numero;
      while(!validacion){           
         try{ System.out.print("INGRESE NOMBRE DEL ALUMNO >> ");    
             ingreso = validarString("NOMBRE"); 
             System.out.print("INGRESE EL DNI DEL ALUMNO >> ");
             numero = validarNumero("NUMERO");
             Alumno alumno = new Alumno(ingreso , numero);
             listaalumnos.add(alumno);
             validacion = true;
         }catch(Exception e){
               System.out.println("Solo se pueden agregar NUMEROS ENTEROS ");
               dato.nextLine();
           }          
          }   
    }
    public void retirarAlumno( ArrayList<Alumno> listaalumnos){
         boolean validacion = false;
        Scanner dato = new Scanner(System.in);
        int numero,i=0;
       
          while(!validacion){
             System.out.print("INGRESE UN NUMERO DE DNI>> ");
           try{     
               numero = validarNumero("NUMERO");              
               while( i < listaalumnos.size() ){
                 
                if(numero == listaalumnos.get(i).getDni()){   
                    System.out.print("ALUMNO NOMBRE >> "+  listaalumnos.get(i).getNombre()+" DNI >>"
                                  +  listaalumnos.get(i).getDni() +"<<RETIRADO DE LA LISTA>>"+"\n");
                    listaalumnos.remove(i);                 
               }else if( i >= listaalumnos.size()-1){
                   System.out.println("<ALUMNO NO ENCONTRADO EN LA LISTA>");    
               }
                 i++;
              }
               validacion = true; //se retiro de la lista el alumno se sale del bucle
             }catch(Exception e){
                  System.out.println("SOLO PUEDE INGRESAR NUMEROS ENTEROS PARA EL DNI");
                  dato.nextLine();
               }
          }     
        
        
    }
    public void mostrarAlumnos(ArrayList<Alumno> listaalumnos){
        System.out.println("<<<<LISTA DE ALUMNOS>>>>");
        for(Alumno a : listaalumnos){
            System.out.println(">> NOMBRE : " + a.getNombre()); 
            System.out.println(">> DNI : " + a.getDni());
            System.out.println(">>---------------------------");
        }
    }
    
    
    public void buscarAlumno( ArrayList<Alumno> listaalumnos ){
        boolean validacion = false;
        Scanner dato = new Scanner(System.in);
        int numero,i=0;
        Alumno alumno = null;
          while(!validacion){
             System.out.print("INGRESE UN NUMERO DE DNI>> ");
           try{     
               numero = validarNumero("NUMERO");//la busqueda es por DNI
               
               while( i < listaalumnos.size() && alumno == null){//si encontro el elemento saldra del while
                              
                if(numero == listaalumnos.get(i).getDni()){
                    alumno = listaalumnos.get(i);
                 System.out.print("Nro. "+i+" ALUMNO ENCONTRADO>> DNI: " + alumno.getDni() +
                                  " NOMBRE : "+ alumno.getNombre()+"\n");
               }else if(i >= listaalumnos.size()-1 && alumno == null){//llego al final de la lista
                   System.out.println("<ALUMNO NO ENCONTRADO>");    
               }
                i++;//siguiente elemento si hay
               }
               validacion = true;
               }catch(Exception e){
                  System.out.println("SOLO PUEDE INGRESAR NUMEROS ENTEROS ");
                  dato.nextLine();
               }
          }     
    }
    
    public String validarString(String tipo){
      Scanner dato = new Scanner(System.in);
      boolean validar = false;
      String texto = "";
      
      while(!validar){
          System.out.print("Ingrese un " + tipo+">> " );
          texto = dato.nextLine();
          
          if(texto.trim().isEmpty()){
              System.out.println("El "+ tipo + " no puede estar vacia");
          }else if(!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")){//expresion regular para detectar 
                                                               //solo letras y tildes  con espacios
              System.out.println("El texto solo puede contener letras");
          }else{
              validar= true;
          }
      }
      return texto;
    }
    
    
    public int validarNumero(String tipo ){
      Scanner dato = new Scanner(System.in);
      boolean validar = false;
      int numero = 0;  
        System.out.print("Ingrese un "+ tipo+ ">> ");
      
      while(!validar){
        try{
          numero = dato.nextInt();
          validar = true;
          if(numero<= 0){
              System.out.println("El numero no puede ser menor a 0(cero)");
              validar = false;
          }
         }catch(Exception e){
            System.out.println("TIENE QUE INGRESAR UN NUMERO"); 
            dato.next(); //se limpia el ingreso del dato incorrecto  
         }
      // dato.next(); //se limpia el ingreso del dato incorrecto      
      }
      return numero;
    }
    
}

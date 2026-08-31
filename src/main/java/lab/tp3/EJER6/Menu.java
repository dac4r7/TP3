/*

*/
package lab.tp3.EJER6;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import lab.tp3.EJER6.Libros.Libro;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Menu implements MenuDAO{
    Scanner num = new Scanner(System.in);
    Scanner dato = new Scanner(System.in);
    private RegistrosDAO registrosdao = new RegistrosDAO(); //recupera y guarda los datos de los libros en archivo
    private HashMap<Integer,Libro> biblioteca = new HashMap<>();
    private Libreria libreria = null;
  
    public Menu() throws ClassNotFoundException, IOException {
                
        this.biblioteca  = this.registrosdao.leerDatos(); //clase que guarda/recupera los datos del archivo
        libreria = new Libreria(this.biblioteca);
        
        
    }

    @Override
    public void menuDeOpciones() {

        int opcion = 0;
        do {
            System.out.println(">>=================MENU==DE==LIBRERIA=================<<");
            System.out.println("0 >=> INGRESAR LIBRO(LIBRO NUEVO EN LA LIBRERIA)");
            System.out.println("1 >=> BUSCAR LIBRO (por TITULO) ");
            System.out.println("2 >=> VENTA DE LIBRO");
            System.out.println("3 >=> AGREGAR STOCK DE LIBRO");          
            System.out.println("4 >=> CONSULTAR STOCK");
            System.out.println("5 >=> GUARDAR DATOS EN EL ARCHIVO");
            System.out.println("6 >=> LEER DATOS DESDE EL ARCHIVO");
            System.out.println("7 >=> SALIR");
            System.out.println(">>====================================================<<");
            opcion = verificarNumero(7,0);
            opcionDelMenu(opcion);
         
        } while (opcion != 7);

    }

    @Override
    public void opcionDelMenu(int opcion) {
        
        switch (opcion) {
                        // ingresar un nuevo libro a la libreria
            case 0 ->  libreria.ingresarNuevoLibro();
                
            case 1 -> { Libro l = this.libreria.buscarLibro(this.biblioteca);//buscar por titulo
                      if(l== null){ System.out.println("--NO SE ENCONTRO EL LIBRO BUSCADO--");
                      }else if(l!= null){ System.out.println("///DETALLES DEL LIBRO///");
                              System.out.println(l.verResumen()); 
                      }}
     
            case 2 ->{ System.out.println("-------BUSQUEDA DE LIBRO PARA VENTA-------");
                       Libro l = this.libreria.buscarLibro(this.biblioteca);//buscar por titulo
                     //  System.out.println("imprime lo que encontro"+l.getTitulo());
                       if(l!= null && l.getStock()>0){
                           System.out.println(l.verResumenCorto());
                           System.out.println("DESEA VENDER EL LIBRO?(S/s o N/n) Existencias:"+l.getStock());
                          if(validarRespuesta()){
                              biblioteca.get(l.getId()).sacarStock(1); //vende un libro
                           System.out.println(">> LIBRO VENDIDO <<");
                          }
                       }
                     }
           case 3 -> { Libro l = this.libreria.buscarLibro(this.biblioteca);//buscar por titulo
                       if(l!= null ){System.out.println("Se encontro el libro REGISTRADO");
                           System.out.println(l.verResumenCorto());
                           System.out.println("INGRESE EL STOCK PARA AGREGAR(Maximo 100 por ingreso>>");
                          int s =  verificarNumero(100, 1);
                             l.agregarStock(s);
                          biblioteca.put(l.getId(),l);                           
                      }}
            case 4 -> {//CONSULTAR STOCK
                          for(Libro l : biblioteca.values()){
                              System.out.println(l.verResumenCorto());
                          }
            }
            case 5 -> {//GUARDAR DATOS EN EL ARCHIVO
                       registrosdao.guardarDatos(biblioteca);
            }

            case 6 -> {//LEER DATOS DESDE EL ARCHIVO
                   Map<Integer,Libro> lectura = registrosdao.leerDatos();
                   for(Libro l : lectura.values()){
                              System.out.println(l.verResumenCorto());
                          }
            }
            case 7 ->//SALIR
                System.out.println(">>>>Saliendo del programa");
            default ->
                System.out.println(">Opcion no Valida<");
        }
    }
    
     @Override
    public int verificarNumero(int max, int min) {
    
     boolean verif = false;
     int n=0;
        while(!verif){
            try{
             n = Integer.parseInt(num.nextLine());           
             if(n > max || n<min){
             throw new InputMismatchException();} 
             verif = true;
            }catch(InputMismatchException ime){
                System.out.println("El Numero ingresado no es una opcion valida , reintente. ");  
            }         
        }
      return n;
    }
    
    @Override
     public boolean validarRespuesta() {                    //Evalua si una respuesta es S o N
        boolean verificar = false, respuesta = false;
        String d;
        while (!verificar) {
            try {
                d = dato.nextLine();
                if (d.equalsIgnoreCase("s")) {
                    respuesta = true;
                    verificar = true;
                } else if (d.equalsIgnoreCase("n")) {
                    respuesta = false;
                    verificar = true;
                } else {
                    throw new InputMismatchException("Solo puede ingresar s(para SI) o n(para NO)");
                }
            } catch (InputMismatchException ime) {
                System.out.println(ime.getMessage());
            }
        }
        return respuesta;
    }

      
}

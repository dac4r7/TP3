/*
*/
package lab.tp3.EJER6;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import lab.tp3.EJER6.Libros.Libro;
import lab.tp3.EJER6.Libros.CienciaFiccionLibro;
import lab.tp3.EJER6.Libros.CienciasNaturalesLibro;
import lab.tp3.EJER6.Libros.CienciasSocialesLibro;
import lab.tp3.EJER6.Libros.IngenieriaLibro;
import lab.tp3.EJER6.Libros.JuvenilesLibro;
import lab.tp3.EJER6.Libros.MisterioLibro;
import lab.tp3.EJER6.Libros.PolicialesLibro;
import lab.tp3.EJER6.Libros.RomanceLibro;


/**
 *
 * @author Diego Adrian Cesarin
 */
public class Libreria implements IProcesodeDatos{
    
    Scanner cadena = new Scanner(System.in);
    Scanner numero = new Scanner(System.in);
    private HashMap<Integer,Libro> biblioteca = new HashMap<>() ;
    private int idprogresivo = 0; 
    
    public Libreria( HashMap<Integer,Libro> biblioteca) {
       
        this.biblioteca = biblioteca ;  //llega cargada con todos los libros leidos
        this.idprogresivo = this.biblioteca.size();//el proximo elemento libre del HashMap 
    }
    
    
    
    public void solicitarLibroAProveedor(Libro libro){       
        
    }

    @Override
    public void ingresarNuevoLibro() {
         boolean datook= false;
         int  op,cat;
         String anio="";
         FormatoLibro fl= FormatoLibro.edicion_economica;//por defecto
         
         System.out.println("== REGISTRO=DE=NUEVO=LIBRO =================");
         System.out.println("->TITULO DEL LIBRO: ");
         String tit = cadena.nextLine();
         System.out.println("->AUTOR/RES: ");
         String aut = cadena.nextLine();
         System.out.println("->EDITORIAL: ");
         String edit = cadena.nextLine();
         System.out.println("->AÑO DE EDICION: ");
         while(!datook){
         try{
           anio = cadena.nextLine();
      //     if(anio<0 || anio>2026){
      //     throw new InputMismatchException();}
           datook=true;
         }catch(InputMismatchException e){
            System.out.println("No se pudo registrar el año correctamente , ingrese nuevamente.");
             cadena.nextLine();
         }}
         System.out.println("->Numero de ISBN: ");
         String isbn = cadena.nextLine();
         System.out.println("->Seleccione el Formato del Libro(tapas_duras(1) , edicion_economica(2)): ");
          op = verificarNumero(2,1);
          System.out.println("valor de la eleccion "+op);
          if(op == 1){
              fl = FormatoLibro.tapas_duras;
          }else if(op == 2){fl = FormatoLibro.edicion_economica;}
         System.out.println("->Ingrese el numero de Existencias : ");
         int exis = verificarNumero(1000,0);// Maximo 1000 libros en stock
         System.out.println("Valor de la eleccion"+exis);
         System.out.println("===========>Ingrese la CATEGORIA============================");
         System.out.println("->(1)Ingenieria (2)Ciencias Naturales (3)Ciencias Sociales ");
         System.out.println("->(4)Ciencia Ficcion (5)Romance (6)Misterio ");
         System.out.println("->(7)Juveniles (8)Policiales ");
         cat = verificarNumero(8,1);
         
         switch(cat){
             case 1 -> {biblioteca.put( this.idprogresivo,new IngenieriaLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                        incrementarId();}
             case 2 -> {biblioteca.put(this.idprogresivo, new CienciasNaturalesLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                         incrementarId(); }
             case 3 -> {biblioteca.put(this.idprogresivo,new CienciasSocialesLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                         incrementarId(); }
             case 4 ->{ biblioteca.put(this.idprogresivo,new CienciaFiccionLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                        incrementarId();}
             case 5 ->{ biblioteca.put(this.idprogresivo, new RomanceLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                        incrementarId();}
             case 6 ->{ biblioteca.put(this.idprogresivo,new MisterioLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                        incrementarId();}
             case 7 ->{ biblioteca.put(this.idprogresivo,new JuvenilesLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                       incrementarId();}
             case 8 ->{ biblioteca.put(this.idprogresivo,new PolicialesLibro(getIdProgresivo(),tit,aut,edit,anio,isbn,fl,exis));
                        incrementarId(); }
                
             } 
    }

    public int getIdProgresivo() {
        return idprogresivo;
    }
    private void incrementarId(){
      this.idprogresivo++;
    }
    private void decrementarId(){
        if(this.idprogresivo > 0 )
        this.idprogresivo--;
    }

    @Override
    public int verificarNumero(int max, int min) {
   
     boolean verif = false;
     int n=0;
        while(!verif){
            try{System.out.print("Ingrese una opcion>> ");
             n = Integer.parseInt(numero.nextLine());
             
             if(n > max || n<min){
             throw new InputMismatchException();} 
             verif = true;
            }catch(InputMismatchException ime){
                System.out.println("El Numero ingresado no es una opcion valida , reintente. ");  
            }catch(Exception e){
                   System.out.println("El Numero ingresado no es una opcion valida , reintente. ");   
            } 
            //numero.nextLine();
        }
   
      return n;
    }
    
    public Libro buscarLibro(HashMap<Integer,Libro> biblioteca){
         
     Libro libro=null ;
     ArrayList<Libro> lista= new ArrayList<>();
     System.out.println(" >>BUSQUEDA POR TITULO");
     System.out.println("INGRESE TITULO> ");    
     String abuscartit = cadena.nextLine();
     abuscartit = abuscartit.toLowerCase();
     
     for(Libro l : biblioteca.values()){
     //for(int i : this.biblioteca.keySet()){
     // Libro l =(Libro)biblioteca.get(i);
      String  titulo = l.getTitulo().toLowerCase();//titulo del libro
     //    System.out.println("titulo: "+titulo);
     //    System.out.println("titulo a buscar:" + abuscartit);
         if (titulo.contains(abuscartit) )
        {  lista.add(l);}
     }
    
     // System.out.println("imprime lo que encontro"+libro.getTitulo());
      return seleccionDeUnLibro(lista);
    }
    
    public Libro buscarAutor(){
     Libro libro=null ;
     ArrayList<Libro> lista= new ArrayList<>();
     
     System.out.println(" >>BUSQUEDA POR AUTOR");
     System.out.println("INGRESE AUTOR> ");    
     String abuscaraut = cadena.next();

     for(int i : this.biblioteca.keySet()){
       Libro l = this.biblioteca.get(i);
       String  autor = l.getAutores().toLowerCase();
        if (autor.equalsIgnoreCase(abuscaraut) )
        {  lista.add(l);}
     }
      return seleccionDeUnLibro(lista);       
    }
    
    public Libro seleccionDeUnLibro(ArrayList<Libro> libros){
        
        if(libros.size()>0){
        System.out.println(">>SELECCIONE UN LIBRO DE LA LISTA (MAS DETALLES)");
        for(int i = 0 ; i < libros.size() ; i ++){
            System.out.println("Nro:"+i+"\n"+libros.get(i).verResumenCorto());             
        }
          return libros.get(verificarNumero(libros.size(),0)); //retorna el libro seleccionado
        }else{
            System.out.println(">> NO SE ENCONTRARON COINCIDENCIAS!");
        }
       
        return null;
    }
    
}

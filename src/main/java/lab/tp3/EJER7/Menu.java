/*
 

*/
package lab.tp3.EJER7;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Menu implements Estados{

    private Tateti tateti = null;
    private Scanner num = new Scanner(System.in);  
    private int[][] tablero = null;
    
    public Menu(int[][] tablero, Tateti tateti) {
   
    this.tateti = tateti;
    this.tablero = tablero;
    inicializar();  //inicializa el tablero
    }
   
     public void inicializar(){
          for(int i = 0 ; i< this.tablero.length ; i++){  //inicializacion del tablero
           Arrays.fill(this.tablero[i], 0);
       }
    }
    
    public void estadoMenu() {
    int opcion = 0;
        while(true){
            System.out.println(">>======= TA - TE - TI =======<<");
            System.out.println("1 >=> MODO JUGADOR vs JUGADOR");
            System.out.println("2 >=> MODO UN JUGADOR vs MAQUINA");
        //    System.out.println("3 >=> REGLAS DEL JUEGO");
            System.out.println("3 >=> SALIR");
            System.out.println(">>=X=0=X=0=X=0=X=0=X=0=X=0=X=<<");
            opcion = verificarIngreso(3,1);
            iniciarSeleccion(opcion);
           
        }    
    }

    public int verificarIngreso(int max, int min) {
    
     boolean verif = false;
     int n=0;
        while(!verif){
            try{
             n = Integer.parseInt(num.nextLine());           
             if(n > max || n<min){
             throw new InputMismatchException();} 
             verif = true;
            }catch(InputMismatchException ime){
                System.out.println("Ingrese una opcion valida: ");  
            }         
        }
      return n;
    }
    
       public void iniciarSeleccion(int opcion) {
        
        switch (opcion){
                
           case 1 ->  this.tateti.setEstado(new DosJugadores(this.tablero,this.tateti));
                     
           case 2 ->  this.tateti.setEstado(new Unjugador(this.tablero,this.tateti));
                     
           case 3 ->  System.exit(0);  
            }
        }
       

    @Override
    public void estadoUnJugador() {
   
    }

    @Override
    public void estadoDosJugadores() {
   
    }

    @Override
    public void estadoGanador() {
   
    
    }

    @Override
    public void estadoEmpate() {
    }

    @Override
    public void setEstadoTateti(Tateti tateti) {
  
        this.tateti = tateti;
    }
    
    
    
}

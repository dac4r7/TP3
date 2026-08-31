/*
 

*/
package lab.tp3.EJER7;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class DosJugadores implements Estados,Graficos{

    private Tateti tateti = null;
    private int[][] tablero = null;
    private String[][] tableromuestra = new String[3][3];
   
    Scanner inum = new Scanner(System.in); 
    
    public DosJugadores(int[][] tablero, Tateti tateti) {
    int i = 0;
    this.tablero = tablero;
    this.tateti = tateti;
    
    for(int j = 0  ; j< 3 ; j++){   //crea un tablero de referencia
          for(int k = 0 ; k < 3 ; k++){  
           tableromuestra[j][k]=Integer.toString(i);
           i++;        
        }
        }
      estadoDosJugadores();         //inicia el juego en modo 1 jugador
    }
       
    @Override
    public void estadoMenu() {
     estadoUnJugador();
    }

    @Override
    public void estadoUnJugador() {
      
    }

    public int verificarIngreso(int max, int min, int jugador,String[][] tableromuestra){
    //verifica que el numero ingresado sea una eleccion valida
     boolean verif = false,posiciontomada= false;
     int n=0;
        while(!verif){
            try{System.out.print(">> Jugador "+jugador+" Ingresa tu opcion >> ");
             n = Integer.parseInt(inum.nextLine());           
              if(n > max || n < min){         //el numero ingresado esta fuera de las opciones a elegir
              throw new InputMismatchException();
              }else{
                for(int j = min  ; j< 3 ; j++){   //
                  for(int k = min ; k < 3 ; k++){  
                   if(tableromuestra[j][k].contains(Integer.toString(n))){//se eligio una posicion del tablero valida
                      tableromuestra[j][k] = " "; 
                      posiciontomada = true;
                   }else if(k == 2 && j == 2 && !posiciontomada){//se eligio una posicion del tablero invalida
                        throw new InputMismatchException();                      
                        }                        
                   }}
              } 
             verif = true;
            }catch(InputMismatchException ime){
                System.out.println("Ingrese una opcion valida: ");  
            }         
        }
      return n;
    }
    
    @Override     //se encarga de ir procesando los datos del juego en modo 2 jugadores
    public void estadoDosJugadores() {
     boolean ganador = false;
     boolean empate = false;
     int turno = 1; 
     int n= 0, cont= 0, estadojuego = -1;
     
     System.out.println("================= MODO JUGADOR VS JUGADOR ======================"); 
     System.out.println(">> Opciones validas aquellas con 0(ceros)");
       System.out.println(">>El numero ingresado corresponde a la posicion,donde quedara tu jugada");
       System.out.println(">> TABLERO DE JUEGO ---- TABLERO DE REFERENCIA\n");
        System.out.println("   | 0 | 0 | 0 |           | 0 | 1 | 2 |");
        System.out.println("   | 0 | 0 | 0 |           | 3 | 4 | 5 |");
        System.out.println("   | 0 | 0 | 0 |           | 6 | 7 | 8 |\n");
       
     while(!ganador || !empate){
        dibujar(this.tablero,verificarIngreso(9,0,turno,this.tableromuestra),turno);
        estadojuego = verificarJuego(this.tablero); //1(j1) o 5(j2) ganadores o 0 empate
        
         if(turno ==1){
            turno = 5;
        }else if(turno == 5){
            turno = 1;
        }
         
        if(estadojuego == 0){
            empate = true ;
             this.tateti.setEstado(new Empate(this.tateti));//juego empatado
          this.tateti.setEstado(new Empate(this.tateti));
        }else if(estadojuego == 1 || estadojuego == 5){
            ganador = true;
          this.tateti.setEstado(new Ganador(estadojuego , this.tateti));
        }              
     } 
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

    @Override   //crea un StringBubber con los tableros y los imprime en pantalla
    public void dibujar(int[][] tablero,int n,int t) {
        StringBuffer sb = new StringBuffer();
        sb.append(" --------------\n");
        for(int j = 0  ; j< 3 ; j++){    
          for(int k = 0 ; k < 3 ; k++){
          if(tableromuestra[j][k].contains(" ") && tablero[j][k] == 0 ){//posicion elegida por el jugador para poner la marca
               tablero[j][k] = t;
           }   
             sb.append("| "+tablero[j][k]+" |");
             if(k == 2){
               sb.append("\t\t| "+tableromuestra[j][k-2]+" | "+tableromuestra[j][k-1]+" | "+tableromuestra[j][k]);  
             }      
              }
          sb.append(" |\n");
        }
        sb.append(" --------------\n");
        System.out.println(sb.toString());
    }
    

    private int verificarJuego(int[][] tablero) {  //devuelve 1 para ganador jugador 1
      int estado=-1,cont=0;                        //devuelve 5  para ganador jugador 2
                                                   //devuelve 0 en caso de empate 
                                                   //devuelve -1 si todavia no termina el juego
     //diagonales se verifican si hay tres aciertos
       if( ( (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] ) || 
          (tablero[2][0] == tablero[1][1] && tablero[1][1] == tablero[0][2] ) ) && tablero[1][1] ==1 ){
        return 1;
        }else if( ( (tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2] ) || 
          (tablero[2][0] == tablero[1][1] && tablero[1][1] == tablero[0][2] ) ) && tablero[1][1] ==5 ){ 
          return 5; 
        }         
                                                   
       //filas y columnas se verifican los aciertos                                           
      for(int i = 0  ; i< 3 ; i++){
          
       if(tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2] ){
        //se evaluan filas
        if(tablero[i][1]==1){  
          return 1;
        }else if(tablero[i][1]==5){ 
          return 5; 
        }        
      }    
       if(tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i] ){
         //se evaluan columnas
         if(tablero[1][i]==1){  
          return 1;
        }else if(tablero[1][i]==5){ 
          return 5; 
        }
      }     
        
       for(int j = 0  ; j< 3 ; j++){
        if(tablero[i][j]==0){
         cont++; }               //se cuentan los 0(ceros) jugadas faltantes                   
       }}
       if(cont == 1 ){       //para determinar si solo falta una jugada
           int filacero=-1;
           int columnacero=-1;
         for(int r = 0  ; r< 3 ; r++){  
          for(int s = 0  ; s< 3 ; s++){ 
              if(tablero[r][s]==0 ){          
               filacero = r;
               columnacero= s;
             }
          }}                           //aqui se determina si la ultima jugada puede ser ganadora o no
         tablero[filacero][columnacero]= 1;
         if( (tablero[filacero][0] +  tablero[filacero][1]+  tablero[filacero][2]==3) ||
              (tablero[0][columnacero] +  tablero[1][columnacero]+  tablero[2][columnacero]==3) ){
            tablero[filacero][columnacero]= 0; //vuelvo a la normalidad la jugada simulada         
         }else{
             tablero[filacero][columnacero]= 0; //vuelvo a la normalidad la jugada simulada 
             return 0;
         }      
              return -1;   // no hubo ganador es empate      
          }           
       return estado;  //por defecto retorna -1(continua el juego)
    }
}

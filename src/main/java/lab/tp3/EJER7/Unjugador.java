/*


*/
package lab.tp3.EJER7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Unjugador implements Estados,Graficos {

    private Tateti tateti = null;
    private int[][] tablero = null;
    private String[][] tableromuestra = new String[3][3];
       
    Scanner inum = new Scanner(System.in);    
    
    public Unjugador(int[][] tablero, Tateti tateti) {
    int i = 0;
    this.tablero = tablero;
    this.tateti = tateti;
    
    for(int j = 0  ; j< 3 ; j++){   //crea un tablero de referencia
          for(int k = 0 ; k < 3 ; k++){  
           tableromuestra[j][k]=Integer.toString(i);
           i++;      
        }
        }
      estadoUnJugador();         //inicia el juego en modo 1 jugador
    }

    @Override
    public void estadoMenu() {
    
    }

    @Override
    public void estadoUnJugador() {
    boolean ganador = false;
     boolean empate = false;
     int turno = 1; 
     int n= 0, cont= 0, estadojuego = -1;
     
     System.out.println("==================JUGADOR VS MAQUINA============================"); 
     System.out.println(">> Opciones validas aquellas con 0(ceros) en el tablero");
       System.out.println(">>Ver tablero de referencia(derecha) con las jugadas validas");
       System.out.println(">> TABLERO DE JUEGO ---- TABLERO DE REFERENCIA\n");
        System.out.println("   | 0 | 0 | 0 |           | 0 | 1 | 2 |");
        System.out.println("   | 0 | 0 | 0 |           | 3 | 4 | 5 |");
        System.out.println("   | 0 | 0 | 0 |           | 6 | 7 | 8 |\n");
       
     while(!ganador || !empate){
        if(turno==1){                  //turno del jugador
        dibujar(this.tablero,verificarIngreso(9,0,turno,this.tableromuestra,this.tablero),turno);
        turno = 5;
        }else if(turno==5){           //turno de la maquina
           dibujar(this.tablero,jugadaDeMaquina(this.tableromuestra,this.tablero),turno);  
           turno = 1;
        }
        estadojuego = verificarJuego(this.tablero); //1(j1) o 5(j2) ganadores o 0 empate      
         
        if(estadojuego == 0){
            empate = true ;
          this.tateti.setEstado(new Empate(this.tateti));//juego empatado
        }else if(estadojuego == 1 || estadojuego == 5){
            ganador = true;
          this.tateti.setEstado(new Ganador(estadojuego , this.tateti));
        }              
     }     
    }
    //Elige un numero al azar entre los disponibles en el tablero
    public int obtenerUnNumero(String[][] tableromuestra,int[][] tablero){
      ArrayList<Integer> listamuestra = new ArrayList<>();
      Random random = new Random();
           
       for(int j = 0  ; j< 3 ; j++){ 
        for(int k = 0 ; k < 3 ; k++){
           if(tablero[j][k]==0  && !tableromuestra[j][k].contains(" "))
          listamuestra.add(Integer.valueOf(tableromuestra[j][k]));
           } }
       
       int numeroaleatorio = random.nextInt(listamuestra.size() );    
        //retorna el numero contenido en la posicion al azar            
       return listamuestra.get(numeroaleatorio);
    }
    
    public int jugadaDeMaquina(String[][] tableromuestra,int[][] tablero){
       
      boolean verif = false,jugadaestrategica= true, jugadarealizada=false;
      int n=0;
  
      while(!verif && !jugadarealizada ){                          
                        
               //la posicion central no esta ocupada  coloca la marca en el centro            
               if(!tableromuestra[1][1].contains(" ") && tablero[1][1]==0){
                    tableromuestra[1][1]=" ";                
                    tablero[1][1]= 5;
                    jugadarealizada =true;
                }else if(jugadaestrategica ){
                    for(int j = 0  ; j< 3 ; j++){//Busca unos adyacentres en una linea del jugador para 
                     for(int k = 0 ; k < 3 ; k++){  //completar y que no forme tateti el jugador
                      if(( j==1 && tablero[j][k]==1 && tablero[j+1][k]==1) &&
                              tableromuestra[j-1][k].contains(" ") && tablero[j-1][k]==0 ){//para fila
                            tablero[j-1][k]=5;
                            tableromuestra[j-1][k]=" "; 
                            k=2;j=2;
                            jugadarealizada = true;
                      }else if(k==1 && tablero[j][k]==1 && tablero[j][k+1]==1 && 
                              tablero[j][k-1]==0 && tableromuestra[j][k-1].contains(" ") ){//para columna
                            tablero[j][k-1]=5;
                            tableromuestra[j][k-1]=" "; 
                             k=2;j=2;
                             jugadarealizada = true;
                      } 
                       }}
                    jugadaestrategica = false;
                }      //tercera opcion entre las jugadas: toma una posicion al azar entre las disponibles 
                 if(!jugadaestrategica && !jugadarealizada){//y la rellena con la jugada de maquina
                       n = obtenerUnNumero(tableromuestra,tablero);              
                      for(int j = 0  ; j< 3 ; j++){   //
                       for(int k = 0 ; k < 3 ; k++){ 
                        if(tablero[j][k]== 0  && tableromuestra[j][k].contains(Integer.toString(n)) ){
                          tablero[j][k]=5;
                          tableromuestra[j][k]= " ";
                          k=2;j=2; 
                         } 
                       }}
                     }                                                                   
             verif = true;                      
        }
      return n; //retorna la posicion elegida en el tablero    
    }
    
    public int verificarIngreso(int max, int min, int jugador,String[][] tableromuestra,int[][] tablero){
    //verifica que el numero ingresado por el jugador sea una eleccion valida
     boolean verif = false,posiciontomada= false;
     int n=0;
        while(!verif){
            try{System.out.print(">> Jugador "+jugador+" Ingresa tu opcion >> ");
             n = Integer.parseInt(inum.nextLine());           
              if(n > max || n < min){         //el numero ingresado esta fuera de las opciones a elegir
              throw new InputMismatchException();
              }else{
                for(int j = min  ; j< 3 ; j++){   //
                  for(int k = min ; k < 3 ; k++){  //se eligio una posicion del tablero valida
                   if(tableromuestra[j][k].contains(Integer.toString(n)) && tablero[j][k] == 0 ){
                      tableromuestra[j][k] = " ";
                      tablero[j][k] = jugador;
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
      return n;  //retorna la posicion elegida en el tablero
    }
    
     @Override   //crea un StringBuffer con los tableros y los imprime en pantalla
    public void dibujar(int[][] tablero,int n,int t) {
        StringBuffer sb = new StringBuffer();
        if(t == 5){
            sb.append(">> Jugada de Maquina\n");
        }
        sb.append(" --------------\n");
        for(int j = 0  ; j< 3 ; j++){    
          for(int k = 0 ; k < 3 ; k++){
          if(tableromuestra[j][k].contains(" ") && tablero[j][k] == 0 ){//posicion elegida por el jugador para poner la marca
               tablero[j][k] = t;
           }   
             sb.append("| "+tablero[j][k]+" |");
             if(k == 2){
               sb.append("\t\t| "+tableromuestra[j][k-2]+" | "+tableromuestra[j][k-1]+" | "+tableromuestra[j][k]);  
             }}
          sb.append(" |\n");
        }
        sb.append(" --------------\n");
        System.out.println(sb.toString());
    }
    

    private int verificarJuego(int[][] tablero) {  //devuelve 1 si el ganador es el jugador 1
      int estado=-1,cont=0;                        //devuelve 5 si el ganador es el jugador 2
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

/*
 EN ESTE PROGRAMA SE HACE USO DEL PATRON STATE PARA LOS ESTADOS DE JUEGO

*/
package lab.tp3.EJER7;

import java.util.Arrays;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Tateti {
    
    private Estados estado = null;
    private int[][] tablero = new int[3][3];
         
    public Tateti(){
       
       setEstado(new Menu(this.tablero,this));               //estado inicial
       menuDeJuego();     //se accede al menu de juego del estado enviado(menu)
    }
    
    public void setEstado(Estados estado){
        this.estado = estado;
        this.estado.setEstadoTateti(this);
    }
    
    public void menuDeJuego(  ){
        this.estado.estadoMenu();     //menu del juego del estado actual
    }
    
    public void modoUnJugador(){
        this.estado.estadoUnJugador();
    }
    
    public void modoDosJugadores(){
        this.estado.estadoDosJugadores();
    }

    public Estados getEstado() {
        return estado;
    }
    
}

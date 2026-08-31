/*
 
*/
package lab.tp3.EJER7;

import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Ganador implements Estados {

    private Tateti tateti = null;
    private int ganador ;
    public Ganador(int jugador, Tateti tateti) {
        this.tateti = tateti;
        this.ganador = jugador;
        estadoGanador();
    }

    @Override
    public void estadoMenu() {
    this.tateti.getEstado().estadoMenu();
    
    }

    @Override
    public void estadoUnJugador() {
  
    }

    @Override
    public void estadoDosJugadores() {
  
    
    }

    @Override
    public void estadoGanador() {
        Scanner tecla = new Scanner(System.in);
        System.out.println("\u001B[31m |<<<<<<<<<<<<<HAY>>UN>>>GANADOR<<<<<<<<<<<<<<<<<<<<<|" );
        System.out.println("|>>>>>>>>>>>>>>>>>> "+getGanador()+"!!!<<<<<<<<<<<<|\u001B[0m" );
        System.out.println("      -- presiona una tecla para continuar --       \n\n");
        tecla.nextLine();
        this.tateti.setEstado(new Menu(new int[3][3],this.tateti));
        estadoMenu();
    }

    @Override
    public void estadoEmpate() {
   
    
    }

    @Override
    public void setEstadoTateti(Tateti tateti) {
  
    }

    public String getGanador() {
        if(this.ganador ==1){
            return "Jugador 1";
        }
        return "Jugador 2";
    }
    
}

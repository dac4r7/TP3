/*
 
*/
package lab.tp3.EJER7;

import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Empate implements Estados {

    private Tateti tateti = null;
    
    public Empate(Tateti tateti) {
        this.tateti = tateti;
        estadoEmpate();
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
   
    }

    @Override
    public void estadoEmpate() {
      Scanner tecla = new Scanner(System.in);
        System.out.println("|>>>>>>>>>>>>>>>>>>>>>HUBO UN EMPATE<<<<<<<<<<<<<<<<<<<<<|" );
        System.out.println(" |>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<|" );
        System.out.println("      -- presiona una tecla para volver al menu --       \n\n");
        tecla.nextLine();
        this.tateti.setEstado(new Menu(new int[3][3],this.tateti));
        estadoMenu();
    }

    @Override
    public void setEstadoTateti(Tateti tateti) {
   
    }
    
}

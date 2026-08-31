/*


*/
package lab.tp3.EJER7;

/**
 *
 * @author Diego Adrian Cesarin
 */
public interface Estados {
    void estadoMenu();
    void estadoUnJugador();
    void estadoDosJugadores();
    void estadoGanador();
    void estadoEmpate();
    
    void setEstadoTateti(Tateti tateti);
    
}

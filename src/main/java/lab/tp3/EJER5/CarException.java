/*
 */
package lab.tp3.EJER5;

/**
 *
 * @author Diego Adrian Cesarin
 */
class CarException extends RuntimeException{

    public CarException() {
        super();
    }

    public CarException(String mensaje) {
        super(mensaje);
    }

    public CarException(String mensaje, Throwable cause) {
        super(mensaje, cause);
    }
}

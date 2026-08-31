/*
Ejercicio 4

Se desea diseñar un programa que permita manipular una agenda mediante serialización-deserialización.
Cada registro de la agenda cuenta con nombre, apellido, teléfono de linea, teléfono móvil, teléfono 
del trabajo, email y un campo que indique de donde conozco a la persona (trabajo, universidad, boliche…).
Deberá contar con un menú para registrar, eliminar, buscar un registro y para mostrar todos.

*/
package lab.tp3.EJER4;

import java.io.IOException;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Test {
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        
        Menu m = new Menu();
        m.menuDeOpciones();
        
    }  
}

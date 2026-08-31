/*
 Ejercicio 5

Se desea diseñar un programa para registrar y persistir en archivos la información de las reservas
de una empresa dedicada al alquiler de automóviles, teniendo en cuenta que:

De cada cliente se desean almacenar su DNI, nombre y apellido, dirección y teléfono.
Además dos clientes se diferencian por un código único.

Un determinado cliente puede tener en un momento dado hechas varias reservas.

Una reserva la realiza un único cliente pero puede involucrar varios autos.

Es importante registrar la fecha de inicio y final de la reserva, el precio del alquiler
de cada uno de los autos, el precio total de la reserva y un indicador de si el auto o 
los autos han sido entregados.

De cada auto se requiere la matricula, la marca, el modelo, el color y las plazas 
(cantidad de personas que puede transportar).*/
package lab.tp3.EJER5;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Test {
    
    public static void main(String[] args) throws ClassNotFoundException, IOException {
             
        MenuDAO menu = new MenuDAO();
        menu.menuDeOpciones();        
        
    }
    
}

/*
Ejercicio 1

Usando ArayList escriba un programa que permita hacer altas, bajas,

búsqueda y recorridos  en una lista de enteros

en una lista de objetos Alumno (cree la clase)


*/
package lab.tp3.EJER1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Test {
    
    
    public static void main(String[] args) {
          
    ArrayList<Integer> listadeenteros = new ArrayList<>();
    ArrayList<Alumno> listadealumnos = new ArrayList<>();          
    ManejoNumeros manejonumeros = new ManejoNumeros(listadeenteros);//clase con opciones para los numeros
    ManejoAlumnos manejoalumnos = new ManejoAlumnos(listadealumnos);//clase con opciones para los alumnos   
    Menu menu = new Menu(manejonumeros, manejoalumnos);
    
    menu.opcionesMenu();
    }
     
   
    
}

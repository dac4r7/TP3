/*
Ejercicio 3

Escribir un programa que ingrese un string y la ruta correspondiente a un archivo de texto y
emita un mensaje indicando si dicho string está o no en el archivo. Por ejemplo en el archivo
se encuentra el texto “Hoy es viernes y esta soleado”,el usuario ingresa el texto “viernes” y 
la respuesta del programa debe ser que se encuentra el texto en el archivo.
*/
package lab.tp3.EJER3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Test {
    
    public static void main(String[] args) {
    ManejoArchivos ma = new ManejoArchivos();
    ma.buscarEnTxt();
        
  
  }  
}

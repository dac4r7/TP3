/*
 

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
public class ManejoArchivos {

    Scanner cadena = new Scanner(System.in);
    String ingreso = null;
    String lectura = null;
    int cont = 1;
    boolean encontre = false;
    String archivo = null;
    String ruta = null;
    File f = null;

    public ManejoArchivos() {
        archivo = "src/main/resources/tp3ejer3.txt";

    }

    public void buscarEnTxt() {

        if (!validarEleccion()) {  //solo si no habre la ruta por defecto
            System.out.println("INGRESE LA RUTA DEL ARCHIVO(Ejemplo: src/archivo.txt ");
            ruta = cadena.nextLine();
            f = new File(ruta);

        } else {
            f = new File(archivo); //ruta por defecto del archivo
        }

        System.out.println("INGRESE LA CADENA DE TEXTO A BUSCAR");
        ingreso = cadena.nextLine();
        ingreso = ingreso.toLowerCase();

        if (!f.exists()) { //ESCRITURA(Ruta por defecto crea unas lineas de texto y las guarda en un archivo de texto)    
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write("Hoy es viernes \n y esta soleado \n");
                bw.write("Mañana es sabado \n se pondra nublado\n");
                bw.write("Pasado Domingo \n estara fresco\n");
                bw.write("Hoy es Lunes \n y salio el sol \n");
                bw.close();
            } catch (FileNotFoundException fne) {
                System.out.println("Error al abrir el archivo");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo");
            }
        }

        //LECTURA
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            while ((lectura = br.readLine()) != null) {//lectura del archivo por linea
                System.out.println("Lectura : " + lectura);
                lectura = lectura.toLowerCase();
                if (lectura.contains(ingreso)) {  //detecta la palabra como una porcion de otra o por si sola
                    System.out.println("SE ENCONTRO LA PALABRA EN EL ARCHIVO EN LA LINEA :" + cont); //entrega la linea
                    encontre = true;
                }
                cont++;
            }
            if (!encontre) { //llegue al final del archivo no encontre la palabra
                System.out.println("NO SE ENCONTRO LA PALABRA");
            }
            br.close();
        } catch (FileNotFoundException fne) {
            System.out.println("Error al abrir el archivo");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    static boolean validarEleccion() {
        Scanner cadena = new Scanner(System.in);
        String ingreso = null;
        String rtasi = "s", rtano = "n";
        boolean validar = false;
        while (!validar) {
            try {
                System.out.println("DESEA ABRIR LA RUTA DEL ARCHIVO POR DEFECTO(src/main/resources/) ? S(si)-N(no)");
                ingreso = cadena.nextLine();
                if (rtano.equalsIgnoreCase(ingreso) || rtasi.equalsIgnoreCase(ingreso)) {
                    validar = true; //valida una respuesta entre si o no sale del while        
                }
            } catch (Exception e) {
                System.out.println("Ingrese una opcion valida ! S(Si) - N(no)");
            }
        }
        if (rtano.equalsIgnoreCase(ingreso)) {
            return false;  //no abrira la ruta por defecto
        }
        return validar;  //abrira la ruta por defecto
    }

}

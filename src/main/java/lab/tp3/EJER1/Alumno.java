/*

Clase : Alumno

*/
package lab.tp3.EJER1;

import java.util.ArrayList;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Alumno {
    
    private String nombre;
    private int dni;
    ArrayList<Integer> notas = null;

    public Alumno(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
        this.notas = new ArrayList<>();
    }
       
    public void agregarNota(int nota){
        this.notas.add(nota);
    }
    
    public ArrayList<Integer> obtenerNotas(){
        return this.notas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }
    
}

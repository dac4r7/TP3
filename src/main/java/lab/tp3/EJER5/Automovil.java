/*
CLASE AUTOMOVIL
De cada auto se requiere 
la matricula, 
la marca,
el modelo,
el color 
las plazas 
(cantidad de personas que puede transportar).

 */
package lab.tp3.EJER5;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Automovil implements Serializable {

    //DATOS DEL AUTOMOVIL                    
    private final int id;
    private String matricula = null;
    private String marca = null;
    private String modelo = null;
    private String color = null;
    private int plazas;
    private double precioAlquiler;
    private boolean enreserva;

    public Automovil(int id, String matricula, String marca, String modelo, String color, int plazas, double precioAlquiler) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.plazas = plazas;
        this.precioAlquiler = precioAlquiler;
    }

    @Override
    public String toString() {
        return "  --VEHICULO------" + "Marca : " + this.marca + "--Modelo : " + this.modelo + "--Matricula : " + this.matricula
                + "\n--Color= " + this.color + "--Plazas=" + this.plazas + " Precio de Alquiler= $" + this.precioAlquiler;
    }

    public String toStringResumido() {
        return " VEHICULO--" + "Marca : " + this.marca + "--Modelo : " + this.modelo + "--Color= " + this.color + "-Precio= $" + this.precioAlquiler;
    }

    public double getPrecioAlquiler() {
        return precioAlquiler;
    }

    public void setEnreserva(boolean enreserva) {
        this.enreserva = enreserva;
    }

    public String verID() {
        return "Id:" + this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Automovil)) {
            return false;
        }
        Automovil a = (Automovil) o;
        return id == a.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}

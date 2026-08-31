/*
Cada registro de la agenda cuenta con nombre, apellido, teléfono de linea, teléfono móvil, teléfono 
del trabajo, email y un campo que indique de donde conozco a la persona (trabajo, universidad, boliche…)
 */
package lab.tp3.EJER4;

import java.io.BufferedWriter;
import java.io.Serializable;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Registro implements Serializable {
   private int numeroderegistro;
   private String nombre =null;
   private String Apellido = null;
   private String telefonoLinea = null;
   private String telefonoMovil = null;
   private String telefonoTrabajo = null;
   private String email = null; 
   private String lugarDeContacto = null;

    public Registro(int numeroderegistro,String nombre, String Apellido, String telefonoLinea, String telefonoMovil, String telefonoTrabajo, String email, String lugarDeContacto) {
        this.numeroderegistro = numeroderegistro;
        this.nombre = nombre;
        this.Apellido = Apellido;
        this.telefonoLinea = telefonoLinea;
        this.telefonoMovil = telefonoMovil;
        this.telefonoTrabajo = telefonoTrabajo;
        this.email = email;
        this.lugarDeContacto = lugarDeContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getTelefonoLinea() {
        return telefonoLinea;
    }

    public void setTelefonoLinea(String telefonoLinea) {
        this.telefonoLinea = telefonoLinea;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getTelefonoTrabajo() {
        return telefonoTrabajo;
    }

    public void setTelefonoTrabajo(String telefonoTrabajo) {
        this.telefonoTrabajo = telefonoTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLugarDeContacto() {
        return lugarDeContacto;
    }

    public void setLugarDeContacto(String lugarDeContacto) {
        this.lugarDeContacto = lugarDeContacto;
    }

    public int getNumeroderegistro() {
        return numeroderegistro;
    }
    
          
   @Override
    public String toString(){
        StringBuffer bf = new StringBuffer();
        bf.append("============================================\n");
        bf.append("REGISTRO "+this.numeroderegistro);
        bf.append("\nNOMBRE:" + this.nombre);
        bf.append("\nAPELLIDO: "+ this.Apellido);
        bf.append("\nTEL. DE LINEA : "+this.telefonoLinea);
        bf.append("\nTEL. DE MOVIL :"+ this.telefonoMovil);
        bf.append("\nTEL. DE TRABAJO :" + this.telefonoTrabajo);
        bf.append("\nEMAIL :" + this.email);
        bf.append("\nLUGAR DE CONTACTO:" + this.lugarDeContacto);
      return bf.toString();
    }
}

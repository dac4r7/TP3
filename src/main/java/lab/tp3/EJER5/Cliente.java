/*
CLASE CLIENTE 

De cada cliente se desean almacenar su
DNI,
nombre 
apellido,
dirección 
teléfono.
Además dos clientes se diferencian por un código único.

*/
package lab.tp3.EJER5;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class Cliente implements Serializable {

 //DATOS DEL CLIENTE
   private final int dni;
   private final int id;
   private String nombre = null;
   private String apellido = null;
   private String direccion = null;
   private String telefono = null;
   LinkedHashSet<Reservas> reservas = null;  //las reservas del cliente

    public Cliente(int DNI, int id, String nombre, String apellido, String direccion, String telefono) {
        this.dni = DNI;
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
         
    }
    public void agregarReservas(Reservas reserva){
        if(this.reservas == null){
            reservas = new LinkedHashSet<Reservas>();
        }
            this.reservas.add(reserva);
            System.out.println("<|RESERVA AGREGADA|>");
    }
    /* public void eliminarReserva(int n){
       if(this.reservas == null || n> this.reservas.size()){
            System.out.println("== NO HAY DATOS QUE ELIMINAR O NO EXISTE LA RESERVA ==");         
        }else if(n<= this.reservas.size() && n>-1){
            reservas.remove(n);
        }                               
    }*/ 
   
       @Override
    public String toString() {
        return  "--Datos del Cliente------>> id cliente = " + this.id+ "---DNI =" + this.dni +"\n" +
                "--Nombre y Apellido = " + this.nombre +"--"+ this.apellido +"\n"+ 
                "--Direccion=" + this.direccion + "---Telefono=" + this.telefono+
                "\n" +"---------------------------------------------------";
    }

    public int getDNI() {
        return this.dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    

    public LinkedHashSet<Reservas> getReservas() {
        if(this.reservas == null){
            System.out.println("--NO-ENCONTRARON-RESERVAS--");
            return new LinkedHashSet<Reservas>();
        }
        return this.reservas;
    }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cliente)) return false;
    Cliente p = (Cliente) o;
    return dni == p.dni;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.dni);
  }

    public int getId() {
        return id;
    }
  
    
}

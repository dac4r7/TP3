/*
 
CLASE RESERVA

*/
package lab.tp3.EJER5;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 *
 * @author Diego Adrian Cesarin
 */
public class Reservas implements Serializable{
  private LinkedHashSet<Automovil> listadeAutos = null; //la lista de autos de la reserva
  private Cliente cliente = null;                   //el cliente se asocia con la reserva
  private LocalDate fechainicioReserva = null;
  private LocalDate  fechafinReserva = null; 
  private boolean estadoDeEntrega = false ;//false no se entrego , true ha sido entregada la reserva
  private double totalReserva;      //monto total de la reserva (dias x preciodealquiler)

    public Reservas(LinkedHashSet<Automovil> listadeAutos, Cliente cliente, LocalDate fechainicioReserva, LocalDate fechafinReserva, double totalReserva) {
        this.listadeAutos = listadeAutos;
        this.cliente = cliente;
        this.fechainicioReserva = fechainicioReserva;
        this.fechafinReserva = fechafinReserva;
        this.totalReserva = totalReserva;
    }
    
  //  private void crearReserva(){          
  //  }
      
    private String listarVehiculos(){
        StringBuffer sb = new StringBuffer();
        for(Automovil a  : listadeAutos){
            sb.append(a.toString());
        }
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return "==== RESERVA ===== Cliente : " + this.cliente.toString() +" ====================\n" +
                "Inicio de Reserva=" + fechainicioReserva +" Fin de Reserva= " + fechafinReserva +"\n"+
                "Vehiculos Reservados : "+ this.listarVehiculos()+
                "Estado De Entrega = " + verEstadoEntrega() +"\n"+
                "Total de la Reserva = " + this.totalReserva ;
    }
  
    public String toStringResumido() {
        return  "Cliente : " + this.cliente.getNombre()+" "+this.cliente.getApellido()+" DNI: "+this.cliente.getDNI() +
                "\n" +"Inicio de Reserva=" + fechainicioReserva +" Fin de Reserva= " + fechafinReserva +"\n"+
                "Vehiculos Reservados : "+ this.listarVehiculos()+
                "Estado De Entrega = " + verEstadoEntrega() +"\n"+
                "Total de la Reserva = " + this.totalReserva ;
    }
    
    private String verEstadoEntrega(){
        if(this.estadoDeEntrega){
            return "VEHICULOS ENTREGADOS";
        }
            return "ENTREGA PENDIENTE";    
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    
    /*
    private String calcularTotalReserva(){ 
        int total = 0;
        for(Automovil a : this.listadeAutos){
        total += a.getPrecioAlquiler();
        }
    return Double.toString(total);
    }  
    */

    public Set<Automovil> getListadeAutos() {
        return listadeAutos;
    }

    
}


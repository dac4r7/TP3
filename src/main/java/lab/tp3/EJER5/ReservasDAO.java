/*
CLASE RESERVAS DAO SE ENCARGA DE Gestionar las Reservas
INICIAR RESERVA--> solo si hay un cliente
ELIMINAR RESERVA--> solo si existe una reserva
LISTAR RESERVAS--> solo si existen reservas
 */
package lab.tp3.EJER5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class ReservasDAO implements LeerTeclado, ValidarRespuesta, EvaluarDato {

    Scanner dato = new Scanner(System.in);
    //los clientes contienen todas sus reservas, la reserva es creada pasandole el cliente
    private LinkedHashSet<Cliente> clientes = null;
    private StockAutomoviles stockautos = null;//lista de automoviles para alquiler
    private Cliente clienteActual = null;
    private ArchivoReservasDAO archivoreservas = null;

    public ReservasDAO(LinkedHashSet<Cliente> clientes, StockAutomoviles stockautos, ArchivoReservasDAO archivoreservas) {
        this.clientes = clientes;     //lista de todos los clientes
        this.stockautos = stockautos; //lista de autos
        this.archivoreservas = archivoreservas;
    }

    public void iniciarReserva(Cliente cliente) {
        LinkedHashSet<Automovil> la = new LinkedHashSet<>();  //lista de autos del cliente creada
        Automovil a;
        double pr = 0;                                        //el total de la reserva
        int dias = 1, diaMax = 1;
        boolean eleccion = false, findeseleccion = false;
        this.clienteActual = cliente;                         //recibe el cliente que inicia la reserva 

        System.out.println("--------SELECCIONE-UN-VEHICULO-DE-LA-LISTA---------");
        System.out.println(stockautos.toString()); //Se muestra la lista de autos para alquiler

        //verifica que la eleccion del id de auto este dentro del conjunto que existe dentro de stockautomoviles
        while (!eleccion && !findeseleccion) {
            System.out.println("Seleccion>> ");
            try {
                a = stockautos.seleccionarAuto(dato.nextInt());   //selecciona el auto y lo retira del stock         
                if (a == null) {                                     //a sera nulo cuando no se encuentre el id del auto en el stock
                    throw new CarException();
                }
                la.add(a);                                          //se agrega el auto elegido a la lista del cliente
                System.out.println("---INDIQUE DIAS DEL ALQUILER DEL VEHICULO---");
                dias = evaluarDato();                                //se piden dias de alquiler para calcular el total de la reserva
                pr += dias * a.getPrecioAlquiler();                    //subtotal del precio del alquiler depende de la cantidad de autos y dias      
                if (dias > diaMax) {
                    diaMax = dias;
                }                    //se guarda el numero de dias mayor para el final de la reserva
                eleccion = true;
            } catch (CarException e) {
                System.out.println("Debe seleccionar un ID de vehiculo dentro del rango");
            } catch (Exception e) {
                System.out.println("¡La ID del vehiculo no existe! , seleccione nuevamente");
            }
            System.out.println("-----DESEA-ALQUILAR-OTRO-VEHICULO?(s/n)-----");
            if (!validarRespuesta()) {
                findeseleccion = true;
            }
        }
        LocalDate fechaactual = LocalDate.now();                     //inicio de la reserva
        LocalDate fechafindereserva = fechaactual.plusDays(diaMax);  //fin de la reserva 
        //se crea la reserva
        this.clienteActual.agregarReservas(new Reservas(la, this.clienteActual, fechaactual, fechafindereserva, pr));

    }

    //lista reservas actuales y las que estan en el archivo 
    public boolean listarReservas(LinkedHashSet<Reservas> reservas) throws ClassNotFoundException, IOException {
        if (reservas == null) {
            System.out.println("---NO-HAY-RESERVAS---");
            return false;
        }
        //System.out.println("=====RESERVAS==ACTUALES=====");  
        for (Reservas r : reservas) {
            System.out.println(r.toStringResumido());
        }

        return true;
    }

    /*  
    public void eliminarReservas(Cliente cliente) throws ClassNotFoundException, IOException{
       int d;
       ArrayList<Reservas> res = new ArrayList<>(); //guardara :reservas del archivo + reservas actuales 
       ArrayList<Reservas> rc = null; //reservas del cliente actual 
       ArrayList<Cliente> cli = null; //reservas del archivo 
       
       cli = archivoreservas.leerDatos();       
       if( cli != null ){           
        for(Cliente c : cli){               //reservas del archivo
            for(Reservas ra : c.getReservas()){
                res.add(ra);
            }
        }
        rc = cliente.getReservas();
        for(Reservas r : rc ){
            res.add(r);                     //+ resservas actuales
        }
       }       
      System.out.println("====== LISTA DE RESERVAS =============================");
        if( !listarReservas( res ) )//lista todas las reservas y verifica que no sea nulo
         { System.out.println("---Vacia---");
            return; }       
       
        System.out.println("INGRESE EL NUMERO ID DE LA RESERVA A ELIMINAR");
        try{ d = evaluarDato() ;        
            if(d > res.size()){
               throw new IndexOutOfBoundsException(); 
            }
            res.remove(d);                              //se remueve la Reserva
            cli =null ;                                  //reciclamos cli
            cli = new ArrayList<Cliente>();            //se recrea 
            for(Reservas r : res){
               cli.add(r.getCliente());              //se agregan todos los clientes 
            }
            this.archivoreservas.guardarDatos(cli);  //se guardan los datos al archivo
           }catch(InputMismatchException ime){
            System.out.println("¡DEBE INGRESAR UN DATO VALIDO!");
           }catch(IndexOutOfBoundsException ioe){
              System.out.println("--NO--EXISTE--EL--ID--INTRODUCIDO--"); 
           }
      }
     */
    @Override
    public String leerTeclado() {                          //lee datos por teclado y devuelve la cadena
        String dato = "";
        try {                                              //flujo de datos de entrada sincronizedo
            InputStreamReader inputsr = new InputStreamReader(System.in);
            BufferedReader flujoDE = new BufferedReader(inputsr);
            dato = flujoDE.readLine();
        } catch (IOException ioe) {
            System.out.println("--ERROR : " + ioe.getMessage());
        }
        return dato;
    }

    @Override
    public boolean validarRespuesta() {                    //Evalua si una respuesta es S o N
        boolean verificar = false, respuesta = false;
        String d;
        while (!verificar) {
            try {
                d = dato.nextLine();
                if (d.equalsIgnoreCase("s")) {
                    respuesta = true;
                    verificar = true;
                } else if (d.equalsIgnoreCase("n")) {
                    respuesta = false;
                    verificar = true;
                } else {
                    throw new InputMismatchException("Solo puede ingresar s(para SI) o n(para NO)");
                }
            } catch (InputMismatchException ime) {
                System.out.println(ime.getMessage());
            }
        }
        return respuesta;
    }

    @Override
    public int evaluarDato() {                               //evalua que un numero ingresado sea mayor a cero y que sea un numero
        boolean e = false;
        int d = 1;
        while (!e) {
            try {
                d = dato.nextInt();
                if (d < 1) {
                    throw new InputMismatchException();
                }
                e = true;
            } catch (InputMismatchException ime) {
                System.out.println("¡El numero de Dias no puede ser Negativo o Cero! ");
                System.out.println("Ingrese numero de dias : ");
            }
        }
        return d;
    }

}

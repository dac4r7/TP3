/*
 SE ENCARGA DE GENERAR UN MENU 
Y LLAMAR A LAS DEMAS CLASES 

 */
package lab.tp3.EJER5;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class MenuDAO implements VerificarEleccion {

    Scanner dato = new Scanner(System.in);
    private ReservasDAO reservasDAO = null;                 //Gestiona las reservas de un cliente
    private GestionDeClientes clientesDAO = null;           //Gestiona los clientes
    private ArchivoReservasDAO archivoreservasDAO = null;   //recupera y guarda los datos de un cliente en archivo
    private LinkedHashSet<Cliente> clientes = null;         //Clientes registrados
    private Cliente clienteActual = null;                   //el cliente actual por el que se actuan las opciones 2,3,y4
    private StockAutomoviles stockautos = null;             //DB con la lista de autos
    private LinkedHashSet<Reservas> reservas = null;        //se almacena lo que se lea del serializable

    public MenuDAO() throws ClassNotFoundException, IOException {
        this.reservas = new LinkedHashSet<>();
        this.clientes = new LinkedHashSet<>();                        //lista de clientes del archivo
        this.stockautos = new StockAutomoviles();                     //se crea la lista de autos para alquiler
        archivoreservasDAO = new ArchivoReservasDAO(this.stockautos); //clase que guarda/recupera los datos del archivo
        reservasDAO = new ReservasDAO(clientes, this.stockautos, archivoreservasDAO);  //clase que gestiona las reservas
        clientesDAO = new GestionDeClientes(clientes);                //clase que gestiona los clientes

    }

    public void menuDeOpciones() throws ClassNotFoundException, IOException {

        int opcion = 0;
        do {
            System.out.println(">======= GESTION RESERVAS AUTOMOVILES ======<");
            System.out.println("0 >=> LISTAR CLIENTES");
            System.out.println("1 >=> CREAR/SELECCIONAR CLIENTE");
            System.out.println("2 >=> INICIAR RESERVA");
            //   System.out.println("3 >=> ELIMINAR RESERVA");
            System.out.println("4 >=> LISTAR RESERVAS");
            System.out.println("5 >=> LEER RESERVAS DE ARCHIVO");
            System.out.println("6 >=> GUARDAR RESERVA EN ARCHIVO");
            System.out.println("7 >=> VER CLIENTE ACTUAL");
            System.out.println("8 >=> SALIR");
            System.out.println(">==========================================<");
            opcion = verificarEleccion();
            opcionDelMenu(opcion);
        } while (opcion != 8);

    }

    private void opcionDelMenu(int opcion) throws ClassNotFoundException, IOException {
        // tomar lo que esta en el archivo crear un nuevo archivo con lo del archivo mas lo
        //que esta listado y guardo todo en el archivo
        switch (opcion) {
            case 0 ->
                clientesDAO.listarClientes();
            case 1 -> {
                if (this.clienteActual == null) {
                    this.clienteActual = clientesDAO.crearCliente();//no hay cliente lo crea
                } else {
                    System.out.println("--CLIENTE--NUEVO(S/s)--O--SELECCIONAR--CLIENTE(N/n)--");
                    if (this.reservasDAO.validarRespuesta()) {
                        this.clienteActual = clientesDAO.crearCliente();        //crea un cliente
                    } else {
                        Cliente c = clientesDAO.seleccionarCliente();    //selecciona un cliente
                        if (c != null) {
                            this.clienteActual = c;
                        }
                    }
                }
            }

            case 2 -> {//inicia la reserva con el cliente actual(el seleccionado que se puede ver con la opcion7)
                if (this.clienteActual == null) {
                    System.out.println("--NO-HAY-UN CLIENTE--");
                } else {
                    this.reservasDAO.iniciarReserva(this.clienteActual);
                }
            }

            //     case 3 ->{if(this.clienteActual==null){System.out.println("--NO-HAY-UN CLIENTE--");
            //               }else{this.reservasDAO.eliminarReservas(this.clienteActual);}}//elimina las reservas del cliente actual
            case 4 -> {
                if (this.clienteActual == null) {//lista las reservas del cliente actual
                    System.out.println("--NO-HAY-UN CLIENTE--");
                } else {
                    System.out.println("=====RESERVAS SIN GUARDAR======");
                    this.reservasDAO.listarReservas(this.clienteActual.getReservas());
                }
                if ((this.clientes = this.archivoreservasDAO.leerDatos()) != null) {
                    System.out.println("=====RESERVAS EN ARCHIVO=====");
                    for (Cliente c : this.clientes) //las reservas del archivo
                    {
                        this.reservasDAO.listarReservas(c.getReservas());
                    }
                }
            }

            case 5 -> {//se leen del archivo los clientes
                LinkedHashSet<Cliente> c = this.archivoreservasDAO.leerDatos();
                if (c == null) {
                    System.out.println("--NO-HAY-RESERVAS-GUARDARDADAS--");//si no hay tampoco hay reservas
                } else {
                    System.out.println(">--RESERVAS--LEIDAS--<");
                    this.clientes = c;
                    for (Cliente cli : this.clientes) //se leen todas las reservas  
                    {
                        for (Reservas res : cli.getReservas()) {
                            System.out.println(res.toStringResumido());
                        }
                    }
                }
            }
            case 6 -> {//guarda las reservas en un archivo
                if (this.clientes == null) {
                    System.out.println("--NO-HAY-RESERVAS-REGISTRADAS--");
                } else {
                    this.clientes.add(clienteActual);               //se guarda tambien el cliente actual
                    archivoreservasDAO.guardarDatos(this.clientes); //Guarda los datos
                    System.out.println(">>DATOS GUARDADOS<<");
                }
            }

            case 7 -> {//muestra el cliente que esta seleccionado con el que se pueden hacer las reservas
                if (this.clienteActual == null) {
                    System.out.println("--No hay cliente seleccionado--");
                } else {
                    System.out.println(this.clienteActual.toString());
                }
            }
            case 8 ->
                System.out.println(">>>>Saliendo del programa");
            default ->
                System.out.println(">Opcion no Valida<");
        }

    }

    //Verifica que la eleccion este entre 1 y 7
    @Override
    public int verificarEleccion() {
        boolean ingresovalido = false;
        int opcion = 0;
        while (!ingresovalido) {
            try {
                System.out.print("OPCION=> ");
                opcion = dato.nextInt();
                if (opcion < 0 || opcion > 8) {
                    throw new InputMismatchException();
                }
                ingresovalido = true;
            } catch (InputMismatchException e) {
                System.out.println("==Ingrese una opcion Valida(0 al 8)==");
                dato.nextLine();  //limpia buffer
            } catch (Exception e) {
                System.out.println("Hubo un error durante su eleccion");
                dato.nextLine();
            }
        }
        return opcion;
    }

}

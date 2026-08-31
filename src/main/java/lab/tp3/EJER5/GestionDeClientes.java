/*
 CLASE GestionDeClientes 
Se encarga de Gestionar Clientes
 */
package lab.tp3.EJER5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diego
 */
public class GestionDeClientes implements LeerTeclado {

    Scanner dato = new Scanner(System.in);
    LinkedHashSet<Cliente> clientes = null;

    public GestionDeClientes(LinkedHashSet<Cliente> clientes) {
        this.clientes = clientes;

    }

    public Cliente crearCliente() {                 // datos del cliente
        int dni = 0, id;
        String nombre, apellido, direccion, telefono;
        boolean ingreso = false;
        System.out.println("===DATOS DEL CLIENTE===");
        System.out.println("=> DNI =");
        while (!ingreso) {
            try {
                dni = dato.nextInt();
                ingreso = true;
            } catch (InputMismatchException e) {
                System.out.println("ingrese un Numero para el DNI !!");
                dato.nextLine();  //limpia buffer
            }
        }
        System.out.println("=> Nombre =");
        nombre = leerTeclado();
        System.out.println("=> Apellido =");
        apellido = leerTeclado();
        System.out.println("=> Direccion =");
        direccion = leerTeclado();
        System.out.println("=> Telefono =");
        telefono = leerTeclado();

        this.clientes.add(new Cliente(dni, this.clientes.size() + 1, nombre, apellido, direccion, telefono));
        return this.clientes.getLast();
    }

    public Cliente seleccionarCliente() {               //elegida la id devuelve el cliente
        int n = 0;
        boolean ingreso = false;
        System.out.println("==SELECCIONE EL ID(identificador) del CLIENTE==");
        try {
            n = dato.nextInt();
            if (n > clientes.size() || n < 1) {
                throw new IndexOutOfBoundsException();
            }
            ingreso = true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Debe ingresar un numero y el numero de ID del cliente ademas debe existir!!");
            return null;
        } catch (Exception e) {
            System.out.println("--No-se-encontro-el-Cliente--");
            return null;
        }
        List<Cliente> lcl = new ArrayList<>(clientes);
        return lcl.get(n - 1);
    }

    public void listarClientes() {
        if (this.clientes == null || this.clientes.size() == 0) {
            System.out.println("--No hay clientes en la lista--");
            return;
        }
        for (Cliente c : this.clientes) {
            System.out.println(c.toString());
        }
    }

    @Override
    public String leerTeclado() {
        String dato = "";
        try {//flujo de datos de entrada sincronized
            InputStreamReader inputsr = new InputStreamReader(System.in);
            BufferedReader flujoDE = new BufferedReader(inputsr);
            dato = flujoDE.readLine();
        } catch (IOException ioe) {
            System.out.println("--ERROR : " + ioe.getMessage());
        }
        return dato;
    }

}

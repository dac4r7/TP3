/*
SE ENCARGA DE GUARDAR LAS RESERVAS EN UN ARCHIVO SERIALIZADO
SE ENCARGA DE LEER LAS RESERVAS DE UN ARCHIVO SERIALIZADO
 */
package lab.tp3.EJER5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class ArchivoReservasDAO {

    Scanner dato = new Scanner(System.in);

    private StockAutomoviles stockautos = null;
    private ObjectInputStream datosentrada = null;
    private ObjectOutputStream datossalida = null;
    private final String rutaalarchivo = "src/main/resources/reservas.dat";//ruta al archivo

    public ArchivoReservasDAO(StockAutomoviles stockautos) throws ClassNotFoundException, IOException {
        //Áctualiza los autos disponibles para renta los compara con los del archivo
        this.stockautos = stockautos;

        LinkedHashSet<Cliente> cl = leerDatos();
        if (cl != null) {
            LinkedHashSet<Automovil> autosreservados = new LinkedHashSet<>();
            LinkedHashSet<Automovil> autosdisponibles = new LinkedHashSet<>();
            for (Cliente c : cl) {
                for (Reservas r : c.getReservas()) {
                    Iterator<Automovil> it = r.getListadeAutos().iterator();
                    while (it.hasNext()) {
                        autosreservados.add(it.next());
                    }
                }
            }
            // System.out.println(this.stockautos.toString()); 
            autosdisponibles = this.stockautos.getListacompletaautosenrenta();

            autosdisponibles.removeAll(autosreservados);

            this.stockautos.setListadeautos(autosdisponibles);
        }
    }

    //Agrega y guarda los datos contenidos en la reserva actual a los del archivo   
    public void guardarDatos(LinkedHashSet<Cliente> cliente) throws IOException, ClassNotFoundException {

        LinkedHashSet<Cliente> clientesleidos = leerDatos();   //se leen los datos desde el archivo

        if (clientesleidos != null) //se unifican los datos de reservas en un solo Set  
        {
            cliente.addAll(clientesleidos);
        }
        //cliente = clientesleidos;
        try {
            datossalida = new ObjectOutputStream(new FileOutputStream(this.rutaalarchivo));
            datossalida.writeObject(cliente);                      //se guardan todos los datos

            System.out.println(">>DATOS DE RESERVAS GUARDADOS<<");
        } catch (IOException ioe) {
            System.out.println("--ERROR-NO-SE-PUDO-GUARDAR-EL-REGISTRO-DE-RESERVAS--");
        }
        if (datossalida != null) {       //se cierra salida
            datossalida.close();
        }
    }

    // lee los datos conenidos en el archivo  
    public LinkedHashSet<Cliente> leerDatos() throws ClassNotFoundException, IOException {
        LinkedHashSet<Cliente> clientes = null;
        File a = new File(this.rutaalarchivo);         //existe el archivo?
        if (!a.exists() || a.length() == 0) {
            return new LinkedHashSet<Cliente>();      //no existe se devuelve un set vacio
        }
        // this.abrirEntrada();           //se abre la entrada(archivo)  
        try {
            this.datosentrada = new ObjectInputStream(new FileInputStream(this.rutaalarchivo));
            clientes = (LinkedHashSet<Cliente>) datosentrada.readObject();//se leen los datos

            System.out.println("----RESERVAS-LEIDAS----");
        } catch (IOException e) {
            System.out.println("--ERROR EN LA LECTURA DEL ARCHIVO--");
            //  e.printStackTrace();
            return new LinkedHashSet<Cliente>();
        } finally {
            if (this.datosentrada != null) {     //se cierra la entrada abierta
                this.datosentrada.close();
                if (clientes == null) {
                    return new LinkedHashSet<Cliente>();
                }
            }
        }
        return clientes;
    }

    // public void eliminarDatos(int id){                   
    // }
    /*  
   public void abrirEntrada() throws IOException{
    try{ 
        this.datosentrada = new ObjectInputStream( new FileInputStream(this.rutaalarchivo));  
        
    }catch(FileNotFoundException fnfe){
        System.out.println("---NO SE ENCONTRO EL ARCHIVO---");
    }
     }       
   
   public void abrirSalida() throws IOException {
     try{  
    datossalida = new ObjectOutputStream(new FileOutputStream(this.rutaalarchivo));   
        }catch(FileNotFoundException fnfe){
         System.out.println("--NO SE ENCONTRO EL ARCHIVO--");
        }
   }
     */
}

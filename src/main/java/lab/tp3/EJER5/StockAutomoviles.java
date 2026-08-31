/*
Contiene una serie de objetos automoviles precargados
retorna esa lista de automoviles(string) o un automovil de la lista  
 */
package lab.tp3.EJER5;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

/**
 *
 * @author Diego Adrian Cesarin
 */
public class StockAutomoviles {

    LinkedHashSet<Automovil> listacompletaautosenrenta = null; //la lista orginal se mantiene intacta
    LinkedHashSet<Automovil> listadeautos = null;

    public StockAutomoviles() {
        // Automovil(String matricula, String marca, String modelo, String color, int plazas, double precioAlquiler) 

        listacompletaautosenrenta = new LinkedHashSet<>();
        listadeautos = new LinkedHashSet<>();
        listadeautos.add(new Automovil(listadeautos.size() + 1, "HRD029", "Peugeot", "208", "Azul", 4, 70000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "HRD029", "Peugeot", "208", "Azul", 4, 70000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "RDN143", "Honda", "Accord", "Gris", 4, 85000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "RDN143", "Honda", "Accord", "Gris", 4, 85000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "MNT011", "Ford", "Mustang", "Rojo", 4, 120000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "MNT011", "Ford", "Mustang", "Rojo", 4, 120000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "LZT408", "Fiat", "Cronos", "verde", 4, 86000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "LZT408", "Fiat", "Cronos", "verde", 4, 86000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "NDK237", "Chevrolet", "Onix", "Gris plata", 4, 76000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "NDK237", "Chevrolet", "Onix", "Gris plata", 4, 76000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "RZT405", "volkswagen", "virtus", "Azul plata", 4, 96000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "RZT405", "volkswagen", "virtus", "Azul plata", 4, 96000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "FEZ562", "Jeep", "Compass", "Blanco", 5, 106000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "FEZ562", "Jeep", "Compass", "Blanco", 5, 106000));
        listadeautos.add(new Automovil(listadeautos.size() + 1, "GHI116", "volkswagen", "gol", "Rojo", 4, 50000));
        listacompletaautosenrenta.add(new Automovil(listacompletaautosenrenta.size() + 1, "GHI116", "volkswagen", "gol", "Rojo", 4, 50000));
    }

    public Automovil seleccionarAuto(int num) {
        Automovil a;
        List<Automovil> la = new ArrayList<>(listadeautos);
        if (num < la.size() && num > -1) {
            a = la.get(num);
            la.remove(num);        //actualiza la lista de autos disponibles
            LinkedHashSet<Automovil> listaautosactualizada = new LinkedHashSet<>(la);
            this.listadeautos = listaautosactualizada;
            return a;
        }
        return null;
    }

    @Override
    public String toString() {
        int i = 0;
        List<Automovil> la = new ArrayList<>(listadeautos);
        StringBuilder lista = new StringBuilder();
        for (int x = 0; x < la.size(); x++) {
            lista.append("\nid : ").
                    append(x).
                    append(" ").
                    append(la.get(x).toStringResumido());
        }
        return lista.toString();
    }

    public void setListadeautos(LinkedHashSet<Automovil> listadeautos) {
        this.listadeautos = listadeautos;
    }

    public LinkedHashSet<Automovil> getListacompletaautosenrenta() {
        return listacompletaautosenrenta;
    }

    public LinkedHashSet<Automovil> getListadeautos() {
        return listadeautos;
    }

}

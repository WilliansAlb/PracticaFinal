import javax.swing.*;

public abstract class Vehiculos extends Casilla {

    public Vehiculos(){

    }
    abstract ImageIcon getImagen(int i);
    abstract ImageIcon getTheImagen();
    abstract String getNombre();
    abstract void setNombre(String nombre);
}

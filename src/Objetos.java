import javax.swing.*;
import java.awt.*;

public abstract class Objetos extends Casilla {

    public Objetos(){
    }

    abstract void setPrueba(int prueba);
    abstract int getPrueba();
    abstract ImageIcon getTheImagen();
}

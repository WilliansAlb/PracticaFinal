import javax.swing.*;
import java.awt.*;

public abstract class Objetos extends Casilla {

    public Objetos(){
    }
    public Objetos(int x, int y, int alto, int posicionX, int posicionY, CampoDeBatalla campo) {
        super(x, y, alto, posicionX, posicionY, campo);
    }
    abstract void setPrueba(int prueba);
    abstract int getPrueba();
}

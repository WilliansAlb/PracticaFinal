import javax.swing.*;
import java.awt.*;

public class Agua extends Objetos {
    private int prueba=100;


    public Agua() {

    }

    public int getPrueba() {
        return prueba;
    }

    public void setPrueba(int prueba) {
        this.prueba += prueba;
    }
}

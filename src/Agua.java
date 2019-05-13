import javax.swing.*;
import java.awt.*;

/**
 * Clase encargada de lo relacionado con el objeto agua
 */
public class Agua extends Objetos {
    /**
     * HP del agua, obligatorio puesto que volvi el hp en un metodo abstracto de objetos
     */
    private int prueba=0;

    /**
     * Constructor de la clase
     */
    public Agua() {

    }

    /**
     * Retorna el hp del agua
     * @return prueba
     */
    public int getPrueba() {
        return prueba;
    }

    /**
     * Modifica el hp del agua
     * @param prueba modificar el hp
     */
    public void setPrueba(int prueba) {
        this.prueba += prueba;
    }

    /**
     * Retorna la imagen correspondiente al agua
     * @return ImageIcon
     */
    @Override
    ImageIcon getTheImagen() {
        return new ImageIcon("src/fts/aguaredimension.gif");
    }
}

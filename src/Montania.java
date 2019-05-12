import javax.swing.*;

public class Montania extends Objetos {
    private int prueba = 60;

    public Montania() {

    }


    @Override
    void setPrueba(int prueba) {
        this.prueba += prueba;
    }

    @Override
    int getPrueba() {
        return prueba;
    }

    @Override
    ImageIcon getTheImagen() {
        return new ImageIcon("src/fts/mountain3.png");
    }
}

import javax.swing.*;
import java.util.Random;

public class Enemigos extends Vehiculos {
    private int hp, atack, numRec = 100;
    String nombre, tipo;
    private ImageIcon theImagen;

    public Enemigos() {
        super();
    }

    @Override
    ImageIcon getImagen(int i) {
        Random aleatorio = new Random();
        switch (aleatorio.nextInt(3))
        {
            case 0:
            {
                ImageIcon imagen = new ImageIcon("src/fts/enemigo2.gif");
                setTheImagen(imagen);
                return getTheImagen();
            }
            case 1:
            {
                ImageIcon imagen = new ImageIcon("src/fts/enemigo4.gif");
                setTheImagen(imagen);
                return getTheImagen();
            }
            case 2:
            {
                ImageIcon imagen = new ImageIcon("src/fts/enemigo5.gif");
                setTheImagen(imagen);
                return getTheImagen();
            }
            default:
            {
                ImageIcon imagen = new ImageIcon("src/fts/enemigo2.gif");
                setTheImagen(imagen);
                return getTheImagen();
            }
        }
    }

    @Override
    ImageIcon getTheImagen() {
        return theImagen;
    }

    @Override
    String getNombre() {
        return "ENEMIGO GENERICO";
    }

    @Override
    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    boolean isElegido() {
        return false;
    }

    @Override
    void setElegido(boolean elegido) {

    }

    @Override
    int getNumRec() {
        return 100;
    }

    @Override
    void setNumRec(int numRec) {

    }

    @Override
    void setHp(int hp) {

    }

    @Override
    void setAtack(int atack) {

    }

    @Override
    int getHp() {
        return 45;
    }

    @Override
    int getAtack() {
        return 20;
    }

    @Override
    boolean isSelec() {
        return false;
    }

    @Override
    void setSelec(boolean selec) {

    }

    @Override
    String getTipo() {
        return "E";
    }

    public void setTheImagen(ImageIcon theImagen) {
        this.theImagen = theImagen;
    }
}

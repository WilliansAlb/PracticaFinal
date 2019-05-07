import javax.swing.*;

public class Arma {
    private ImageIcon theImagen;
    private String nombre;

    public Arma() {
    }

    ImageIcon getImagen(int i) {
        switch (i){
            case 0:
            {
                agregarImagen("src/fts/arma1.png");
                return getTheImagen();
            }
            case 1:
            {
                agregarImagen("src/fts/arma2.png");
                return getTheImagen();
            }
            case 2:
            {
                agregarImagen("src/fts/arma3.png");
                return getTheImagen();
            }
            case 3:
            {
                agregarImagen("src/fts/arma4.png");
                return getTheImagen();
            }
            case 4:
            {
                agregarImagen("src/fts/arma5.png");
                return getTheImagen();
            }
            case 5:
            {
                agregarImagen("src/fts/arma6.png");
                return getTheImagen();
            }
            default:
                System.out.println("nel");
                return null;
        }
    }
    public void agregarImagen(String imagen){
        setTheImagen(new ImageIcon(imagen));
    }

    public ImageIcon getTheImagen() {
        return theImagen;
    }

    public void setTheImagen(ImageIcon theImagen) {
        this.theImagen = theImagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

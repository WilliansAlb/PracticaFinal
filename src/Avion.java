import javax.swing.*;

public class Avion extends Vehiculos {
    private ImageIcon theImagen;
    private int numRec;
    private boolean elegido;
    private String nombre;

    public Avion(){

    }

    ImageIcon getImagen(int i) {
        switch (i){
            case 0:
            {
                agregarImagen("src/fts/aviongif1.gif");
                return getTheImagen();
            }
            case 1:
            {
                agregarImagen("src/fts/aviongif2.gif");
                return getTheImagen();
            }
            case 2:
            {
                agregarImagen("src/fts/avionpng4.png");
                return getTheImagen();
            }
            case 3:
            {
                agregarImagen("src/fts/avionpng4.png");
                return getTheImagen();
            }
            case 4:
            {
                agregarImagen("src/fts/avionpng4.png");
                return getTheImagen();
            }
            default:
                System.out.println("nel");
                return null;
        }
    }

    String getNombre() {
        return nombre;
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

    void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean isElegido() {
        return elegido;
    }

    @Override
    public void setElegido(boolean elegido) {
        this.elegido = elegido;
    }

    public int getNumRec() {
        return numRec;
    }

    public void setNumRec(int numRec) {
        this.numRec = numRec;
    }
}

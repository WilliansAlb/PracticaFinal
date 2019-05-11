import javax.swing.*;

public class Arma {
    private ImageIcon theImagen;
    private String nombre;
    private boolean elegido, selec;
    private int ataque, precision;

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

    public void setElegido(boolean elegido){
        this.elegido = elegido;
    }

    public boolean isElegido(){
        return elegido;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public boolean isSelec() {
        return selec;
    }

    public void setSelec(boolean selec) {
        this.selec = selec;
    }
}

import javax.swing.*;

/**
 * Clase encargada de guardar las armas
 */
public class Arma {
    /**
     * Guarda la imagen que se usara en el boton
     */
    private ImageIcon theImagen;
    /**
     * Guarda el nombre del arma
     */
    private String nombre;
    /**
     * Sirven para saber si el arma fue elegida o no
     */
    private boolean elegido, selec;
    /**
     * Sirven para saber el ataque y la precision del arma
     */
    private int ataque, precision;

    /**
     * Metodo constructor
     */
    public Arma() {
    }

    /**
     * Retorna la imagen que se le pida
     * @param i para saber que imagen retornara
     * @return ImageIcon
     */
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

    /**
     * Agrega la imagen a la variable
     * @param imagen para agregar la imagen
     */
    public void agregarImagen(String imagen){
        setTheImagen(new ImageIcon(imagen));
    }

    /**
     * Retorna la imagen seleccionada
     * @return theImagen
     */
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

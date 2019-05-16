import javax.swing.*;

public class Tanque extends Vehiculos {
    private ImageIcon theImagen;
    private int numRec, hp, atack, eliminados;
    private boolean elegido, selec;
    private String nombre;
    public Tanque(){

    }
    ImageIcon getImagen(int i) {
        switch (i){
            case 0:
            {
                agregarImagen("src/fts/tanquesuelo1.gif");
                return getTheImagen();
            }
            case 1:
            {
                agregarImagen("src/fts/tanquesuelo2.gif");
                return getTheImagen();
            }
            case 2:
            {
                agregarImagen("src/fts/tanquesuelo3.gif");
                return getTheImagen();
            }
            case 3:
            {
                agregarImagen("src/fts/tanquesuelo4.gif");
                return getTheImagen();
            }
            case 4:
            {
                agregarImagen("src/fts/tanquesuelo5.gif");
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

    public boolean isElegido() {
        return elegido;
    }

    public void setElegido(boolean elegido) {
        this.elegido = elegido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumRec() {
        return numRec;
    }

    public void setNumRec(int numRec) {
        this.numRec = numRec;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp += hp;
    }

    public int getAtack() {
        return atack;
    }

    public void setAtack(int atack) {
        this.atack = atack;
    }

    public boolean isSelec() {
        return selec;
    }

    public void setSelec(boolean selec) {
        this.selec = selec;
    }

    @Override
    String getTipo() {
        return "T";
    }

    public int getEliminados() {
        return eliminados;
    }

    public void setEliminados(int eliminados) {
        this.eliminados += eliminados;
    }
}

import javax.swing.*;

public abstract class Vehiculos extends Casilla {

    public Vehiculos(){

    }
    abstract ImageIcon getImagen(int i);
    abstract ImageIcon getTheImagen();
    abstract String getNombre();
    abstract void setNombre(String nombre);
    abstract boolean isElegido();
    abstract void setElegido(boolean elegido);
    abstract int getNumRec();
    abstract void setNumRec(int numRec);
    abstract void setHp(int hp);
    abstract void setAtack(int atack);
    abstract int getHp();
    abstract int getAtack();
    abstract boolean isSelec();
    abstract void setSelec(boolean selec);
    abstract String getTipo();
    abstract void setEliminados(int eliminados);
    abstract int getEliminados();
}

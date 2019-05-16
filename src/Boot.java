import javax.swing.*;

public class Boot extends Vehiculos {
    int hp=100, atack=15, numRec=4;
    String nombre = "Monito";

    public Boot() {
    }

    @Override
    ImageIcon getImagen(int i) {
        return null;
    }

    @Override
    ImageIcon getTheImagen() {
        return new ImageIcon("src/fts/boot1.gif");
    }

    @Override
    String getNombre() {
        return "Monito";
    }

    @Override
    void setNombre(String nombre) {
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
        return numRec;
    }

    @Override
    void setNumRec(int numRec) {
        this.numRec += numRec;
    }

    @Override
    void setHp(int hp) {
        this.hp += hp;
    }

    @Override
    void setAtack(int atack) {

    }

    @Override
    int getHp() {
        return hp;
    }

    @Override
    int getAtack() {
        return atack;
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
        return "B";
    }

    @Override
    void setEliminados(int eliminados) {
        System.out.println("no hace nada");
    }

    @Override
    int getEliminados() {
        return 0;
    }
}

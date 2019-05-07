import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Casilla extends JButton implements ActionListener {

    private int posicionX;
    private int posicionY;
    private int alto;
    private int x, y;
    private boolean click = true;
    int tieneAlgo = 0;
    int queSera;
    private ImageIcon imagen1;
    CampoDeBatalla campo;
    protected Objetos agua;
    protected Objetos montania;
    protected Vehiculos avion;
    protected Vehiculos tanque;
    protected Vehiculos enemigos;

    public Casilla(int x, int y, int alto, int posicionX, int posicionY, CampoDeBatalla campo){
        agua = null;
        montania = null;
        avion = null;
        tanque = null;
        enemigos = null;
        this.campo = campo;
        setBounds(x,y,alto,alto);
        this.alto = alto;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.x = x;
        this.y = y;
        addActionListener(this::actionPerformed);
        setBorderPainted(false);
        this.queSera = 3;
        //ImageIcon tierra = new ImageIcon("src/fts/tierra.jpg");
        //ImageIcon tierra1 = new ImageIcon(tierra.getImage().getScaledInstance((alto-5),(alto-5),Image.SCALE_REPLICATE));
        //setIcon(tierra1);
        //this.setContentAreaFilled(false);
        //this.setBorderPainted(false);
    }
    public Casilla(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (getQueSera()>2 && campo.isClick()){
            ImageIcon tanque = new ImageIcon("src/fts/tanquesuelo5.gif");
            setIcon(new ImageIcon(tanque.getImage().getScaledInstance(getAlto(),getAlto(),Image.SCALE_REPLICATE)));
            campo.setClick(false);
        }*/
    }

    public int getQueSera() {
        return queSera;
    }

    public void setQueSera(int queSera) {
        this.queSera = queSera;
    }

    public int getAlto() {
        return alto;
    }

    public int getTieneAlgo() {
        return tieneAlgo;
    }

    public void setTieneAlgo(int tieneAlgo) {
        this.tieneAlgo = tieneAlgo;
    }
    public void inicializarAgua(){
        this.agua = new Agua();
        ImageIcon agua1 = new ImageIcon("src/fts/aguaredimension.gif");
        ImageIcon agua = new ImageIcon(agua1.getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));
        setIcon(agua);
        setTieneAlgo(1);
        setQueSera(1);
    }
    public void inicializarTanque(){
        this.tanque = new Tanque();
        ImageIcon tanque1 = new ImageIcon("src/fts/tanquesuelo2.gif");
        ImageIcon tanque = new ImageIcon(tanque1.getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));
        setIcon(tanque);
    }
    public void inicializarAvion(){
        this.avion = new Avion();
        ImageIcon avion1 = new ImageIcon("src/fts/aviongif1.gif");
        ImageIcon avion = new ImageIcon(avion1.getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));
        setIcon(avion);

    }

    public Vehiculos getAvion() {
        return avion;
    }

    public void setAvion(Vehiculos avion) {
        this.avion = avion;
    }

    public void setTanque(Vehiculos tanque) {
        this.tanque = tanque;
    }

    public Objetos getAgua() {
        return agua;
    }
    public Vehiculos getTanque() {
        return tanque;
    }
}

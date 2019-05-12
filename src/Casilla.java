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
    }
    public Casilla(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {
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

    public void inicializarObjetos(int u){
        if (u==1){
            this.agua = new Agua();
            ImageIcon agua1 = new ImageIcon(agua.getTheImagen().getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));
            setIcon(agua1);
            setTieneAlgo(1);
            setQueSera(1);
        } else {
            this.montania = new Montania();
            ImageIcon montania1 = new ImageIcon(montania.getTheImagen().getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));
            setIcon(montania1);
            setTieneAlgo(1);
            setQueSera(2);
        }
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
    public void iniciarEnemigo(){
        this.enemigos = new Enemigos();
        setIcon(new ImageIcon(this.enemigos.getImagen(0).getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE)));
        setTieneAlgo(1);
        setQueSera(3);
    }


    public Vehiculos getVehiculo() {
        if(avion!=null){
            return avion;
        } else {
            return tanque;
        }
    }
    public void setVehiculo(Vehiculos veh){
        avion = null;
        tanque = null;
        if (veh.getTipo().equalsIgnoreCase("A"))
        {
            avion = veh;
        } else
        {
            tanque = veh;
        }

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

    public void eliminarLoQueTenga()
    {
        ImageIcon tierra = new ImageIcon("src/fts/tierra.jpg");
        ImageIcon tierra1 = new ImageIcon(tierra.getImage().getScaledInstance(getAlto()-5,getAlto()-5,Image.SCALE_REPLICATE));

        if (enemigos!=null)
        {
            enemigos = null;
            setIcon(tierra1);
            setTieneAlgo(0);
            setQueSera(0);
        } else {
            montania = null;
            setIcon(tierra1);
            setTieneAlgo(0);
            setQueSera(0);
        }
    }
}

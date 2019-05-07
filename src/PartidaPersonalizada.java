import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.ReplicateScaleFilter;
import java.util.Random;

import static java.awt.Color.yellow;

public class PartidaPersonalizada extends JFrame {

    FondoWWI fondito;
    JLabel bienvenida, vehiculos, armas, sele;
    PanelNuevo informacion, vehiculos1, armas1, selec;
    AgregarVeh agregando, agregandoA;
    Seleccion[] vehSelec;
    BotonesArmas[] armSelec;

    public PartidaPersonalizada(){
        agregando = new AgregarVeh();
        agregandoA = new AgregarVeh();
        for (int i = 0; i<3; i++){
            agregando.insertarNodo(new Tanque(), null,i);
            agregando.buscarNodo(i).getImagen(i);
        }
        for (int u = 3; u<6; u++){
            agregando.insertarNodo(null, new Avion(), u);
            agregando.buscarNodo(u).getImagen(u-3);
        }
        for (int p = 0; p<6; p++)
        {
            agregandoA.insertarNodo(new Arma(), p);
            agregandoA.buscarNodoA(p,"no").getImagen(p);
        }
        String[] nombres = {"METAL SLUG","STRIKER","SERVOARMOUR","FALCON","LOCKHEED MARTIN F-16","HALCON MILENARIO"};
        String[] nombresA = {"MK1 DRIVER","MK2 DRIVER","MK1 GAUSS","MK2 GAUSS","LASER GUN","SHOT GUN"};
        for (int q = 0; q<6; q++)
        {
            agregandoNombres(q,nombres[q]);
            agregandoA.buscarNodoA(q,"no").setNombre(nombresA[q]);
        }
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(4);
        this.getContentPane().add(fondito);
        informacion = new PanelNuevo();
        vehiculos1 = new PanelNuevo();
        armas1 = new PanelNuevo();
        selec = new PanelNuevo();
        iniciarVehiculos();
        iniciarArmas();
        iniciarEtiquetas();

    }
    public void iniciarEtiquetas(){
        bienvenida = new JLabel("Como te llamas?");
        sele = new JLabel("Estas seguro?");
        TitledBorder bordePanelInformacion = new TitledBorder("Antes que nada, configura tu partida, guerrero");
        bordePanelInformacion.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion.setTitleFont(new Font("Arcade Interlaced",Font.PLAIN,8));
        bordePanelInformacion.setTitleColor(yellow);
        informacion.setBorder(bordePanelInformacion);
        bienvenida.setForeground(yellow);
        bienvenida.setFont(new Font("Arcade Interlaced",Font.PLAIN,8));
        informacion.add(bienvenida);
        JTextField nombre = new JTextField(10);
        informacion.add(nombre);
        informacion.setOpaque(false);

        TitledBorder bordePanelInformacion2 = new TitledBorder("Elige tus armas");
        bordePanelInformacion2.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion2.setTitlePosition(TitledBorder.CENTER);
        bordePanelInformacion2.setTitleFont(new Font("Arcade Interlaced",Font.PLAIN,8));
        bordePanelInformacion2.setTitleColor(yellow);
        armas1.setBorder(bordePanelInformacion2);

        TitledBorder bordePanelInformacion1 = new TitledBorder("Elige tus vehiculos");
        bordePanelInformacion1.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion1.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion1.setTitleFont(new Font("Arcade Interlaced",Font.PLAIN,8));
        bordePanelInformacion1.setTitleColor(yellow);
        vehiculos1.setBorder(bordePanelInformacion1);

        selec.add(sele)
        this.getContentPane().add(BorderLayout.NORTH, informacion);
        this.getContentPane().add(BorderLayout.CENTER, vehiculos1);
        this.getContentPane().add(BorderLayout.EAST, armas1);
        this.getContentPane().add(BorderLayout.SOUTH, selec);
    }
    public void iniciarVehiculos(){
        vehSelec = new Seleccion[agregando.getLeght()];
        vehiculos1.setLayout(new GridLayout(2,3));
        for (int i = 0; i<agregando.getLeght(); i++)
        {
            vehSelec[i] = new Seleccion();
            ImageIcon imagen = new ImageIcon(agregando.buscarNodo(i).getTheImagen().getImage().getScaledInstance(80,80, Image.SCALE_REPLICATE));
            vehSelec[i].setIcon(imagen);
            vehSelec[i].setContentAreaFilled(false);
            vehiculos1.add(vehSelec[i]);
        }

    }
    public void iniciarArmas(){
        armSelec = new BotonesArmas[agregandoA.getLeghtA()];
        armas1.setLayout(new GridLayout(3,2));
        for (int u = 0; u<agregandoA.getLeghtA(); u++)
        {
            armSelec[u] = new BotonesArmas();
            ImageIcon imagen2 = new ImageIcon(agregandoA.buscarNodoA(u,"no").getTheImagen().getImage().getScaledInstance(80,80,Image.SCALE_REPLICATE));
            armSelec[u].setIcon(imagen2);
            armSelec[u].setContentAreaFilled(false);
            armas1.add(armSelec[u]);
        }
    }
    public void iniciarBotSec(){


    }
    public void agregandoNombres(int correp, String nombre){
        agregando.buscarNodo(correp).setNombre(nombre);
    }
}

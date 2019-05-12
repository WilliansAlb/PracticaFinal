import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ReplicateScaleFilter;
import java.util.Random;

import static java.awt.Color.yellow;

public class PartidaPersonalizada extends JFrame {

    FondoWWI fondito;
    JLabel bienvenida;
    PanelNuevo informacion, vehiculos1, armas1, selec;
    AgregarVeh agregando, agregandoA;
    Seleccion[] vehSelec;
    BotonesArmas[] armSelec;
    JButton ir, regresar, no;
    JTextField nombre;
    private int seleccionV;
    private int seleccionA;
    private String jugador;
    Font pant = new Font("Arcade Normal",Font.BOLD,8);

    public PartidaPersonalizada(){
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        inicioA();
        iniciarComponentes();
    }
    public void inicioA(){
        agregando = new AgregarVeh();
        agregandoA = new AgregarVeh();

        String[] nombres = {"METAL SLUG","STRIKER","SERVOARMOUR","FALCON","LOCKHEED MARTIN F-16","HALCON MILENARIO"};
        String[] nombresA = {"MK1 DRIVER","MK2 DRIVER","MK1 GAUSS","MK2 GAUSS","LASER GUN","SHOT GUN"};

        int[] ataques = {10,20,15,18,17,12};
        int[] precisiones = {20,10,18,12,17,15};

        int[] ataquesV = {30,24,25,15,35,20};
        int[] hp = {70,80,78,90,65,95};

        for (int i = 0; i<3; i++){
            agregando.insertarNodo(new Tanque(), null,i);
            agregando.buscarNodo(i).getImagen(i);
            agregando.buscarNodo(i).setHp(hp[i]);
            agregando.buscarNodo(i).setAtack(ataquesV[i]);
        }
        for (int u = 3; u<6; u++){
            agregando.insertarNodo(null, new Avion(), u);
            agregando.buscarNodo(u).getImagen(u-3);
            agregando.buscarNodo(u).setHp(hp[u]);
            agregando.buscarNodo(u).setAtack(ataquesV[u]);
        }
        for (int p = 0; p<6; p++)
        {
            agregandoA.insertarNodo(new Arma(), p);
            agregandoA.buscarNodoA(p).getImagen(p);

            agregandoNombres(p,nombres[p]);
            agregandoA.buscarNodoA(p).setNombre(nombresA[p]);

            agregandoA.buscarNodoA(p).setAtaque(ataques[p]);
            agregandoA.buscarNodoA(p).setPrecision(precisiones[p]);

        }
    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(7);
        this.getContentPane().add(fondito);
        informacion = new PanelNuevo(0);
        vehiculos1 = new PanelNuevo(1);
        armas1 = new PanelNuevo(2);
        selec = new PanelNuevo(0);
        iniciarVehiculos();
        iniciarArmas();
        iniciarBotSec();
        iniciarEtiquetas();

    }
    public void iniciarEtiquetas(){
        bienvenida = new JLabel("Como te llamas?");
        TitledBorder bordePanelInformacion = new TitledBorder("Antes que nada, configura tu partida, guerrero");
        bordePanelInformacion.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion.setTitleFont(pant);
        bordePanelInformacion.setTitleColor(yellow);

        TitledBorder bordePanelInformacion3 = new TitledBorder("Listo?");
        bordePanelInformacion3.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion3.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion3.setTitleFont(pant);
        bordePanelInformacion3.setTitleColor(yellow);
        selec.setBorder(bordePanelInformacion3);

        nombre = new JTextField(10);
        nombre.setFont(pant);
        nombre.setOpaque(false);
        nombre.setForeground(yellow);
        informacion.setBorder(bordePanelInformacion);
        bienvenida.setForeground(yellow);
        bienvenida.setFont(pant);
        informacion.add(bienvenida);
        informacion.add(nombre);
        informacion.setOpaque(false);

        TitledBorder bordePanelInformacion2 = new TitledBorder("Elige tus armas iniciales");
        bordePanelInformacion2.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion2.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion2.setTitleFont(pant);
        bordePanelInformacion2.setTitleColor(yellow);
        armas1.setBorder(bordePanelInformacion2);

        TitledBorder bordePanelInformacion1 = new TitledBorder("<html><body>Elige tus vehiculos iniciales, mantente<br>sobre la imagen para ver las caracteristicas</body></html>");
        bordePanelInformacion1.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion1.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion1.setTitleFont(pant);
        bordePanelInformacion1.setTitleColor(yellow);
        vehiculos1.setBorder(bordePanelInformacion1);

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
            vehSelec[i] = new Seleccion(this);
            ImageIcon imagen = new ImageIcon(agregando.buscarNodo(i).getTheImagen().getImage().getScaledInstance(100,100, Image.SCALE_REPLICATE));
            vehSelec[i].setIcon(imagen);
            vehSelec[i].setContentAreaFilled(false);
            vehSelec[i].setNumRec(i);
            String nombre = agregando.buscarNodo(i).getNombre();
            String hp = "HP: "+agregando.buscarNodo(i).getHp();
            String pp = "ATAQUE: "+agregando.buscarNodo(i).getAtack();
            String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
            vehSelec[i].setToolTipText(todo1);
            vehiculos1.add(vehSelec[i]);
        }
    }
    public void iniciarArmas(){
        armSelec = new BotonesArmas[agregandoA.getLeghtA()];
        armas1.setLayout(new GridLayout(3,2));
        for (int u = 0; u<agregandoA.getLeghtA(); u++)
        {
            armSelec[u] = new BotonesArmas(this);
            ImageIcon imagen2 = new ImageIcon(agregandoA.buscarNodoA(u).getTheImagen().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE));
            armSelec[u].setIcon(imagen2);
            armSelec[u].setContentAreaFilled(false);
            armSelec[u].setNumRec(u);
            String nombre = agregandoA.buscarNodoA(u).getNombre();
            String hp = "PRECISIÓN: "+agregandoA.buscarNodoA(u).getPrecision()+"%";
            String pp = "ATAQUE: "+agregandoA.buscarNodoA(u).getAtaque();
            String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
            armSelec[u].setToolTipText(todo1);
            armas1.add(armSelec[u]);
        }
    }
    public void iniciarBotSec(){
        selec.setLayout(new GridLayout(1,3));
        ir = new JButton("CREAR PARTIDA");
        ir.setEnabled(false);
        regresar = new JButton("REGRESO MENU PRINCIPAL");
        ir.setFont(pant);
        regresar.setFont(pant);
        ir.setForeground(yellow);
        regresar.setForeground(yellow);
        ir.setContentAreaFilled(false);
        regresar.setContentAreaFilled(false);
        no = new JButton("NO SE QUE MAS");

        selec.add(ir);
        selec.add(regresar);

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicio temp = new Inicio();
                temp.setVisible(true);
                setVisible(false);
            }
        });
        ir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.getText().length()==0){
                    ImageIcon vault = new ImageIcon("src/fts/desacuerdo.png");
                    JOptionPane.showMessageDialog(null,"No has puesto tu nombre","Error",JOptionPane.INFORMATION_MESSAGE,vault);
                } else {

                    for (int i = 0; i <agregando.getLeght();i++)
                    {
                        if (vehSelec[i].isClick())
                        {
                            agregando.buscarNodo(i).setElegido(true);
                        }
                    }
                    for (int u = 0; u < agregandoA.getLeghtA(); u++)
                    {
                        if (armSelec[u].isClick())
                        {
                            agregandoA.buscarNodoA(u).setElegido(true);
                        }
                    }
                    setJugador(nombre.getText());

                    Jugador jugando = new Jugador();
                    jugando.setArmas(agregandoA);
                    jugando.setVehiculo(agregando);
                    jugando.setNombre(nombre.getText());

                    MenuJugar tmp = new MenuJugar(jugando);
                    tmp.setVisible(true);
                    setVisible(false);
                }
            }
        });
    }

    public void agregandoNombres(int correp, String nombre){
        agregando.buscarNodo(correp).setNombre(nombre);
    }

    public int getSeleccionV() {
        return seleccionV;
    }

    public void setSeleccionV(int seleccionV) {
        this.seleccionV += seleccionV;
        if (getSeleccionV()==3 && getSeleccionA()==3)
        {
            ir.setEnabled(true);
        } else
        {
            ir.setEnabled(false);
        }
    }

    public int getSeleccionA() {
        return seleccionA;
    }

    public void setSeleccionA(int seleccionA) {
        this.seleccionA += seleccionA;
        if (getSeleccionA()==3 && getSeleccionV()==3)
        {
            ir.setEnabled(true);
        } else
        {
            ir.setEnabled(false);
        }
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }
}

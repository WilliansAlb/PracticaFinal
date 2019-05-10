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
    JLabel bienvenida, vehiculos, armas, sele;
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
            agregandoA.buscarNodoA(p).getImagen(p);
        }
        String[] nombres = {"METAL SLUG","STRIKER","SERVOARMOUR","FALCON","LOCKHEED MARTIN F-16","HALCON MILENARIO"};
        String[] nombresA = {"MK1 DRIVER","MK2 DRIVER","MK1 GAUSS","MK2 GAUSS","LASER GUN","SHOT GUN"};
        for (int q = 0; q<6; q++)
        {
            agregandoNombres(q,nombres[q]);
            agregandoA.buscarNodoA(q).setNombre(nombresA[q]);
        }
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
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
        bordePanelInformacion2.setTitlePosition(TitledBorder.CENTER);
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
            String hp = "HP: 80";
            String pp = "ATAQUE: 30";
            String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
            vehSelec[i].setToolTipText(todo1);
            vehiculos1.add(vehSelec[i]);
        }
        /*for (int i = 0; i<3; i++)
        {
            String nombre = agregando.buscarNodo(i).getNombre();
            String hp = "HP: 80";
            String pp = "ATAQUE: 30";
            String todo2 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
            JLabel inf1 = new JLabel(todo2);
            inf1.setForeground(yellow);
            vehiculos1.add(inf1);
        }*/

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
            String hp = "PRECISIÓN: 80%";
            String pp = "ATAQUE: 30";
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
        //selec.add(no);
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
                    System.out.println("¡qué de ahuevo!");
                    setJugador(nombre.getText());
                    System.out.println(getJugador());
                    int[] vehSeleccionados = new int[3];
                    int[] armSeleccionadas = new int[3];
                    int conteo = 0;
                    for (int i = 0; i<6; i++)
                    {
                        if (vehSelec[i].isSeleccionado())
                        {
                            vehSeleccionados[conteo] = vehSelec[i].getNumRec();
                            agregando.buscarNodo(vehSelec[i].getNumRec()).setElegido(true);
                            conteo++;
                        }
                    }
                    conteo = 0;
                    for (int u = 0; u<6; u++)
                    {
                        if (armSelec[u].isSeleccionado())
                        {
                            armSeleccionadas[conteo] = armSelec[u].getNumRec();
                            agregandoA.buscarNodoA(vehSelec[u].getNumRec()).setElegido(true);
                            conteo++;
                        }
                    }
                    Jugador jugando = new Jugador();
                    jugando.setArmas(agregandoA);
                    jugando.setVehiculo(agregando);
                    jugando.setNombre(nombre.getText());
                    MenuJugar tmp = new MenuJugar(jugando);

                    //CampoDeBatalla tmp = new CampoDeBatalla();
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

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.lightGray;
import static java.awt.Color.yellow;

public class Modalidad extends JFrame {

    FondoWWI fondito;
    JLabel bienvenida;
    PanelNuevo informacion, vehiculos1, armas1, selec, escenario;
    AgregarVeh agregandoA;
    Seleccion[] vehSelec;
    BotonesArmas[] armSelec;
    JButton ir, regresar, no, unoVsCom, unoVsUno, escenario1,escenario2,escenario3;
    JTextField nombre;
    private int seleccionV;
    private int escena = 0;
    private int seleccionA;
    private boolean modali, nueva1, click;
    Jugador jugando, jugador1, jugador3;
    Font pant = new Font("Arcade Normal",Font.BOLD,8);

    public Modalidad(Jugador jugando){
        modali = true;
        this.jugando = jugando;
        this.agregandoA = jugando.getArmas();
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }
    public Modalidad(boolean modali, int escena, Jugador jugador1)
    {
        this.jugador1 = jugador1;
        nueva1 = modali;
        this.escena = escena;
        this.modali = false;
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
        if (modali){
            escenario = new PanelNuevo(2);
        }
        iniciarVehiculos();
        iniciarArmas();
        iniciarBotSec();
        iniciarEtiquetas();
        iniciarBEscenarios();
    }
    public void iniciarEtiquetas(){
        bienvenida = new JLabel("Cual es tu nombre Jugador 1?");

        TitledBorder bordePanelInformacion = new TitledBorder("Antes de jugar, personaliza tu juego");
        colocaTitulos(bordePanelInformacion,informacion);
        TitledBorder bordePanelInformacion3 = new TitledBorder("Listo?");
        colocaTitulos(bordePanelInformacion3,selec);
        TitledBorder bordePanelInformacion2 = new TitledBorder("Elige tus armas iniciales");
        colocaTitulos(bordePanelInformacion2,armas1);
        TitledBorder bordePanelInformacion1 = new TitledBorder("<html><body>Elige tus vehiculos, manten el cursor<br>sobre la imagen para ver las caracteristicas</body></html>");
        colocaTitulos(bordePanelInformacion1,vehiculos1);


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



        this.getContentPane().add(BorderLayout.NORTH, informacion);
        this.getContentPane().add(BorderLayout.CENTER, vehiculos1);
        this.getContentPane().add(BorderLayout.EAST, armas1);
        this.getContentPane().add(BorderLayout.SOUTH, selec);
        if (escena==0){
            TitledBorder bordePanel = new TitledBorder("Elige el escenario");
            colocaTitulos(bordePanel,escenario);
            nombre.setText(this.jugando.getNombre());
            informacion.add(unoVsCom);
            informacion.add(unoVsUno);
            this.getContentPane().add(BorderLayout.WEST, escenario);
        }
    }
    public void iniciarVehiculos(){
        vehSelec = new Seleccion[this.jugando.getVehiculo().getLeght()];
        vehiculos1.setLayout(new GridLayout(2,3));
        int conteo = 0;
        for (int i = 0; i<this.jugando.getVehiculo().getLeght(); i++)
        {
            if (this.jugando.getVehiculo().buscarNodo(i).isElegido()){
                vehSelec[conteo] = new Seleccion(this);
                ImageIcon imagen = new ImageIcon(this.jugando.getVehiculo().buscarNodo(i).getTheImagen().getImage().getScaledInstance(100,100, Image.SCALE_REPLICATE));
                vehSelec[conteo].setIcon(imagen);
                vehSelec[conteo].setContentAreaFilled(false);
                vehSelec[conteo].setNumRec(i);
                String nombre = this.jugando.getVehiculo().buscarNodo(i).getNombre();
                String hp = "HP: "+this.jugando.getVehiculo().buscarNodo(i).getHp();
                String pp = "ATAQUE: "+this.jugando.getVehiculo().buscarNodo(i).getAtack();
                String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
                vehSelec[conteo].setToolTipText(todo1);
                vehiculos1.add(vehSelec[conteo]);
                conteo++;
            }
        }
    }
    public void iniciarArmas(){
        armSelec = new BotonesArmas[agregandoA.getLeghtA()];
        armas1.setLayout(new GridLayout(3,2));
        int conteo = 0;
        for (int u = 0; u<agregandoA.getLeghtA(); u++)
        {
            if (agregandoA.buscarNodoA(u).isElegido()){
                armSelec[conteo] = new BotonesArmas(this);
                ImageIcon imagen2 = new ImageIcon(agregandoA.buscarNodoA(u).getTheImagen().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE));
                armSelec[conteo].setIcon(imagen2);
                armSelec[conteo].setContentAreaFilled(false);
                armSelec[conteo].setNumRec(u);
                String nombre = agregandoA.buscarNodoA(u).getNombre();
                String hp = "PRECISIÓN: "+agregandoA.buscarNodoA(u).getPrecision()+"%";
                String pp = "ATAQUE: "+agregandoA.buscarNodoA(u).getAtaque();
                String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+pp+"</body></html>";
                armSelec[conteo].setToolTipText(todo1);
                armas1.add(armSelec[conteo]);
                conteo++;
            }
        }
    }
    public void iniciarBotSec(){

        ir = new JButton("Jugar");
        regresar = new JButton("Regresar");

        if (escena==0)
        {
            unoVsCom = new JButton("1vsCOM");
            unoVsUno = new JButton("1vs1");
            unoVsCom.setBackground(lightGray);
            unoVsUno.setBackground(lightGray);
            unoVsCom.setEnabled(false);
            unoVsUno.setEnabled(true);
        }

        selec.setLayout(new GridLayout(1,3));

        ir.setEnabled(false);
        ir.setFont(pant);
        ir.setForeground(yellow);
        ir.setContentAreaFilled(false);

        regresar.setFont(pant);
        regresar.setForeground(yellow);
        regresar.setContentAreaFilled(false);

        no = new JButton("Listo");
        if(escena==0)
        {
            selec.add(ir);
            selec.add(regresar);
        }

        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("boolean "+isModali());
            }
        });
        ir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nombre.getText().length()==0){
                    ImageIcon vault = new ImageIcon("src/fts/desacuerdo.png");
                    JOptionPane.showMessageDialog(null,"No has puesto tu nombre","Error",JOptionPane.INFORMATION_MESSAGE,vault);
                } else {
                    if (!isModali())
                    {
                        if (jugador3==null){
                            Jugador nuevoJ = new Jugador();
                            nuevoJ.setNombre(nombre.getText());
                            nuevoJ.setVehiculo(getJugando().getVehiculo());
                            nuevoJ.setArmas(getJugando().getArmas());

                            for (int i = 0; i<getJugando().getVehiculo().getLeght(); i++)
                            {
                                if(vehSelec[i].isClick())
                                {
                                    nuevoJ.getVehiculo().buscarNodo(vehSelec[i].getNumRec()).setSelec(true);
                                }
                            }
                            for (int u = 0; u<getJugando().getArmas().getLeghtA();u++)
                            {
                                if(armSelec[u].isClick())
                                {
                                    nuevoJ.getArmas().buscarNodoA(armSelec[u].getNumRec()).setSelec(true);
                                }
                            }
                            Modalidad nuevoM = new Modalidad(isModali(),getEscena(), nuevoJ);
                            nuevoM.setVisible(true);
                            setVisible(false);
                        } else {


                        }

                    } else {

                        try {
                            for (int i = 0; i<getJugando().getVehiculo().getLeght(); i++)
                            {
                                if(vehSelec[i].isClick())
                                {
                                    getJugando().getVehiculo().buscarNodo(vehSelec[i].getNumRec()).setSelec(true);
                                    System.out.println(vehSelec[i].getNumRec());
                                }
                            }
                        }catch (Exception er){
                            System.out.println("un error");
                        }
                        try {
                            for (int u = 0; u<getJugando().getArmas().getLeghtA();u++)
                            {
                                if(armSelec[u].isClick())
                                {
                                    getJugando().getArmas().buscarNodoA(armSelec[u].getNumRec()).setSelec(true);
                                    System.out.println(armSelec[u].getNumRec());
                                }
                            }
                        }catch (Exception er){
                            System.out.println("un error");
                        }

                        CampoDeBatalla campo = new CampoDeBatalla(getEscena(), getJugando());
                        campo.setVisible(true);
                        setVisible(false);
                    }
                }
            }
        });
        unoVsCom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir.setText("JUGAR");
                unoVsCom.setEnabled(false);
                unoVsUno.setEnabled(true);
                setModali(true);
            }
        });
        unoVsUno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ir.setText("JUGADOR 2");
                unoVsUno.setEnabled(false);
                unoVsCom.setEnabled(true);
                setModali(false);
            }
        });
    }
    public void iniciarBEscenarios(){
        if (escena==0){
            escenario.setLayout(new GridLayout(3,1));

            ImageIcon esce1 = new ImageIcon("src/fts/escenario3.png");
            ImageIcon esce2 = new ImageIcon("src/fts/escenario1.png");
            ImageIcon esce3 = new ImageIcon("src/fts/escenario2.png");

            escenario1 = new JButton(new ImageIcon(esce1.getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
            escenario2 = new JButton(new ImageIcon(esce2.getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));
            escenario3 = new JButton(new ImageIcon(esce3.getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE)));

            escenario1.setContentAreaFilled(false);
            escenario2.setContentAreaFilled(false);
            escenario3.setContentAreaFilled(false);

            escenario.add(escenario1);
            escenario.add(escenario2);
            escenario.add(escenario3);

            setClick(false);

            escenario1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isClick() && escena==0)
                    {
                        setEscena(1);
                        escenario1.setContentAreaFilled(true);
                        escenario1.setBackground(yellow);
                        setClick(true);
                    } else
                    {
                        if (escena==1){
                            setEscena(0);
                        }
                        escenario1.setContentAreaFilled(false);
                        setClick(false);
                    }
                    verificador();
                }
            });
            escenario2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isClick() && escena==0) {
                        setEscena(2);
                        escenario2.setContentAreaFilled(true);
                        escenario2.setBackground(yellow);
                        setClick(true);
                    } else {
                        if (escena==2){
                            setEscena(0);
                        }
                        escenario2.setContentAreaFilled(false);
                        setClick(false);
                    }
                    verificador();
                }
            });
            escenario3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isClick() && escena==0) {
                        setEscena(3);
                        escenario3.setContentAreaFilled(true);
                        escenario3.setBackground(yellow);
                        setClick(true);
                    } else {
                        if (escena==3){
                            setEscena(0);
                        }
                        escenario3.setContentAreaFilled(false);
                        setClick(false);
                    }
                    verificador();
                }
            });
        }
    }

    public int getSeleccionV() {
        return seleccionV;
    }

    public void setSeleccionV(int seleccionV) {
        this.seleccionV += seleccionV;
        verificador();
    }

    public int getSeleccionA() {
        return seleccionA;
    }

    public void setSeleccionA(int seleccionA) {
        this.seleccionA += seleccionA;
        verificador();
    }


    public boolean isModali() {
        return modali;
    }

    public void setModali(boolean modali) {
        this.modali = modali;
    }

    public int getEscena() {
        return escena;
    }

    public void setEscena(int escena) {
        this.escena = escena;
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public void verificador(){
        if (getSeleccionV()==3 && getSeleccionA()==3 && getEscena()!=0)
        {
            ir.setEnabled(true);
        } else
        {
            ir.setEnabled(false);
        }
    }

    public void colocaTitulos(TitledBorder bordePanelInformacion1, PanelNuevo panel1){
        bordePanelInformacion1.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion1.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion1.setTitleFont(pant);
        bordePanelInformacion1.setTitleColor(yellow);
        panel1.setBorder(bordePanelInformacion1);
    }

    public Jugador getJugando() {
        return jugando;
    }
}

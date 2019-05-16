import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.yellow;

public class Tienda extends JFrame {
    FondoWWI fondito;
    Jugador jugando;
    PanelNuevo informacion, vehiculos1, armas1, selec, escenario;
    Seleccion[] vehSelec;
    BotonesArmas[] armSelec;
    JLabel capital;
    JButton comprar, regresar, boot;
    JTextField dinero;
    private int bootCompra;

    Tienda(Jugador jugando) {
        this.jugando = jugando;
        setSize(700, 700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
        //iniciarBotones();
    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(7);
        this.getContentPane().add(fondito);
        informacion = new PanelNuevo(0);
        vehiculos1 = new PanelNuevo(1);
        armas1 = new PanelNuevo(2);
        selec = new PanelNuevo(0);
        escenario = new PanelNuevo(2);
        iniciarInformacion();
        iniciarVehiculos();
        iniciarArmas();
        iniciarElementosAleatorios();
        iniciarBotonesSeleccion();
    }

    public void iniciarInformacion(){
        capital = new JLabel("Tu saldo es de: ");
        capital.setForeground(yellow);
        capital.setFont(new Font("Arcade Interlaced", Font.PLAIN, 15));

        TitledBorder bordePanelInformacion = new TitledBorder("Bienvenido a la tienda, ¿qué compraras?");
        colocaTitulos(bordePanelInformacion,informacion);
        TitledBorder bordePanelInformacion3 = new TitledBorder("¿Seguro lo compraras?");
        colocaTitulos(bordePanelInformacion3,selec);
        TitledBorder bordePanelInformacion2 = new TitledBorder("Comprar armas");
        colocaTitulos(bordePanelInformacion2,armas1);
        TitledBorder bordePanelInformacion1 = new TitledBorder("<html><body>Compra vehiculos, manten el cursor<br>sobre la imagen para ver el precio</body></html>");
        colocaTitulos(bordePanelInformacion1,vehiculos1);
        TitledBorder bordePanelInformacion4 = new TitledBorder("Compra de boots");
        colocaTitulos(bordePanelInformacion4,escenario);

        dinero = new JTextField(4);
        dinero.setFont(new Font("Arcade Interlaced", Font.PLAIN, 15));
        dinero.setOpaque(false);
        dinero.setForeground(yellow);
        dinero.setText(""+jugando.getDinero());

        informacion.setBorder(bordePanelInformacion);

        informacion.setOpaque(false);
        informacion.add(capital);
        informacion.add(dinero);

        this.getContentPane().add(BorderLayout.NORTH, informacion);
        this.getContentPane().add(BorderLayout.CENTER, vehiculos1);
        this.getContentPane().add(BorderLayout.SOUTH, selec);
    }

    public void iniciarVehiculos(){
        vehSelec = new Seleccion[this.jugando.getVehiculo().getLeght()];
        vehiculos1.setLayout(new GridLayout(2,3));
        int conteo = 0;
        for (int i = 0; i<this.jugando.getVehiculo().getLeght(); i++)
        {
            if (!this.jugando.getVehiculo().buscarNodo(i).isElegido()){
                vehSelec[conteo] = new Seleccion(this, jugando);
                ImageIcon imagen = new ImageIcon(this.jugando.getVehiculo().buscarNodo(i).getTheImagen().getImage().getScaledInstance(100,100, Image.SCALE_REPLICATE));
                vehSelec[conteo].setIcon(imagen);
                vehSelec[conteo].setContentAreaFilled(false);
                vehSelec[conteo].setNumRec(i);
                String nombre = this.jugando.getVehiculo().buscarNodo(i).getNombre();
                String hp = "HP: "+this.jugando.getVehiculo().buscarNodo(i).getHp();
                String pp = "ATAQUE: "+this.jugando.getVehiculo().buscarNodo(i).getAtack();
                String precio = "PRECIO: 50";
                String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+precio+"<br>"+pp+"</body></html>";
                vehSelec[conteo].setToolTipText(todo1);
                vehiculos1.add(vehSelec[conteo]);
                conteo++;
            }
        }
        this.getContentPane().add(BorderLayout.CENTER, vehiculos1);
    }

    public void iniciarArmas(){
        armSelec = new BotonesArmas[jugando.getArmas().getLeghtA()];
        armas1.setLayout(new GridLayout(3,2));
        int conteo = 0;
        for (int u = 0; u<jugando.getArmas().getLeghtA(); u++)
        {
            if (!jugando.getArmas().buscarNodoA(u).isElegido()){
                armSelec[conteo] = new BotonesArmas(this,jugando);
                ImageIcon imagen2 = new ImageIcon(jugando.getArmas().buscarNodoA(u).getTheImagen().getImage().getScaledInstance(100,100,Image.SCALE_REPLICATE));
                armSelec[conteo].setIcon(imagen2);
                armSelec[conteo].setContentAreaFilled(false);
                armSelec[conteo].setNumRec(u);
                String nombre = jugando.getArmas().buscarNodoA(u).getNombre();
                String hp = "PRECISIÓN: "+jugando.getArmas().buscarNodoA(u).getPrecision()+"%";
                String pp = "ATAQUE: "+jugando.getArmas().buscarNodoA(u).getAtaque();
                String precio = "PRECIO: 35";
                String todo1 = "<html><body>"+nombre+"<br>"+hp+"<br>"+precio+"<br>"+pp+"</body></html>";
                armSelec[conteo].setToolTipText(todo1);
                armas1.add(armSelec[conteo]);
                conteo++;
            }
        }
        this.getContentPane().add(BorderLayout.EAST, armas1);
    }
    public void iniciarElementosAleatorios(){
        ImageIcon boot1 = new ImageIcon("src/fts/boot1.gif");
        boot = new JButton(new ImageIcon(boot1.getImage().getScaledInstance(60,60,Image.SCALE_REPLICATE)));
        boot.setContentAreaFilled(false);
        JButton menos = new JButton("-");
        menos.setEnabled(false);
        menos.setContentAreaFilled(false);
        menos.setFont(new Font("Arcade Interlaced", Font.PLAIN,40));
        menos.setForeground(yellow);

        JButton mas = new JButton("+");
        mas.setContentAreaFilled(false);
        mas.setFont(new Font("Arcade Interlaced", Font.PLAIN,40));
        mas.setForeground(yellow);


        boot.setText("0");
        boot.setForeground(yellow);
        boot.setToolTipText("PRECIO: 75");
        boot.setEnabled(false);
        boot.setFont(new Font("Arcade Interlaced", Font.PLAIN,40));

        escenario.add(mas);
        escenario.add(boot);
        escenario.add(menos);

        escenario.setLayout(new GridLayout(3,1));

        mas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((jugando.getDinero()-75)<0){
                    ImageIcon vault = new ImageIcon("src/fts/desacuerdo.png");
                    JOptionPane.showMessageDialog(null,"No tienes mas dinero para boots","Falta de dinero",JOptionPane.INFORMATION_MESSAGE,vault);
                    mas.setEnabled(false);
                    menos.setEnabled(true);
                } else {
                    jugando.setDinero(-75);
                    dinero.setText(""+jugando.getDinero());
                    setBootCompra(+1);
                    boot.setText(""+getBootCompra());
                    menos.setEnabled(true);
                    if ((jugando.getDinero()-75)<0){
                        mas.setEnabled(false);
                    }
                }
            }
        });
        menos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (getBootCompra()==0){
                    menos.setEnabled(false);
                } else {
                    jugando.setDinero(+75);
                    dinero.setText(""+jugando.getDinero());
                    setBootCompra(-1);
                    if (getBootCompra()==0){
                        menos.setEnabled(false);}
                    boot.setText(""+getBootCompra());
                }
                mas.setEnabled(true);
            }
        });

        this.getContentPane().add(BorderLayout.WEST, escenario);
    }
    public void iniciarBotonesSeleccion(){
        comprar = new JButton("COMPRAR");
        regresar = new JButton("REGRESAR AL MENU");

        comprar.setContentAreaFilled(false);
        regresar.setContentAreaFilled(false);

        comprar.setForeground(yellow);
        regresar.setForeground(yellow);

        selec.setLayout(new GridLayout(1,2));
        selec.add(comprar);
        selec.add(regresar);

        this.getContentPane().add(BorderLayout.SOUTH, selec);

        comprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    for (int i = 0; i<jugando.getVehiculo().getLeght(); i++)
                    {
                        if(vehSelec[i].isClick())
                        {
                            jugando.getVehiculo().buscarNodo(vehSelec[i].getNumRec()).setElegido(true);
                            vehSelec[i].setEnabled(false);
                            vehSelec[i].setContentAreaFilled(false);
                        }
                    }
                }catch (Exception er){
                }
                try {
                    for (int u = 0; u<jugando.getArmas().getLeghtA();u++)
                    {
                        if(armSelec[u].isClick())
                        {
                            jugando.getArmas().buscarNodoA(armSelec[u].getNumRec()).setElegido(true);
                            armSelec[u].setContentAreaFilled(true);
                            armSelec[u].setEnabled(false);
                        }
                    }
                }catch (Exception er){

                }
                ImageIcon vault = new ImageIcon("src/fts/ganaste.png");
                JOptionPane.showMessageDialog(null,"Elementos comprados","WWI/ COMPRADO",JOptionPane.INFORMATION_MESSAGE,vault);
                jugando.setBootsComprados(+getBootCompra());
                int temp = getBootCompra();
                setBootCompra(-temp);
            }
        });
        regresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuJugar jugar = new MenuJugar(jugando);
                jugar.setVisible(true);
                setVisible(false);
            }
        });
    }

    public void colocaTitulos(TitledBorder bordePanelInformacion1, PanelNuevo panel1){
        bordePanelInformacion1.setTitleJustification(TitledBorder.CENTER);
        bordePanelInformacion1.setTitlePosition(TitledBorder.TOP);
        bordePanelInformacion1.setTitleColor(yellow);
        panel1.setBorder(bordePanelInformacion1);
    }

    public int getBootCompra() {
        return bootCompra;
    }

    public void setBootCompra(int bootCompra) {
        this.bootCompra += bootCompra;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.red;
import static java.awt.Color.yellow;

public class MenuJugar extends JFrame {
    FondoWWI fondito;
    Jugador jugando;
    JButton jugar, tienda, garage, creacion, menuP;
    Font arcade = new Font("Arcade Interlaced",Font.PLAIN,10);

    public MenuJugar(Jugador jugando){
        this.jugando = jugando;
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
        iniciarBotones();
    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(4);
        this.getContentPane().add(fondito);
        JLabel bienvenida = new JLabel();
        bienvenida.setText("<html><body>¡Bienvenido "+jugando.getNombre()+"!<br>¿Que deseas hacer?</body></html>");
        bienvenida.setFont(arcade);
        bienvenida.setBounds(295,80,150,150);
        bienvenida.setForeground(red);
        fondito.add(bienvenida);
    }
    public void iniciarBotones(){
        jugar = new JButton();
        tienda = new JButton();
        creacion = new JButton();
        garage = new JButton();
        menuP = new JButton();

        configurarBotonesM(jugar,330);
        configurarBotonesM(tienda,368);
        configurarBotonesM(creacion,406);
        configurarBotonesM(garage,444);
        configurarBotonesM(menuP,482);

        jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Modalidad modo = new Modalidad(getJugando());
                modo.setVisible(true);
                setVisible(false);
            }
        });
        tienda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tienda comprar = new Tienda(getJugando());
                comprar.setVisible(true);
                setVisible(false);
            }
        });

    }

    public void configurarBotonesM(JButton boton1, int y){
        boton1.setBounds(264,y,25,25);
        boton1.setOpaque(false);
        boton1.setContentAreaFilled(false);
        boton1.setBorderPainted(false);
        fondito.add(boton1);
    }

    public Jugador getJugando() {
        return jugando;
    }
}

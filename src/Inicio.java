import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicio extends JFrame {
    FondoWWI fondito;
    private int partida = 0;
    private int puntaje;
    private String usuario;
    Inicio(){
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
    }
    public void iniciarComponentes(){
        fondito = new FondoWWI(1);
        this.getContentPane().add(fondito);
        iniciarBotones();
    }

    public void iniciarBotones(){
        JButton jugar;
        JButton cargarPartida;
        JButton puntajePartidas;
        JButton salir;
        ImageIcon botoncito = new ImageIcon("src/fts/botoncito.png");
        ImageIcon boton = new ImageIcon(botoncito.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH));
        jugar = new JButton(boton);
        cargarPartida = new JButton(boton);
        puntajePartidas = new JButton(boton);
        salir = new JButton(boton);

        jugar.setOpaque(false);
        jugar.setBorderPainted(false);

        cargarPartida.setOpaque(false);
        cargarPartida.setBorderPainted(false);

        puntajePartidas.setBorderPainted(false);
        puntajePartidas.setOpaque(false);

        salir.setOpaque(false);
        salir.setBorderPainted(false);

        if (partida!=0){
            cargarPartida.setBounds(264,377, 30,30);
            jugar.setBounds(264,330,30,30);
            puntajePartidas.setBounds(264,424,30,30);
            salir.setBounds(264,472,30,30);
            fondito.add(jugar);
            fondito.add(cargarPartida);
            fondito.add(puntajePartidas);
            fondito.add(salir);
        } else {
            jugar.setBounds(264,330,30,30);
            puntajePartidas.setBounds(264,424,30,30);
            salir.setBounds(264,472,30,30);
            fondito.add(jugar);
            fondito.add(puntajePartidas);
            fondito.add(salir);
        }
        jugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PartidaPersonalizada campo = new PartidaPersonalizada();
                setVisible(false);
                campo.setVisible(true);
                /*PartidaPersonalizada partidaN = new PartidaPersonalizada();
                partidaN.setVisible(true);
                setVisible(false);*/
            }
        });
        puntajePartidas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuJugar jugar = new MenuJugar(new Jugador());
                jugar.setVisible(true);
                setVisible(false);
            }
        });

        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(WIDTH);
            }
        });
    }
}

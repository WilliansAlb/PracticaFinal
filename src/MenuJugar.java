import javax.swing.*;
import java.awt.*;

import static java.awt.Color.red;
import static java.awt.Color.yellow;

public class MenuJugar extends JFrame {
    FondoWWI fondito;
    Jugador jugando;
    Font arcade = new Font("Arcade Interlaced",Font.PLAIN,10);

    public MenuJugar(Jugador jugando){
        this.jugando = jugando;
        setSize(700,700); //se establece el tamaño de la ventana
        setLocationRelativeTo(null);
        setTitle("WWI"); // le coloca titulo a la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        iniciarComponentes();
        for (int i = 0; i < this.jugando.getVehiculo().getLeght(); i++)
        {
            if (this.jugando.getVehiculo().buscarNodo(i).isElegido()){
                System.out.println(this.jugando.getVehiculo().buscarNodo(i).getNombre());
            }
        }
        for (int i = 0; i < this.jugando.getArmas().getLeghtA(); i++)
        {
            if (this.jugando.getArmas().buscarNodoA(i).isElegido()){
                System.out.println(this.jugando.getArmas().buscarNodoA(i).getNombre());
            }
        }
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
}

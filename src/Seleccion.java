import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seleccion extends JButton implements ActionListener {
    private boolean click;
    PartidaPersonalizada nueva;
    Modalidad nuevo;
    Tienda tienda1;
    Jugador jugando;
    private boolean seleccionado;
    private int numRec;

    public Seleccion(PartidaPersonalizada nueva) {
        this.nueva = nueva;
        click = false;
        addActionListener(this::actionPerformed);
    }
    public Seleccion(Modalidad nuevo)
    {
        this.nuevo = nuevo;
        click = false;
        addActionListener(this::actionPerformed);
    }
    public Seleccion(Tienda tienda1, Jugador jugando){
        this.jugando = jugando;
        this.tienda1 = tienda1;
        click = false;
        addActionListener(this::actionPerformed);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (tienda1==null){
            if (nueva != null){
                if (!isClick())
                {
                    if (nueva.getSeleccionV()<3){
                        nueva.setSeleccionV(1);
                        setClick(true);
                        setContentAreaFilled(true);
                        setBackground(Color.yellow);
                        setSeleccionado(true);
                    }

                } else
                {
                    nueva.setSeleccionV(-1);
                    setClick(false);
                    setContentAreaFilled(false);
                    setSeleccionado(false);
                }
            } else {
                if (!isClick())
                {
                    if (nuevo.getSeleccionV()<3){
                        nuevo.setSeleccionV(1);
                        setClick(true);
                        setContentAreaFilled(true);
                        setBackground(Color.yellow);
                        setSeleccionado(true);
                    }

                } else
                {
                    nuevo.setSeleccionV(-1);
                    setClick(false);
                    setContentAreaFilled(false);
                    setSeleccionado(false);
                }
            }
        } else {
            if (!isClick())
            {
                if (jugando.getDinero()>0){
                    if ((jugando.getDinero()-50)<0){
                        ImageIcon vault = new ImageIcon("src/fts/desacuerdo.png");
                        JOptionPane.showMessageDialog(null,"No tienes tanto dinero para comprar el vehiculo","Falta de dinero",JOptionPane.INFORMATION_MESSAGE,vault);
                    } else {
                        jugando.setDinero(-50);
                        tienda1.dinero.setText(""+jugando.getDinero());
                        setClick(true);
                        setContentAreaFilled(true);
                        setBackground(Color.yellow);
                        setSeleccionado(true);
                    }
                }

            } else
            {
                jugando.setDinero(+50);
                tienda1.dinero.setText(""+jugando.getDinero());
                setClick(false);
                setContentAreaFilled(false);
                setSeleccionado(false);
            }
        }
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public int getNumRec() {
        return numRec;
    }

    public void setNumRec(int numRec) {
        this.numRec = numRec;
    }
}

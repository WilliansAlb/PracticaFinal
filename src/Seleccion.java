import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seleccion extends JButton implements ActionListener {
    private boolean click;
    PartidaPersonalizada nueva;
    Modalidad nuevo;
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
    @Override
    public void actionPerformed(ActionEvent e) {
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

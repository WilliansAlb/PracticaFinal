import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seleccion extends JButton implements ActionListener {
    private boolean click;
    PartidaPersonalizada nueva;
    private boolean seleccionado;
    private int numRec;

    public Seleccion(PartidaPersonalizada nueva) {
        this.nueva = nueva;
        click = false;
        addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isClick())
        {
            if (nueva.getSeleccionV()<3){
                nueva.setSeleccionV(1);
                setClick(true);
                setContentAreaFilled(true);
                setBackground(Color.yellow);
                System.out.println(nueva.getSeleccionV());
                setSeleccionado(true);

            }

        } else
        {
            nueva.setSeleccionV(-1);
            setClick(false);
            setContentAreaFilled(false);
            System.out.println(nueva.getSeleccionV());
            setSeleccionado(false);
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

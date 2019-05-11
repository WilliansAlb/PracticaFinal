import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonesArmas extends JButton implements ActionListener {
    PartidaPersonalizada nueva2;
    Modalidad nuevo2;
    private boolean click;
    private boolean seleccionado;
    private int numRec;


    public BotonesArmas(PartidaPersonalizada nueva2) {
        this.nueva2 = nueva2;
        click = false;
        addActionListener(this::actionPerformed);
    }
    public BotonesArmas(Modalidad nuevo2)
    {
        this.nuevo2 = nuevo2;
        click = false;
        addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nueva2!=null)
        {
            if (!isClick())
            {
                if (nueva2.getSeleccionA()<3){
                    nueva2.setSeleccionA(1);
                    setClick(true);
                    setContentAreaFilled(true);
                    setBackground(Color.yellow);
                    setSeleccionado(true);
                }
            } else
            {
                nueva2.setSeleccionA(-1);
                setClick(false);
                setContentAreaFilled(false);
                setSeleccionado(false);
            }
        }
        else {
            if (!isClick())
            {
                if (nuevo2.getSeleccionA()<3){
                    nuevo2.setSeleccionA(1);
                    setClick(true);
                    setContentAreaFilled(true);
                    setBackground(Color.yellow);
                    setSeleccionado(true);
                }
            } else
            {
                nuevo2.setSeleccionA(-1);
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

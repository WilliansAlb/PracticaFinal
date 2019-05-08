import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BotonesArmas extends JButton implements ActionListener {
    PartidaPersonalizada nueva2;
    private boolean click;

    public BotonesArmas(PartidaPersonalizada nueva2) {
        this.nueva2 = nueva2;
        click = false;
        addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isClick())
        {
            nueva2.setSeleccionA(1);
            setClick(true);
            setContentAreaFilled(true);
            setBackground(Color.lightGray);
            System.out.println(nueva2.getSeleccionV());
        } else
        {
            nueva2.setSeleccionA(-1);
            setClick(false);
            setContentAreaFilled(false);
            System.out.println(nueva2.getSeleccionV());
        }
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Seleccion extends JButton implements ActionListener {
    private boolean click;
    PartidaPersonalizada nueva;

    public Seleccion(PartidaPersonalizada nueva) {
        this.nueva = nueva;
        click = false;
        addActionListener(this::actionPerformed);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!isClick())
        {
            nueva.setSeleccionV(1);
            setClick(true);
            setContentAreaFilled(true);
            setBackground(Color.lightGray);
            System.out.println(nueva.getSeleccionV());
        } else
        {
            nueva.setSeleccionV(-1);
            setClick(false);
            setContentAreaFilled(false);
            System.out.println(nueva.getSeleccionV());
        }
    }

    public boolean isClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

}

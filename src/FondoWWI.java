import javax.swing.*;
import java.awt.*;

public class FondoWWI extends JPanel {
    Image fondo;

    FondoWWI(int i){
        setSize(700,700);
        setBounds(0,0,700,700);
        setOpaque(true);
        setLayout(null);
        switch (i){
            case 1:
            {
                ImageIcon imagen = new ImageIcon("src/fts/war5.png");
                fondo = imagen.getImage();
                break;
            }
            case 2:
            {
                ImageIcon imagen = new ImageIcon("src/fts/fallout11.jpg");
                fondo = imagen.getImage();
                break;
            }
            case 3:
            {
                ImageIcon imagen = new ImageIcon("src/fts/sinrelampago.gif");
                fondo = imagen.getImage();
                break;
            }
            case 4:
            {
                fondo = null;
                break;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paint(g);
    }
}

import javax.swing.*;
import java.awt.*;

public class TerrenoLibre extends Casilla{

    public TerrenoLibre(int x, int y, int alto, int posicionX, int posicionY, CampoDeBatalla campo) {
        super(x, y, alto, posicionX, posicionY, campo);
        ImageIcon tierra = new ImageIcon("src/fts/tierra.jpg");
        ImageIcon tierraR = new ImageIcon(tierra.getImage().getScaledInstance(getAlto()-5,getAlto()-5, Image.SCALE_REPLICATE));
        setIcon(tierraR);
    }

}

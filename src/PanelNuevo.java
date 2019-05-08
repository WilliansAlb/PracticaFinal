import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelNuevo extends JPanel {

    private Image bgImage;

    public PanelNuevo(int i) {
        this.setOpaque(false);
        setBackgroundImage(i);
    }

    public void setBackgroundImage(int i) {
        switch (i){
            case 0:
                {
                    ImageIcon nuevaImagen = new ImageIcon("src/fts/fondo1.png");
                    this.bgImage = nuevaImagen.getImage();
                    break;
                }
            case 1:
            {
                ImageIcon nuevaImagen = new ImageIcon("src/fts/panel1.png");
                this.bgImage = nuevaImagen.getImage();
                break;
            }
            case 2:
            {
                ImageIcon nuevaImagen = new ImageIcon("src/fts/panel2.png");
                this.bgImage = nuevaImagen.getImage();
                break;
            }
            default:
                System.out.println("Hubo un error");
        }

    }

    /**
     * Para recuperar una imagen de un archivo...
     *
     * @param path Ruta de la imagen relativa al proyecto
     * @return una imagen
     */
    /*public ImageIcon createImage(String path) {
        URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }*/

    @Override
    public void paint(Graphics g) {

        // Pintamos la imagen de fondo...
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(),getHeight(), null);
        }

        // Y pintamos el resto de cosas que pueda tener el panel
        super.paint(g);

    }
}

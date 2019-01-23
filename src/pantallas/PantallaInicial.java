package pantallas;

import base.PanelJuego;
import base.Pantalla;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class PantallaInicial implements Pantalla {
    PanelJuego panelJuego;

    BufferedImage bfOriginal;
    Image imgReescalada;

    public PantallaInicial(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
    }

    @Override
    public void inicializarPantalla() {
        this.bfOriginal = ImageIO.read(new File("imagenes/imagenInicioSnake.jpg"));


    }

    @Override
    public void pintarPantalla(Graphics g) {

    }

    @Override
    public void ejecutarFrame() {

    }

    @Override
    public void pulsarRaton(MouseEvent e) {

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {

    }
}

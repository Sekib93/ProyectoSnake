package pantallas;

import base.PanelJuego;
import base.Pantalla;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PantallaGameOver implements Pantalla {
    PanelJuego panelJuego;

    BufferedImage bfOriginal;
    Image imageReescalada;
    Font fuente;

    int puntuacion;

    public PantallaGameOver(PanelJuego panelJuego, int puntuacion) {
        this.panelJuego = panelJuego;
        this.puntuacion = puntuacion;
    }

    @Override
    public void inicializarPantalla() {
        try {
            bfOriginal = ImageIO.read(new File("imagenes/gameOver"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public void pintarPantalla(Graphics g) {
        g.drawImage(imageReescalada,0,0,null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("American Typewriter", Font.BOLD, 60));
        g.drawString(String.valueOf(puntuacion), panelJuego.getWidth()-50, panelJuego.getHeight()+50);
        g.setFont(new Font("American Typewriter", Font.BOLD, 40));
        g.drawString(String.valueOf(puntuacion),panelJuego.getWidth()-780,panelJuego.getHeight()-250);
        reescalarImagen();
    }

    private void reescalarImagen() {
        imageReescalada = bfOriginal.getScaledInstance(panelJuego.getWidth(),panelJuego.getHeight(), Image.SCALE_SMOOTH);
    }

    @Override
    public void ejecutarFrame() {

    }

    @Override
    public void pulsarRaton(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
    }
}

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

public class PantallaInicial implements Pantalla {
    private PanelJuego panelJuego;

    private BufferedImage bfOriginal;
    private Image imgReescalada;
    private Font fuente;
    private Color color = Color.GREEN;
    private int contadorColorFrames = 0;
    static final int CAMBIO_COLOR = 5;


    public PantallaInicial(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;

    }

    @Override
    public void inicializarPantalla() {
        try {
            this.bfOriginal = ImageIO.read(new File("imagenes/imagenInicioSnake.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fuente = new Font("American Typewriter", Font.BOLD, 45);
    }

    @Override
    public void pintarPantalla(Graphics g) {
        g.drawImage(imgReescalada,0,0,null);
        g.setColor(color);
        g.setFont(fuente);
        g.drawString("HAZ CLICK PARA EMPEZAR A JUGAR", panelJuego.getWidth()/2-450,
                panelJuego.getHeight()/2-40);
    }

    @Override
    public void ejecutarFrame() {
        this.contadorColorFrames++;
        if(this.contadorColorFrames%CAMBIO_COLOR == 0){
            if(this.color.equals(Color.GREEN)){
                this.color = Color.RED;
            } else {
                this.color = Color.GREEN;
            }
        }
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        PantallaJuego pantallaJuego = new PantallaJuego(panelJuego);
        pantallaJuego.inicializarPantalla();
        panelJuego.setPantallaActual(pantallaJuego);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        imgReescalada = bfOriginal.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(), Image.SCALE_SMOOTH);
    }
}

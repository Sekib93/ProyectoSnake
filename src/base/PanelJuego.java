package base;

import pantallas.PantallaInicial;

import javax.swing.*;
import java.awt.*;

/**
 * PanelJuego, controla los gráficos del juego.
 */
public class PanelJuego extends JPanel implements Runnable{
    private static final long serialVersionUID = 1L;
    private Pantalla pantallaActual;




    public PanelJuego() {
        new Thread(this).start();

        this.pantallaActual = new PantallaInicial();
        this.pantallaActual.inicializarPantalla();
    }

    public Pantalla getPantallaActual() {
        return pantallaActual;
    }

    public void setPantallaActual(Pantalla pantallaActual) {
        this.pantallaActual = pantallaActual;
    }

    /**
     * Sobreescritura del metodo paintComponent. Se le llama con repaint()
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        pantallaActual.pintarPantalla(g);
    }



    @Override
    public void run() {
        while (true){
            repaint();
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pantallaActual.ejecutarFrame();
            Toolkit.getDefaultToolkit().sync();
        }
    }
}

package base;

import pantallas.PantallaInicial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * PanelJuego, controla los gráficos del juego.
 */
public class PanelJuego extends JPanel implements Runnable, ComponentListener, MouseListener{
    private static final long serialVersionUID = 1L;
    private Pantalla pantallaActual;




    public PanelJuego() {
        this.setFocusable(true);
        this.addComponentListener(this);
        this.addMouseListener(this);
        new Thread(this).start();

        this.pantallaActual = new PantallaInicial(this);
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

    @Override
    public void componentResized(ComponentEvent e) {
        this.pantallaActual.redimensionarPantalla(e);
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pantallaActual.pulsarRaton(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}

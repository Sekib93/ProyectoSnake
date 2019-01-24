package pantallas;

import base.PanelJuego;
import base.Pantalla;
import base.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PantallaJuego implements Pantalla, KeyListener {

    private PanelJuego panelJuego;

    private boolean derecha = true;
    private boolean izquierda = false;
    private boolean arriba = false;
    private boolean abajo = false;

    private Sprite sprite;
    private ArrayList<Sprite> serpiente;

    private int posX = 10;
    private int posY = 10;
    private int tamanyo = 5;


    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.serpiente = new ArrayList<>();

    }
    /**
     * Primero comprobamos que nuestra serpiente tenga la cabeza e
     * inicializamos el fondo del panel
     *
     */
    @Override
    public void inicializarPantalla() {
        this.panelJuego.setBackground(Color.black);
        if(serpiente.size() == 0){
            sprite = new Sprite(posX, posY, 30, "imagenes/spriteSerpiente/blueHeadStraight.png");
            serpiente.add(sprite);
        }
    }

    @Override
    public void pintarPantalla(Graphics g) {
        for (int i = 0; i < serpiente.size(); i++) {
            serpiente.get(i).pintarSprite(g);
        }
        System.out.println("a");
    }


    @Override
    public void ejecutarFrame() {
        System.out.println("b");
            if(derecha){
                posX++;
            }
            if(izquierda){
                posX--;
            }
            if(arriba){
                posY--;
            }
            if(abajo){
                posY++;
            }
        serpiente.add(new Sprite(posX,posY,20,"imagenes/spriteSerpiente/blueHeadStraight.png"));

        sprite = new Sprite(posX,posY,30,"imagenes/spriteSerpiente/blueBodyStraight.png");
            serpiente.add(sprite);
            /**
             * Para dar el efecto de avanzar borramos la primera posicion de la serpiente
             */
            if(serpiente.size() > tamanyo){
                serpiente.remove(0);
            }
    }

    @Override
    public void pulsarRaton(MouseEvent e) {

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if(tecla == KeyEvent.VK_RIGHT && !izquierda){
            derecha = true;
            arriba = false;
            abajo = false;
        }
        if(tecla == KeyEvent.VK_LEFT && !derecha){
            izquierda = true;
            arriba = false;
            abajo = false;
        }
        if(tecla == KeyEvent.VK_UP && !abajo){
            derecha = false;
            arriba = true;
            izquierda = false;
        }
        if(tecla == KeyEvent.VK_DOWN && !arriba){
            derecha = false;
            izquierda = false;
            abajo = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

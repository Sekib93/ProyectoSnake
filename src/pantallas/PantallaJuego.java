package pantallas;

import base.PanelJuego;
import base.Pantalla;
import base.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PantallaJuego implements Pantalla, KeyListener {
    private static final int ANCHO_CUERPO = 20;
    private static final int ANCHO_CABEZA = 25;
    private static final int ANCHO_ITEM = 15;
    private static final Color COLOR_PUNTUACION = Color.YELLOW;
    private int puntuacion;
    private int velocidadExtra;
    private Sprite parteSerpiente;
    private Sprite item;
    private ArrayList<Sprite> serpienteCompleta;

    private PanelJuego panelJuego;
    private BufferedImage bfOriginal;
    private Image imgRescalada;
    private Font fuente;
    private int tamamyo;
    private int tiempoMover = 0;
    private boolean corriendo = true;
    private boolean derecha = true;
    private boolean izquierda = false;
    private boolean arriba = false;
    private boolean abajo = false;



    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.puntuacion = 0;
        this.panelJuego.setBackground(Color.black);
        this.serpienteCompleta = new ArrayList<>();
    }

    public void start(){

    }

    @Override
    public void inicializarPantalla() {

    }

    public void mover(){
        System.out.println("corriendo");
    }

    @Override
    public void pintarPantalla(Graphics g) {
        if(this.corriendo == true){
            mover();
        }


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

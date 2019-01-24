package pantallas;

import base.Manzana;
import base.PanelJuego;
import base.Pantalla;
import base.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class PantallaJuego implements Pantalla, KeyListener{

    private PanelJuego panelJuego;

    private boolean derecha = true;
    private boolean izquierda = false;
    private boolean arriba = false;
    private boolean abajo = false;

    private ArrayList<Sprite> serpiente;

    private int posX = 10;
    private int posY = 10;
    private boolean manzanaComida = false;
    private Manzana manzana;
    private int tiempo = 0;


    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.serpiente = new ArrayList<>();
        this.panelJuego.addKeyListener(this);
    }
    /**
     * Primero comprobamos que nuestra serpiente tenga la cabeza e
     * inicializamos el fondo del panel
     *
     */
    @Override
    public void inicializarPantalla() {
        this.panelJuego.setBackground(Color.black);
        manzana = new Manzana(300,300,"imagenes/manzana.png");
        if(serpiente.size() == 0){
            serpiente.add(new Sprite(posX, posY, 50, "imagenes/spriteSerpiente/blueHeadStraight.png"));
        }
    }

    @Override
    public void pintarPantalla(Graphics g) {
        for (int i = 0; i < serpiente.size(); i++) {
            if(i==0){
              serpiente.get(i).setRutaImagen("imagenes/spriteSerpiente/blueHeadStraight.png");
            }
            serpiente.get(i).pintarSprite(g);
        }
        manzana.pintarSprite(g);

    }

    @Override
    public void ejecutarFrame() {
            tiempo++;
            if(tiempo>5){
                if(derecha){
                    posX+=50;
                }
                if(izquierda){
                    posX-=50;
                }
                if(arriba){
                    for (int i = 0; i < serpiente.size(); i++) {
                        serpiente.get(i).setRutaImagen("imagenes/spriteSerpiente/blueBodyStraightVertical.png");
                    }
                    posY-=50;
                }
                if(abajo){
                    posY+=50;
                }
                serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueBodyStraight.png"));
                serpiente.remove(0);
                if(manzana.colisionan(serpiente.get(serpiente.size()-1))){
                    manzanaComida = true;
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueBodyStraight.png"));
                }
                for (int i = 0; i < serpiente.size()-1; i++) {
                    for (int j = i+1; j < serpiente.size(); j++) {
                        if(serpiente.get(i).colisionan(serpiente.get(j))){
                            System.out.println("BOOOOOOOM");
                        }
                    }

                }
                if(manzanaComida){
                    Random rd = new Random();
                    do{
                        manzana.setPosX(rd.nextInt(panelJuego.getWidth()));
                        manzana.setPosY(rd.nextInt(panelJuego.getHeight()));
                    } while (manzana.getPosX()>=panelJuego.getWidth() || manzana.getPosX()<=0
                    || manzana.getPosY()>=panelJuego.getHeight() || manzana.getPosY()<=0);

                    manzanaComida =false;
                }

                tiempo = 0;
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
            tiempo=5;
        }
        if(tecla == KeyEvent.VK_LEFT && !derecha){
            izquierda = true;
            arriba = false;
            abajo = false;
            tiempo=5;
        }
        if(tecla == KeyEvent.VK_UP && !abajo){
            derecha = false;
            arriba = true;
            izquierda = false;
            tiempo=5;
        }
        if(tecla == KeyEvent.VK_DOWN && !arriba){
            derecha = false;
            izquierda = false;
            abajo = true;
            tiempo=5;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

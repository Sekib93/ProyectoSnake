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
    private int puntuacion = 0;
    private int tiempo = 0;
    private Font fuente;


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
            serpiente.add(new Sprite(posX, posY, 50, "imagenes/spriteSerpiente/blueHeadStraight.png"));
        }
        fuente = new Font("American Typewriter", Font.BOLD, 40);
    }

    @Override
    public void pintarPantalla(Graphics g) {
        g.setFont(fuente);
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(puntuacion),panelJuego.getWidth()-50, 50);
        for (int i = 0; i < serpiente.size(); i++) {
            serpiente.get(i).pintarSprite(g);
        }
        manzana.pintarSprite(g);

    }

    @Override
    public void ejecutarFrame() {
            tiempo++;
            if(tiempo>5){
                if(derecha){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueHeadStraight.png"));
                    serpiente.remove(0);
                    posX+=50;
                    if(posX>=panelJuego.getWidth()){
                        posX=0;
                    }
                }
                if(izquierda){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueHeadStraightIzquierda.png"));
                    serpiente.remove(0);
                    posX-=50;
                    if(posX<=0){
                        posX=panelJuego.getWidth();
                    }
                }
                if(arriba){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueHeadStraightArriba.png"));
                    serpiente.remove(0);
                    posY-=50;
                    if(posY<=0){
                        posY=panelJuego.getHeight();
                    }
                }
                if(abajo){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/spriteSerpiente/blueHeadStraightAbajo.png"));
                    serpiente.remove(0);
                    posY+=50;
                    if(posY>=panelJuego.getHeight()){
                        posY=0;
                    }
                }




                if(manzana.colisionan(serpiente.get(serpiente.size()-1))){
                    manzanaComida = true;
                    puntuacion++;
                    serpiente.add(new Sprite(posX,posY ,50,"imagenes/spriteSerpiente/blueBodyStraight.png"));
                }

                for (int i = 2; i < serpiente.size(); i++) {
                        if(serpiente.get(0).colision(serpiente.get(i))){
                            PantallaGameOver pantallaGameOver = new PantallaGameOver(panelJuego,puntuacion);
                            pantallaGameOver.inicializarPantalla();
                            panelJuego.setPantallaActual(pantallaGameOver);
                        }
                    }

                if(manzanaComida){
                    Random rd = new Random();
                    do{
                        manzana.setPosX(rd.nextInt(panelJuego.getWidth()));
                        manzana.setPosY(rd.nextInt(panelJuego.getHeight()));
                    } while ((manzana.getPosY()>=panelJuego.getHeight() || manzana.getPosY()<=0) &&
                            (manzana.getPosX()>=panelJuego.getWidth() || manzana.getPosX()<=0));
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

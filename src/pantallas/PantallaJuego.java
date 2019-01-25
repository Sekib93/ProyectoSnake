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
    private int velocidad = 5;

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
            serpiente.add(0,new Sprite(posX, posY, 50, "imagenes/susuwatariDerecha.png"));
        }
        fuente = new Font("American Typewriter", Font.BOLD, 40);
    }

    /**
     * Pinta por pantalla
     * @param g
     */
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
        /**
         * genera una pieza del cuerpo nueva y borra la ultima para dar sensacion de movimiento
         */
        if(tiempo>velocidad){
                if(derecha){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/susuwatariDerecha.png"));
                    serpiente.remove(0);
                    posX+=50;
                    if(posX>=panelJuego.getWidth()){
                        posX=0;
                    }
                }
                if(izquierda){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/susuwatariIzquierda.png"));
                    serpiente.remove(0);
                    posX-=50;
                    if(posX<=0){
                        posX=panelJuego.getWidth();
                    }
                }
                if(arriba){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/susuwatariArriba.png"));
                    serpiente.remove(0);
                    posY-=50;
                    if(posY<=0){
                        posY=panelJuego.getHeight();
                    }
                }
                if(abajo){
                    serpiente.add(new Sprite(posX,posY,50,"imagenes/susuwatariAbajo.png"));
                    serpiente.remove(0);
                    posY+=50;
                    if(posY>=panelJuego.getHeight()){
                        posY=0;
                    }
                }

                tiempo = 0;
            }
        /**
         * Si come la manzana aparecera una nueva parte del cuerpo mirando en la direccion a la que iba
         */
        if(manzana.colisionan(serpiente.get(serpiente.size()-1))){
            manzanaComida = true;
            puntuacion++;
            if(puntuacion%10==0 && velocidad>0){
                velocidad--;
            }
            if(arriba){
                serpiente.add(0,new Sprite(posX,posY ,50,"imagenes/susuwatariArriba.png"));
            }else if(abajo){
                serpiente.add(0,new Sprite(posX,posY ,50,"imagenes/susuwatariAbajo.png"));
            }else if(derecha){
                serpiente.add(0,new Sprite(posX,posY ,50,"imagenes/susuwatariDerecha.png"));
            }else if(izquierda){
                serpiente.add(0,new Sprite(posX,posY ,50,"imagenes/susuwatariIzquierda.png"));
            }

        }
        /**
         * Si la serpiente choca con sigo misma perdera el juego y saltara la pantalla gameOver
         */
        for (int i = 2; i < serpiente.size(); i++) {
            if(serpiente.get(0).colision(serpiente.get(i))){
                PantallaGameOver pantallaGameOver = new PantallaGameOver(panelJuego,puntuacion);
                pantallaGameOver.inicializarPantalla();
                panelJuego.setPantallaActual(pantallaGameOver);
            }
        }
        /**
         * Si la manzana ha sido comida genera otra en una posicion aleatoria
         */
        if(manzanaComida){
            Random rd = new Random();
            do{
                manzana.setPosX(rd.nextInt(panelJuego.getWidth()));
                manzana.setPosY(rd.nextInt(panelJuego.getHeight()));
            } while ((manzana.getPosY()>=panelJuego.getHeight() || manzana.getPosY()<=0) &&
                    (manzana.getPosX()>=panelJuego.getWidth() || manzana.getPosX()<=0));
            manzanaComida =false;
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

    /**
     * Este metodo controla el funcionamiento de las teclas de direccion
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if(tecla == KeyEvent.VK_RIGHT && !izquierda){
            derecha = true;
            arriba = false;
            abajo = false;
            tiempo=velocidad;
        }
        if(tecla == KeyEvent.VK_LEFT && !derecha){
            izquierda = true;
            arriba = false;
            abajo = false;
            tiempo=velocidad;
        }
        if(tecla == KeyEvent.VK_UP && !abajo){
            derecha = false;
            arriba = true;
            izquierda = false;
            tiempo=velocidad;
        }
        if(tecla == KeyEvent.VK_DOWN && !arriba){
            derecha = false;
            izquierda = false;
            abajo = true;
            tiempo=velocidad;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

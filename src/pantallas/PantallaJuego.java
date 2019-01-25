package pantallas;

import base.Susuwatari;
import base.konpeitou;
import base.PanelJuego;
import base.Pantalla;

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

    private ArrayList<Susuwatari> grupoSusuwatari;

    private int posX = 10;
    private int posY = 10;
    private boolean manzanaComida = false;
    private konpeitou konpeitou;
    private int puntuacion = 0;
    private int tiempo = 0;
    private Font fuente;
    private int velocidad = 5;

    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        this.grupoSusuwatari = new ArrayList<>();
        this.panelJuego.addKeyListener(this);
    }
    /**
     * Primero comprobamos que nuestra grupoSusuwatari tenga la cabeza e
     * inicializamos el fondo del panel
     *
     */
    @Override
    public void inicializarPantalla() {
        this.panelJuego.setBackground(Color.black);
        konpeitou = new konpeitou(300,300,"imagenes/konpeitou.png");
        if(grupoSusuwatari.size() == 0){
            grupoSusuwatari.add(0,new Susuwatari(posX, posY, 50, "imagenes/susuwatariDerecha.png"));
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
        for (int i = 0; i < grupoSusuwatari.size(); i++) {
            grupoSusuwatari.get(i).pintarSprite(g);
        }
        konpeitou.pintarSprite(g);

    }

    @Override
    public void ejecutarFrame() {
            tiempo++;
        /**
         * genera una pieza del cuerpo nueva y borra la ultima para dar sensacion de movimiento
         */
        if(tiempo>velocidad){
                if(derecha){
                    grupoSusuwatari.add(new Susuwatari(posX,posY,50,"imagenes/susuwatariDerecha.png"));
                    grupoSusuwatari.remove(0);
                    posX+=50;
                    if(posX>=panelJuego.getWidth()){
                        posX=0;
                    }
                }
                if(izquierda){
                    grupoSusuwatari.add(new Susuwatari(posX,posY,50,"imagenes/susuwatariIzquierda.png"));
                    grupoSusuwatari.remove(0);
                    posX-=50;
                    if(posX<=0){
                        posX=panelJuego.getWidth()-50;
                    }
                }
                if(arriba){
                    grupoSusuwatari.add(new Susuwatari(posX,posY,50,"imagenes/susuwatariArriba.png"));
                    grupoSusuwatari.remove(0);
                    posY-=50;
                    if(posY<=0){
                        posY=panelJuego.getHeight()-50;
                    }
                }
                if(abajo){
                    grupoSusuwatari.add(new Susuwatari(posX,posY,50,"imagenes/susuwatariAbajo.png"));
                    grupoSusuwatari.remove(0);
                    posY+=50;
                    if(posY>=panelJuego.getHeight()){
                        posY=0;
                    }
                }

                tiempo = 0;
            }
        /**
         * Si come la konpeitou aparecera una nueva parte del cuerpo mirando en la direccion a la que iba
         */
        if(konpeitou.colisionan(grupoSusuwatari.get(grupoSusuwatari.size()-1))){
            manzanaComida = true;
            puntuacion++;
            if(puntuacion%10==0 && velocidad>0){
                velocidad--;
            }
            if(arriba){
                grupoSusuwatari.add(0,new Susuwatari(posX,posY ,50,"imagenes/susuwatariArriba.png"));
            }else if(abajo){
                grupoSusuwatari.add(0,new Susuwatari(posX,posY ,50,"imagenes/susuwatariAbajo.png"));
            }else if(derecha){
                grupoSusuwatari.add(0,new Susuwatari(posX,posY ,50,"imagenes/susuwatariDerecha.png"));
            }else if(izquierda){
                grupoSusuwatari.add(0,new Susuwatari(posX,posY ,50,"imagenes/susuwatariIzquierda.png"));
            }

        }
        /**
         * Si la grupoSusuwatari choca con sigo misma perdera el juego y saltara la pantalla gameOver
         */
        for (int i = 2; i < grupoSusuwatari.size(); i++) {
            if(grupoSusuwatari.get(0).colision(grupoSusuwatari.get(i))){
                PantallaGameOver pantallaGameOver = new PantallaGameOver(panelJuego,puntuacion);
                pantallaGameOver.inicializarPantalla();
                panelJuego.setPantallaActual(pantallaGameOver);
            }
        }
        /**
         * Si la konpeitou ha sido comida genera otra en una posicion aleatoria
         */
        if(manzanaComida){
            Random rd = new Random();
            do{
                konpeitou.setPosX(rd.nextInt(panelJuego.getWidth()));
                konpeitou.setPosY(rd.nextInt(panelJuego.getHeight()));
            } while ((konpeitou.getPosY()>=panelJuego.getHeight() || konpeitou.getPosY()<=0) &&
                    (konpeitou.getPosX()>=panelJuego.getWidth() || konpeitou.getPosX()<=0));
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

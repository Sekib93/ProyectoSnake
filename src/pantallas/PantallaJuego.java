package pantallas;

import base.PanelJuego;
import base.Pantalla;
import base.Sprite;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PantallaJuego implements Pantalla {
    private static final int ANCHO_CUERPO = 20;
    private static final int ANCHO_CABEZA = 25;
    private static final int ANCHO_ITEM = 15;
    private static final Color COLOR_PUNTUACION = Color.YELLOW;
    private int puntuacion;
    private int velocidadExtra;
    private Sprite serpiente;
    private Sprite item;
    private ArrayList<Sprite> serpienteCompleta;

    private PanelJuego panelJuego;
    private BufferedImage bfOriginal;
    private Image imgRescalada;
    private Font fuente;
    private int tamamyo;
    private int partes = 0;




    public PantallaJuego() {
        this.panelJuego.setBackground(Color.black);
        this.serpienteCompleta = new ArrayList<>();
        
    }

    public void partes(){
        if(serpienteCompleta.size() == 0){
            serpiente = new Sprite(20,"im");
        }
    }

    @Override
    public void inicializarPantalla() {

    }

    @Override
    public void pintarPantalla(Graphics g) {
        for (int i = 0; i < serpienteCompleta.size(); i++) {
            serpienteCompleta.get(i).pintarSprite(g);
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
}

package base;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal {

    final int anchoPantalla = 1200;
    final int altoPantalla = 775;

    private JFrame ventana;
    public PanelJuego panelJuego;
    public VentanaPrincipal() {
        this.ventana = new JFrame();
        this.ventana.setBounds(200,100, this.anchoPantalla, this.altoPantalla);
        this.ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ventana.setTitle("SUSUWATARI SNAKE");
        this.ventana.setResizable(false);
    }

    /**
     * Hacemos visible la ventana
     */
    public void inicializar(){
        this.ventana.setVisible(true);
        inicializarComponentes();
    }

    /**
     * Inicializamos el panel juego en nuestra ventana
     */
    public void inicializarComponentes(){
        this.ventana.setLayout(new GridLayout(1,1));
        this.panelJuego = new PanelJuego();
        this.ventana.add(this.panelJuego);

    }


}

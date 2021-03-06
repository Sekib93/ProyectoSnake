package base;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Pantalla {
    public void inicializarPantalla();
    public void pintarPantalla(Graphics g);
    public void ejecutarFrame();
    public void pulsarRaton(MouseEvent e);
    public void keyPressed(KeyEvent e);
    public void redimensionarPantalla(ComponentEvent e);

}

package base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int posX = 10;
    private int posY = 10;
    private int ancho;
    private int alto;
    private Color color = Color.BLACK;
    private String rutaImagen;
    private BufferedImage bfImagen;



    public Sprite(int tamanyo, String rutaImagen) {
        this.ancho = tamanyo;
        this.alto = tamanyo;
        this.rutaImagen = rutaImagen;
        actualizarBuffer();

    }
    public Sprite(int tamanyo) {
        this.ancho = tamanyo;
        this.alto = tamanyo;
        actualizarBuffer();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        actualizarBuffer();
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public BufferedImage getBfImagen() {
        return bfImagen;
    }

    public void setBfImagen(BufferedImage bfImagen) {
        this.bfImagen = bfImagen;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }
    public void moverSprite(){

    }
    /**
     * Metodo para actualizar el buffer de cada Sprite
     */
    public void actualizarBuffer(){

        this.bfImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.bfImagen.getGraphics();

        try {
            BufferedImage imagenSprite = ImageIO.read(new File(rutaImagen));
            g.drawImage(imagenSprite.getScaledInstance(ancho,alto, Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            g.setColor(color);
            g.fillRect(0,0,ancho,alto);
            g.dispose();
        }
    }

    public void pintarSprite(Graphics g){
        g.drawImage(bfImagen, posX, posY,null);
    }
}

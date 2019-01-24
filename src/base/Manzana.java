package base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Manzana {
    private int posX;
    private int posY;
    private int ancho;
    private int alto;
    private String rutaImagen;
    private BufferedImage bfImagen;

    public Manzana(int posX, int posY, String rutaImagen) {
        this.posX = posX;
        this.posY = posY;
        this.ancho = 50;
        this.alto = 50;
        this.rutaImagen = rutaImagen;
        actualizarBuffer();
    }

    public boolean colisionan(Sprite otroSprite) {
        boolean colision = false;
        int centro1 = posX+(ancho/2);
        int centroUno = posY+(alto/2);
        int centro2 = otroSprite.getPosX()+(otroSprite.getAncho()/2);
        int centroOtro = otroSprite.getPosY()+(otroSprite.getAlto()/2);
        int r1 = ancho/2;
        int r2 = otroSprite.getAncho()/2;
        double distancia = Math.sqrt(Math.pow((centro1-centro2), 2)
                +Math.pow((centroUno-centroOtro), 2));
        if((r1+r2)>=distancia) {
            colision = true;
        }
        return colision;

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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

    public void actualizarBuffer(){
        this.bfImagen = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = this.bfImagen.getGraphics();
        try {
            BufferedImage imagenSprite = ImageIO.read(new File(rutaImagen));
            g.drawImage(imagenSprite.getScaledInstance(ancho,alto, Image.SCALE_SMOOTH), 0 , 0, null);
        } catch (IOException e) {
            g.setColor(Color.BLACK);
            g.fillRect(posX,posY,ancho,alto);
            g.setColor(Color.GREEN);
            g.fillRect(posX,posY,ancho,alto);
            g.dispose();
        }
    }

    public void pintarSprite(Graphics g){
        g.drawImage(bfImagen, posX, posY,null);
    }

}

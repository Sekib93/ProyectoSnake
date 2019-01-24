package base;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite {
    private int posX;
    private int posY;
    private int ancho;
    private int alto;
    private String rutaImagen;
    private BufferedImage bfImagen;


    /**
     * La variable cuadrado no es mas que imaginar que la serpiente se mueve por un tablero de ajedrez y cuadrado hace
     * referencia al tamanyo que tendria la casilla
     *
     * @param posX
     * @param posY
     * @param cuadrado
     * @param rutaImagen
     */
    public Sprite(int posX, int posY, int cuadrado, String rutaImagen) {
        this.rutaImagen = rutaImagen;
        this.posY = posY;
        this.posX = posX;
        this.ancho = cuadrado;
        this.alto = cuadrado;
        actualizarBuffer();

    }
    public Sprite(int posX, int posY, int cuadrado) {
        this.posY = posY;
        this.posX = posX;
        this.ancho = cuadrado;
        this.alto = cuadrado;
        actualizarBuffer();
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
            g.drawImage(imagenSprite.getScaledInstance(ancho,alto, Image.SCALE_SMOOTH), 0 , 0, null);
        } catch (IOException e) {
            g.setColor(Color.BLACK);
            g.fillRect(posX,posY,ancho,alto);
        }
    }
    public boolean colision(Sprite otro) {
        boolean colisionX = false;
        boolean colisionY = false;
        if(posX+ancho>otro.posX) {//el de la izquierda es el otro
            if(otro.getPosX()+otro.getAncho() > posX) {
                colisionX = true;
            }
        } else {//si no soy yo el de la izquierda
            if(posX+ancho > otro.getPosX()) {
                colisionX = true;
            }
        }
        if(posY>otro.posY) {//el de la izquierda es el otro
            if(otro.getPosY()+otro.getAlto() > posY) {
                colisionY = true;
            }
        } else {//si no soy yo el de la izquierda
            if(posY+alto > otro.getPosY()) {
                colisionY = true;
            }
        }
        return colisionX && colisionY;
    }

    public void pintarSprite(Graphics g){
        g.drawImage(bfImagen, posX, posY,null);
    }
}

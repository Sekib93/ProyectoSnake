package base;

import java.util.ArrayList;

public class Serpiente extends Sprite {
    private ArrayList<Sprite> serpiente;
    public Serpiente(int tamanyo, String rutaImagen) {
        super(tamanyo, rutaImagen);
        this.serpiente = new ArrayList<>();
    }

    public Serpiente(int tamanyo) {
        super(tamanyo);
    }

    public ArrayList<Sprite> getSerpiente() {
        return serpiente;
    }

    public void setSerpiente(ArrayList<Sprite> serpiente) {
        this.serpiente = serpiente;
    }

    public void comprobarTamaño(){
        if(serpiente.size()==0){
            Sprite cabeza = new Sprite(15,"imagenes,spriteSerpiente/blueHeadStraight");
            serpiente.add(cabeza);
        }
    }

}

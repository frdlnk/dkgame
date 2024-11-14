package modelo.spritesCargados;

import modelo.entidades.Player;
import motor_v1.motor.component.Animation;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Gif;
import motor_v1.motor.util.Loader;
import motor_v1.motor.util.Vector2D;
import utils.Assets;
import utils.SpriteLoader;

import java.awt.image.BufferedImage;

public class SpritesPlayer extends Animation{

	private double escala;
	
    public SpritesPlayer( Transform transform) {
        super("");
        this.setTransformar(transform);
        loadGifs();
        escala = 2;
    }

    private void loadGifs() {
        Assets.load();
        for (int i = 0; i < Assets.gifs.size(); i++) {
            Gif gif = new Gif(Assets.spriteNames[i], Assets.gifs.get(Assets.spriteNames[i]), this.getTransformar(), 150);
            this.add(gif.getNombre(), gif);
        }
        cambiarAnimacionA(Assets.spriteNames[0]);
    
    @Override
    public void dibujar(Graphics g) {
    	Vector2D pos = getTransformar().getPosicion();
    	Vector2D escala = getTransformar().getEscala();
    	getTransformar().setEscala(new Vector2D(this.escala, this.escala));
		pos.setY(pos.getY()+.5);
    	super.dibujar(g);
    	pos.setY(pos.getY()-.5);
    	getTransformar().setEscala(escala);
    }
}

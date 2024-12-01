package modelo.spritesCargados;

import modelo.componentes.RelativeTransform;
import modelo.entidades.Player;
import motor_v1.motor.component.Animation;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Gif;
import motor_v1.motor.util.Vector2D;
import utils.constants.Assets;

import java.awt.Graphics;

public class SpritesPlayer extends Animation{
	private double escala;
    private Animation legs;
    private Player player;
	
    public SpritesPlayer(Player player) {
        super("torso");
        this.player = player;
        this.setTransformar(this.player.getTransformar());
        legs = new Animation("legs");
        loadGifs();
        escala = 2;
    }

    private void loadGifs() {
        Assets.load();
        for (int i = 0; i < Assets.gifs.size(); i++) {
            Gif gif = new Gif(Assets.spriteNames[i], Assets.gifs.getImages(Assets.spriteNames[i]), this.getTransformar(), 150);
            this.add(gif.getNombre(), gif);
        }
        cambiarAnimacionA(Assets.spriteNames[0]);

        for (int i = 0; i < Assets.gifsL.size(); i++) {
            Gif gif = new Gif(Assets.legNames[i], Assets.gifsL.getImages(Assets.legNames[i]), this.getTransformar(), 150);
            legs.add(gif.getNombre(), gif);
        }
        legs.cambiarAnimacionA(Assets.legNames[0]);
    }
    
    @Override
    public void dibujar(Graphics g) {
    	Vector2D pos = getTransformar().getPosicion();
    	Vector2D escala = getTransformar().getEscala();
    	getTransformar().setEscala(new Vector2D(this.escala, this.escala));
		pos.setY(pos.getY()+.6);
    	super.dibujar(g);
    	pos.setY(pos.getY()-.6);
    	getTransformar().setEscala(escala);
    }
}

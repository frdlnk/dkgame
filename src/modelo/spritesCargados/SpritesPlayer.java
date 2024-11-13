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

    public SpritesPlayer( Transform transform) {
        super("");
        this.setTransformar(transform);
        loadGifs();
    }

    private void loadGifs() {
        Assets.load();
        Gif gif = new Gif("Test", Assets.gifs.get(Assets.spriteNames[0]), this.getTransformar(), 150);
        setAnimacionActual(gif);
    }
}

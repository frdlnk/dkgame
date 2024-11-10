package modelo.spritesCargados;

import modelo.entidades.Player;
import motor_v1.motor.component.Animation;
import motor_v1.motor.entidades.Gif;
import motor_v1.motor.util.Vector2D;
import utils.SpriteLoader;

import java.awt.image.BufferedImage;

public class SpritesPlayer{
    Animation playerSp;
    modelo.entidades.Player player;

    public SpritesPlayer(Player player) {
        this.player = player;
        playerSp = new Animation("Player");
        loadGifs();
    }

    private void loadGifs() {
        BufferedImage sheet = SpriteLoader.getSpriteAtlas(SpriteLoader.PLAYER_SPRITE);
        int[] cuts = {570, 604, 621, 644, 678, 694};
        BufferedImage[] test = SpriteLoader.getSubImageArray(547, 274, 148, 21, SpriteLoader.getSpriteAtlas(SpriteLoader.PRAY), cuts);
        Gif gif = new Gif("Test", test, player.getTransformar(), 150);
    }

    public Animation getPlayerSp() {
        return playerSp;
    }

    public void setPlayerSp(Animation playerSp) {
        this.playerSp = playerSp;
    }
}

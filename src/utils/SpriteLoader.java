package utils;

import motor_v1.motor.component.Animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class SpriteLoader{

    public static final String PLAYER_SPRITE = "player_sprites.png";
    public static final String PRAY = "Walter_Revised.png";

    public static BufferedImage getSpriteAtlas(String fileName){
        BufferedImage img = null;
        InputStream in = SpriteLoader.class.getResourceAsStream("/" + fileName);
        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return img;
    }

    public static BufferedImage[] getSubImageArray(int x, int y, int w, int h, int indent, int amm, BufferedImage img){
        BufferedImage[] animation = new BufferedImage[amm];
        int xVar = x;
        animation[0] = img.getSubimage(x, y, w, h);
        for (int i = 1; i < animation.length; i++) {
            xVar += w + indent;
            animation[i] = img.getSubimage(xVar, y, w, h);
        }
        return animation;
    }

    public static BufferedImage[] getSubImageArray(int x, int y, int w, int h, BufferedImage image, int[] cuts){
        BufferedImage[] animation = new BufferedImage[cuts.length];
        int starter = x;
        int width;
        for (int i = 0; i < animation.length; i++) {
            width = cuts[i] - starter;
            try {
                animation[i] = image.getSubimage(starter, y, width, h);
            } catch (Exception e) {
                break;
            }
            starter = cuts[i];
        }
        return animation;

    }
    public static BufferedImage[] getSubImageArray(int[] coords, BufferedImage image, int[] cuts){
        BufferedImage[] animation = new BufferedImage[cuts.length];
        int starter = coords[0];
        int width;
        for (int i = 0; i < animation.length; i++) {
            width = cuts[i] - starter;
            try {
                animation[i] = image.getSubimage(starter, coords[1], width, coords[3]);
            } catch (Exception e) {
                break;
            }
            starter = cuts[i];
        }
        return animation;

    }




}

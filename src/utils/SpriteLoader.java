package utils;


import java.awt.image.BufferedImage;

public class SpriteLoader{

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

    public static BufferedImage[] getSubImageArray(int[] coords, BufferedImage image){
        BufferedImage[] animation = new BufferedImage[1];
        animation[0] = image.getSubimage(coords[0], coords[1], coords[2], coords[3]);
        return animation;

    }




}

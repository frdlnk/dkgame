package utils;

import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.Hashtable;

import motor_v1.motor.util.Loader;

public class Assets {

	public static BufferedImage MAPA_NIVEL_1;
	public static BufferedImage PLAYER_SPRITES;
	public static Dictionary<String, int[]> coord;
	public static Dictionary<String, int[]> cuts;
	public static Dictionary<String, BufferedImage[]> gifs;
	public static String[] spriteNames = {"pistol_idle", "pistol_shoot", "pistol_up_shoot"};
	
	public static boolean load() {
		MAPA_NIVEL_1 = Loader.cargarImagen("/MetalSlug-Mission1.png");
		PLAYER_SPRITES = Loader.cargarImagen("/Walter_Revised.png");
		coord = new Hashtable<>();
		cuts = new Hashtable<>();
		gifs = new Hashtable<>();
		loadCoord();
		loadCuts();
		loadGifs(coord, cuts);
		return true;
	}

	private static void loadGifs(Dictionary<String, int[]> coord, Dictionary<String, int[]> cuts) {
		BufferedImage[] temp;
		for (int i = 0; i < coord.size(); i++) {
			temp = SpriteLoader.getSubImageArray(coord.get(spriteNames[i]), PLAYER_SPRITES, cuts.get(spriteNames[i]));
			gifs.put(spriteNames[i], temp);
		}
	}

	private static void loadCoord() {
		coord.put(spriteNames[0], new int[]{3, 1, 94, 35});
		coord.put(spriteNames[1], new int[]{288, 1, 144, 35	});
	}

	private static void loadCuts() {
		cuts.put(spriteNames[0], new int[]{34, 65, 97});
		cuts.put(spriteNames[1], new int[]{340, 392, 431});
	}


}

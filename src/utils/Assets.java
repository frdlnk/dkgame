package utils;

import java.awt.image.BufferedImage;
import java.util.Dictionary;
import java.util.Hashtable;

import motor_v1.motor.util.Loader;
import utils.arrays.ArrayCoords;

public class Assets {

	public static BufferedImage MAPA_NIVEL_1;
	public static BufferedImage PLAYER_SPRITES;
	public static BufferedImage AVION_CATARATA_M1;
	public static BufferedImage CATARATA_AVION;
	public static ArrayCoords coord;
	public static ArrayCoords cuts;
	public static ArrayCoords gifs;
	public static String[] spriteNames = {"pistol_idle", "pistol_shoot", "pistol_up_shoot", "crouch_pistol_idle"};
	
	public static boolean load() {
		MAPA_NIVEL_1 = Loader.cargarImagen("/MetalSlug-Mission1.png");
		PLAYER_SPRITES = Loader.cargarImagen("/Walter_Revised.png");
		AVION_CATARATA_M1 = Loader.cargarImagen("/avionCatarataM1.png");
		CATARATA_AVION = Loader.cargarImagen("/catarata.png");
		coord = new ArrayCoords();
		cuts = new ArrayCoords();
		gifs = new ArrayCoords();
		loadCoord();
		loadCuts();
		loadGifs(coord, cuts);
		return true;
	}

	private static void loadGifs(ArrayCoords coord, ArrayCoords cuts) {
		BufferedImage[] temp;
		for (int i = 0; i < coord.size(); i++) {
			temp = SpriteLoader.getSubImageArray(coord.get(spriteNames[i]), PLAYER_SPRITES, cuts.get(spriteNames[i]));
			gifs.putImage(spriteNames[i], temp);
		}
	}

	private static void loadCoord() {
		coord.put(spriteNames[0], new int[]{3, 1, 94, 35});
		coord.put(spriteNames[1], new int[]{288, 1, 144, 35	});
		coord.put(spriteNames[2], new int[]{237, 38, 100, 65});
	}

	private static void loadCuts() {
		cuts.put(spriteNames[0], new int[]{34, 65, 97});
		cuts.put(spriteNames[1], new int[]{340, 392, 431});
		cuts.put(spriteNames[2], new int[]{261, 285, 311, 337});
	}


}

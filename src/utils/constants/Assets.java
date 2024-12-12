package utils.constants;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.sound.sampled.Clip;

import motor_v1.motor.util.Loader;
import utils.SpriteLoader;

/**
 * Assets del juego
 */
public class Assets {
	public static boolean isAssetsLoaded = false;

	public static BufferedImage MAPA_NIVEL_1;
	public static BufferedImage PLAYER_SPRITES;
	public static BufferedImage ENEMY_SPRITES;
	public static BufferedImage AVION_CATARATA_M1;
	public static BufferedImage CATARATA_AVION;
	public static BufferedImage LETRA_H;
	public static BufferedImage LETRA_R;
	public static Clip HEAVY_MACHINEGUN_SOUND;
	public static Clip ROCKET_LAUNCHER_SOUND;
	public static Clip MISSION_COMPLETE_SOUND;

	public static HashMap<String, int[]> coord;
	public static HashMap<String, int[]> cuts;
	public static HashMap<String, BufferedImage[]> gifs;

	public static HashMap<String, int[]> coordL;
	public static HashMap<String, int[]> cutsL;
	public static HashMap<String, BufferedImage[]> gifsL;

	public static HashMap<String, int[]> coordE;
	public static HashMap<String, int[]> cutsE;
	public static HashMap<String, BufferedImage[]> gifsE;

	public static Font future;

	public static String[] spriteNames = { "pistol_idle", "pistol_shoot", "pistol_up_shoot", "crouch_pistol_idle",
			"crouch_pistol_shoot", "crouch_pistol_walk", "pistol_look_up", "knife_cut" };
	public static String[] legNames = { "legs_running" };

	public static String[] enemNames = { "soldier_idle", "soldier_run", "soldier_throw", "rifle_idle", "rifle_run",
			"rifle_shoot", "fucking_shobu" };

	public static boolean load() {
		if (isAssetsLoaded) {
			return isAssetsLoaded;
		}
		MAPA_NIVEL_1 = Loader.cargarImagen("/MetalSlug-Mission1.png");
		PLAYER_SPRITES = Loader.cargarImagen("/Walter_Revised.png");
		ENEMY_SPRITES = Loader.cargarImagen("/SoldierSprites.png");

		LETRA_H = Loader.cargarImagen("/H.gif");
		LETRA_R = Loader.cargarImagen("/R.gif");

		HEAVY_MACHINEGUN_SOUND = Loader.cargarSonido("/heavyMachineGun.wav");
		ROCKET_LAUNCHER_SOUND = Loader.cargarSonido("/rocket-launcher-sound.wav");
		MISSION_COMPLETE_SOUND = Loader.cargarSonido("/metal-slug-mission-complete.wav");

		future = Loader.cargarFuente("/font/futureFont.ttf", 20);

		AVION_CATARATA_M1 = Loader.cargarImagen("/avionCatarataM1.png");
		CATARATA_AVION = Loader.cargarImagen("/catarata.png");
		coord = new HashMap<>();
		cuts = new HashMap<>();
		gifs = new HashMap<>();

		coordL = new HashMap<>();
		cutsL = new HashMap<>();
		gifsL = new HashMap<>();

		coordE = new HashMap<>();
		cutsE = new HashMap<>();
		gifsE = new HashMap<>();

		loadCoord();
		loadCuts();
		loadGifs(coord, cuts);
		isAssetsLoaded = true;
		return true;
	}

	private static void loadGifs(HashMap<String, int[]> coord, HashMap<String, int[]> cuts) {

		BufferedImage[] temp;
		for (int i = 0; i < coord.size(); i++) {

			temp = SpriteLoader.getSubImageArray(coord.get(spriteNames[i]), PLAYER_SPRITES, cuts.get(spriteNames[i]));
			gifs.put(spriteNames[i], temp);

		}

		for (int i = 0; i < coordL.size(); i++) {

			temp = SpriteLoader.getSubImageArray(coordL.get(legNames[i]), PLAYER_SPRITES, cutsL.get(legNames[i]));
			gifsL.put(legNames[i], temp);

		}

		for (int i = 0; i < coordE.size(); i++) {

			temp = SpriteLoader.getSubImageArray(coordE.get(enemNames[i]), ENEMY_SPRITES);
			gifsE.put(enemNames[i], temp);

		}
	}

	private static void loadCoord() {
		// Jugador
		coord.put(spriteNames[0], new int[] { 3, 1, 94, 35 });
		coord.put(spriteNames[1], new int[] { 288, 1, 144, 35 });
		coord.put(spriteNames[2], new int[] { 237, 38, 100, 65 });
		coord.put(spriteNames[3], new int[] { 573, 37, 107, 26 });
		coord.put(spriteNames[4], new int[] { 3, 169, 181, 28 });
		coord.put(spriteNames[5], new int[] { 605, 96, 143, 25 });
		coord.put(spriteNames[6], new int[] { 140, 95, 91, 28 });
		coord.put(spriteNames[7], new int[] { 69, 467, 42, 43 });

		coordL.put(legNames[0], new int[] { 547, 274, 148, 21 });

		// Enemigo
		coordE.put(enemNames[0], new int[] { 16, 255, 29, 38 });
		coordE.put(enemNames[1], new int[] { 224, 195, 22, 41 });
		coordE.put(enemNames[2], new int[] { 318, 22, 36, 42 });
		coordE.put(enemNames[3], new int[] { 23, 323, 40, 43 });
		coordE.put(enemNames[4], new int[] { 492, 384, 35, 37 });
		coordE.put(enemNames[5], new int[] { 72, 464, 47, 38 });
		coordE.put(enemNames[6], new int[] { 291, 272, 72, 58 });

	}

	private static void loadCuts() {

		// Jugador
		cuts.put(spriteNames[0], new int[] { 34, 65, 97 });
		cuts.put(spriteNames[1], new int[] { 340, 392, 431 });
		cuts.put(spriteNames[2], new int[] { 261, 285, 311, 337 });
		cuts.put(spriteNames[3], new int[] { 609, 645, 678 });
		cuts.put(spriteNames[4], new int[] { 55, 108, 147, 184 });
		cuts.put(spriteNames[5], new int[] { 641, 677, 713, 748 });
		cuts.put(spriteNames[6], new int[] { 171, 202, 231 });
		cuts.put(spriteNames[7], new int[] { 111, 112, 114, 115 });

		cutsL.put(legNames[0], new int[] { 570, 604, 621, 644, 678, 694 });

		// Enemigo
		cutsE.put(enemNames[0], new int[] { 48, 80, 111, 135 });
	}

}

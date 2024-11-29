package utils.constants;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import modelo.arrays.ArrayCoords;
import motor_v1.motor.util.Loader;
import utils.SpriteLoader;

/**
 * Assets del juego
 */
public class Assets {

	public static BufferedImage MAPA_NIVEL_1;
	public static BufferedImage PLAYER_SPRITES;
	public static BufferedImage AVION_CATARATA_M1;
	public static BufferedImage CATARATA_AVION;

	public static ArrayCoords coord;
	public static ArrayCoords cuts;
	public static ArrayCoords gifs;

	public static ArrayCoords coordL;
	public static ArrayCoords cutsL;
	public static ArrayCoords gifsL;

	public static BufferedImage[] PLAYER_IDLE = new BufferedImage[1];
	public static BufferedImage[] PLAYER_IDLE_IZQ = new BufferedImage[2];

	public static String[] spriteNames = {"pistol_idle", "pistol_shoot", "pistol_up_shoot", "crouch_pistol_idle", "crouch_pistol_shoot", "crouch_pistol_walk", "pistol_look_up"};
	public static String[] legNames = {"legs_running"};
	
	public static boolean load() {
		MAPA_NIVEL_1 = Loader.cargarImagen("/MetalSlug-Mission1.png");
		PLAYER_SPRITES = Loader.cargarImagen("/Walter_Revised.png");
		
		AVION_CATARATA_M1 = Loader.cargarImagen("/avionCatarataM1.png");
		CATARATA_AVION = Loader.cargarImagen("/catarata.png");
		coord = new ArrayCoords();
		cuts = new ArrayCoords();
		gifs = new ArrayCoords();

		coordL = new ArrayCoords();
		cutsL = new ArrayCoords();
		gifsL = new ArrayCoords();


		loadCoord();
		loadCuts();
		loadGifs(coord, cuts);
		return true;
	}

	
	/**
	 * invierte una imagen
	 * @param image imagen a invertir
	 * @return imagen invertida
	 */
	private static BufferedImage invertir(BufferedImage image) {
		AffineTransform at = AffineTransform.getScaleInstance(-1, 1);
		at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics2D g = newImage.createGraphics();
		g.drawImage(image, at,null);
		g.dispose();
		return newImage;
	}
	
	private static void loadGifs(ArrayCoords coord, ArrayCoords cuts) {

		BufferedImage[] temp;
		for (int i = 0; i < coord.size(); i++) {

			temp = SpriteLoader.getSubImageArray(coord.get(spriteNames[i]), PLAYER_SPRITES, cuts.get(spriteNames[i]));
			gifs.putImage(spriteNames[i], temp);

		}

		for (int i = 0; i < coordL.size(); i++) {

			temp = SpriteLoader.getSubImageArray(coordL.get(legNames[i]), PLAYER_SPRITES, cutsL.get(legNames[i]));
			gifsL.putImage(legNames[i], temp);

		}
	}

	private static void loadCoord() {
		coord.put(spriteNames[0], new int[]{3, 1, 94, 35});
		coord.put(spriteNames[1], new int[]{288, 1, 144, 35	});
		coord.put(spriteNames[2], new int[]{237, 38, 100, 65});
		coord.put(spriteNames[3], new int[]{573, 37, 107, 26});
		coord.put(spriteNames[4], new int[]{3, 169, 181, 28});
		coord.put(spriteNames[5], new int[]{605, 96, 143, 25});
		coord.put(spriteNames[6], new int[]{140, 95, 91, 28});
		coordL.put(legNames[0], new int[]{547, 274, 148, 21});


	}

	private static void loadCuts() {
		cuts.put(spriteNames[0], new int[]{34, 65, 97});
		cuts.put(spriteNames[1], new int[]{340, 392, 431});
		cuts.put(spriteNames[2], new int[]{261, 285, 311, 337});
		cuts.put(spriteNames[3], new int[]{609, 645, 678});
		cuts.put(spriteNames[4], new int[]{55, 108, 147, 184});
		cuts.put(spriteNames[5], new int[]{641, 677, 713, 748});
		cuts.put(spriteNames[6], new int[]{171, 202, 231});
		cutsL.put(legNames[0], new int[]{570, 604, 621, 644, 678, 694});
	}


}

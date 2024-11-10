package utils;

import java.awt.image.BufferedImage;

import motor_v1.motor.util.Loader;

public class Assets {

	public static BufferedImage MAPA_NIVEL_1;
	
	public static boolean load() {
		MAPA_NIVEL_1 = Loader.cargarImagen("/MetalSlug-Mission1.png");
		return true;
	}
}

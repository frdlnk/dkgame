package utils.constants;

/**
 * Configuraciones del juego general
 */
public final class Conf {
	public final static int WINDOW_HEIGHT = 428;
	public final static int WINDOW_WIDTH = 590;

	public final static int PLAYER_HEIGHT = 80;
	public final static int PLAYER_WIDTH = 50;

	public final static double GRAVITY = 9.8;

	public final static double VELOCIDAD_CAMBIO_DE_ZONAS = 300;

	public static String enemyType = "Soldados";
	public static int currentLevel = 1;
	public static int enemiesPerType = 10;
	public static int extraLives = 3;
	public static float gameSpeed = 1.0f;
	public static String playerWeaponType = "Pistol";
	public static float difficultyIncrease = 1.1f;

	public static void showConfig() {
		System.out.println("Configuración actual del juego:");
		System.out.println("Tipo de enemigos: " + enemyType);
		System.out.println("Nivel actual: " + currentLevel);
		System.out.println("Enemigos por tipo: " + enemiesPerType);
		System.out.println("Vidas extras: " + extraLives);
		System.out.println("Velocidad del juego: " + gameSpeed);
		System.out.println("Tipo de arma: " + playerWeaponType);
		System.out.println("Aumento de dificultad: " + difficultyIncrease);
	}
}

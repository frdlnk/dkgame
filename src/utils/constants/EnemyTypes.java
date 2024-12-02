package utils.constants;

/**
 * Tipos de enemigos que pueden aparecer
 */
public class EnemyTypes {
	public final static String HELICOPTERO = "HELICOPTERO";
	public final static String GRANADERO = "GRANADERO";
	public final static String PISTOLERO = "PISTOLERO";

	public static String[] values() {
		return new String[] { HELICOPTERO, GRANADERO, PISTOLERO };
	}
}

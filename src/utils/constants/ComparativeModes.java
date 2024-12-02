package utils.constants;

/**
 * Modos de comparacion de valores
 * 
 * @implNote puede ser un enum
 */
public class ComparativeModes {
	public final static String IGUAL = "Igual";
	public final static String MAYOR_QUE = "Mayor que";
	public final static String MENOR_QUE = "Menor que";

	/**
	 * @return lista con todos los valores de comparacion
	 */
	public static String[] values() {
		return new String[] { IGUAL, MAYOR_QUE, MENOR_QUE };
	}
}

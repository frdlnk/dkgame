package utils.constants;

/**
 * Campos del usuario que pueden ser comparados
 */
public class UserFields {
	//esto podria ser un enum
	public final static String FIELD_USERNAME = "USERNAME";
	public final static String FIELD_SCORE = "SCORE";
	public final static String FIELD_LEVEL = "LEVEL";
	
	/**
	 * @return lista con los campos del usuario que pueden ser comparados
	 */
	public static String[] values() {
		return new String[] {
				FIELD_USERNAME,
				FIELD_LEVEL,
				FIELD_SCORE
		};
	}
}

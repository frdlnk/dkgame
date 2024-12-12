package utils.constants;

/**
 * Campos del usuario que pueden ser comparados
 */
public enum UserFields {
	FIELD_USERNAME("Username"), FIELD_SCORE("Score"), FIELD_LEVEL("Level");

	private String text;

	private UserFields(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}

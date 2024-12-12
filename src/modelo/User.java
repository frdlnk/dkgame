package modelo;

/**
 * Modelo de datos para la informacion del usuario
 */
public class User extends DBModel {
	private int id;
	private String username;
	private String password;
	private int score;
	private int level;
	private int configId;

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public User() {
		super();
	}

	@Override
	public String toString() {
		return username + ": password=" + password + ", score=" + score + ", level=" + level;
	}

	/**
	 * Genera un registro de texto con los datos del usuario
	 * 
	 * @return registro de texto
	 */
	public String crearRegistroTexto() {
		String username = this.username.replace("','", "\",\"");
		String password = this.password.replace("','", "\",\"");
		String recordString = id + "','" + username + "','" + password + "','" + score + "','" + level + "','"
				+ configId;
		recordString = recordString.replace("\n", "\\n");
		return recordString;
	}

	/**
	 * Deserializa un registro de texto para crear de nuevo el objeto
	 * 
	 * @param userInfo registro de texto
	 * @return Usuario deserializado
	 */
	public static User deserializeUsuario(String userInfo) {
		String[] data = userInfo.replace("\\n", "\n").split("','");
		User user = new User();
		try {
			user.setId(Integer.parseInt(data[0]));
			user.setUsername(data[1]);
			user.setPassword(data[2]);
			user.setScore(Integer.parseInt(data[3]));
			user.setLevel(Integer.parseInt(data[4]));
			user.setConfigId(Integer.parseInt(data[5]));
		} catch (Exception e) {
			// invalid data format
			return null;
		}
		return user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if (level >= 0 && level < 11) {
			this.level = level;
		}
	}

	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}

	@Override
	public String getTableSchema() {
		String userConfigTable = new UserConfig().getTable();
		return "CREATE TABLE IF NOT EXISTS " + getTable() + "(" 
				+ "id INTEGER,"
				+ "Username VARCHAR(50) UNIQUE NOT NULL," 
				+ "Password VARCHAR(10) NOT NULL," 
				+ "Score INT default 0,"
				+ "Level INT default 0," 
				+ "ConfigID INT not null," 
				+ "PRIMARY KEY(id AUTOINCREMENT),"
				+ "FOREIGN KEY (ConfigID) REFERENCES " + userConfigTable + "(id)" 
				+ ")";
	}

}

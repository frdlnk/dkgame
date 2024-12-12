package modelo.Dao.file;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.UserConfig;
import modelo.Dao.IDAOUserConfigs;
import modelo.db.SQLiteManager;

/**
 * DAO de acceso a las configuraciones de usuario, mediante SQLITE
 */
public class DAO_UserConfig implements IDAOUserConfigs {
	private String tableString;

	/**
	 * Crea un nuevo DAO
	 */
	public DAO_UserConfig() {
		tableString = new UserConfig().getTable();
	}

	@Override
	public int insert(UserConfig config) {
		String sql = "INSERT INTO " + tableString
				+ "(VidasIniciales, MultiplicadorDano, MultiplicadorDanoEnemigo, ArmaInicial) " + "VALUES(?,?,?,?)";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, config.getVidasIniciales());
			statement.setDouble(2, config.getMultiplicadorDano());
			statement.setDouble(3, config.getMultiplicadorDanoEnemigo());
			statement.setString(4, config.getArmainicial());
			statement.execute();
			ResultSet idSet = statement.getGeneratedKeys();
			idSet.next();
			return idSet.getInt(1);
		} catch (SQLException e) {
			System.err.println("error al insertar");
		}
		return -1;
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM " + tableString + " WHERE id = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {

		}
	}

	@Override
	public void delete(UserConfig config) {
		delete(config.getId());
	}

	@Override
	public UserConfig get(int id) {
		String sql = "select * from " + tableString + " where id = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, id);
			ResultSet data = statement.executeQuery();
			if (data.next()) {
				return mapRecordToConfig(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private UserConfig mapRecordToConfig(ResultSet data) throws SQLException {
		UserConfig config = new UserConfig();
		config.setId(data.getInt(1));
		config.setVidasIniciales(data.getInt(2));
		config.setMultiplicadorDano(data.getDouble(3));
		config.setMultiplicadorDanoEnemigo(data.getDouble(4));
		config.setArmainicial(data.getString(5));
		return config;
	}

	private ArrayList<UserConfig> mapRecordsToArray(ResultSet data) throws SQLException {
		ArrayList<UserConfig> configs = new ArrayList<UserConfig>();
		while (data.next()) {
			configs.add(mapRecordToConfig(data));
		}
		return configs;
	}

	@Override
	public void update(UserConfig config) {
		String sql = "UPDATE " + tableString + " SET " + "VidasIniciales = ?," + "MultiplicadorDano = ?,"
				+ "MultiplicadorDanoEnemigo = ?," + "ArmaInicial = ? " + "WHERE id = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, config.getVidasIniciales());
			statement.setDouble(2, config.getMultiplicadorDano());
			statement.setDouble(3, config.getMultiplicadorDanoEnemigo());
			statement.setString(4, config.getArmainicial());
			statement.setInt(5, config.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("error al actualizar");
		}
	}

	@Override
	public ArrayList<UserConfig> getAll() {
		String sqlString = "select * from " + tableString;
		ResultSet data = SQLiteManager.get(sqlString);
		try {
			return mapRecordsToArray(data);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "DAO_UserConfig []";
	}

}

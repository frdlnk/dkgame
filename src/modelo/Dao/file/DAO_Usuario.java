package modelo.Dao.file;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.User;
import modelo.Dao.IDAOUsuario;
import modelo.db.SQLiteManager;
import utils.constants.ComparativeModes;
import utils.constants.UserFields;

/**
 * DAO de acceso y guarado de usuarios mediante SQLITE
 */
public class DAO_Usuario implements IDAOUsuario {
	private String tableString;

	/**
	 * crea un nuevo DAO
	 */
	public DAO_Usuario() {
		tableString = new User().getTable();
	}

	/**
	 * Mapea los valores de la row actual de un {@link ResultSet} a un {@link User}
	 * 
	 * @param data ResultSet con la fila selecianda para mapear
	 * @return User con los datos extraidos
	 * @throws SQLException si hay problemas al extraer los datos de la fila
	 */
	private User mapRecordToUsuario(ResultSet data) throws SQLException {
		User user = new User();
		user.setId(data.getInt("id"));
		user.setConfigId(data.getInt("ConfigId"));
		user.setLevel(data.getInt("Level"));
		user.setPassword(data.getString("Password"));
		user.setScore((data.getInt("Score")));
		user.setUsername((data.getString("UserName")));
		return user;
	}

	/**
	 * Crea un arreglo de objetos {@link User} a partir de un {@link ResultSet}
	 * 
	 * @param data el ResultSet que contiene los registros de users
	 * @return ArrayList<User> con los usuarios mapeados de los registros
	 * @throws SQLException si hay errores al acceder a los datpos edl ResultSet
	 */
	private ArrayList<User> mapRecordsToArray(ResultSet data) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		while (data.next()) {
			users.add(mapRecordToUsuario(data));
		}
		return users;
	}

	@Override
	public int insert(User usuario) {
		String sql = "INSERT INTO " + tableString + "(Username, Password, Score, Level, ConfigID) "
				+ "VALUES(?,?,?,?,?)";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getScore());
			statement.setInt(4, usuario.getLevel());
			statement.setInt(5, usuario.getConfigId());
			statement.execute();
			ResultSet idSet = statement.getGeneratedKeys();
			idSet.next();
			return idSet.getInt(1);
		} catch (SQLException e) {
			System.err.println("error al insertar");
			e.printStackTrace();
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
	public void delete(User user) {
		delete(user.getId());
	}

	@Override
	public User get(int id) {
		String sql = "select * from " + tableString + " where id = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, id);
			ResultSet data = statement.executeQuery();
			if (data.first()) {
				return mapRecordToUsuario(data);
			}
		} catch (SQLException e) {

		}
		return null;
	}

	@Override
	public void update(User usuario) {
		String sql = "UPDATE " + tableString + " SET " + "Username = ?," + "Password = ?," + "Score = ?," + "Level = ? "
				+ "WHERE id = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setString(1, usuario.getUsername());
			statement.setString(2, usuario.getPassword());
			statement.setInt(3, usuario.getScore());
			statement.setInt(4, usuario.getLevel());
			statement.setInt(5, usuario.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("error al actualizar");
		}
	}

	@Override
	public ArrayList<User> getAll() {
		String sqlString = "select * from " + tableString;
		ResultSet data = SQLiteManager.get(sqlString);
		try {
			return mapRecordsToArray(data);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public ArrayList<User> search(Object value, UserFields field, ComparativeModes searchMode) {
		String sql = "select * from " + tableString + " where " + field + getComparativeModeSyntax(searchMode) + "?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setObject(1, value);
			ResultSet data = statement.executeQuery();
			return mapRecordsToArray(data);
		} catch (SQLException e) {
			return null;
		}
	}

	private String getComparativeModeSyntax(ComparativeModes mode) {
		return switch (mode) {
		case IGUAL -> "=";
		case MAYOR_QUE -> ">";
		case MENOR_QUE -> "<";
		};
	}

	@Override
	public User get(String username) {
		String sql = "select * from " + tableString + " where Username = ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setString(1, username);
			ResultSet data = statement.executeQuery();
			if (data.first()) {
				return mapRecordToUsuario(data);
			}
		} catch (SQLException e) {

		}
		return null;
	}

	@Override
	public ArrayList<User> getBestScores(int cantidadRegistros) {
		String sql = "select * from " + tableString + " order by Score desc limit ?";
		PreparedStatement statement = SQLiteManager.getPreparedStatement(sql);
		try {
			statement.setInt(1, cantidadRegistros);
			ResultSet data = statement.executeQuery();
			return mapRecordsToArray(data);
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public String toString() {
		return "DAO_Usuario []";
	}

}

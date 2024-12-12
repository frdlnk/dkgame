package modelo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import modelo.DBModel;

public class SQLiteManager {
	public final static String CONNECTION_STRING = "jdbc:sqlite:metalSlug.db";
	private static Connection connection;

	public static PreparedStatement getPreparedStatement(String sql) {
		openCon();
		try {
			return connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void suscribeModel(DBModel modelo) {
		openCon();
		try {
			Statement smtp = connection.createStatement();
			smtp.execute(modelo.getTableSchema());
		} catch (SQLException e) {
			System.err.println("error al crear la tabla " + modelo.getTable());
			e.printStackTrace();
		}
	}

	public static void suscribeModels(DBModel[] modelos) {
		for (DBModel dbModel : modelos) {
			suscribeModel(dbModel);
		}
	}

	public static ResultSet get(String sql) {
		openCon();
		try {
			Statement stm = connection.createStatement();
			return stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static void openCon() {
		try {
			if (connection == null || connection.isClosed()) {
				connection = DriverManager.getConnection(CONNECTION_STRING);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

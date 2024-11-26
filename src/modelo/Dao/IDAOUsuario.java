package modelo.Dao;

import ctrl.Main;
import modelo.Usuario;
import modelo.arrays.UserArray;
import utils.ComparativeModes;

public interface IDAOUsuario {
	enum UserFields {
		USERNAME,
		SCORE,
		LEVEL,
	};
	
	String fileName = "user.txt";
	UserArray lista = Main.UserDataSet;
	public int insert(Usuario usuario);
	public void delete(int id);
	public void delete(Usuario user);
	public Usuario get(int id);
	public Usuario get(String username);
	public UserArray getAll();
	public UserArray search(Object value, UserFields field, ComparativeModes searchMode);
	public void update(Usuario usuario);
	public UserArray getBestScores(int cantidadRegistros);
}

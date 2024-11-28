package modelo.Dao;

import ctrl.Main;
import modelo.Usuario;
import modelo.arrays.UserArray;
import utils.constants.ComparativeModes;
import utils.constants.UserFields;

/**
 * Interfaz de acceso a los usuarios
 * 
 * @see Usuario
 */
public interface IDAOUsuario {
	
	UserArray lista = Main.UserDataSet;
	public int insert(Usuario usuario);
	public void delete(int id);
	public void delete(Usuario user);
	public Usuario get(int id);
	public Usuario get(String username);
	public UserArray getAll();
	/**
	 * REaliza una busaqueda de usuarios segun los criterios especificados
	 * @param value El valor del campo a buscar
	 * @param field campo del usuarios por el que se desea consultar
	 * @param searchMode Modo de busqueda (mayor_que, menor que, igual)
	 * @return Array con los usuarios encontrados
	 * @see UserFields
	 * @see ComparativeModes
	 */
	public UserArray search(Object value, String field, String searchMode);
	public void update(Usuario usuario);
	public UserArray getBestScores(int cantidadRegistros);
}

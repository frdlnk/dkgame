package modelo.Dao;

import java.util.ArrayList;

import modelo.User;
import utils.constants.ComparativeModes;
import utils.constants.UserFields;

/**
 * Interfaz de acceso a los usuarios
 * 
 * @see User
 */
public interface IDAOUsuario {

	/**
	 * Agrega un nuevo usuario
	 * 
	 * @param usuario Usuario a agregar
	 * @return id del usuario insertado, -1 si a ocurrido un error
	 */
	public int insert(User usuario);

	/**
	 * elimina un usuario por su id
	 * 
	 * @param id id del usuario a eliminar
	 */
	public void delete(int id);

	/**
	 * Elimina el usuario especificado
	 * 
	 * @param user usuario a eliminar
	 */
	public void delete(User user);

	/**
	 * Regresa el usuario con el id especificado
	 * 
	 * @param id id buscado
	 * @return el usuario buscado, null si no existe
	 */
	public User get(int id);

	/**
	 * Regresa un usuario por su username
	 * 
	 * @param username del usuario buscado
	 * @return Usuario buscado, null si no existe
	 */
	public User get(String username);

	/**
	 * Regresa todos los usuarios existentes
	 * 
	 * @return Array de usuarios con todos los usuarios
	 */
	public ArrayList<User> getAll();

	/**
	 * REaliza una busaqueda de usuarios segun los criterios especificados
	 * 
	 * @param value      El valor del campo a buscar
	 * @param field      campo del usuarios por el que se desea consultar
	 * @param searchMode Modo de busqueda (mayor_que, menor que, igual)
	 * @return Array con los usuarios encontrados
	 * @see UserFields
	 * @see ComparativeModes
	 */
	public ArrayList<User> search(Object value, UserFields field, ComparativeModes searchMode);

	/**
	 * actualiza un usuario existente <br>
	 * si el usuario no existe <b>no lo agrega<b>
	 * 
	 * @param usuario usuario a actualizar
	 */
	public void update(User usuario);

	/**
	 * Realiza una busqueda de los mejores scores y regresa la cantidad solicitada
	 * <br>
	 * Si no existen los suficientes registros para la cantidad solicitada se envian
	 * los registros existentes
	 * 
	 * @param cantidadRegistros cantidad de registros a traer
	 * @return Array de Usuarios con los mejores Scores
	 */
	public ArrayList<User> getBestScores(int cantidadRegistros);
}

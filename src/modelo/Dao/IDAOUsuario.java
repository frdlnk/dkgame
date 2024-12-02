package modelo.Dao;

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
	
	/**
	 * Agrega un nuevo usuario 
	 * @param usuario Usuario a agregar
	 * @return id del usuario insertado, -1 si a ocurrido un error
	 */
	public int insert(Usuario usuario);
	/**
	 * elimina un usuario por su id
	 * @param id id del usuario a eliminar
	 */
	public void delete(int id);
	/**
	 * Elimina el usuario especificado
	 * @param user usuario a eliminar
	 */
	public void delete(Usuario user);
	/**
	 * Regresa el usuario con el id especificado
	 * @param id id buscado
	 * @return el usuario buscado, null si no existe
	 */
	public Usuario get(int id);
	/**
	 * Regresa un usuario por su username
	 * @param username del usuario buscado
	 * @return Usuario buscado, null si no existe
	 */
	public Usuario get(String username);
	/**
	 * Regresa todos los usuarios existentes
	 * @return Array de usuarios con todos los usuarios
	 */
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
	/**
	 * actualiza un usuario existente
	 * <br> si el usuario no existe <b>no lo agrega<b>
	 * @param usuario usuario a actualizar
	 */
	public void update(Usuario usuario);
	/**
	 * Realiza una busqueda de los mejores scores y regresa la cantidad solicitada
	 * <br> Si no existen los suficientes registros para la cantidad solicitada se envian los registros existentes
	 * @param cantidadRegistros cantidad de registros a traer
	 * @return Array de Usuarios con los mejores Scores
	 */
	public UserArray getBestScores(int cantidadRegistros);
}

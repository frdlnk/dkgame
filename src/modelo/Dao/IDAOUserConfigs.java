package modelo.Dao;

import java.util.ArrayList;

import modelo.UserConfig;

/**
 * Interfaz de acceso a las configuraciones de usuario
 * 
 * @see UserConfig
 */
public interface IDAOUserConfigs {

	/**
	 * Inserta una nueva Configuracion de usuario
	 * 
	 * @param config configuracion a agregar
	 * @return id del registro insertado
	 */
	public int insert(UserConfig config);

	/**
	 * Elimina un registro por id
	 * 
	 * @param id
	 */
	public void delete(int id);

	/**
	 * Elimina el registro especificado
	 * 
	 * @param config
	 */
	public void delete(UserConfig config);

	/**
	 * regresa el registrop con el id especificado
	 * 
	 * @param id buscado
	 * @return UserConfig con id buscado, null si no existe
	 */
	public UserConfig get(int id);

	/**
	 * Regresa todas las configuraciones existentes
	 * 
	 * @return Array con las configuraciones
	 */
	public ArrayList<UserConfig> getAll();

	/**
	 * Actualiza un registro especificado <br>
	 * si el resgistro no existe, <b>no se agrega</b>
	 * 
	 * @param config objeto a actualizar
	 */
	public void update(UserConfig config);
}

package ctrl.adminControlers;

import modelo.Dao.IDAOUsuario;

/**
 * Clase para validacion de datos de usuario
 * 
 */
public class UserFieldsValidator {
	private IDAOUsuario modeloUsuario;
	
	
	/**
	 * Construye un nuevo validador para los adtos de usuario con el modelo especificado
	 * @param modeloUsuario IDAOUsuario para el acceso a los usuarios
	 */
	public UserFieldsValidator(IDAOUsuario modeloUsuario) {
		this.modeloUsuario = modeloUsuario;
	}
	
	/**
	 * Valida que un string que contiene la contrasena de usuario
	 * <br>cumpla con las siguientes condiciones
	 * <ul>
	 * 	<li>Longitud entre 4 y 10 caracteres</li>
	 * 	<li>No este vacia(por mensaje estetico ya que al validar la longitud esta implicito)</li>
	 * 	<li>Contiene 1 letra mayuscula</li>
	 * 	<li>Contiene una letra minuscula</li>
	 * 	<li>Contiene un numero</li>
	 * 	<li>Contiene un craacter especial</li>
	 * </ul>
	 * 
	 * @implNote La comprobacion valores minimos regresa un mensaje completo para una mejor compresion
	 * <br>del error para el usuario
	 * @param password string con la contrasena a evaluar
	 * @return null si no hay errores, String con el error si existe
	 */
	public String validatePassword(String password) {
		if (password == null || password.isEmpty()) {
			return "Debe ingresar una contrasena";
			
		}else if(password.length() < 4 || password.length() > 10) {
			return "La contrasena debe tener entre 4 y 10 cracteres";
			
		}else if(!password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{4,}$")) {
			/* 
			 * regex valida que hayan al menos una mayuscula, una minuscula, 1 digito, 1 caracter especial
			 * (?=.*\\d): un digito
			 * (?=.*[a-z]): una letra minuscula
			 * (?=.*[A-Z]): una mayuscula
			 * (?=.*\\W): un caracter especial
			 * .{4,}: cualquier caracter al menos 4 veces
			 * ^$: inicio y final de la cadena respectivamente
			 */
			return	  "<html>"
					+ 	"<p>"
					+ 		"La contrasena debe tener al menos 1 mayuscula, 1 miniscula,"
					+ 		"<br> 1 numero y 1 caracter especial"
					+ 	"</p>"
					+ "</html>";
		}
		//No hay errores
		return null;
	}
	
	
	/**
	 * Valida un String con un nombre d usuario
	 * <br> Valida las siguientes condiciones
	 * <ul>
	 * 	<li>Longitud entre 4 y 50 caracteres</li>
	 * 	<li>No este vacia(por mensaje estetico ya que al validar la longitud esta implicito)</li>
	 * 	<li>No existe el nombre</li>
	 * </ul>
	 * 
	 * @param username String con el nombre de usuario
	 * @return
	 */
	public String validateUsername(String username) {
		if (username == null || username.isEmpty()) {
			return "Debe ingresar un nombre";
		}
		if(username.length() < 4 || username.length() > 50) {
			return "El nombre deusuario debe tener entre 4 y 50 caracteres";
		}
		if(modeloUsuario.get(username) != null) {
			return "El nombre de usuario ya existe";
		}
		return null;
	}
}

package ctrl.adminControlers;

import modelo.Dao.IDAOUsuario;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.arrays.UserArray;
import utils.constants.UserFields;
import vista.admin.VistaBuscar;

/** 
 * Controlador encargado de realizar la busqueda de usuarios
 * 
 * <br>Implementa DocumentListener para actualizacion en tiempo real de los datos ingresados
 * 
 * @see DocumentListener
 * @see VistaBuscar
 */
public class BuscarControler implements DocumentListener{
	private VistaBuscar vista;
	protected IDAOUsuario modeloUsuario;
	
	/** 
	 * Constructor, genera un nuevo controlador con la vista y modelo indicados
	 * 
	 * @param vista VistaBuscar que se se usara para la interaccion
	 * @param modelo Dao de acceso a usuarios
	 */
	public BuscarControler(VistaBuscar vista, IDAOUsuario modelo) {
		this.vista = vista;
		this.modeloUsuario = modelo;
		vista.getSearchTextField().getDocument().addDocumentListener(this);
		//carga los datos iniciales
		onTextChanged();
	}
	
	/** 
	 * Encargado de realizar la busqeuda segun los criterios ingresados en la vista 
	 * <br>setea los resultados en el Jlist
	 */
	public void onTextChanged() {
		Object searchedValue = getSearchValue();
		UserArray data;
		
		//si no hay busqueda definida se crgan todos los datos
		if (searchedValue.equals("")) {
			data = modeloUsuario.getAll();
		}else {
			String criterio = vista.getComparativeMode();
			String modo = vista.getSearchMode();
			data = modeloUsuario.search(searchedValue, criterio, modo);
		}
		
		vista.loadUsers(data.getArregloObjetos());
	}
	
	
	/** 
	 * Se encarga de verificar y convertir el texto a buscar en el valor correspondiente
	 * <br>ya sea int o string
	 * @return Object valor ingresado por el usuario con el tipo de objeto correcto
	 */
	private Object getSearchValue() {
		String criterioFields = vista.getComparativeMode();
		String searchedValue = vista.getSearchTextField().getText();

		//verifica si la busqueda debe ser por entero
		if (criterioFields == UserFields.FIELD_LEVEL || criterioFields == UserFields.FIELD_SCORE) {
			if (isInteger(searchedValue)) {
				return Integer.parseInt(searchedValue);
			}
			//si no es un numero
			return null;
		}
		return searchedValue;
	}
	
	
	/** 
	 * Verifica si un texto es un entero
	 * @param text texto a verificar si es un entero
	 * @return boolean true si el texto contiene un entero, false en caso contrario
	 */
	private boolean isInteger(String text) {
		try {
			Integer.parseInt(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// listeners de cambio en el searchInput, todos los cambios en el input desencadenan la actualizacion
	@Override
	public void insertUpdate(DocumentEvent e) {
		onTextChanged();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		onTextChanged();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		onTextChanged();
	}

}

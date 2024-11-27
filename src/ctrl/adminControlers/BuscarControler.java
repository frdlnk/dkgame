package ctrl.adminControlers;

import modelo.Dao.IDAOUsuario;
import modelo.Dao.IDAOUsuario.UserFields;


import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.arrays.UserArray;
import utils.ComparativeModes;
import vista.admin.VistaBuscar;

/** 
 * Controlador encargado de realizar la busqueda de usuarios
 * 
 * implementa DocumentListener para actualizacion en tiempo real de los datos ingresados
 * 
 * @see DocumentListener
 */
public class BuscarControler implements DocumentListener{
	private VistaBuscar vista;
	protected IDAOUsuario modelo;
	
	/** 
	 * Constructor, genera un nuevo controlador con la vista y modelo indicados
	 * 
	 * @param vista VistaBuscar que se se usara para la interaccion
	 * @param modelo Dao de acceso a usuarios
	 */
	public BuscarControler(VistaBuscar vista, IDAOUsuario modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.getSearchTextField().getDocument().addDocumentListener(this);
		onTextChanged();
	}
	
	/** 
	 * Encargado de realizar la busqeuda segun los criterios ingresados en la vista 
	 * setea los resultados en el Jlist
	 */
	public void onTextChanged() {
		Object searchedValue = getSearchValue();
		UserArray data;
		
		if (searchedValue.equals("")) {
			data = modelo.getAll();
		}else {
			UserFields criterio = vista.getComparativeMode();
			ComparativeModes modo = vista.getSearchMode();
			data = modelo.search(searchedValue, criterio, modo);
		}
		
		vista.loadUsers(data.getArregloObjetos());
	}
	
	
	/** 
	 * Se encarga de verificar y convertir el texto a buscar en el valor correspondiente
	 * ya sea int o string
	 * @return Object valor ingresado por el usuario con el tipo de objeto correcto
	 */
	private Object getSearchValue() {
		UserFields criterioFields = vista.getComparativeMode();
		String searchedValue = vista.getSearchTextField().getText();

		if (criterioFields == UserFields.LEVEL || criterioFields == UserFields.SCORE) {
			if (isInteger(searchedValue)) {
				return Integer.parseInt(searchedValue);
			}
			return -1;
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

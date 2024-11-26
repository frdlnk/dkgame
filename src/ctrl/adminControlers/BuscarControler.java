package ctrl.adminControlers;

import modelo.Dao.IDAOUsuario;
import modelo.Dao.IDAOUsuario.UserFields;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import modelo.arrays.UserArray;
import utils.ComparativeModes;
import vista.admin.VistaBuscar;

public class BuscarControler implements DocumentListener{
	private VistaBuscar vista;
	protected IDAOUsuario modelo;
	
	public BuscarControler(VistaBuscar vista, IDAOUsuario modelo) {
		this.vista = vista;
		this.modelo = modelo;
		vista.getSearchTextField().getDocument().addDocumentListener(this);
		onTextChanged();
	}
	
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

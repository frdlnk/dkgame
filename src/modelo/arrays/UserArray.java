package modelo.arrays;

import java.util.Iterator;

import modelo.Usuario;
import modelo.Dao.IDAOUsuario.UserFields;
import utils.ComparativeModes;

public class UserArray extends Array {
	public UserArray() {
		super(new Usuario[0]);
	}
	
	public UserArray(Usuario[] arreglo) {
		super(arreglo);
	}

	public boolean contains(Usuario object) {
		return super.contains(object);
	}
	
	public Usuario get(String username) {
		Usuario[] users = getArregloObjetos();
		for (int i = 0; i < users.length; i++) {
			if (users[i].getUsername().equals(username)) {
				return users[i];
			}
		}
		return null;
	}

	public void add(Usuario object) {
		super.add(object);
	}

	public void remove(Usuario object) {
		super.remove(object);
	}
	
	public Usuario get(int index) {
		return (Usuario) super.get(index);
	}
	
	public Usuario getById(int id) {
		for (Usuario user : getArregloObjetos()) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	public void setArregloObjetos(Usuario[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

	public Usuario[] getArregloObjetos() {
		Object[] superListObjects = super.getArregloObjetos();
		if (superListObjects instanceof Usuario[]) {
			return (Usuario[]) superListObjects;
		}
		
		Usuario[] listaUsuario = new Usuario[getSize()];
		for (int i = 0; i < listaUsuario.length; i++) {
			if (superListObjects[i] instanceof Usuario) {
				listaUsuario[i] = (Usuario) superListObjects[i];
			}
		}
		setArregloObjetos(listaUsuario);
		return listaUsuario;
	}
	
	public void removeById(int id) {
		Usuario[] users = getArregloObjetos();
		for (int i = 0; i < getSize(); i++) {
			if (users[i].getId() == id) {
				remove(i);
			}
		}
	}
	
	public UserArray searchByString(Object value, UserFields field) {
		UserArray userReturned = new UserArray();
		if (!(value instanceof String)) return userReturned;
		Usuario[] users = getArregloObjetos();
		for (int i = 0; i < getSize(); i++) {
			String userValue = (String) getField(field, users[i]);
			System.out.println(userValue);
			if (userValue.equals(value)) {
				userReturned.add(users[i]);
				return userReturned;
			}
		}
		return userReturned;
	}

	public UserArray searchByInt(Object value, ComparativeModes searchMode, UserFields field) {
		UserArray userReturned = new UserArray();
		if (!(value instanceof Integer)) return userReturned;
		Usuario[] users = getArregloObjetos();
		for (int i = 0; i < getSize(); i++) {
			int userValue = (int) getField(field, users[i]);
			if (compareIntByMode(userValue, (int) value, searchMode)) {
				userReturned.add(users[i]);
			}
		}
		return userReturned;
	}
	
	public UserArray search(Object value, UserFields field, ComparativeModes searchMode) {
		return switch (field) {
		case USERNAME -> searchByString(value, UserFields.USERNAME);
		case LEVEL -> searchByInt(value, searchMode, UserFields.LEVEL);
		case SCORE -> searchByInt(value, searchMode, UserFields.SCORE);
		default -> new UserArray();
		};
	}
	
	private Object getField(UserFields field, Usuario user) {
		return switch (field) {
		case USERNAME -> user.getUsername();
		case LEVEL -> user.getLevel();
		case SCORE -> user.getScore();
		default -> null;
		};
	}
	
	private boolean compareIntByMode(int val1, int val2, ComparativeModes mode) {
		return switch (mode) {
		case MAYOR_QUE -> val1 > val2;
		case MENOR_QUE -> val1 < val2;
		case IGUAL -> val1 == val2;
		default -> false;
		};
	}
	
	public UserArray getBestScores(int cantidadRegistros) {
		UserArray returArray = new UserArray();
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize()-1; j++) {
				if (get(j) == null || get(j).getScore() < get(j+1).getScore()) {
					Usuario tempUsuario = get(j);
					set(j,get(j+1));
					set(j+1, tempUsuario);
				}
			}
		}
		if (cantidadRegistros > getSize()) {
			cantidadRegistros = getSize();
		}
		for (int i = 0; i < cantidadRegistros; i++) {
			returArray.add(get(i));
		}
		return returArray;
	}
	
	@Override
	public void sort() {
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize()-1; j++) {
				if (get(j) == null || get(j).getId() > get(j+1).getId()) {
					Usuario tempUsuario = get(j);
					set(j,get(j+1));
					set(j+1, tempUsuario);
				}
			}
		}
	}
}

package modelo.arrays;

import modelo.UserConfig;
import modelo.Usuario;

public class UserConfigArray extends Array {

	public UserConfigArray() {
		super(new UserConfig[0]);
	}
	
	public UserConfigArray(UserConfig[] arreglo) {
		super(arreglo);
	}

	public boolean contains(UserConfig object) {
		return super.contains(object);
	}

	public void add(UserConfig object) {
		super.add(object);
	}

	public void remove(UserConfig object) {
		super.remove(object);
	}
	
	public UserConfig get(int index) {
		return (UserConfig) super.get(index);
	}

	public void setArregloObjetos(UserConfig[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

	public UserConfig[] getArregloObjetos() {
		UserConfig[] lista = new UserConfig[getSize()];
		Object[] superListObjects = super.getArregloObjetos();
		for (int i = 0; i < lista.length; i++) {
			if (superListObjects[i] instanceof UserConfig) {
				lista[i] = (UserConfig) superListObjects[i];
			}
		}
		return lista;
	}
	
	public UserConfig getById(int id) {
		for (UserConfig userConfig : getArregloObjetos()) {
			if (userConfig.getId() == id) {
				return userConfig;
			}
		}
		return null;
	}
	
	public void removeById(int id) {
		UserConfig[] userConfigs = getArregloObjetos();
		for (int i = 0; i < getSize(); i++) {
			if (userConfigs[i].getId() == id) {
				remove(i);
			}
		}
	}
	
	@Override
	public void sort() {

	}

}

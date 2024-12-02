package modelo.arrays;

import modelo.UserConfig;

public class UserConfigArray extends Array {

	public UserConfigArray() {
		super();
	}

	public UserConfigArray(UserConfig[] arreglo) {
		super(arreglo);
	}

	public boolean contains(UserConfig object) {
		return super.contains(object);
	}

	/**
	 * Anade un string al arreglo
	 * 
	 * @param String a agregar
	 */
	public void add(Object string) {
		if (string instanceof UserConfig) {
			super.addInternal(string);
		}
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

	@Override
	public String toString() {
		return "UserConfigArray []";
	}

}

package modelo.arrays;

import modelo.Usuario;
import motor_v1.motor.util.Vector2D;

/**
 * Array que contiene Strings
 */
public class ArrayString extends Array {
	/**
	 * Crea un nuevo arreglo vacio
	 */
	public ArrayString() {
		super();
	}
	
	/**
	 * Crea un Array a partir de un arreglo de String
	 * @param arreglo
	 */
	public ArrayString(String[] arreglo) {
		super(arreglo);
	}
	
	/**
	 * Anade un string al arreglo
	 * @param String a agregar
	 */
	public void add(Object string) {
		if (string instanceof String) {
			super.addInternal(string);
		}
	}

	/**
	 * elimina el string especificado
	 * @param object string a eliminar
	 */
	public void remove(String object) {
		super.remove(object);
	}
	
	/**
	 * regresa el String en el indice especificado
	 * @param index indice buscado
	 */
	public String get(int index) {
		return (String) super.get(index);
	}

	/**
	 * Setea un nuevo arreglo de Strings
	 * @param arregloObjetos arreglo de string
	 */
	public void setArregloObjetos(String[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

	/**
	 *Regresa el arreglo de Strings almacenados
	 */
	protected String[] getArregloObjetos() {
		String[] lista = new String[getSize()];
		Object[] superListObjects = super.getArregloObjetos();
		for (int i = 0; i < lista.length; i++) {
			if (superListObjects[i] instanceof String) {
				lista[i] = (String) superListObjects[i];
			}
		}
		return lista;
	}

	@Override
	public void sort() {
		
	}
}

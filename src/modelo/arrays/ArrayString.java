package modelo.arrays;

import modelo.Usuario;
import motor_v1.motor.util.Vector2D;

public class ArrayString extends Array {
	public ArrayString() {
		super(new String[0]);
	}
	
	public ArrayString(String[] arreglo) {
		super(arreglo);
	}

	public boolean contains(String object) {
		return super.contains(object);
	}

	public void add(String object) {
		super.add(object);
	}

	public void remove(String object) {
		super.remove(object);
	}
	
	public String get(int index) {
		return (String) super.get(index);
	}

	public void setArregloObjetos(String[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

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

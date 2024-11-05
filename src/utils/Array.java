package utils;

import java.util.Arrays;

public class Array<T> {
	public final static int TAMANO_INICIAL = 10;
	private T[] arregloObjetos;
	private int size;
	
	public Array() {
		this.arregloObjetos = (T[]) new Object[TAMANO_INICIAL];
		size = 0;
	}
	
	public Array(T[] arreglo) {
		arregloObjetos = arreglo;
		size = arreglo.length;
	} 
	
	public void clear() {
		arregloObjetos = (T[]) new Object[TAMANO_INICIAL];
		size = 0;
	}
	
	public boolean contains(T object) {
		T searchedT = get(object);
		if (searchedT != null) {
			return true;
		}
		return false;
	}
	
	private void grow() {
		T[] nuevoArreglo = (T[]) new Object[arregloObjetos.length*2];
		for (int i = 0; i < arregloObjetos.length; i++) {
			nuevoArreglo[i] = arregloObjetos[i];
		}
		arregloObjetos = nuevoArreglo;
	}
	
	public void add(T object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = object;
		size++;
	}
	
	public T pop(T object) {
		for (int i = 0; i < size; i++) {
			T actual = arregloObjetos[i];
			if (actual.equals(arregloObjetos[i])) {
				remove(i);
				return actual;
			}
		}
		return null;
	}
	
	public void remove(T object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arregloObjetos[i])) {
				remove(i);
			}
		}
	}
	
	public void remove(int index) {
		for (int i = index; i < size; i++) {
			arregloObjetos[i] = arregloObjetos[i+1];
		}
		arregloObjetos[size] = null;
		size--;
	}
	
	public T get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arregloObjetos[index];
	}
	
	public T get(T object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arregloObjetos[i])) {
				return arregloObjetos[i];
			}
		}
		return null;
	}

	public T[] getArregloObjetos() {
		return arregloObjetos;
	}

	public void setArregloObjetos(T[] arregloObjetos) {
		this.arregloObjetos = arregloObjetos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Array [arregloObjetos=" + Arrays.toString(arregloObjetos) + ", size=" + size + "]";
	}
	
	
}

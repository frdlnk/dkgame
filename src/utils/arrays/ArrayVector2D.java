package utils.arrays;

import motor_v1.motor.util.Vector2D;

public class ArrayVector2D {
	public final static int TAMANO_INICIAL = 10;
	private Vector2D[] arregloObjetos;
	private int size;
	
	public ArrayVector2D() {
		this.arregloObjetos = new Vector2D[TAMANO_INICIAL];
		size = 0;
	}
	
	public ArrayVector2D(Vector2D[] arreglo) {
		arregloObjetos = arreglo;
		size = arreglo.length;
	} 
	
	public void clear() {
		arregloObjetos = new Vector2D[TAMANO_INICIAL];
		size = 0;
	}
	
	public boolean contains(Vector2D object) {
		Vector2D searchedT = get(object);
		if (searchedT != null) {
			return true;
		}
		return false;
	}
	
	private void grow() {
		Vector2D[] nuevoArreglo = new Vector2D[arregloObjetos.length*2];
		for (int i = 0; i < arregloObjetos.length; i++) {
			nuevoArreglo[i] = arregloObjetos[i];
		}
		arregloObjetos = nuevoArreglo;
	}
	
	public void add(Vector2D object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = object;
		size++;
	}
	
	public void remove(Vector2D object) {
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
	
	public Vector2D get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arregloObjetos[index];
	}
	
	public Vector2D get(Vector2D object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arregloObjetos[i])) {
				return arregloObjetos[i];
			}
		}
		return null;
	}

	public Vector2D[] getArregloObjetos() {
		return arregloObjetos;
	}

	public void setArregloObjetos(Vector2D[] arregloObjetos) {
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
		return "Array [arregloObjetos=" + arregloObjetos + ", size=" + size + "]";
	}
	
	
}

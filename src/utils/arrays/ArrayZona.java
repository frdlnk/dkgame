package utils.arrays;

import vista.zonas.Zona;

public class ArrayZona {
	public final static int TAMANO_INICIAL = 10;
	private Zona[] arregloObjetos;
	private int size;
	
	public ArrayZona() {
		this.arregloObjetos = new Zona[TAMANO_INICIAL];
		size = 0;
	}
	
	public ArrayZona(Zona[] arreglo) {
		arregloObjetos = arreglo;
		size = arreglo.length;
	} 
	
	public void clear() {
		arregloObjetos = new Zona[TAMANO_INICIAL];
		size = 0;
	}
	
	public boolean contains(Zona object) {
		Zona searchedT = get(object);
		if (searchedT != null) {
			return true;
		}
		return false;
	}
	
	private void grow() {
		Zona[] nuevoArreglo = new Zona[arregloObjetos.length*2];
		for (int i = 0; i < arregloObjetos.length; i++) {
			nuevoArreglo[i] = arregloObjetos[i];
		}
		arregloObjetos = nuevoArreglo;
	}
	
	public void add(Zona object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = object;
		size++;
	}
	
	
	public void remove(Zona object) {
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
	
	public Zona get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arregloObjetos[index];
	}
	
	public Zona get(Zona object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arregloObjetos[i])) {
				return arregloObjetos[i];
			}
		}
		return null;
	}

	public Zona[] getArregloObjetos() {
		return arregloObjetos;
	}

	public void setArregloObjetos(Zona[] arregloObjetos) {
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

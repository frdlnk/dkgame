package utils.arrays;


public class ArrayString {
	public final static int TAMANO_INICIAL = 10;
	private String[] arregloObjetos;
	private int size;
	
	public ArrayString() {
		this.arregloObjetos = new String[TAMANO_INICIAL];
		size = 0;
	}
	
	public ArrayString(String[] arreglo) {
		arregloObjetos = arreglo;
		size = arreglo.length;
	} 
	
	public void clear() {
		arregloObjetos = new String[TAMANO_INICIAL];
		size = 0;
	}
	
	public boolean contains(String object) {
		String searchedT = get(object);
		if (searchedT != null) {
			return true;
		}
		return false;
	}
	
	private void grow() {
		String[] nuevoArreglo = new String[arregloObjetos.length*2];
		for (int i = 0; i < arregloObjetos.length; i++) {
			nuevoArreglo[i] = arregloObjetos[i];
		}
		arregloObjetos = nuevoArreglo;
	}
	
	public void add(String object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = object;
		size++;
	}
	
	
	public void remove(String object) {
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
	
	public String get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arregloObjetos[index];
	}
	
	public String get(String object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arregloObjetos[i])) {
				return arregloObjetos[i];
			}
		}
		return null;
	}

	public String[] getArregloObjetos() {
		return arregloObjetos;
	}

	public void setArregloObjetos(String[] arregloObjetos) {
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

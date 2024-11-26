package modelo.arrays;

public abstract class Array {

	public static final int TAMANO_INICIAL = 10;
	protected Object[] arreglo;
	protected int size;

	public Array(Object[] arreglo) {
		this.arreglo = arreglo;
		this.size = arreglo.length;
	}

	public void clear() {
		arreglo = new Object[TAMANO_INICIAL];
		size = 0;
	}

	protected boolean contains(Object object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arreglo[i])) {
				return true;
			}
		}
		return false;
	}

	public abstract void sort();
	
	private void grow() {
		Object[] nuevoArreglo = new Object[arreglo.length*2+1];
		for (int i = 0; i < arreglo.length; i++) {
			nuevoArreglo[i] = arreglo[i];
		}
		arreglo = nuevoArreglo;
	}
	
	public void add(Object object) {
		if (size == arreglo.length) {
			grow();
		}
		arreglo[size] = object;
		size++;
	}
	
	public void set(int index, Object value) {
		if (index < getSize()) {
			arreglo[index] = value;
		}
	}

	protected void remove(Object object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arreglo[i])) {
				remove(i);
			}
		}
	}

	public void remove(int index) {
		for (int i = index; i < size-1; i++) {
			arreglo[i] = arreglo[i+1];
		}
		size--;
		arreglo[size] = null;
	}

	protected Object get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arreglo[index];
	}

	protected Object[] getArregloObjetos() {
		return arreglo;
	}

	protected void setArregloObjetos(Object[] arregloObjetos) {
		this.arreglo = arregloObjetos;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Array [arregloObjetos=" + arreglo + ", size=" + size + "]";
	}

}
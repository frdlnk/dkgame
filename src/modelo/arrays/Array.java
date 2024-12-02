package modelo.arrays;


/**
 * Clase encargada de logica basica para el guaradado de objetos
 * 
 * @implNote esta clase podria ser usada con Genericos<T> para un mejor manejo
 * <br> no se usan por recomendsacion del profesor de no usar sintaxis no vista en clase
 */
public abstract class Array {

	public static final int TAMANO_INICIAL = 10;
	protected Object[] arreglo;
	protected int size;

	/**
	 * Crea un Array apartir de un arreglo cualquiera
	 * @param arreglo
	 */
	public Array(Object[] arreglo) {
		this.arreglo = arreglo;
		this.size = arreglo.length;
	}
	
	
	/**
	 * Crea un nuevo arreglo vacio
	 */
	public Array() {
		this.arreglo = new Object[TAMANO_INICIAL];
		this.size = 0;
	}

	/**
	 * limpia todos los registros
	 */
	public void clear() {
		arreglo = new Object[TAMANO_INICIAL];
		size = 0;
	}

	/**
	 * verifica que el objeto existe dentro del arreglo
	 * @param object objeto buscado
	 * @return true si existe en el Array, false si no
	 */
	public boolean contains(Object object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arreglo[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Ordena el arreglo de forma predeterminada al objeto asociado
	 */
	public abstract void sort();
	
	/**
	 * aumenta el tamano del arreglo fijo para incrementar su capacidad
	 */
	private void grow() {
		//+1 siu el arreglo tiene longitud 0
		Object[] nuevoArreglo = new Object[arreglo.length*2+1];
		for (int i = 0; i < arreglo.length; i++) {
			nuevoArreglo[i] = arreglo[i];
		}
		arreglo = nuevoArreglo;
	}
	
	/**
	 * agrega un objeto al final del Array
	 * @param object Objeto a agregar
	 */
	protected void addInternal(Object object) {
		if (size == arreglo.length) {
			grow();
		}
		arreglo[size] = object;
		size++;
	}
	
	public abstract void add(Object object);
	
	/**
	 * Setea el indice especificado con el objeto dado
	 * @param index indice a setear
	 * @param value objeto a setear
	 */
	protected void set(int index, Object value) {
		if (index < getSize()) {
			arreglo[index] = value;
		}
	}

	/**
	 * elimina el objeto especificado del arreglo
	 * @param object objeto a eliminar
	 */
	public void remove(Object object) {
		for (int i = 0; i < size; i++) {
			if (object.equals(arreglo[i])) {
				remove(i);
			}
		}
	}

	/**
	 * elimina el objeto en el indice indicado
	 * @param index indice del objeto a eliminar
	 */
	public void remove(int index) {
		for (int i = index; i < size-1; i++) {
			arreglo[i] = arreglo[i+1];
		}
		size--;
		arreglo[size] = null;
	}

	/**
	 * Regresa el objeto en la posicion dada
	 * @param index indice buscado
	 * @return objeto en la posicion especificada
	 */
	protected Object get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return arreglo[index];
	}

	//getters y setters
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
		return "Array [arreglo=" + arreglo + ", size=" + size + "]";
	}


	public Object[] getArreglo() {
		return arreglo;
	}


	public void setArreglo(Object[] arreglo) {
		this.arreglo = arreglo;
	}

	
}
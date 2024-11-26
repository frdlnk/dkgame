package modelo.arrays;

import motor_v1.motor.util.Vector2D;

public class ArrayVector2D extends Array {
	public ArrayVector2D() {
		super(new Vector2D[0]);
	}
	
	public ArrayVector2D(Vector2D[] arreglo) {
		super(arreglo);
	}

	public boolean contains(Vector2D object) {
		return super.contains(object);
	}

	public void add(Vector2D object) {
		super.add(object);
	}

	public void remove(Vector2D object) {
		super.remove(object);
	}
	
	public Vector2D get(int index) {
		return (Vector2D) super.get(index);
	}

	public void setArregloObjetos(Vector2D[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

	protected Vector2D[] getArregloObjetos() {
		Vector2D[] lista = new Vector2D[getSize()];
		Object[] superListObjects = super.getArregloObjetos();
		for (int i = 0; i < lista.length; i++) {
			if (superListObjects[i] instanceof Vector2D) {
				lista[i] = (Vector2D) superListObjects[i];
			}
		}
		return lista;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
}

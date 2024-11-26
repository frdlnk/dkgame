package modelo.arrays;

import vista.zonas.Zona;
import vista.zonas.Zona1N1;

public class ArrayZona extends Array {
	
	public ArrayZona() {
		super(new Zona1N1[0]);
	}
	
	public ArrayZona(Zona[] arreglo) {
		super(arreglo);
	} 

	public boolean contains(Zona object) {
		return super.contains(object);
	}

	public void add(Zona object) {
		super.add(object);
	}

	public void remove(Zona object) {
		super.remove(object);
	}
	
	public Zona get(int index) {
		return (Zona) super.get(index);
	}

	public void setArregloObjetos(Zona[] arregloObjetos) {
		super.setArregloObjetos(arregloObjetos);
	}

	protected Zona[] getArregloObjetos() {
		Zona[] lista = new Zona[getSize()];
		Object[] superListObjects = super.getArregloObjetos();
		for (int i = 0; i < lista.length; i++) {
			if (superListObjects[i] instanceof Zona) {
				lista[i] = (Zona) superListObjects[i];
			}
		}
		return lista;
	}

	@Override
	public void sort() {
		// TODO Auto-generated method stub
		
	}
}
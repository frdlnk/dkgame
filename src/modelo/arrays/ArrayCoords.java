package modelo.arrays;

import java.awt.image.BufferedImage;

public class ArrayCoords {

	public final static int TAMANO_INICIAL = 10;
	private Object[][] arregloObjetos;
	private int size;

	public ArrayCoords() {
		this.arregloObjetos = new Object[TAMANO_INICIAL][1];
		size = 0;

	}

	public int size() {
		return size;
	}

	private void grow() {
		Object[][] nuevoArreglo = new Object[arregloObjetos.length * 2][1];
		for (int i = 0; i < arregloObjetos.length; i++) {
			nuevoArreglo[i] = arregloObjetos[i];
		}
		arregloObjetos = nuevoArreglo;
	}

	public void put(String tag, int[] object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = new Object[] { tag, object };
		size++;
	}

	public void putImage(String tag, BufferedImage[] object) {
		if (size == arregloObjetos.length) {
			grow();
		}
		arregloObjetos[size] = new Object[] { tag, object };
		size++;
	}

	public void remove(int index) {
		for (int i = index; i < size; i++) {
			arregloObjetos[i] = arregloObjetos[i + 1];
		}
		arregloObjetos[size] = null;
		size--;
	}

	public int[] get(int index) {
		if (index >= size || index < 0) {
			return null;
		}
		return (int[]) arregloObjetos[index][2];
	}

	public int[] get(String index) {
		for (Object[] obj : arregloObjetos) {
			if (obj[0].toString().equals(index)) {
				return (int[]) obj[1];
			}
		}
		return null;
	}

	public BufferedImage[] getImages(String res) {
		for (Object[] obj : arregloObjetos) {
			if (obj[0].toString().equals(res)) {
				return (BufferedImage[]) obj[1];
			}
		}
		return null;
	}

	public BufferedImage getImage(String res, int indic) {
		for (Object[] obj : arregloObjetos) {
			if (obj[0].toString().equals(res)) {
				BufferedImage[] bufferedImages = getImages((String) obj[0]);
				return bufferedImages[indic];
			}
		}
		return null;
	}

	public Object[][] getArregloObjetos() {
		return arregloObjetos;
	}

	public void setArregloObjetos(Object[][] arregloObjetos) {
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
		return "ArrayCoords [arregloObjetos=" + arregloObjetos + ", size=" + size + "]";
	}

}

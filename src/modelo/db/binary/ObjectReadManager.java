package modelo.db.binary;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import modelo.arrays.Array;

/**
 * Objeto encargado de la lectura de archivos binarios
 */
public class ObjectReadManager implements AutoCloseable {
	private FileInputStream in;
	private ObjectInputStream reader;

	/**
	 * Crea un nuevo objeto lector <br>
	 * si el archivo especificado no existe lo crea
	 * 
	 * @param fileName ruta al archivo
	 * @throws IOException si hay problemas en la creacion o apertura del archivo
	 */
	public ObjectReadManager(String fileName) throws IOException {
		File file = new File(fileName);

		if (!file.exists()) {
			file.createNewFile();
		}

		in = new FileInputStream(file);
		if (in.available() > 0) {
			reader = new ObjectInputStream(in);
		}
	}

	@Override
	public void close() throws Exception {
		if (in != null) {
			in.close();
		}
		if (reader != null) {
			reader.close();
		}
	}

	/**
	 * Lee un registro del archivo
	 * 
	 * @return el resgistro encontrado
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Object read() throws ClassNotFoundException, IOException {
		if (isReaderAvaible()) {
			try {
				return reader.readObject();
			} catch (EOFException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Verifica que hay datos disponibles para leer
	 * 
	 * @return true si hay datos disponibles
	 * @throws IOException
	 */
	private boolean isReaderAvaible() throws IOException {
		if (in.available() > 0) {
			if (reader == null) {
				reader = new ObjectInputStream(in);
			}
			return true;
		}
		return false;
	}

	/**
	 * lee todos los registros del archivo
	 * 
	 * @param arreglo arreglo para agregar los registros
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void readAll(Array arreglo) throws ClassNotFoundException, IOException {
		Object objeto;
		while ((objeto = read()) != null) {
			arreglo.add(objeto);
		}
	}

	public FileInputStream getIn() {
		return in;
	}

	public void setIn(FileInputStream in) {
		this.in = in;
	}

	public ObjectInputStream getReader() {
		return reader;
	}

	public void setReader(ObjectInputStream reader) {
		this.reader = reader;
	}

	@Override
	public String toString() {
		return "ObjectReadManager [in=" + in + ", reader=" + reader + "]";
	}

}

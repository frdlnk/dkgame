package modelo.db.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import modelo.arrays.ArrayString;

/**
 * Clase encragada de la lectura de registros de un archivo de texto
 */
public class ObjectReadManager implements AutoCloseable{
	private FileReader in;
	private BufferedReader reader;

	/**
	 * Crea un nuevo objeto lector, del archivo especificado
	 * <br> si no existe lo crea
	 * @param fileName ruta al archivo de datos
	 * @throws IOException si hay algun problema con el archivo
	 */
	public ObjectReadManager(String fileName) throws IOException {
		File file = new File(fileName);
		if (!file.exists()) file.createNewFile();
		
		in = new FileReader(file);
		reader = new BufferedReader(in);
	}

	@Override
	public void close() throws Exception {
		in.close();
		reader.close();
	}
	
	/**
	 * Lee un registro del archivo
	 * @return registro leido
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public String read() throws ClassNotFoundException, IOException {
		return reader.readLine();
	} 
	
	/**
	 * Lee todos los registros del archivo
	 * @param arreglo arreglo para almacenar los registros especificados
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void readAll(ArrayString arreglo) throws ClassNotFoundException, IOException {
		String objeto;
		while ((objeto = read()) != null) {
			arreglo.add(objeto);
		}
	}
}

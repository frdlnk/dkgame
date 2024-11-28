package modelo.db.binary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Clase encragda de guardado de objetos serializables en archivos binarios
 */
public class ObjectFileWriter implements AutoCloseable{
	
	private ObjectOutputStream writer;
	private FileOutputStream out;
	private String fileName;

	/**
	 * Crea un nuevo escritor que usara el archivo especificado
	 * <br>si el archivo no existe lo crea
	 * @implNote el escritor elimina la informacion del archivo al crearse, guarade los datos antes de escribir nuevamente
	 * @param fileName ruta al archivo de datos
	 * @throws IOException si existen errores con el archivo
	 */
	public ObjectFileWriter(String fileName) throws IOException {
		this.fileName = fileName;
		out = new FileOutputStream(fileName);
		writer = new ObjectOutputStream(out);
	}
	
	/**
	 * Escribe un objeto en el archivo
	 * @param objSer Objeto serializable a escribir
	 * @throws IOException si hay errores con la escritura del archivo
	 */
	public void write(Serializable objSer) throws IOException {
		writer.writeObject(objSer);
	}
	
	/**
	 * escribe varios objetos en el archivo
	 * @param serObjs objetos a escribir
	 * @throws IOException si hay errores en la escritura
	 */
	public void writeMany(Serializable[] serObjs) throws IOException {
		for (Serializable object : serObjs) {
			write(object);
		}
	}
	
	/**
	 * Reemplaza todos los registros del archivo con nuevos
	 * @param serObjs Objetos a escribir
	 * @throws IOException si hay errores en la escritura
	 */
	public void replaceAll(Serializable[] serObjs) throws IOException {
		if (serObjs != null) {
			out = new FileOutputStream(fileName);
			writer = new ObjectOutputStream(out);
			for (Serializable serializable : serObjs) {
				if (serializable != null) {
					writer.writeObject(serializable);
				}
			}
		}
	}

	/**
	 * Libera los recursos
	 */
	@Override
	public void close() throws IOException {
		writer.close();
	}
}

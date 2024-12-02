package modelo.db.text;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import modelo.arrays.ArrayString;

/**
 * Clase escritura de objetos en archivos de texto
 */
public class ObjectFileWriter implements AutoCloseable{
	
	private BufferedWriter writer;
	private String filename;

	/**
	 * Crea un nuevo escritor en el archivo especificado
	 * @param fileName ruta al archivo de datos
	 * @throws IOException
	 */
	public ObjectFileWriter(String fileName) throws IOException {
		this.filename = fileName;
		FileWriter out = new FileWriter(fileName,true);
		writer = new BufferedWriter(out);
	}
	
	/**
	 * escribe un registro(linea) en el archivo
	 * @param registro linea a agregar
	 * @throws IOException
	 */
	public void write(String registro) throws IOException {
		writer.newLine();
		registro = registro.replace("\n", "\\n");
		writer.write(registro);
		writer.flush();
	}
	
	/**
	 * Escribe multiples registros(lineas) en el archivo
	 * @param registros
	 * @throws IOException
	 */
	public void writeMany(ArrayString registros) throws IOException {
		for (int i = 0; i < registros.getSize(); i++) {
			String registro = registros.get(i);
			if (registro != null)
				write(registro);
		}
	}
	
	/**
	 * Reemplaza todos los resgistros(lineas) del archivo con nuevas
	 * @param registros registros a insertar
	 * @throws IOException
	 */
	public void replace(ArrayString registros) throws IOException {
		FileWriter out = new FileWriter(filename);
		writer = new BufferedWriter(out);
		writeMany(registros);
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "ObjectFileWriter [writer=" + writer + ", filename=" + filename + "]";
	}
	
	
}

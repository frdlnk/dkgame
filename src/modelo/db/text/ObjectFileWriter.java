package modelo.db.text;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import modelo.arrays.ArrayString;

public class ObjectFileWriter implements AutoCloseable{
	
	private BufferedWriter writer;
	private String filename;

	public ObjectFileWriter(String fileName) throws IOException {
		this.filename = fileName;
		FileWriter out = new FileWriter(fileName,true);
		writer = new BufferedWriter(out);
	}
	
	public void write(String registro) throws IOException {
		writer.newLine();
		writer.write(registro);
		writer.flush();
	}
	
	public void writeMany(ArrayString registros) throws IOException {
		for (int i = 0; i < registros.getSize(); i++) {
			String registro = registros.get(i);
			if (registro != null)
				write(registro);
		}
	}
	
	public void replace(ArrayString registros) throws IOException {
		FileWriter out = new FileWriter(filename);
		writer = new BufferedWriter(out);
		writeMany(registros);
	}

	@Override
	public void close() throws IOException {
		writer.close();
	}
}

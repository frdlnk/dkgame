package modelo.db.binary;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import modelo.arrays.Array;

public class ObjectReadManager implements AutoCloseable{
	private FileInputStream in;
	private ObjectInputStream reader;

	public ObjectReadManager(String fileName) throws IOException {
		File file = new File(fileName);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		
		in = new FileInputStream(file);
		if (in.available()>0) {
			reader = new ObjectInputStream(in);
		}
	}

	@Override
	public void close() throws Exception {
		in.close();
		reader.close();
	}
	
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
	
	private boolean isReaderAvaible() throws IOException {
		if (in.available() > 0) {
			if (reader == null) {
				reader = new ObjectInputStream(in);
			}
			return true;
		}
		return false;
	}
	
	public void readAll(Array arreglo) throws ClassNotFoundException, IOException {
		Object objeto;
		while ((objeto = read()) != null) {
			arreglo.add(objeto);
		}
	}
}

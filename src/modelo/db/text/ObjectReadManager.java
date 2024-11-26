package modelo.db.text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import modelo.arrays.Array;

public class ObjectReadManager implements AutoCloseable{
	private FileReader in;
	private BufferedReader reader;

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
	
	public String read() throws ClassNotFoundException, IOException {
		return reader.readLine();
	} 
	
	public void readAll(Array arreglo) throws ClassNotFoundException, IOException {
		Object objeto;
		while ((objeto = read()) != null) {
			arreglo.add(objeto);
		}
	}
}

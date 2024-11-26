package modelo.db.binary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectFileWriter implements AutoCloseable{
	
	private ObjectOutputStream writer;
	private FileOutputStream out;
	private String fileName;

	public ObjectFileWriter(String fileName) throws IOException {
		this.fileName = fileName;
		out = new FileOutputStream(fileName);
		writer = new ObjectOutputStream(out);
	}
	
	public void write(Serializable objSer) throws IOException {
		if (writer == null) {
			writer = new ObjectOutputStream(out);
		}
		writer.writeObject(objSer);
	}
	
	public void writeMany(Serializable[] serObjs) throws IOException {
		for (Serializable object : serObjs) {
			write(object);
		}
	}
	
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

	@Override
	public void close() throws IOException {
		writer.close();
	}
}

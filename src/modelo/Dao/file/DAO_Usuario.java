package modelo.Dao.file;


import modelo.Usuario;
import modelo.Dao.IDAOUsuario;
import modelo.arrays.ArrayString;
import modelo.arrays.UserArray;
import modelo.db.text.ObjectFileWriter;
import modelo.db.text.ObjectReadManager;
import utils.ComparativeModes;

public class DAO_Usuario implements IDAOUsuario {
	
	public DAO_Usuario() {
		load();
	}
	
	public void load() {
		try (ObjectReadManager reader = new ObjectReadManager(fileName)){
			ArrayString records = new ArrayString();
			reader.readAll(records);
			for (int i = 0; i < records.getSize(); i++) {
				Usuario user = Usuario.deserializeUsuario(records.get(i));
				if (user != null) 
					lista.add(user);
			}
		} catch (Exception e) {System.err.println("Error al leer usuarios");}
	}
	
	public void saveAll() {
		try (ObjectFileWriter writer = new ObjectFileWriter(fileName)){
			ArrayString recordsArray = new ArrayString();
			for (Usuario user : lista.getArregloObjetos()) {
				if (user != null) {
					recordsArray.add(user.crearRegistroTexto());
				}
			}
			writer.replace(recordsArray);
		} catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public int insert(Usuario usuario) {
		//si tiene id 0 se le asigna un id autoincrementable
		if (isIdValid(usuario)) {
			lista.add(usuario);
			saveAll();
			return usuario.getId();
		}
		return -1;
	}
	
	private boolean isIdValid(Usuario user) {
		if (user == null)return false;
		
		//id autoincremetable
		if (user.getId() == 0) {
			lista.sort();
			int nextId = 1;
			if (lista.getSize() != 0)
				nextId = lista.get(lista.getSize()-1).getId()+1;
				
			user.setId(nextId);
			return true;
		}
		
		//si intenta insertar con un id establecido verifica que no exista
		return lista.getById(user.getId()) != null;
	}

	@Override
	public void delete(int id) {
		lista.removeById(id);
		saveAll();
	}
	
	@Override
	public void delete(Usuario user) {
		lista.remove(user);
		saveAll();
	}

	@Override
	public Usuario get(int id) {
		return lista.getById(id);
	}

	@Override
	public void update(Usuario usuario) {
		Usuario userToUpdate = lista.getById(usuario.getId());
		userToUpdate.setLevel(usuario.getLevel());
		userToUpdate.setPassword(usuario.getPassword());
		userToUpdate.setScore(usuario.getScore());
		userToUpdate.setUsername(usuario.getUsername());
		saveAll();
	}

	@Override
	public UserArray getAll() {
		return lista;
	}

	@Override
	public UserArray search(Object value, UserFields field, ComparativeModes searchMode) {
		return lista.search(value, field, searchMode);
	}

	@Override
	public Usuario get(String username) {
		return lista.get(username);
	}

	@Override
	public UserArray getBestScores(int cantidadRegistros) {
		return lista.getBestScores(cantidadRegistros);
	}



}

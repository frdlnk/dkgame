package modelo.Dao.file;

import java.io.IOException;

import modelo.UserConfig;
import modelo.Dao.IDAOUserConfigs;
import modelo.arrays.UserConfigArray;
import modelo.db.binary.ObjectFileWriter;
import modelo.db.binary.ObjectReadManager;

/**
 * DAO de acceso a las configuraciones de usuario, mediante archivo binario
 */
public class DAO_UserConfig implements IDAOUserConfigs {
	String fileName = "userConfig.data";
	/**
	 * Crea un nuevo DAO
	 * @throws IOException si el archivo se encuentra corrupto o no se pued3e abrir
	 * @throws ClassNotFoundException si {@link UserConfig} no se encuentra en los recursos del sistema
	 */
	public DAO_UserConfig() throws IOException, ClassNotFoundException {
		load();
	}
	
	/**
	 * Carga los datos en el dataset
	 * @throws ClassNotFoundException si {@link UserConfig} no se encuentra en los recursos del sistema
	 * @throws IOException si existe problemas con el archivo
	 */
	public void load() throws ClassNotFoundException, IOException {
		try (ObjectReadManager reader = new ObjectReadManager(fileName)){
			reader.readAll(lista);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Guarad todos los registros en el archivo
	 */
	public void saveAll() {
		try (ObjectFileWriter writer = new ObjectFileWriter(fileName)){
			writer.replaceAll(lista.getArregloObjetos());
		} catch (Exception e) {
		}
		
	}
	
	@Override
	public int insert(UserConfig config) {
		//si tiene id cero se le asigna un id autoincrementable
		if (isIdValid(config)) {
			lista.add(config);
			saveAll();
			return config.getId();
		}else {
			System.err.println("Id ya existente");
		}
		return -1;
	}
	
	/**
	 * verifica que el id no exista y asigna uno consecutivo si no tiene id
	 * @param config configuracion a verificar
	 * @return
	 */
	private boolean isIdValid(UserConfig config) {
		//id autoincremetable
		if (config.getId() == 0) {
			lista.sort();
			int nextId = 1;
			if (lista.getSize() != 0)
				nextId = lista.get(lista.getSize()-1).getId()+1;
				
			config.setId(nextId);
			return true;
		}
		//si intenta insertar con un id establecido verifica que no exista
		return lista.getById(config.getId()) != null;
	}

	@Override
	public void delete(int id) {
		lista.removeById(id);
		saveAll();
	}
	
	@Override
	public void delete(UserConfig config) {
		lista.remove(config);
		saveAll();
	}

	@Override
	public UserConfig get(int id) {
		return lista.getById(id);
	}

	@Override
	public void update(UserConfig config) {
		UserConfig configToUpdate = lista.getById(config.getId());
		configToUpdate.setArmainicial(config.getArmainicial());
		configToUpdate.setEnemigosActivos(config.getEnemigosActivos());
		configToUpdate.setMultiplicadorDano(config.getMultiplicadorDano());
		configToUpdate.setMultiplicadorDanoEnemigo(config.getMultiplicadorDanoEnemigo());
		configToUpdate.setVidasIniciales(config.getVidasIniciales());
		saveAll();
	}

	@Override
	public UserConfigArray getAll() {
		return lista;
	}

	
}

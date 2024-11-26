package modelo.Dao;

import ctrl.Main;
import modelo.UserConfig;
import modelo.Usuario;
import modelo.arrays.UserArray;
import modelo.arrays.UserConfigArray;

public interface IDAOUserConfigs {
	String fileName = "userConfig.data";
	UserConfigArray lista = Main.UserConfigsSet;
	public int insert(UserConfig usuario);
	public void delete(int id);
	public void delete(UserConfig user);
	public UserConfig get(int id);
	public UserConfigArray getAll();
	public void update(UserConfig usuario);
}

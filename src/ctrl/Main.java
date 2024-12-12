package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.UIManager;

import modelo.UserConfig;
import modelo.DBModel;
import modelo.User;
import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import vista.MenuInicio;

public class Main {
	// datasets
	public final static ArrayList<User> UserDataSet = new ArrayList<>();
	public final static ArrayList<UserConfig> UserConfigsSet = new ArrayList<>();

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		setUIManager();
		DAO_Usuario modeloUsuario = new DAO_Usuario();
		DAO_UserConfig modeloConfigs = new DAO_UserConfig();
		MenuInicio menu = new MenuInicio();
		// iniciar
		new MenuInicioControler(menu, modeloUsuario, modeloConfigs);

	}

	public static void setUIManager() {
		// busca el manger de UI de cuerdo al sistema oprerativo y no como undefined
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

package ctrl;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import modelo.Dao.file.DAO_UserConfig;
import modelo.Dao.file.DAO_Usuario;
import modelo.arrays.UserArray;
import modelo.arrays.UserConfigArray;


public class Main {
	public final static UserArray UserDataSet = new UserArray();
	public final static UserConfigArray UserConfigsSet = new UserConfigArray();
	
    public static void main(String[] args) throws ClassNotFoundException, IOException {
    	setUIManager();
    	DAO_Usuario modeloUsuario = new DAO_Usuario();
    	DAO_UserConfig modeloConfigs = new DAO_UserConfig();
    	MenuInicioControler menuControler = new MenuInicioControler(modeloUsuario, modeloConfigs);
    	for (int i = 0; i < UserDataSet.getSize(); i++) {
			System.out.println(UserDataSet.get(i));
		}
    }

    public static void setUIManager() {
    	//busca el manger de UI de cuerdo al sistema oprerativo y no como undefined
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

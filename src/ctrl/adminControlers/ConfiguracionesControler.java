package ctrl.adminControlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import vista.admin.VistaConfiguracionesUsuario;

public class ConfiguracionesControler extends BuscarControler implements ActionListener{
	private IDAOUserConfigs modeloConfigs;

	public ConfiguracionesControler(VistaConfiguracionesUsuario vista, IDAOUsuario modelo, IDAOUserConfigs modeloConfigs) {
		super(vista, modelo);
		this.modeloConfigs = modeloConfigs;
		
		vista.getBtnSave().addActionListener(this);
		vista.getBtnCancelar().addActionListener(this);
		
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}

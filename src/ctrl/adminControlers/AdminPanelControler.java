package ctrl.adminControlers;

import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.Dao.file.DAO_UserConfig;
import vista.admin.AdminPanel;
import vista.admin.VistaCrear;
import vista.admin.VistaDetalles;
import vista.admin.VistaActualizar;
import vista.admin.VistaBuscar;

public class AdminPanelControler {
	private AdminPanel vista;
	private IDAOUserConfigs modeloConfigs;
	private IDAOUsuario modeloUsers;

	public AdminPanelControler(AdminPanel vista, IDAOUserConfigs modeloConfigs, IDAOUsuario modeloUsers) {
		this.vista = vista;
		this.modeloConfigs = modeloConfigs;
		this.modeloUsers = modeloUsers;
		vista.getBtnActualizar().addActionListener(e -> openActualizar());
		vista.getBtnAgregar().addActionListener(e -> openAgregar());
		vista.getBtnBuscar().addActionListener(e -> openBuscar());
		vista.getBtnEliminar().addActionListener(e -> openEliminar());
		vista.setVisible(true);
	}
	
	private void openActualizar() {
		VistaActualizar vistaActualizar = new VistaActualizar(vista);
		new ActualizarControler(vistaActualizar, modeloUsers);
	}
	private void openBuscar() {
		VistaDetalles vistaBuscar = new VistaDetalles(vista);
		new DetallesControler(vistaBuscar, modeloUsers);
	}
	private void openEliminar() {
		
	}
	private void openAgregar() {
		VistaCrear vistaAgregar = new VistaCrear(vista);
		new CreateControler(vistaAgregar, modeloConfigs, modeloUsers);
	}
}

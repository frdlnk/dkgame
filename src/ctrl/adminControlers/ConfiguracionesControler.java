package ctrl.adminControlers;

import java.awt.event.ActionEvent;

import modelo.UserConfig;
import modelo.Dao.IDAOUserConfigs;
import modelo.Dao.IDAOUsuario;
import modelo.arrays.ArrayString;
import utils.constants.EnemyTypes;
import vista.admin.VistaConfiguracionesUsuario;

public class ConfiguracionesControler extends BuscarControler {
	private IDAOUserConfigs modeloConfigs;
	private VistaConfiguracionesUsuario vistaConf;

	public ConfiguracionesControler(VistaConfiguracionesUsuario vista, IDAOUsuario modelo,
			IDAOUserConfigs modeloConfigs) {
		super(vista, modelo);
		this.modeloConfigs = modeloConfigs;
		vistaConf = vista;

		vista.getBtnSave().addActionListener(this);
		vista.getBtnCancelar().addActionListener(this);
		vista.getBtnSelecionar().addActionListener(this);

		vista.setVisible(true);
	}

	private void mostrarBusquedaPanel() {
		vistaConf.changeToBuscarPanel();
	}

	private void guardarConfiguracion() {
		int idConfig = vistaConf.getListaUsuarios().getSelectedValue().getConfigId();
		UserConfig configuracion = modeloConfigs.get(idConfig);

		configuracion.setArmainicial(getArmaInicial());
		configuracion.setEnemigosActivos(getEnemigosActivos());
		configuracion.setMultiplicadorDano(getMultiplicadorDanoJugador());
		configuracion.setMultiplicadorDanoEnemigo(getMultiplicadorDanoEnemigo());
		configuracion.setVidasIniciales(getVidasIniciales());

		modeloConfigs.update(configuracion);
		vistaConf.dispose();
	}

	private int getVidasIniciales() {
		return (int) vistaConf.getSpVidasIniciales().getValue();
	}

	private double getMultiplicadorDanoJugador() {
		return (double) vistaConf.getSpMultiplicadorDanoJugador().getValue();
	}

	private double getMultiplicadorDanoEnemigo() {
		return (double) vistaConf.getSpMultiplicadorDanoEnemigos().getValue();
	}

	private String[] getEnemigosActivos() {
		ArrayString enemiesActive = new ArrayString();
		boolean helicopterActive = vistaConf.getChBoxHelicopteros().isSelected();
		boolean granaderoActive = vistaConf.getChBoxGranaderos().isSelected();
		boolean pistoleroActive = vistaConf.getChBoxPistoleros().isSelected();
		if (pistoleroActive) {
			enemiesActive.add(EnemyTypes.PISTOLERO);
		}
		if (granaderoActive) {
			enemiesActive.add(EnemyTypes.GRANADERO);
		}
		if (helicopterActive) {
			enemiesActive.add(EnemyTypes.HELICOPTERO);
		}
		return enemiesActive.getArregloObjetos();
	}

	private String getArmaInicial() {
		return vistaConf.getcBoxArmaInicial().getSelectedItem().toString();
	}

	private void mostrarConfigPanel() {
		int id = vistaConf.getListaUsuarios().getSelectedValue().getConfigId();
		UserConfig configuracion = modeloConfigs.get(id);

		vistaConf.changeToConfigPanel(configuracion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(vistaConf.getBtnCancelar())) {
			mostrarBusquedaPanel();
		} else if (source.equals(vistaConf.getBtnSave())) {
			guardarConfiguracion();
		} else if (source.equals(vistaConf.getBtnSelecionar())) {
			mostrarConfigPanel();
		}
	}

	public IDAOUserConfigs getModeloConfigs() {
		return modeloConfigs;
	}

	public void setModeloConfigs(IDAOUserConfigs modeloConfigs) {
		this.modeloConfigs = modeloConfigs;
	}

	public VistaConfiguracionesUsuario getVistaConf() {
		return vistaConf;
	}

	public void setVistaConf(VistaConfiguracionesUsuario vistaConf) {
		this.vistaConf = vistaConf;
	}

	@Override
	public String toString() {
		return "ConfiguracionesControler [modeloConfigs=" + modeloConfigs + ", vistaConf=" + vistaConf + "]";
	}

}

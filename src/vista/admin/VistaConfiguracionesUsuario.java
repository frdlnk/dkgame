package vista.admin;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import modelo.UserConfig;
import utils.constants.ArmasDisponibles;
import utils.constants.Colors;
import utils.constants.EnemyTypes;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import vista.components.Button;

/**
 * Vista encargada de mostrar y selecionar las configuraciones para un usuario
 */
public class VistaConfiguracionesUsuario extends VistaBuscar {

	private static final long serialVersionUID = 1L;
	private final JPanel configPanel = new JPanel();
	private JCheckBox chBoxHelicopteros;
	private JCheckBox chBoxGranaderos;
	private JCheckBox chBoxPistoleros;
	private JLabel lblEnemigosDisponibles;
	private JLabel lblMultiplicadorDanoJugador;
	private JSpinner spMultiplicadorDanoJugador;
	private JLabel lblMultiplicadorDanoEnemigos;
	private JSpinner spMultiplicadorDanoEnemigos;
	private JLabel lblVidasIniciales;
	private JSpinner spVidasIniciales;
	private JLabel lblArmaInicial;
	private JComboBox<String> cBoxArmaInicial;
	private Button btnSave;
	private Button btnCancelar;
	private Rectangle originaBounds;
	private Button btnSelecionar;
	
		/**
		 * Create the dialog.
		 */
		public VistaConfiguracionesUsuario(JFrame frame) {
			super(frame);
			Dimension lastDimension = super.getSize();
			Dimension newDimension = new Dimension(lastDimension.width, lastDimension.height+30);
			super.setSize(newDimension);
			originaBounds = super.getBounds();
			configPanel.setSize(419, 261);
			configPanel.setLocation(getLocation());
			initComponents();
		}
		
		private void initComponents() {
		configPanel.setLayout(null);
	
		btnSelecionar = new Button("Selecionar");
		btnSelecionar.setBounds(448, 408, 80, 20);
		btnSelecionar.setBackground(Colors.COLOR6);
		getBusquedaPanel().add(btnSelecionar);
		
		chBoxHelicopteros = new JCheckBox("Helicopteros");
		chBoxHelicopteros.setBounds(28, 32, 97, 23);
		configPanel.add(chBoxHelicopteros);
		
		chBoxGranaderos = new JCheckBox("Granaderos");
		chBoxGranaderos.setBounds(153, 32, 97, 23);
		configPanel.add(chBoxGranaderos);
		
		chBoxPistoleros = new JCheckBox("Pistoleros");
		chBoxPistoleros.setBounds(278, 32, 97, 23);
		configPanel.add(chBoxPistoleros);
		
		lblEnemigosDisponibles = new JLabel("Enemigos disponibles:");
		lblEnemigosDisponibles.setBounds(10, 11, 122, 14);
		configPanel.add(lblEnemigosDisponibles);
		
		lblMultiplicadorDanoJugador = new JLabel("Multiplicador dano jugador");
		lblMultiplicadorDanoJugador.setBounds(10, 73, 149, 14);
		configPanel.add(lblMultiplicadorDanoJugador);
		
		spMultiplicadorDanoJugador = new JSpinner();
		spMultiplicadorDanoJugador.setBounds(150, 70, 246, 20);
		spMultiplicadorDanoJugador.setModel(new SpinnerNumberModel(Integer.valueOf(1), 1, null, Integer.valueOf(1)));
		configPanel.add(spMultiplicadorDanoJugador);
		
		lblMultiplicadorDanoEnemigos = new JLabel("Multiplicador dano enemigos");
		lblMultiplicadorDanoEnemigos.setBounds(10, 101, 149, 14);
		configPanel.add(lblMultiplicadorDanoEnemigos);
		
		spMultiplicadorDanoEnemigos = new JSpinner();
		spMultiplicadorDanoEnemigos.setBounds(150, 98, 246, 20);
		spMultiplicadorDanoEnemigos.setModel(new SpinnerNumberModel(Integer.valueOf(1), 1, null, Integer.valueOf(1)));
		configPanel.add(spMultiplicadorDanoEnemigos);
		
		lblVidasIniciales = new JLabel("Vidas Iniciales");
		lblVidasIniciales.setBounds(10, 129, 149, 14);
		configPanel.add(lblVidasIniciales);
		
		spVidasIniciales = new JSpinner();
		spVidasIniciales.setBounds(150, 126, 246, 20);
		spVidasIniciales.setModel(new SpinnerNumberModel(Integer.valueOf(1), 1, null, Integer.valueOf(1)));
		configPanel.add(spVidasIniciales);
		
		lblArmaInicial = new JLabel("Arma Inicial");
		lblArmaInicial.setBounds(10, 157, 149, 14);
		configPanel.add(lblArmaInicial);
		
		cBoxArmaInicial = new JComboBox<>();
		cBoxArmaInicial.setBounds(150, 153, 246, 22);
		cBoxArmaInicial.setModel(new DefaultComboBoxModel<>(ArmasDisponibles.values()));
		configPanel.add(cBoxArmaInicial);
		
		btnSave = new Button("Guardar");
		btnSave.setBackground(Colors.COLOR2);
		btnSave.setBounds(335, 186, 61, 25);
		configPanel.add(btnSave);
		
		btnCancelar = new Button("Cancelar");
		btnCancelar.setBackground(null);
		btnCancelar.setBorder(new LineBorder(Colors.COLOR_GRAY));
		btnCancelar.setForeground(Colors.COLOR_GRAY);
		btnCancelar.setBounds(10, 186, 61, 25);
		configPanel.add(btnCancelar);

	}
	
	public void changeToConfigPanel(UserConfig configuracion) {
		String[] enemies = configuracion.getEnemigosActivos();
		for (String enemy : enemies) {
			switch (enemy) {
				case EnemyTypes.GRANADERO:
					chBoxGranaderos.setSelected(true);
					break;
				case EnemyTypes.HELICOPTERO:
					chBoxHelicopteros.setSelected(true);
					break;
				case EnemyTypes.PISTOLERO:
					chBoxPistoleros.setSelected(true);
					break;
			}
		}

		spMultiplicadorDanoEnemigos.setValue(configuracion.getMultiplicadorDanoEnemigo());
		spMultiplicadorDanoJugador.setValue(configuracion.getMultiplicadorDano());
		spVidasIniciales.setValue(configuracion.getVidasIniciales());
		cBoxArmaInicial.setSelectedItem(configuracion.getArmainicial());
		setContentPane(configPanel);
		setBounds(configPanel.getBounds());
	}
	
	public void changeToBuscarPanel() {
		setContentPane(getBusquedaPanel());
		setBounds(originaBounds);
	}

	public JCheckBox getChBoxHelicopteros() {
		return chBoxHelicopteros;
	}

	public void setChBoxHelicopteros(JCheckBox chBoxHelicopteros) {
		this.chBoxHelicopteros = chBoxHelicopteros;
	}

	public JCheckBox getChBoxGranaderos() {
		return chBoxGranaderos;
	}

	public void setChBoxGranaderos(JCheckBox chBoxGranaderos) {
		this.chBoxGranaderos = chBoxGranaderos;
	}

	public JCheckBox getChBoxPistoleros() {
		return chBoxPistoleros;
	}

	public void setChBoxPistoleros(JCheckBox chBoxPistoleros) {
		this.chBoxPistoleros = chBoxPistoleros;
	}

	public JLabel getLblEnemigosDisponibles() {
		return lblEnemigosDisponibles;
	}

	public void setLblEnemigosDisponibles(JLabel lblEnemigosDisponibles) {
		this.lblEnemigosDisponibles = lblEnemigosDisponibles;
	}

	public JLabel getLblMultiplicadorDanoJugador() {
		return lblMultiplicadorDanoJugador;
	}

	public void setLblMultiplicadorDanoJugador(JLabel lblMultiplicadorDanoJugador) {
		this.lblMultiplicadorDanoJugador = lblMultiplicadorDanoJugador;
	}

	public JSpinner getSpMultiplicadorDanoJugador() {
		return spMultiplicadorDanoJugador;
	}

	public void setSpMultiplicadorDanoJugador(JSpinner spMultiplicadorDanoJugador) {
		this.spMultiplicadorDanoJugador = spMultiplicadorDanoJugador;
	}

	public JLabel getLblMultiplicadorDanoEnemigos() {
		return lblMultiplicadorDanoEnemigos;
	}

	public void setLblMultiplicadorDanoEnemigos(JLabel lblMultiplicadorDanoEnemigos) {
		this.lblMultiplicadorDanoEnemigos = lblMultiplicadorDanoEnemigos;
	}

	public JSpinner getSpMultiplicadorDanoEnemigos() {
		return spMultiplicadorDanoEnemigos;
	}

	public void setSpMultiplicadorDanoEnemigos(JSpinner spMultiplicadorDanoEnemigos) {
		this.spMultiplicadorDanoEnemigos = spMultiplicadorDanoEnemigos;
	}

	public JLabel getLblVidasIniciales() {
		return lblVidasIniciales;
	}

	public void setLblVidasIniciales(JLabel lblVidasIniciales) {
		this.lblVidasIniciales = lblVidasIniciales;
	}

	public JSpinner getSpVidasIniciales() {
		return spVidasIniciales;
	}

	public void setSpVidasIniciales(JSpinner spVidasIniciales) {
		this.spVidasIniciales = spVidasIniciales;
	}

	public JLabel getLblArmaInicial() {
		return lblArmaInicial;
	}

	public void setLblArmaInicial(JLabel lblArmaInicial) {
		this.lblArmaInicial = lblArmaInicial;
	}

	public JComboBox<String> getcBoxArmaInicial() {
		return cBoxArmaInicial;
	}

	public void setcBoxArmaInicial(JComboBox<String> cBoxArmaInicial) {
		this.cBoxArmaInicial = cBoxArmaInicial;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public void setBtnSave(Button btnSave) {
		this.btnSave = btnSave;
	}

	public Button getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(Button btnCancelar) {
		this.btnCancelar = btnCancelar;
	}

	public Rectangle getOriginaBounds() {
		return originaBounds;
	}

	public void setOriginaBounds(Rectangle originaBounds) {
		this.originaBounds = originaBounds;
	}

	public JPanel getConfigPanel() {
		return configPanel;
	}

	public Button getBtnSelecionar() {
		return btnSelecionar;
	}

	public void setBtnSelecionar(Button btnSelecionar) {
		this.btnSelecionar = btnSelecionar;
	}

	
}

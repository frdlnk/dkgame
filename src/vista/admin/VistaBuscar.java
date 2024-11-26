package vista.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.color.ColorSpace;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.Usuario;
import utils.ComparativeModes;
import vista.uiConstants.Colors;

import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import modelo.Dao.IDAOUsuario.UserFields;
import javax.swing.ListSelectionModel;

public class VistaBuscar extends JDialog {
	private static final long serialVersionUID = 1L;
	private final JPanel busquedaPanel = new JPanel();
	private JTextField searchTextField;
	private JList<Usuario> listaUsuarios;
	private JLabel lblCriterio;
	private JLabel lblSearchMode;
	private JLabel lblBusqueda;
	private JComboBox<UserFields> comboBoxCriterios;
	private JComboBox<ComparativeModes> comboBoxSearchModes;

	/**
	 * Create the dialog.
	 */
	public VistaBuscar(JFrame frame) {
		super(frame, true);
		setBounds(100, 100, 554, 451);
		getContentPane().setLayout(new BorderLayout());
		busquedaPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(busquedaPanel, BorderLayout.CENTER);
		busquedaPanel.setLayout(null);
		busquedaPanel.setBackground(Colors.COLOR1);
		
		initComponents();
	}
	
	public void loadUsers(Usuario[] users) {
		if (users != null) {
			listaUsuarios.setListData(users);
		}
	}
	
	private void initComponents() {

		searchTextField = new JTextField();
		searchTextField.setBounds(101, 9, 427, 20);
		busquedaPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		listaUsuarios = new JList<>();
		listaUsuarios.setOpaque(false);
		listaUsuarios.setBackground(Colors.COLOR3);
		listaUsuarios.setVisibleRowCount(100);
		listaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaUsuarios.setSelectionBackground(Colors.COLOR6);
		listaUsuarios.setSelectionForeground(Colors.COLOR1);
		listaUsuarios.setBounds(10, 68, 518, 333);
		busquedaPanel.add(listaUsuarios);
		
		lblCriterio = new JLabel("Criterio de Busqueda");
		lblCriterio.setForeground(Color.WHITE);
		lblCriterio.setBounds(10, 40, 112, 14);
		busquedaPanel.add(lblCriterio);
		
		lblSearchMode = new JLabel("Modo de busqueda");
		lblSearchMode.setForeground(Color.WHITE);
		lblSearchMode.setBounds(290, 40, 168, 14);
		busquedaPanel.add(lblSearchMode);
		
		lblBusqueda = new JLabel("Busqueda");
		lblBusqueda.setForeground(Color.WHITE);
		lblBusqueda.setBounds(10, 12, 71, 14);
		busquedaPanel.add(lblBusqueda);
		
		comboBoxCriterios = new JComboBox<>();
		comboBoxCriterios.setModel(new DefaultComboBoxModel<>(UserFields.values()));
		comboBoxCriterios.setBounds(184, 35, 96, 22);
		busquedaPanel.add(comboBoxCriterios);
		
		comboBoxSearchModes = new JComboBox<>();
		
		comboBoxSearchModes.setModel(new DefaultComboBoxModel<>(ComparativeModes.values()));
		comboBoxSearchModes.setBounds(438, 35, 90, 22);
		busquedaPanel.add(comboBoxSearchModes);
	}
	
	public ComparativeModes getSearchMode() {
		ComparativeModes mode = comboBoxSearchModes.getItemAt(comboBoxSearchModes.getSelectedIndex());
		return mode;
	}
	
	public UserFields getComparativeMode() {
		UserFields mode = comboBoxCriterios.getItemAt(comboBoxCriterios.getSelectedIndex());
		return mode;
	}
	
	public JList<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(JList<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}


	public JLabel getLblCriterio() {
		return lblCriterio;
	}

	public void setLblCriterio(JLabel lblCriterio) {
		this.lblCriterio = lblCriterio;
	}

	public JLabel getLblSearchMode() {
		return lblSearchMode;
	}

	public void setLblSearchMode(JLabel lblSearchMode) {
		this.lblSearchMode = lblSearchMode;
	}


	public JLabel getLblBusqueda() {
		return lblBusqueda;
	}

	public void setLblBusqueda(JLabel lblBusqueda) {
		this.lblBusqueda = lblBusqueda;
	}

	public JPanel getContentPanel() {
		return busquedaPanel;
	}

	public JTextField getSearchTextField() {
		return searchTextField;
	}

	public void setSearchTextField(JTextField searchTextField) {
		this.searchTextField = searchTextField;
	}

	public JComboBox<UserFields> getComboBoxCriterios() {
		return comboBoxCriterios;
	}

	public void setComboBoxCriterios(JComboBox<UserFields> comboBoxCriterios) {
		this.comboBoxCriterios = comboBoxCriterios;
	}

	public JComboBox<ComparativeModes> getComboBoxSearchModes() {
		return comboBoxSearchModes;
	}

	public void setComboBoxSearchModes(JComboBox<ComparativeModes> comboBoxSearchModes) {
		this.comboBoxSearchModes = comboBoxSearchModes;
	}

	public JPanel getBusquedaPanel() {
		return busquedaPanel;
	}
}

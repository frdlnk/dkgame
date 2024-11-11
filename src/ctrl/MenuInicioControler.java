package ctrl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.MenuInicio;
import vista.MenuProvicional;

public class MenuInicioControler implements ActionListener{
	private MenuInicio vista;
	
	public MenuInicioControler() {
		vista = new MenuInicio();
		vista.getBtnAdministracion().addActionListener(this);
		vista.getBtnJugar().addActionListener(this);
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(vista.getBtnAdministracion())) {
			MenuProvicional.mostrarMenuPrincipal();
		}else if(e.getSource().equals(vista.getBtnJugar())) {
			GameControler game = new GameControler();
			game.iniciarJuego();
			vista.setVisible(false);
		}
	}
}

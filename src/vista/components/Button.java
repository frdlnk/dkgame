package vista.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Button extends JPanel {

	private JButton boton;

	public Button(String text) {
		super();
		setLayout(new GridLayout(1,1));
		boton = new JButton(text);
		boton.setContentAreaFilled(false);
		boton.setBorder(null);
		add(boton);
	}

	@Override
	public void setForeground(Color fg) {
		super.setForeground(fg);
		if (boton != null) {
			boton.setForeground(fg);
		}
	}
	
	@Override
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if (boton != null) {
			boton.setBackground(bg);
		}
	}
	
	public void addActionListener(ActionListener listener) {
		boton.addActionListener(listener);
	}
}


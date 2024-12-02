package vista.components;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Boton personalizado, permite una mejor forma de alterar su forma sin afectar
 * el comportamiento normal <br>
 * funciona como un wrapper para estilizar el panel con mayor libertad que el
 * boton
 */
public class Button extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton boton;
	private ActionListener actionListener;

	public Button(String text) {
		super();
		setLayout(new GridLayout(1, 1));
		boton = new JButton(text);
		boton.setContentAreaFilled(false);
		boton.setBorder(null);
		boton.addActionListener(this);
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
		actionListener = listener;
		boton.addActionListener(listener);
	}

	public JButton getBoton() {
		return boton;
	}

	public void setBoton(JButton boton) {
		this.boton = boton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		e.setSource(this);
		actionListener.actionPerformed(e);
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}

	@Override
	public String toString() {
		return "Button [boton=" + boton + ", actionListener=" + actionListener + "]";
	}

}

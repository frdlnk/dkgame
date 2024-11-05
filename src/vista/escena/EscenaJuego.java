package vista.escena;

import java.awt.Graphics;

import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.entidades.ListaEntidades;
import utils.Colisionable;

public abstract class EscenaJuego extends Scene {
	public static ListaEntidades entidades = new ListaEntidades();
	
	@Override
	public void actualizar() {
		
	}

	@Override
	public void destruir() {
		
	}

	@Override
	public void dibujar(Graphics arg0) {
		
	}

	public abstract void addEntidad(Entidad entidad);
	
	public void calcularColisiones() {
		for (int i = 0; i < entidades.getSize(); i++) {
			if (entidades.get(i) instanceof Colisionable) {
				Colisionable colisionable1 = (Colisionable) entidades.get(i);
				for (int j = i+1; j < entidades.getSize(); j++) {
					if(entidades.get(j) instanceof Colisionable) {
						Colisionable colisionable2 = (Colisionable) entidades.get(j);
						colisionResolve(colisionable1, colisionable2, entidades.get(i), entidades.get(j));
					}
				}
			}
		}
	}
	
	private void colisionResolve(Colisionable col1, Colisionable col2, Entidad ent1, Entidad ent2) {
		boolean col1Colisiona = col1.hayColision(col2);
		boolean col2Colisiona = col2.hayColision(col1);
		
		if (col1.isTrigger() && col1Colisiona && !col2.isTrigger()) {
			col1.onColision(ent2);
		}else if(col2.isTrigger() && col2Colisiona && !col1.isTrigger()) {
			col2.onColision(ent1);
		}else if(col1Colisiona && col2Colisiona) {
			col1.onColision(ent2);
			col2.onColision(ent1);
		}
		
	}

	public static void setListaEntidades(ListaEntidades entidades) {
		EscenaJuego.entidades = entidades;
	}

	public ListaEntidades getEntidades() {
		return entidades;
	}

}

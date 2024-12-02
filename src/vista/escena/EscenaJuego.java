package vista.escena;

import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.entidades.ListaEntidades;
import utils.colision.ColisionUtils;
import utils.colision.Colisionable;

/**
 * Clase base para las escenas del juego
 */
public abstract class EscenaJuego extends Scene {
	protected ListaEntidades entidades = new ListaEntidades();

	/**
	 * Anade una entidad a la escena
	 * 
	 * @param entidad
	 */
	public abstract void addEntidad(Entidad entidad);

	/**
	 * Calculo de colisiones entre todos los objetos de la lista de entidades
	 */
	public void calcularColisiones() {
		for (int i = 0; i < entidades.getSize(); i++) {
			if (entidades.get(i) instanceof Colisionable) {
				Colisionable colisionable1 = (Colisionable) entidades.get(i);
				for (int j = i + 1; j < entidades.getSize(); j++) {
					if (entidades.get(j) instanceof Colisionable) {
						Colisionable colisionable2 = (Colisionable) entidades.get(j);
						ColisionUtils.colisionResolve(colisionable1, colisionable2);
					}
				}
			}
		}
	}

	public void setListaEntidades(ListaEntidades entidades) {
		this.entidades = entidades;
	}

	public ListaEntidades getEntidades() {
		return entidades;
	}

	public void setEntidades(ListaEntidades entidades) {
		this.entidades = entidades;
	}

	@Override
	public String toString() {
		return "EscenaJuego []";
	}

}

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
	public static ListaEntidades entidades = new ListaEntidades();

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

	public static void setListaEntidades(ListaEntidades entidades) {
		EscenaJuego.entidades = entidades;
	}

	public ListaEntidades getEntidades() {
		return entidades;
	}

	public static void setEntidades(ListaEntidades entidades) {
		EscenaJuego.entidades = entidades;
	}

	@Override
	public String toString() {
		return "EscenaJuego []";
	}

}

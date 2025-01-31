package modelo.armamento.municiones;

import java.util.ArrayList;

import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.util.Vector2D;

/**
 * {@link Municion} mas comun, crea una bala que viaja en linea recta hasta
 * impactar
 * 
 * @author Joshua Elizondo Vasquez
 * @see Municion
 */
public class Bala extends Municion {

	/**
	 * Crea una nueva bala con velocidad de 7 px/s
	 * 
	 * @param posicion       Vector2D posicion inicial de la bala
	 * @param direccion      Vector2D direccion de la bala
	 * @param targetsIgnored lista de tags a ignorar
	 * @param dano           double cantidad de dano a generar en las entidades
	 */
	public Bala(Vector2D posicion, Vector2D direccion, ArrayList<String> targetsIgnored, double dano) {
		super(posicion, direccion, targetsIgnored, dano, 7);
	}

	public Bala() {
		super();
	}

	@Override
	protected void impacto(Entidad entidad) {
		// si impacta en un soldado le hace dano
		if (entidad instanceof Soldado) {
			((Soldado) entidad).recibirDano(getDano());
		}
		// al impactar se destruye
		destruir();
	}

	@Override
	public String toString() {
		return "Bala []";
	}

}

package modelo.armamento.municiones;

import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.util.Vector2D;
import utils.Array;

public class Bala extends Municion {
	
	public Bala(Vector2D posicion, Vector2D direccion, Array<String> target, double dano) {
		super("Bala", posicion, direccion, target, dano);
		setDano(5);
	}


	@Override
	protected void impacto(Entidad entidad) {
		if (entidad instanceof Soldado) {
			((Soldado) entidad).recibirDano(getDano());
		}

	}

}

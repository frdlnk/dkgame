package modelo.armamento.armas;

import java.util.Random;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import motor_v1.motor.util.Vector2D;
import utils.arrays.ArrayString;

public class HeavyMachineGun extends Arma {

	public HeavyMachineGun() {
		shootDelay = 0.1;
	}
	
	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnored) {
		Random random = new Random();
		double dispersion = random.nextDouble(-15, 15);
		
		if (direccion.getY() != 0 ) 
			posicion = posicion.add(new Vector2D(dispersion,0));
		else
			posicion = posicion.add(new Vector2D(0,dispersion));
		
		return new Bala(posicion, direccion, targetsIgnored, 10);
	}

}

package modelo.armamento.armas;

import java.util.Random;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import motor_v1.motor.util.Vector2D;

public class HeavyMachineGun extends Arma {
	private double dano;

	public HeavyMachineGun(){
		this(100,10);
	}

	public HeavyMachineGun(int municiones, int dano) {
		super(0.1,municiones);
		this.dano = dano;
	}
	
	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnored) {
		Random random = new Random();
		double dispersion = random.nextDouble(-15, 15);
		
		if (direccion.getY() != 0 ) 
			posicion = posicion.add(new Vector2D(dispersion,0));
		else
			posicion = posicion.add(new Vector2D(0,dispersion));
		
		return new Bala(posicion, direccion, targetsIgnored, dano);
	}

}

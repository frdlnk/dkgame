package modelo.armamento.armas;

import java.util.Random;

import modelo.armamento.municiones.Bala;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import motor_v1.motor.util.Vector2D;

/**
 * Ametralladora se encraga de disparar con alta cadencia y una pequena dispercion
 */
public class HeavyMachineGun extends Arma {
	private final static int DEFAULT_DANO = 10;
	private final static int DEFAULT_MUNICIONES = 100;
	private final static double SHOT_DELAY = .1;
	private double dano;

	/**
	 * Crea una nueva Heavy MachineGun con dano de {@value HeavyMachineGun#DEFAULT_DANO}
	 * cadencia de tiro de {@value #SHOT_DELAY}s y {@value HeavyMachineGun#DEFAULT_MUNICIONES} balas
	 */
	public HeavyMachineGun(){
		this(DEFAULT_MUNICIONES,DEFAULT_DANO);
		setShootDelay(SHOT_DELAY);
	}

	/**
	 * Crea una nueva Machinegun con cadenmcia de {@value #SHOT_DELAY}s
	 * @param municiones cantidad de balas del cargador
	 * @param dano dano que haran las balas
	 */
	public HeavyMachineGun(int municiones, int dano) {
		super(SHOT_DELAY,dano);
		this.setBalasRestantes(municiones); 
	}
	
	@Override
	protected Municion generarBala(Vector2D posicion, Vector2D direccion, ArrayString targetsIgnored) {
		Random random = new Random();
		double dispersion = random.nextDouble(-15, 15);
		
		//agrega la dispersion en "x" si dispara vertical y en "y" si dispara horizontal
		if (direccion.getY() != 0 ) 
			posicion = posicion.add(new Vector2D(dispersion,0));
		else
			posicion = posicion.add(new Vector2D(0,dispersion));
		
		return new Bala(posicion, direccion, targetsIgnored, dano);
	}

}

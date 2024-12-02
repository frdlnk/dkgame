package modelo.entidades.recogibles;

import modelo.armamento.armas.HeavyMachineGun;
import modelo.entidades.Player;
import modelo.entidades.Recogible;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.constants.Assets;

/**
 * Caja de recompensa {@link Recogible} que da un nuevo {@link HeavyMachineGun}
 * 
 * @see HeavyMachineGun
 * @see Recogible
 */
public class HRecogible extends Recogible {

	public HRecogible(Transform transformar, int puntos) {
		super(Assets.LETRA_H, transformar, puntos);
		transformar.escalarloA(1.5);
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Player) {
			Assets.HEAVY_MACHINEGUN_SOUND.start();
			destruir();
		}
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] { colisiona };
	}

	@Override
	public Object getReward() {
		return new HeavyMachineGun(300, HeavyMachineGun.DEFAULT_DANO);
	}

	@Override
	public String toString() {
		return "HRecogible []";
	}

}

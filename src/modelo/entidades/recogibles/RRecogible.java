package modelo.entidades.recogibles;

import modelo.armamento.armas.LanzaCohetes;
import modelo.entidades.Player;
import modelo.entidades.Recogible;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import utils.colision.ColisionInfo;
import utils.constants.Assets;

/**
 * Caja de recompensa {@link Recogible} que da un nuevo {@link LanzaCohetes}
 * 
 * @see LanzaCohetes
 * @see Recogible
 */
public class RRecogible extends Recogible {

	public RRecogible(Transform transformar, int puntos) {
		super(Assets.LETRA_R, transformar, puntos);
		transformar.escalarloA(1.5);
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision.getEntidad() instanceof Player) {
			Assets.ROCKET_LAUNCHER_SOUND.start();
			destruir();
		}
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] { colisiona };
	}

	@Override
	public Object getReward() {
		return new LanzaCohetes(20);
	}

}

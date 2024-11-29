package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Player;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Tags;

/**
 * Barrera de movimiento que detecta la colision del jugador para limitar su movimeinto y actuar segun su posicion en el mapa
 */
public class MovementBarrier extends Caja {
	private boolean playerOverlap;
	private Player player;
	private boolean isTrigger;
	private boolean isEnable;

	/**
	 * Crea una nueva barrera de movimiento
	 * @param textura imagen a mostrar
	 * @param transformar posicion inicial
	 */
	public MovementBarrier(BufferedImage textura, Transform transformar) {
		super(Tags.STATIC_OBJECT, textura, transformar);
		setTrigger(false);
		setEnable(true);
	}

	@Override
	public void onColision(ColisionInfo colision) {
		//verifica si colisiono con el jugador
		if (isEnable && colision.getEntidad() instanceof Player) {
			player = (Player) colision.getEntidad();
			if (!isTrigger) {
				Vector2D lastP = player.getFisica().getUltimaDireccion();
				Vector2D copy = lastP.add(0);
				super.onColision(colision);
				player.getFisica().setUltimaDireccion(copy);
			}
			playerOverlap = true;
		}
	}
	
	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		if (entidad instanceof Player) {
			return super.hayColision(entidad);
		}
		return null;
	}
	
	@Override
	public void actualizar() {
		playerOverlap = false;
		super.actualizar();
	}
	
	public boolean isPlayerOverlap() {
		return playerOverlap && isEnable;
	}

	public void setPlayerOverlap(boolean playerOverlap) {
		this.playerOverlap = playerOverlap;
	}

	public boolean isTrigger() {
		return isTrigger;
	}

	public void setTrigger(boolean isTrigger) {
		this.isTrigger = isTrigger;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public void setEnable(boolean enable) {
		this.isEnable = enable;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
}

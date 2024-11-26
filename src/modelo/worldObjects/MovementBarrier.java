package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Player;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;

public class MovementBarrier extends Caja {
	private boolean playerOverlap;
	private Player player;
	private boolean isTrigger;
	private boolean isEnable;

	public MovementBarrier(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
		setTrigger(false);
		setEnable(true);
	}

	public MovementBarrier(String nombre, BufferedImage imageMB, Vector2D posicionMB) {
		super(nombre,imageMB,posicionMB);
		setTrigger(false);
		setEnable(true);
	}

	@Override
	public void onColision(ColisionInfo colision) {
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

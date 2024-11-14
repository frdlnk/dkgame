package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Player;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;

public class MovementBarrier extends Caja {
	private boolean playerOverlap;
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
	public void onColision(Entidad entidad) {
		if (isEnable) {
			if (entidad instanceof Player) {
				if (!isTrigger) {
					super.onColision(entidad);
				}
				playerOverlap = true;
			}
		}
	}
	
	@Override
	public boolean hayColision(Colisionable entidad) {
		if (entidad instanceof Player) {
			return super.hayColision(entidad);
		}
		return false;
	}
	
	@Override
	public void actualizar() {
		playerOverlap = false;
		super.actualizar();
	}

	public boolean isPlayerOverlap() {
		return playerOverlap;
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
	
}

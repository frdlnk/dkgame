package modelo.worldObjects;

import java.awt.image.BufferedImage;

import modelo.entidades.Player;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;

public class MovementBarrier extends Caja {
	private boolean playerOverlap;

	public MovementBarrier(String nombre, BufferedImage textura, Transform transformar) {
		super(nombre, textura, transformar);
	}

	public MovementBarrier(String nombre, BufferedImage imageMB, Vector2D posicionMB) {
		super(nombre,imageMB,posicionMB);
	}

	@Override
	public void onColision(Entidad entidad) {
		super.onColision(entidad);
		if (entidad instanceof Player) {
			playerOverlap = true;
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
	
}

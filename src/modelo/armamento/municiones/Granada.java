package modelo.armamento.municiones;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import modelo.armamento.Explosion;
import modelo.armamento.Explosivo;
import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.constants.Tags;
import vista.escena.EscenaJuego;

/**
 * Granada que explota al tocar otros objetos
 * 
 * @see Municion
 * @see Explosivo
 */
public class Granada extends Municion implements Explosivo {

	/**
	 * Crea una nueva Granada
	 * 
	 * @param posicion      Posicion inicial de la granada
	 * @param direccion     Direccion de lanzamiento
	 * @param targetsIgnore Objetivos a ignorar
	 * @param dano          dano causado
	 * @param velocidad     velocidad de movimiento
	 */
	public Granada(Vector2D posicion, Vector2D direccion, ArrayList<String> targetsIgnore, double dano,
			double velocidad) {
		super(posicion, direccion, targetsIgnore, dano, velocidad);
		setNombre(Tags.GRANADA);
		fisica.setGravity(1);
	}

	/**
	 * Crea una granada sin movimiento Solo cae
	 */
	public Granada() {
		super();
		setNombre(Tags.GRANADA);
		fisica.setGravity(1);
	}

	@Override
	public Explosion generarExplosion() {
		Rectangle rect = new Rectangle(30, 50);
		Color color = new Color(255, 0, 0, 30);
		BufferedImage texturaExplosion = Renderer.crearTextura(rect, color);
		return new Explosion(texturaExplosion, transformar, targetIgnore, (int) getDano());
	}

	@Override
	public void actualizar() {
		super.actualizar();
	}

	@Override
	public void explotar() {
		Scene escena = Scene.getEscenaActual();
		if (escena instanceof EscenaJuego) {
			((EscenaJuego) escena).addEntidad(generarExplosion());
		}
	}

	@Override
	protected void impacto(Entidad entidad) {
		if (getViva()) {
			explotar();
			destruir();
		}
	}

	@Override
	public String toString() {
		return "Granada []";
	}

}

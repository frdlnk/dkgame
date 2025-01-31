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
import vista.escena.EscenaJuego;

/**
 * Choete que explota al impactar con algun objetivo
 * 
 * @author Joshua Elizondo Vasquez
 * @see Municion, Explosivo
 */
public class Choete extends Municion implements Explosivo {

	/**
	 * Crea un nuevo cohete con velocidad 10 px/s
	 * 
	 * @param nombre         String tag de la entidad
	 * @param posicion       Vector2D posicion inicial
	 * @param direccion      Vector2D direccion del Cohete
	 * @param targetsIgnored lista de tags a ignorar
	 * @param dano           double cantidad de dano a realizar a los objeivos
	 */
	public Choete(Vector2D posicion, Vector2D direccion, ArrayList<String> targetsIgnored, double dano) {
		super(posicion, direccion, targetsIgnored, dano, 10);
		// asigna gravedad al cohete
		this.fisica.setGravity(1);
	}

	/**
	 * Crea un choete vacio
	 */
	public Choete() {
		super();
	}

	@Override
	protected void impacto(Entidad entidad) {
		// al impactar el cohete explota
		explotar();
	}

	@Override
	public void actualizar() {
		super.actualizar();
		// TODO: rotacion de la entidad segun caida
		transformar.rotarloA(fisica.getUltimaDireccion().getAngle());
	}

	@Override
	public Explosion generarExplosion() {
		// Forma de la explosion
		Rectangle rect = new Rectangle(30, 50);
		Color color = new Color(255, 0, 0, 30);
		BufferedImage texturaExplosion = Renderer.crearTextura(rect, color);
		// Crea el objeto explosion que realizara la hitbox y dano
		// correspondiente a la explosion del cohete
		return new Explosion(texturaExplosion, transformar, targetIgnore, (int) getDano());
	}

	@Override
	public void explotar() {
		// solo explota una vez
		if (getViva()) {
			// genera la explosion
			Explosion explocion = generarExplosion();
			Scene escenaActual = Scene.getEscenaActual();
			if (Scene.getEscenaActual() instanceof EscenaJuego) {
				((EscenaJuego) escenaActual).addEntidad(explocion);
			}
			destruir();
		}
	}

	@Override
	public String toString() {
		return "Choete []";
	}

}

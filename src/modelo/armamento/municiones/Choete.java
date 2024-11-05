package modelo.armamento.municiones;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.Explosion;
import modelo.armamento.Explosivo;
import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Tags;
import vista.escena.EscenaJuego;

public class Choete extends Municion implements Explosivo{

	public Choete(String nombre, Vector2D posicion, Vector2D direccion, Array<String> target, double dano) {
		super(nombre, posicion, direccion, target, dano);
		this.fisica.setGravity(1);
		targetIgnore.add(Tags.FLOOR.getTag());
	}


	@Override
	protected void impacto(Entidad entidad) {
		explotar();
	}
	
	@Override
	public void actualizar() {
		super.actualizar();
		transformar.rotarloA(fisica.getUltimaDireccion().getAngle());
	}
	
	@Override
	public Explosion generarExplosion() {
		Rectangle rect = new Rectangle(30,50);
		Color color = new Color(255,0,0,30);
		BufferedImage texturaExplosion = Renderer.crearTextura(rect, color);
		return new Explosion("Explosion", texturaExplosion, transformar.getPosicion(), targetIgnore);
	}

	@Override
	public void explotar() {
		if (getViva()) {
			Explosion explocion = generarExplosion();
			Scene escenaActual = Scene.getEscenaActual();
			if (Scene.getEscenaActual() instanceof EscenaJuego) {
				((EscenaJuego) escenaActual).addEntidad(explocion);
			}
			destruir();
		}
	}

}

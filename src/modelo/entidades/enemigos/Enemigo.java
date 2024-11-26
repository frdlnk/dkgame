package modelo.entidades.enemigos;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import modelo.componentes.Fisica;
import modelo.entidades.Soldado;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.Conf;
import utils.Tags;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import vista.escena.EscenaJuego;

public abstract class Enemigo extends Soldado {

	public Enemigo(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		colisiona.actualizar();
		fisica = new Fisica(1,1,transformar);
	}
	
	@Override
	public void actualizar() {
		fisica.actualizar();
		colisiona.actualizar();
		super.actualizar();
	}
	
	public boolean isInScreen() {
		Vector2D pos = transformar.getPosicion();
		if (pos.getX() + colisiona.getHitbox().getWidth() > 0 && pos.getX() < Conf.WINDOW_WIDTH) {
			return true;
		}
		return false;
	}
	
	@Override
	public void recibirDano(double dano) {
		salud -= dano;
		if (salud <= 0) {
			morir();
		}
	}
	
	protected Vector2D getDireccionJugador(Vector2D posicion) {
		Vector2D distancia = getDistanciaJugador(posicion);
		
		return distancia != null ? distancia.normalize() : null;
	}
	
	
	protected Vector2D getDistanciaJugador(Vector2D posicion) {
		Scene escenaActual = Scene.getEscenaActual();
		if (escenaActual instanceof EscenaJuego && ((EscenaJuego) escenaActual).getPlayer() != null) {
			Vector2D posJugador = ((EscenaJuego)escenaActual).getPlayer().getCentro();
			Vector2D direccionHaciaJugador = posJugador.subtract(posicion);
			return direccionHaciaJugador;
		}
		return null;
	}
	
	@Override
	public Collider[] getColliders() {
		return new Collider[]{colisiona};
	}
	
	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		if (isInScreen()) {
			return super.hayColision(entidad);
		}
		return null;
	}

}

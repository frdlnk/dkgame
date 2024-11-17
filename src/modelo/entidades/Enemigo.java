package modelo.entidades;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Municion;
import modelo.componentes.Fisica;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.ColisionInfo;
import utils.Conf;
import utils.Tags;
import utils.arrays.ArrayString;
import vista.escena.EscenaJuego;

public class Enemigo extends Soldado {

	public Enemigo(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		colisiona.actualizar();
		setArma(new Pistola());
		fisica = new Fisica(1,1,transformar);
		salud = 20;
	}
	
	@Override
	public void actualizar() {
		disparar();
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
	public void dibujar(Graphics g) {
		Rectangle hitboxRectangle = colisiona.getHitbox();
		Vector2D posVector2d = new Vector2D(hitboxRectangle.getX(), hitboxRectangle.getY());
		Renderer.dibujarBordes(g, posVector2d, hitboxRectangle.getWidth(), hitboxRectangle.getHeight());
		super.dibujar(g);
	}

	@Override
	public void recibirDano(double dano) {
		System.out.println(dano);
		salud -= dano;
		if (salud <= 0) {
			morir();
		}
	}

	@Override
	public void morir() {
		destruir();
	}

	@Override
	public void disparar() {
		Scene escena = Scene.getEscenaActual();
		if (escena instanceof EscenaJuego) {
			ArrayString targetsIgnore = new ArrayString();
			targetsIgnore.add(Tags.ENEMY);
			Municion disparo = getArma().disparar(getCentro(), Vector2D.LEFT, targetsIgnore);
			if(disparo != null) {
				((EscenaJuego) escena).addEntidad(disparo);
			}
		}
	}

	@Override
	public void onColision(ColisionInfo colision) {
		
	}

	@Override
	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[]{colisiona};
	}

}

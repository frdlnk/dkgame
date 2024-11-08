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
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.Tags;
import utils.arrays.ArrayString;
import vista.escena.EscenaJuego;

public class Enemigo extends Soldado {
	public final static double TIEMPO_ENTRE_DISPAROS = 2;
	private double tiempoParaSiguienteDisparo;

	public Enemigo(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		colisiona.actualizar();
		setArma(new Pistola());
		tiempoParaSiguienteDisparo = 0;
		fisica = new Fisica(1,1,transformar);
		salud = 20;
	}
	
	@Override
	public void actualizar() {
		tiempoParaSiguienteDisparo -= GameLoop.dt/1000;
		if (tiempoParaSiguienteDisparo <= 0) {
			disparar();
			tiempoParaSiguienteDisparo = TIEMPO_ENTRE_DISPAROS;
		}
		fisica.actualizar();
		colisiona.actualizar();
		super.actualizar();
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
	public void onColision(Entidad entidad) {
		
	}

	@Override
	public void setFisica(Fisica fisica) {
		this.fisica = fisica;
	}

}

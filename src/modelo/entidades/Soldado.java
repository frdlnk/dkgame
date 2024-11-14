package modelo.entidades;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import modelo.armamento.armas.Arma;
import modelo.componentes.Fisica;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.GifMovible;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;
import utils.Movible;

public abstract class Soldado extends GifMovible implements Colisionable, Movible {
	protected double salud;
	private Arma arma;
	protected Fisica fisica;

	public Soldado(String nombre, BufferedImage[] imagenes, Transform transformar, double duracionImagen) {
		super(nombre, imagenes, transformar, duracionImagen);
		salud = 100;
	}
	

	public Soldado(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		salud = 100;
	}
	
	@Override
	public void actualizar() {
		arma.actualizar();
		super.actualizar();
	}

	@Override
	public void dibujar(Graphics g) {
		Vector2D pos = transformar.getPosicion();
		pos.setY(pos.getY()+.5);
		super.dibujar(g);
		pos.setY(pos.getY()-.5);
	}

	public abstract void recibirDano(double dano);
	
	public abstract void morir();
	
	public abstract void disparar();

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	public Fisica getFisica() {
		return fisica;
	}
	
	public double getSalud() {
		return salud;
	}


	public void setSalud(double salud) {
		this.salud = salud;
	}
	
	@Override
	public boolean hayColision(Colisionable entidad) {
		Collider otroCollider = ((Colisionable) entidad).getColisiona();
		return colisiona.colisionaCon(otroCollider);
	}
	
	@Override
	public boolean isTrigger() {
		return false;
	}
}

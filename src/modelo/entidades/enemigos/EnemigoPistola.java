package modelo.entidades.enemigos;

import java.awt.image.BufferedImage;

import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Municion;
import modelo.arrays.ArrayString;
import modelo.entidades.Player;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.util.Vector2D;
import utils.Conf;
import utils.colision.ColisionInfo;
import vista.escena.EscenaJuego;

public class EnemigoPistola extends Enemigo {
	public final static int SPEED = 100;
	private boolean huir;

	public EnemigoPistola(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		setArma(new Pistola());
		getArma().setShootDelay(3);
		getArma().setBalasRestantes(1000);
		salud = 20;
		huir = false;
	}

	@Override
	public void onColision(ColisionInfo colision) {
		if (colision .getEntidad() instanceof Player) {
			huir = puedeHuir(getDistanciaJugador(getCentro()));
		}
	}
	
	@Override
	public void actualizar() {
		if (huir) {
			Vector2D distanciaJugador = getDistanciaJugador(getCentro());
			if (distanciaJugador != null) {
				Vector2D direccion = new Vector2D(distanciaJugador.getX(),0).normalize().scale(-1);
				fisica.addForce(direccion.scale(SPEED*GameLoop.dt));
				huir = puedeHuir(distanciaJugador);
			}
			
		}else if (isInScreen()) {
			disparar();
		}
		super.actualizar();
	}
	
	private boolean puedeHuir(Vector2D distanciaJugador) {
		return Math.abs(distanciaJugador.getX()) < 120 
				&& transformar.getPosicion().getX() > 30
				&& transformar.getPosicion().getX() < Conf.WINDOW_WIDTH-30;
	}

	@Override
	public void disparar() {
		Scene escena = Scene.getEscenaActual();
		if (escena instanceof EscenaJuego) {
			Vector2D posicionDis = posicionDisparo();
			Vector2D direccion = getDireccionJugador(posicionDis);
			ArrayString targetsIgnore = new ArrayString();
			targetsIgnore.add(getNombre());
			Municion disparo = getArma().disparar(posicionDis, direccion, targetsIgnore);
			if(disparo != null) {
				((EscenaJuego) escena).addEntidad(disparo);
			}
		}
	}
	

	@Override
	public void morir() {
		destruir();
	}

	@Override
	protected Vector2D posicionDisparo() {
		int disBalaX = 60;
		int disBalaY = -20;
		Vector2D direccionDisparo = getDireccionJugador(getCentro());
		int x = direccionDisparo.getX() > 0 ? disBalaX : -disBalaX;
		int y = disBalaY;
		
		Vector2D pos = getCentro().add(new Vector2D(x, y));
		return pos;
	}
}

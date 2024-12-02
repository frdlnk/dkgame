package modelo.entidades.enemigos;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import ctrl.gameControlers.Game;
import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Granada;
import modelo.arrays.ArrayString;
import modelo.spritesCargados.SpritesEnemy;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.constants.Assets;
import vista.escena.EscenaJuego;

/**
 * {@link Enemigo} que arroja granadas al jugador
 */
public class EnemigoGranada extends Enemigo {
	// constantes de movimiento
	public final static int VALOR_PUNTAJE = 300;
	public final static int SALUD = 70;
	public final static int SPEED = 80;
	public final static int DANO_BASE = 15;

	// variables de control
	private double tiempoEntreLanzamientos;
	private double siguientePosicion;
	private int siguienteDireccionX;
	private boolean posicionAlcanzada;

	// Sprites
	private SpritesEnemy sprites;

	/**
	 * crea un nuevo Granadero
	 * 
	 * @param imagen         principal para dimensiones y localizacion
	 * @param posicion       inicial del enemigo
	 * @param duracionImagen tiempo de reproducion del gif
	 */
	public EnemigoGranada(BufferedImage imagenes, Transform posicion) {
		super(imagenes, posicion, SALUD, VALOR_PUNTAJE);
		setArma(new Pistola());
		sprites = new SpritesEnemy(this);
		siguientePosicion = 0;
		siguienteDireccionX = 1;
		posicionAlcanzada = true;
		tiempoEntreLanzamientos = 2;
	}

	@Override
	public void onColision(ColisionInfo colision) {
	}

	@Override
	public void morir() {
		super.morir();
		destruir();
	}

	@Override
	public void actualizar() {

		if (isInScreen()) {
			sprites.cambiarAnimacionA(Assets.enemNames[0]);
			if (tiempoEntreLanzamientos < 0 && posicionAlcanzada) {
				disparar();
				sprites.cambiarAnimacionA(Assets.enemNames[2]);
				tiempoEntreLanzamientos = 2;

				Random random = new Random();
				siguienteDireccionX = random.nextBoolean() ? 1 : -1;
				siguientePosicion = random.nextInt(0, 400);
			} else if (!posicionAlcanzada) {
				Vector2D direccion = new Vector2D(siguienteDireccionX, 0);
				fisica.addForce(direccion.scale(SPEED * GameLoop.dt));
				siguientePosicion -= fisica.getVectorMovimiento().getX();
				sprites.cambiarAnimacionA(Assets.enemNames[1]);
			}
			posicionAlcanzada = siguientePosicion < 0;
			tiempoEntreLanzamientos -= GameLoop.dt;
			sprites.actualizar();
			super.actualizar();
		}

	}

	/**
	 * Genera un lanzamiento de una granada con velocidades variadas en base a la
	 * distancia hacia el jugador
	 * 
	 * @implNote El calculo de velocidad y angulo no es exacto hacia el jugador este
	 *           tiene una variacion, aveces tira la granada mas lejos o mas cerca
	 *           del jugador
	 */
	@Override
	public void disparar() {
		// consigue la distncia al jugador
		Vector2D posicionJugador = getDistanciaJugador(getCentro());
		if (posicionJugador != null) {
			Random random = new Random();

			// divide la distancia en sus componentes
			double xf = posicionJugador.getX();
			double yf = posicionJugador.getY();

			double varianzaAngulo = random.nextDouble(0.7, 1.3);

			// Cuando el enemigo esta muy cerca del jugador
			// aumenta su distancia para un calculo de disparo mas acertado
			if (Math.abs(xf) < 70) {
				xf += xf < 0 ? -50 : 50;
			}

			// calculo de velocidad de la granada
			double v0 = (xf + yf) * varianzaAngulo * GameLoop.dt;
			int direccionX = 1;

			// verifica la direccion horizontal de lanzamiento
			if (v0 < 0) {
				direccionX = -1;
				v0 = -v0;
			}

			// la direccion de disparo de la granada siempre son 45 o 135 grados
			Vector2D direccionDisparo = new Vector2D(direccionX, -1);

			// Crea y agrega la granada al escenario
			ArrayString targetsIgnored = new ArrayString();
			targetsIgnored.add(getNombre());
			double dano = DANO_BASE * Game.getConfiguracion().getMultiplicadorDanoEnemigo();
			Granada granada = new Granada(posicionDisparo(), direccionDisparo, targetsIgnored, dano, v0);
			Scene escenaActual = Scene.getEscenaActual();
			if (escenaActual instanceof EscenaJuego) {
				((EscenaJuego) escenaActual).addEntidad(granada);
			}
		}
	}

	@Override
	protected Vector2D posicionDisparo() {
		double direccionJugadorX = getDireccionJugador(getCentro()).getX();
		Vector2D pos = transformar.getPosicion();
		if (direccionJugadorX < 0) {
			double widht = colisiona.getHitbox().getWidth();
			return pos.add(new Vector2D(widht, 0));
		}
		return pos;
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[] { colisiona };
	}

	@Override
	public void dibujar(Graphics g) {
		sprites.dibujar(g);
	}

	public double getTiempoEntreLanzamientos() {
		return tiempoEntreLanzamientos;
	}

	public void setTiempoEntreLanzamientos(double tiempoEntreLanzamientos) {
		this.tiempoEntreLanzamientos = tiempoEntreLanzamientos;
	}

	public double getSiguientePosicion() {
		return siguientePosicion;
	}

	public void setSiguientePosicion(double siguientePosicion) {
		this.siguientePosicion = siguientePosicion;
	}

	public int getSiguienteDireccionX() {
		return siguienteDireccionX;
	}

	public void setSiguienteDireccionX(int siguienteDireccionX) {
		this.siguienteDireccionX = siguienteDireccionX;
	}

	public boolean isPosicionAlcanzada() {
		return posicionAlcanzada;
	}

	public void setPosicionAlcanzada(boolean posicionAlcanzada) {
		this.posicionAlcanzada = posicionAlcanzada;
	}

	public SpritesEnemy getSprites() {
		return sprites;
	}

	public void setSprites(SpritesEnemy sprites) {
		this.sprites = sprites;
	}

	@Override
	public String toString() {
		return "EnemigoGranada [tiempoEntreLanzamientos=" + tiempoEntreLanzamientos + ", siguientePosicion="
				+ siguientePosicion + ", siguienteDireccionX=" + siguienteDireccionX + ", posicionAlcanzada="
				+ posicionAlcanzada + ", sprites=" + sprites + "]";
	}

}

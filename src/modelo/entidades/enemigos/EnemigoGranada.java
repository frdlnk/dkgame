package modelo.entidades.enemigos;

import java.awt.image.BufferedImage;
import java.util.Random;

import modelo.armamento.armas.Pistola;
import modelo.armamento.municiones.Granada;
import modelo.arrays.ArrayString;
import modelo.entidades.Player;
import motor_v1.motor.GameLoop;
import motor_v1.motor.Scene;
import motor_v1.motor.util.Vector2D;
import utils.Conf;
import utils.Tags;
import utils.colision.ColisionInfo;
import vista.escena.EscenaJuego;

public class EnemigoGranada extends Enemigo{
	public final static int SPEED = 80;
	private double tiempoEntreLanzamientos;
	private double siguientePosicion;
	private int siguienteDireccionX;
	private boolean posicionAlcanzada;

	public EnemigoGranada(String nombre, BufferedImage[] imagenes, Vector2D posicion, double duracionImagen) {
		super(nombre, imagenes, posicion, duracionImagen);
		setArma(new Pistola());
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
		setViva(false);
	}
	
	@Override
	public void actualizar() {
		
		if (isInScreen()) {
			if (tiempoEntreLanzamientos < 0 && posicionAlcanzada) {
				disparar();
				tiempoEntreLanzamientos = 2;
				
				Random random = new Random();
				siguienteDireccionX = random.nextBoolean() ? 1 : -1;
				siguientePosicion = random.nextInt(-200,200);
			}else if(!posicionAlcanzada){
				Vector2D direccion = new Vector2D(siguienteDireccionX,0);
				fisica.addForce(direccion.scale(SPEED*GameLoop.dt));
				siguientePosicion -= SPEED*GameLoop.dt;
			}
			posicionAlcanzada = siguientePosicion < 0;
			tiempoEntreLanzamientos -= GameLoop.dt;
			super.actualizar();
		}
		
	}

	/**
	 * Genera un lanzamiento de una granada con velocidades variadas
	 * en base a la distancia hacia el jugador
	 * 
	 * @implNote El calculo de velocidad y angulo no es exacto hacia el jugador
	 * este tiene una variacion, aveces tira la granada mas lejos o mas cerca del jugador
	 */
	@Override
	public void disparar() {
		//consigue la distncia al jugador
		Scene escenaActual = Scene.getEscenaActual();
		Vector2D posicionJugador = getDistanciaJugador(getCentro());
		if (posicionJugador != null) {
			Random random = new Random();
			
			//divide la distancia en sus componentes
			double xf = posicionJugador.getX();
			double yf = posicionJugador.getY();
			
			double varianzaAngulo = random.nextDouble(0.7,1.3);
			
			//Cuando el enemigo esta muy cerca del jugador 
			//aumenta su distancia para un calculo de disparo mas acertado
			if (Math.abs(xf) < 70) {
				xf += xf<0 ? -50 : 50;
			}
			
			//calculo de velocidad de la granada
			double v0 = (xf+yf)*varianzaAngulo*GameLoop.dt;
			int direccionX = 1;
			
			//verifica la direccion horizontal de lanzamiento
			if (v0 < 0) {
				direccionX = -1;
				v0 = -v0;
			}
			
			//la direccion de disparo de la granada siempre son 45 o 135 grados
			Vector2D direccionDisparo = new Vector2D(direccionX,-1);
			
			//Crea y agrega la granada al escenario
			ArrayString targetsIgnored = new ArrayString();
			targetsIgnored.add(getNombre());
			int dano = 10;
			Granada granada = new Granada(Tags.EXPLOCION, posicionDisparo(), direccionDisparo, targetsIgnored, dano, v0);
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
			return pos.add(new Vector2D(widht,0));
		}
		return pos;
	}
}

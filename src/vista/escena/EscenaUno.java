package vista.escena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.entidades.Player;
import modelo.entidades.enemigos.Enemigo;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.Colisionable;
import utils.Tags;
import vista.mapas.Map;
import vista.mapas.MapaNivel1;

public class EscenaUno extends EscenaJuego{
	private Map mapa;
	private Player jugador1;
	
	public EscenaUno() {
		
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,0);
		BufferedImage[] image = {Renderer.crearTextura(rect, color)};
		Vector2D posicionJ = new Vector2D(10,180);
		this.jugador1 = new Player(Tags.PLAYER, image, posicionJ, 10);
		
		mapa = new MapaNivel1();
		
		entidades.add(mapa.getNombre(), mapa);
		entidades.add(jugador1.getNombre(), jugador1);
	}
	
	@Override
	public void actualizar() {
		mapa.actualizar();
		if (!mapa.isChangingZone()) {
			entidades.actualizar();
			calcularColisiones();
			calcularColisionesMapa(jugador1);
			entidades.destruir();
		}
	}

	@Override
	public void calcularColisiones() {
		super.calcularColisiones();
		for (int i = 0; i < entidades.getSize(); i++) {
			if (entidades.get(i) instanceof Colisionable) {
				calcularColisionesMapa((Colisionable)entidades.get(i));
			}
		}
	}

	@Override
	public void destruir() {
		entidades.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		mapa.dibujar(g);
		entidades.dibujar(g);
	}
	
	public void addEntidad(Entidad entidad) {
		if (entidad != null) {
			mapa.getZonaActual().addMapObjects(entidad);
		}
	}
	
	
	public void calcularColisionesMapa(Colisionable colisionable) {
		mapa.generarColisiones(colisionable);
	}
	
	public Player getJugador1() {
		return jugador1;
	}

	public void setJugador1(Player jugador1) {
		this.jugador1 = jugador1;
	}

	@Override
	public Player getPlayer() {
		return jugador1;
	}
	

	
}

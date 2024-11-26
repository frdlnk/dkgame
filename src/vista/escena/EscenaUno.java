package vista.escena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.GameControler;
import modelo.entidades.Player;
import modelo.worldObjects.MovementBarrier;
import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.Tags;
import utils.colision.ColisionUtils;
import vista.zonas.Zona;
import vista.zonas.Zona1N1;

public class EscenaUno extends EscenaJuego{
	private Zona zona;
	private Player jugador1;
	
	public EscenaUno() {
		
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,0);
		BufferedImage[] image = {Renderer.crearTextura(rect, color)};
		Vector2D posicionJ = new Vector2D(10,180);
		this.jugador1 = new Player(Tags.PLAYER, image, posicionJ, 10);
		
		zona = new Zona1N1();
		
		entidades.add(zona.getNombre(), zona);
		entidades.add(jugador1.getNombre(), jugador1);
	}
	
	@Override
	public void actualizar() {
		entidades.actualizar();
		entidades.destruir();
		calcularColisiones();
		if (zona.enemigosrestantes() == 0) {
			GameControler.detener();
		}
	}

	@Override
	public void calcularColisiones() {
		super.calcularColisiones();
		ColisionUtils.colisionResolve(jugador1, zona);
	}

	@Override
	public void destruir() {
		entidades.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		zona.dibujar(g);
		entidades.dibujar(g);
	}
	
	public void addEntidad(Entidad entidad) {
		if (entidad != null) {
			zona.addMapObjects(entidad);
		}
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

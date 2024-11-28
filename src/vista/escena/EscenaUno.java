package vista.escena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import ctrl.gameControlers.Game;
import ctrl.gameControlers.GameControler;
import modelo.entidades.Player;
import modelo.worldObjects.MovementBarrier;
import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionUtils;
import utils.constants.Tags;
import vista.zonas.Zona;
import vista.zonas.Zona1N1;

public class EscenaUno extends EscenaJuego{
	private Zona zona;
	private Player jugador1;
	
	public EscenaUno() {
		
		jugador1 = Game.getJugador();
		
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
	public void destruir() {
		entidades.destruir();
	}

	@Override
	public void dibujar(Graphics g) {
		entidades.dibujar(g);
	}
	
	public void addEntidad(Entidad entidad) {
		if (entidad != null) {
			zona.addMapObjects(entidad);
		}
	}
	
}

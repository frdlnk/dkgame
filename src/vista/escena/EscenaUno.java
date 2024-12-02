package vista.escena;

import java.awt.Graphics;

import ctrl.gameControlers.Game;
import modelo.entidades.Player;
import motor_v1.motor.Entidad;
import motor_v1.motor.Scene;
import vista.zonas.Zona;
import vista.zonas.Zona1N1;

public class EscenaUno extends EscenaJuego{
	private Zona zona;
	private Player jugador1;
	
	public EscenaUno() {
		
		jugador1 = Game.getJugador();
		
		zona = new Zona1N1(true);
		
		entidades.add(zona.getNombre(), zona);
		entidades.add(jugador1.getNombre(), jugador1);
	}
	
	@Override
	public void actualizar() {
		entidades.actualizar();
		entidades.destruir();
		calcularColisiones();
		
		if (!jugador1.getViva()) {
			Scene.cambiarEscena(new EscenaPerder());
		}
		if (zona.enemigosrestantes() == 0) {
			Scene.cambiarEscena(new EscenaGanar());
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

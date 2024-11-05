package vista.escena;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.armamento.Explosion;
import modelo.entidades.Enemigo;
import modelo.entidades.Player;
import modelo.maps.Map;
import modelo.maps.MapaNivel1;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.entidades.ListaEntidades;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.ColisionUtils;
import utils.Colisionable;
import utils.Tags;

public class EscenaUno extends EscenaJuego{
	public static ListaEntidades entidades = new ListaEntidades();
	private Map mapa;
	private Player jugador1;
	
	public EscenaUno() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0);
		BufferedImage[] image = {Renderer.crearTextura(rect, color)};
		Vector2D posicionJ = new Vector2D(10,180);
		this.jugador1 = new Player(Tags.PLAYER.getTag(), image, posicionJ, 10);
		
		BufferedImage[] imageE = {Renderer.crearTextura(rect, color)};
		Vector2D posicionE = new Vector2D(300,200);
		Enemigo enemy = new Enemigo(Tags.ENEMY.getTag(), imageE, posicionE, 10);
		
		mapa = new MapaNivel1();
		
		
		entidades.add(jugador1.getNombre(), jugador1);
		entidades.add(enemy.getNombre(), enemy);
	}
	
	@Override
	public void actualizar() {
		if (InputKeyboard.isDown(Key.L)) {
			mapa.nextZone();
		}
		mapa.actualizar();
		if (!mapa.isChangingZone()) {
			entidades.actualizar();
			calcularColisiones();
			entidades.destruir();
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
		System.err.println("add" + entidad.getNombre());
		if (entidad != null) {
			entidades.add(entidad.getNombre(), entidad);
		}
	}
	
	public void calcularColisiones() {
		for (int i = 0; i < entidades.getSize(); i++) {
			if (entidades.get(i) instanceof Colisionable) {
				Colisionable colisionable1 = (Colisionable) entidades.get(i);
				calcularColisionesMapa(colisionable1, entidades.get(i));
				for (int j = i+1; j < entidades.getSize(); j++) {
					if(entidades.get(j) instanceof Colisionable) {
						if (entidades.get(j) instanceof Explosion) {
							System.out.println(entidades.get(i).getNombre());
						}
						Colisionable colisionable2 = (Colisionable) entidades.get(j);
						ColisionUtils.colisionResolve(colisionable1, colisionable2, entidades.get(i), entidades.get(j));
					}
				}
			}
		}
	}
	
	public void calcularColisionesMapa(Colisionable colisionable, Entidad entidad) {
		mapa.generarColisiones(colisionable, entidad);
	}
	
	public Player getJugador1() {
		return jugador1;
	}

	public void setJugador1(Player jugador1) {
		this.jugador1 = jugador1;
	}
	
	public static void setListaEntidades(ListaEntidades entidades) {
		EscenaUno.entidades = entidades;
	}

	public ListaEntidades getEntidades() {
		return entidades;
	}

	
}

package vista.zonas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.worldObjects.Caja;
import modelo.worldObjects.DeadBox;
import modelo.worldObjects.MovementBarrier;
import modelo.worldObjects.Plataforma;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.Assets;
import utils.Conf;
import utils.Tags;

public class Zona1N1 extends Zona{
	public final static double limit = -100000;
	MovementBarrier barrier;
	Sprite bac;
	Caja plat1;
	
	public Zona1N1() {
		super(Vector2D.RIGHT, new Vector2D(0,0));
	}

	@Override
	protected void crearComponentes() {
		bac  = new Sprite(Tags.STATIC_OBJECT, Assets.MAPA_NIVEL_1, new Vector2D(0,-224));
		bac.getTransformar().escalarloA(2);
		
		//plataforma inicial
		Rectangle rectFloor = new Rectangle(1350, 50);
		Color colorFloor = new Color(128,0,0,40);
		BufferedImage imageFloor = Renderer.crearTextura(rectFloor, colorFloor);
		Vector2D posicionF = new Vector2D(0,343);
		Caja platform1 = new Caja("floor", imageFloor, posicionF);
		platform1.getColisiona().actualizar();;
		
		//segundo piso despues del puente
		Rectangle rectFloor3 = new Rectangle(2_345, 50);
		Color colorFloor3 = new Color(128,0,0,40);
		BufferedImage imageFloor3 = Renderer.crearTextura(rectFloor3, colorFloor3);
		Vector2D posicionF3 = new Vector2D(1351,420);
		Caja platform3 = new Caja("floor", imageFloor3, posicionF3);
		platform3.getColisiona().actualizar();
		
		//primera plataforma avion
		Rectangle rectPl1Avion = new Rectangle(123, 10);
		Color colorPl1Avion = new Color(128,50,0);
		BufferedImage imagePl1Avion = Renderer.crearTextura(rectPl1Avion, colorPl1Avion);
		Vector2D posicionPl1Avion = new Vector2D(1713,330);
		Plataforma pl_1Avion = new Plataforma(Tags.PLATFORM, imagePl1Avion, posicionPl1Avion);
		pl_1Avion.getColisiona().actualizar();
		
		//segunda plataforma Avion
		Rectangle rectPl2Avion = new Rectangle(350, 10);
		Color colorPl2Avion = new Color(128,50,0);
		BufferedImage imagePl2Avion = Renderer.crearTextura(rectPl2Avion, colorPl2Avion);
		Vector2D posicionPl2Avion = new Vector2D(1880,251);
		Plataforma pl_2Avion = new Plataforma(Tags.PLATFORM, imagePl2Avion, posicionPl2Avion);
		pl_2Avion.getColisiona().actualizar();
		plat1 = pl_2Avion;
		
		//tercera plataforma avion
		Rectangle rectPl3Avion = new Rectangle(123, 10);
		Color colorPl3Avion = new Color(128,50,0);
		BufferedImage imagePl3Avion = Renderer.crearTextura(rectPl3Avion, colorPl3Avion);
		Vector2D posicionPl3Avion = new Vector2D(2_225.5,330);
		Plataforma pl_3Avion = new Plataforma(Tags.PLATFORM, imagePl3Avion, posicionPl3Avion);
		pl_3Avion.getColisiona().actualizar();
		
		
		Rectangle rectDead = new Rectangle(60, 60);
		Color colorDead = new Color(128,200,0,80);
		BufferedImage imageDead = Renderer.crearTextura(rectDead, colorDead);
		Vector2D posicionDead = new Vector2D(400,280-80);
		DeadBox dead = new DeadBox("dead", imageDead, posicionDead);
		dead.getColisiona().actualizar();
		
		//barrera de movimiento
		Rectangle rectMB = new Rectangle(Conf.WINDOW_HEIGT, Conf.WINDOW_WIDHT/2);
		Color colorMB = new Color(128,200,100,90);
		BufferedImage imageMB = Renderer.crearTextura(rectMB, colorMB);
		Vector2D posicionMB = new Vector2D(Conf.WINDOW_WIDHT/2,0);
		MovementBarrier barrier = new MovementBarrier("barrier", imageMB, posicionMB);
		barrier.getColisiona().actualizar();
		this.barrier = barrier;
		
		//mapObjects.add(dead.getNombre(), dead);
		mapObjects.add(bac.getNombre(), bac);
		mapObjects.add(pl_1Avion.getNombre(), pl_1Avion);
		mapObjects.add(pl_2Avion.getNombre(), pl_2Avion);
		mapObjects.add(pl_3Avion.getNombre(), pl_3Avion);
		//mapObjects.add(caja1.getNombre(), caja1);
		staticObjects.add(barrier.getNombre(), barrier);
		mapObjects.add(platform1.getNombre(), platform1);
		mapObjects.add(platform3.getNombre(), platform3);
	}

	private double contx =0;
	private double conty =0;
	@Override
	public void actualizar() {
		if (InputKeyboard.isKeyPressed(Key.UP)) {
			conty += -0.5;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(0,-0.5));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.DOWN)) {
			conty += 0.5;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(0,0.5));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.LEFT)) {
			contx += -0.5;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(-0.5,0));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(contx);
		}
		if (InputKeyboard.isKeyPressed(Key.RIGHT)) {
			contx += 0.5;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(0.5,0));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(contx);
		}
		plat1.getColisiona().actualizar();
		
		if (barrier.isPlayerOverlap() && getPosition().getX() > limit) {
			moverZona(getDireccionMovimiento().scale(-1));
		}
		if (getPosition().getX() < limit) {
			barrier.destruir();
			staticObjects.destruir();
		}
		super.actualizar();
	}
	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
	}
}

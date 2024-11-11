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
		System.out.println(Assets.MAPA_NIVEL_1);
		bac  = new Sprite(Tags.STATIC_OBJECT, Assets.MAPA_NIVEL_1, new Vector2D(0,-224));
		bac.getTransformar().escalarloA(2);
		mapObjects.add(bac.getNombre(), bac);
		//plataforma inicial
		Rectangle rectFloor = new Rectangle(1350, 50);
		Color colorFloor = new Color(128,0,0,40);
		BufferedImage imageFloor = Renderer.crearTextura(rectFloor, colorFloor);
		Vector2D posicionF = new Vector2D(0,343);
		Caja platform1 = new Caja("floor", imageFloor, posicionF);
		platform1.getColisiona().actualizar();
		
		//segundo piso despues del puente
		Rectangle rectFloor3 = new Rectangle(6000, 50);
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
		
		//tercera plataforma avion
		Rectangle rectPl3Avion = new Rectangle(123, 10);
		Color colorPl3Avion = new Color(128,50,0);
		BufferedImage imagePl3Avion = Renderer.crearTextura(rectPl3Avion, colorPl3Avion);
		Vector2D posicionPl3Avion = new Vector2D(2_225.5,330);
		Plataforma pl_3Avion = new Plataforma(Tags.PLATFORM, imagePl3Avion, posicionPl3Avion);
		pl_3Avion.getColisiona().actualizar();
		
		Rectangle rectPl4Avion = new Rectangle(274, 10);
		Color colorPl4Avion = new Color(128,50,0);
		BufferedImage imagePl4Avion = Renderer.crearTextura(rectPl4Avion, colorPl4Avion);
		Vector2D posicionPl4Avion = new Vector2D(2_350,246);
		Plataforma pl_4Avion = new Plataforma(Tags.PLATFORM, imagePl4Avion, posicionPl4Avion);
		pl_4Avion.getColisiona().actualizar();
		
		Rectangle rectPl5Avion = new Rectangle(309, 10);
		Color colorPl5Avion = new Color(128,50,0);
		BufferedImage imagePl5Avion = Renderer.crearTextura(rectPl5Avion, colorPl5Avion);
		Vector2D posicionPl5Avion = new Vector2D(2_725,242);
		Plataforma pl_5Avion = new Plataforma(Tags.PLATFORM, imagePl5Avion, posicionPl5Avion);
		pl_5Avion.getColisiona().actualizar();
		
		Rectangle rectPl6Avion = new Rectangle(122, 10);
		Color colorPl6Avion = new Color(128,50,0);
		BufferedImage imagePl6Avion = Renderer.crearTextura(rectPl6Avion, colorPl6Avion);
		Vector2D posicionPl6Avion = new Vector2D(3_060,326);
		Plataforma pl_6Avion = new Plataforma(Tags.PLATFORM, imagePl6Avion, posicionPl6Avion);
		pl_6Avion.getColisiona().actualizar();
		
		Rectangle rectPl7Avion = new Rectangle(122, 10);
		Color colorPl7Avion = new Color(128,50,0);
		BufferedImage imagePl7Avion = Renderer.crearTextura(rectPl7Avion, colorPl7Avion);
		Vector2D posicionPl7Avion = new Vector2D(3_060,326);
		Plataforma pl_7Avion = new Plataforma(Tags.PLATFORM, imagePl7Avion, posicionPl7Avion);
		pl_7Avion.getColisiona().actualizar();
		plat1 = pl_7Avion;
		
		Vector2D posicionPl1Pantano = new Vector2D(4087,346);
		createPlatform(posicionPl1Pantano,122,10);
	
		Vector2D posicionPl2Pantano = new Vector2D(4144,258);
		createPlatform(posicionPl2Pantano,122,10);
	
		Vector2D posicionPl3Pantano = new Vector2D(4320,161);
		createPlatform(posicionPl3Pantano,122,10);
	
		Vector2D posicionPl4Pantano = new Vector2D(4528,260);
		createPlatform(posicionPl4Pantano,122,10);
	
		Vector2D posicionPl5Pantano = new Vector2D(4704,180);
		createPlatform(posicionPl5Pantano,122,10);
	
		Vector2D posicionPl6Pantano = new Vector2D(4794,161);
		createPlatform(posicionPl6Pantano,122,10);
	
		Vector2D posicionPl7Pantano = new Vector2D(4896,321);
		createPlatform(posicionPl7Pantano,122,10);
	
		Vector2D posicionPl8Pantano = new Vector2D(4929,241);
		createPlatform(posicionPl8Pantano,122,10);
	
		Vector2D posicionPl9Pantano = new Vector2D(5166,243);
		createPlatform(posicionPl9Pantano,122,10);
	
		Vector2D posicionPl10Pantano = new Vector2D(5393,161);
		createPlatform(posicionPl10Pantano,122,10);
	
		Vector2D posicionPl11Pantano = new Vector2D(5611,291);
		createPlatform(posicionPl11Pantano,122,10);
	
		Vector2D posicionPl12Pantano = new Vector2D(5778,322);
		createPlatform(posicionPl12Pantano,122,10);
	
		
		//barrera de movimiento
		Rectangle rectMB = new Rectangle(Conf.WINDOW_HEIGHT, Conf.WINDOW_WIDTH/2);
		Color colorMB = new Color(128,200,100,90);
		BufferedImage imageMB = Renderer.crearTextura(rectMB, colorMB);
		Vector2D posicionMB = new Vector2D(Conf.WINDOW_WIDTH/2,0);
		MovementBarrier barrier = new MovementBarrier("barrier", imageMB, posicionMB);
		barrier.getColisiona().actualizar();
		this.barrier = barrier;
		
		//mapObjects.add(dead.getNombre(), dead);
		
		mapObjects.add(pl_1Avion.getNombre(), pl_1Avion);
		mapObjects.add(pl_2Avion.getNombre(), pl_2Avion);
		mapObjects.add(pl_3Avion.getNombre(), pl_3Avion);
		mapObjects.add(pl_4Avion.getNombre(), pl_4Avion);
		mapObjects.add(pl_5Avion.getNombre(), pl_5Avion);
		mapObjects.add(pl_6Avion.getNombre(), pl_6Avion);
		mapObjects.add(pl_7Avion.getNombre(), pl_7Avion);
		//mapObjects.add(caja1.getNombre(), caja1);
		staticObjects.add(barrier.getNombre(), barrier);
		mapObjects.add(platform1.getNombre(), platform1);
		mapObjects.add(platform3.getNombre(), platform3);
	}

	private void createPlatform(Vector2D pos, int width, int height){
		Rectangle dimensiones = new Rectangle(width, height);
		Color color = new Color(128,50,0);
		BufferedImage image = Renderer.crearTextura(dimensiones, color);
		Plataforma platform = new Plataforma(Tags.PLATFORM, image, pos);
		platform.getColisiona().actualizar();
		plat1 = platform;
		mapObjects.add(platform.getNombre(), platform);
	}

	private double contx =0;
	private double conty =0;
	@Override
	public void actualizar() {
		double distance = 0.5;
		if (InputKeyboard.isDown(Key.SHIFT)) {
			distance = 5;
		}
		if (InputKeyboard.isKeyPressed(Key.UP)) {
			conty += -distance;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(0,-distance));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.DOWN)) {
			conty += distance;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(0,distance));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(conty);
		}
		if (InputKeyboard.isKeyPressed(Key.LEFT)) {
			contx += -distance;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(-distance,0));
			plat1.getTransformar().setPosicion(newpos);
			System.out.println(contx);
		}
		if (InputKeyboard.isKeyPressed(Key.RIGHT)) {
			contx += distance;
			Vector2D newpos = plat1.getTransformar().getPosicion().add(new Vector2D(distance,0));
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

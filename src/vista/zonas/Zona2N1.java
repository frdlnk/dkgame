package vista.zonas;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.worldObjects.Caja;
import modelo.worldObjects.DeadBox;
import modelo.worldObjects.Plataforma;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.util.Vector2D;
import utils.Conf;

public class Zona2N1 extends Zona {

	public Zona2N1() {
		super(Vector2D.LEFT, new Vector2D(Conf.WINDOW_WIDHT,0));
	}

	@Override
	protected void crearComponentes() {
		Rectangle rectFloor = new Rectangle(Conf.WINDOW_WIDHT, 10);
		Color colorFloor = new Color(80,70,0);
		BufferedImage imageFloor = Renderer.crearTextura(rectFloor, colorFloor);
		Vector2D posicionF = new Vector2D(0,280);
		Plataforma platform1 = new Plataforma("floor", imageFloor, posicionF);
		platform1.getColisiona().getHitbox().setLocation(0,280);
		
		Rectangle rectFloor2 = new Rectangle(80, 10);
		Color colorFloor2 = new Color(128,160,0);
		BufferedImage imageFloor2 = Renderer.crearTextura(rectFloor2, colorFloor2);
		Vector2D posicionF2 = new Vector2D(500,180);
		Plataforma platform2 = new Plataforma("floor", imageFloor2, posicionF2);
		platform2.getColisiona().getHitbox().setLocation(500,180);
		
		Rectangle rectCaja = new Rectangle(60, 60);
		Color colorCaja = new Color(0,200,0,100);
		BufferedImage imageCaja = Renderer.crearTextura(rectCaja, colorCaja);
		Vector2D posicionCaja = new Vector2D(600,280-80);
		Caja caja1 = new Caja("caja", imageCaja, posicionCaja);
		caja1.getColisiona().actualizar();
		
		Rectangle rectDead = new Rectangle(60, 60);
		Color colorDead = new Color(128,100,0,80);
		BufferedImage imageDead = Renderer.crearTextura(rectDead, colorDead);
		Vector2D posicionDead = new Vector2D(400,280-80);
		DeadBox dead = new DeadBox("dead", imageDead, posicionDead);
		dead.getColisiona().actualizar();
		
		mapObjects.add(dead.getNombre(), dead);
		mapObjects.add(platform1.getNombre(), platform1);
		mapObjects.add(platform2.getNombre(), platform2);
		mapObjects.add(caja1.getNombre(), caja1);
	}


}

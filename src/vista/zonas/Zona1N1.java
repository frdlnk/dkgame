package vista.zonas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.RelativeTransform;
import modelo.entidades.Soldado;
import modelo.entidades.enemigos.EnemigoGranada;
import modelo.entidades.enemigos.EnemigoPistola;
import modelo.entidades.enemigos.Helicoptero;
import modelo.worldObjects.Caja;
import modelo.worldObjects.DeadBox;
import modelo.worldObjects.MovementBarrier;
import modelo.worldObjects.Plataforma;
import motor_v1.motor.Entidad;
import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.input.InputKeyboard;
import motor_v1.motor.input.Key;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Assets;
import utils.constants.Conf;
import utils.constants.EnemyTypes;
import utils.constants.Tags;
import utils.interfaces.BorderDrawAble;


/**
 * Zona correspondiente al nivel 1, cuenta con 3 jefes, y cuenta con movimiento de camara para el avance del escenario
 * 
 * Cuenta con modo diseñador para la colocacion de estructuras del mapa;
 * 
 * @author Joshua Elizondo Vasquez
 * 
 * @see Zona
 */
public class Zona1N1 extends Zona{
	//constantes
	public final static int CANTIDAD_JEFES = 3;
	
	//variables de control para el movimiento de pantalla
	private int[] checkPoints = {-3040,-5650, -6300, -6700, -7225, -7500};
	private Vector2D[] direccionChecpoints = {
		Vector2D.LEFT,Vector2D.LEFT, Vector2D.LEFT, 
		new Vector2D(-1, .25), new Vector2D(-.5,.1), Vector2D.LEFT
	};
	private int actualCheckpoint;
	MovementBarrier barrier;
	
	//Variables del modo diseño
	boolean modoDiseno = true;
	Sprite plat1;
	int actualPlatform;
	private double contx;
	private double conty;
	double changeplatDelay;

	//Entidades principales
	Sprite background;
	private Entidad[] jefes;
	private int jefeActual;
	
	
	/**
	 * Constructor sin parametros 
	 */
	public Zona1N1() {
		super(Vector2D.RIGHT, new Vector2D(0,0));
		changeplatDelay = 0;
		actualCheckpoint = 0;
		setDireccionMovimiento(Vector2D.LEFT);
	}

	@Override
	protected void crearComponentes() {
		Transform transformBG = new RelativeTransform(new Vector2D(0,-224), transformar);
		background  = new Sprite(Tags.STATIC_OBJECT, Assets.MAPA_NIVEL_1, transformBG);
		background.getTransformar().escalarloA(2);
		mapObjects.add(background.getNombre(), background);
		Transform transformAV = new RelativeTransform(new Vector2D(6511,18), transformar);
		Sprite avion  = new Sprite(Tags.STATIC_OBJECT, Assets.AVION_CATARATA_M1, transformAV);
		avion.getTransformar().escalarloA(2);
		mapObjects.add(avion.getNombre(), avion);
		
		
		//plataforma inicial
		Vector2D posicionF = new Vector2D(0,343);
		createCaja(posicionF, 1350, 50);
		
		//segundo piso despues del puente
		Vector2D posicionF3 = new Vector2D(1351,420);
		createCaja(posicionF3, 6000, 50);
		
		//primera plataforma avion
		Vector2D posicionPl1Avion = new Vector2D(1713,330);
		createPlatform(posicionPl1Avion, 123, 10);
		
		//segunda plataforma Avion
		Vector2D posicionPl2Avion = new Vector2D(1880,251);
		createPlatform(posicionPl2Avion, 350, 10);
		
		//tercera plataforma avion
		Vector2D posicionPl3Avion = new Vector2D(2_225.5,330);
		createPlatform(posicionPl3Avion, 123, 10);
		
		//Cuarta plataforma Avion
		Vector2D posicionPl4Avion = new Vector2D(2_350,246);
		createPlatform(posicionPl4Avion, 274, 10);
		
		// quinta plataforma Avion
		Vector2D posicionPl5Avion = new Vector2D(2_725,242);
		createPlatform(posicionPl5Avion, 309, 10);
		
		//Sexta plataforma Avion
		Vector2D posicionPl6Avion = new Vector2D(3_060,326);
		createPlatform(posicionPl6Avion, 122, 10);
		
		//Septima Plataforma Avion
		Vector2D posicionPl7Avion = new Vector2D(3_060,326);
		createPlatform(posicionPl7Avion, 122, 10);
		
		//Primera plataforma Pantano
		Vector2D posicionPl1Pantano = new Vector2D(4087,346);
		createPlatform(posicionPl1Pantano,122,10);
	
		//Segunda Plataforma Pantano
		Vector2D posicionPl2Pantano = new Vector2D(4144,258);
		createPlatform(posicionPl2Pantano,122,10);
	
		//Tercera Plataforma Pantano
		Vector2D posicionPl3Pantano = new Vector2D(4320,161);
		createPlatform(posicionPl3Pantano,122,10);
	
		//Cuarta Plataforma Pantano
		Vector2D posicionPl4Pantano = new Vector2D(4528,260);
		createPlatform(posicionPl4Pantano,122,10);
	
		//Quinta plataforma Pantano
		Vector2D posicionPl5Pantano = new Vector2D(4704,180);
		createPlatform(posicionPl5Pantano,122,10);
	
		//Sexta plataforma Pantano
		Vector2D posicionPl6Pantano = new Vector2D(4794,161);
		createPlatform(posicionPl6Pantano,122,10);
	
		//Septima plataforma Pantano
		Vector2D posicionPl7Pantano = new Vector2D(4896,321);
		createPlatform(posicionPl7Pantano,122,10);
	
		//Octava plataforma Pantano
		Vector2D posicionPl8Pantano = new Vector2D(4929,241);
		createPlatform(posicionPl8Pantano,122,10);
	
		//Novena plataforma Pantano
		Vector2D posicionPl9Pantano = new Vector2D(5166,243);
		createPlatform(posicionPl9Pantano,122,10);
	
		//Decima plataforma Pantano
		Vector2D posicionPl10Pantano = new Vector2D(5393,161);
		createPlatform(posicionPl10Pantano,122,10);
	
		//Undecima plataforma Pantano
		Vector2D posicionPl11Pantano = new Vector2D(5611,291);
		createPlatform(posicionPl11Pantano,122,10);
	
		//Duodecima plataforma Pantano
		Vector2D posicionPl12Pantano = new Vector2D(5778,322);
		createPlatform(posicionPl12Pantano,122,10);
		
		//Primer escalon Avion Catarata
		Vector2D posicionCaja1Avion2 = new Vector2D(6633, 321);
		createCaja(posicionCaja1Avion2, 200, 200);
		
		//Segundo escalon avion catarata
		Vector2D posicionCaja2Avion2 = new Vector2D(6803, 261);
		createCaja(posicionCaja2Avion2, 200, 200);
		
		//Tercer escalon Avion Catarata
		Vector2D posicionCaja3Avion2 = new Vector2D(6957, 200);
		createCaja(posicionCaja3Avion2, 200, 200);
		
		//Piso final catarata
		Vector2D posicionCaja4Avion2 = new Vector2D(7114, 129);
		plat1 = createCaja(posicionCaja4Avion2, 2000, 200);
		actualPlatform = mapObjects.getSize()-1;
		
		//barrera de movimiento, para el avance por pantalla
		Rectangle rectMB = new Rectangle(Conf.WINDOW_WIDTH/2, Conf.WINDOW_HEIGHT);
		Color colorMB = new Color(128,200,100,90);
		BufferedImage imageMB = Renderer.crearTextura(rectMB, colorMB);
		Vector2D posicionMB = new Vector2D(Conf.WINDOW_WIDTH/2,0);
		Transform transformMB = new Transform(posicionMB);
		barrier = new MovementBarrier(imageMB, transformMB);
		barrier.getColisiona().actualizar();
		barrier.setTrigger(false);
		
		//deadBox
		Rectangle rectDB = new Rectangle(Conf.WINDOW_WIDTH, 1000);
		Color colorDB = new Color(255,0,0,90);
		BufferedImage imageDB = Renderer.crearTextura(rectDB, colorDB);
		Vector2D posicionDB = new Vector2D(0,Conf.WINDOW_HEIGHT+40);
		Transform transformDb = new Transform(posicionDB);
		DeadBox deadBox = new DeadBox(imageDB, transformDb);
		deadBox.getColisiona().actualizar();
		
		staticObjects.add(deadBox.getNombre(), deadBox);
		staticObjects.add(barrier.getNombre(), barrier);
	}

	private Plataforma createPlatform(Vector2D pos, int width, int height){
		Rectangle dimensiones = new Rectangle(width, height);
		Color color = new Color(128,50,0);
		BufferedImage image = Renderer.crearTextura(dimensiones, color);
		Transform transform = new RelativeTransform(pos,this.transformar);
		Plataforma platform = new Plataforma(image, transform);
		platform.getColisiona().actualizar();
		mapObjects.add(platform.getNombre(), platform);
		return platform;
	}
	
	private Caja createCaja(Vector2D pos, int width, int height){
		Rectangle dimensiones = new Rectangle(width, height);
		Color color = new Color(128,50,0, 50);
		BufferedImage image = Renderer.crearTextura(dimensiones, color);
		Transform transform = new RelativeTransform(pos,this.transformar);
		Caja caja = new Caja(Tags.STATIC_OBJECT, image, transform);
		caja.getColisiona().actualizar();
		mapObjects.add(caja.getNombre(), caja);
		return caja;
	}

	@Override
	public void actualizar() {
		actualizarModoDiseno();
		
		if (jefeActual < jefes.length && !jefes[jefeActual].getViva()) {
			if (!barrier.isEnable()) {
				barrier.getTransformar().getPosicion().setX(Conf.WINDOW_WIDTH-1);
			}
			
			if (actualCheckpoint < checkPoints.length-1)actualCheckpoint++;
			jefeActual++;
		}
		
		if (barrier.isPlayerOverlap() && getTransformar().getPosicion().getX() > checkPoints[actualCheckpoint]) {
			moverZona(direccionChecpoints[actualCheckpoint]);
			if (barrier.getTransformar().getPosicion().getX() > Conf.WINDOW_WIDTH/2) {
				Vector2D posBarrier = barrier.getTransformar().getPosicion();
				posBarrier.setX(posBarrier.getX()-6.5);
			}
		}
		
		if(getTransformar().getPosicion().getX() >= checkPoints[actualCheckpoint]) {
			barrier.setEnable(true);
		}else {
			barrier.setEnable(false);
			if (actualCheckpoint == 2 || actualCheckpoint == 3 || actualCheckpoint == 4) {
				actualCheckpoint++;
			}
		}
		
		destruirEnemigosResagados();
		super.actualizar();
	}
	
	private void actualizarModoDiseno() {
		if (InputKeyboard.isDown(Key.ENTER) && changeplatDelay <= 0) {
			if (InputKeyboard.isDown(Key.SHIFT)) {
				actualPlatform = actualPlatform-1 < 0 ? mapObjects.getSize()-1 : actualPlatform-1;
			}else {
				actualPlatform = actualPlatform+1>= mapObjects.getSize() ? 0 : actualPlatform+1;
			}
			
			if (mapObjects.get(actualPlatform) instanceof Sprite) {
				plat1 = (Caja) mapObjects.get(actualPlatform) ;
			}
			contx = 0;
			conty = 0;
			changeplatDelay = .5;
		}else {
			changeplatDelay -= GameLoop.dt;
		}
		
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
		//plat1.getColisiona().actualizar();
	}
	
	private void generarHelicopteros() {
		
	}
	
	private void generarPistoleros() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage[] imageE = {Renderer.crearTextura(rect, color)};
		Vector2D posicionE = new Vector2D(1400,200);
		Transform transformE = new RelativeTransform(posicionE, transformar);
		
		EnemigoPistola enemigo = new EnemigoPistola(imageE, transformE, 1);
		enemigos.add(enemigo.getNombre(), enemigo);
		
	}
	
	private void generarGranaderos() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage[] imageE = {Renderer.crearTextura(rect, color)};
		Vector2D posicionE = new Vector2D(700,200);
		Transform transformE = new RelativeTransform(posicionE, transformar);
		
		EnemigoGranada enemigo = new EnemigoGranada(imageE, transformE, 1);
		enemigos.add(enemigo.getNombre(), enemigo);
	}
	
	@Override
	protected void generarEnemigos() {
		jefes = new Entidad[CANTIDAD_JEFES];
		
		for (int i = 0; i < config.getEnemigosActivos().length; i++) {
			switch (config.getEnemigosActivos()[i]) {
			case EnemyTypes.HELICOPTERO:
				generarHelicopteros();
				break;
			case EnemyTypes.GRANADERO:
				generarGranaderos();
				break;
			case EnemyTypes.PISTOLERO:
				generarPistoleros();
				break;
			case "all":
				generarGranaderos();
				generarHelicopteros();
				generarPistoleros();
			}
		}
		
		//jefes
		
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage[] imageE = {Renderer.crearTextura(rect, color)};
		
		Rectangle rectJ1 = new Rectangle(85, 40);
		BufferedImage[] imageJ1 = {Renderer.crearTextura(rectJ1, color)};
		Vector2D posicionJefe1 = new Vector2D(3200,0);
		Transform transformJefe1 = new RelativeTransform(posicionJefe1, transformar);
		jefes[0] = new Helicoptero(imageJ1, transformJefe1, 10);
		
		Vector2D posicionJefe2 = new Vector2D(5000,200);
		Transform transformJefe2 = new RelativeTransform(posicionJefe2, transformar);
		jefes[1] = new EnemigoPistola(imageE, transformJefe2, 10);
		
		Vector2D posicionJefeFinal = new Vector2D(8000,80);
		Transform transformJefeFinal = new RelativeTransform(posicionJefeFinal, transformar);
		jefes[2] = new EnemigoPistola(imageE, transformJefeFinal, 10);

		for (int i = 0; i < jefes.length; i++) {
			enemigos.add(jefes[i].getNombre(), jefes[i]);
		}
		
		jefeActual = 0;
	}
	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
		if (plat1 instanceof BorderDrawAble) {
			((BorderDrawAble) plat1).drawBorders(g);
		}
	}
	
	
	private void destruirEnemigosResagados() {
		for (int i = 0; i < enemigos.getSize(); i++) {
			Entidad enemigo = enemigos.get(i);
			if (enemigo instanceof Soldado) {
				Vector2D posicionEnemigo = ((Soldado) enemigo).getTransformar().getPosicion();
				double widht = ((Soldado) enemigo).getColisiona().getHitbox().getWidth();
				if (posicionEnemigo.getX() + widht < 0) {
					enemigo.destruir();
				}
			}
		}
	}

	@Override
	public void onColision(ColisionInfo colision) {
		//do Nothing
	}

	@Override
	public ColisionInfo hayColision(Colisionable entidad) {
		verificarColisiones(entidad);
		return null;
	}

	@Override
	public Collider[] getColliders() {
		return new Collider[0];
	}
}

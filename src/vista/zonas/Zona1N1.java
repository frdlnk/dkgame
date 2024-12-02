package vista.zonas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import modelo.componentes.RelativeTransform;
import modelo.entidades.Recogible;
import modelo.entidades.Soldado;
import modelo.entidades.enemigos.Enemigo;
import modelo.entidades.enemigos.EnemigoGranada;
import modelo.entidades.enemigos.EnemigoPistola;
import modelo.entidades.enemigos.Helicoptero;
import modelo.entidades.recogibles.HRecogible;
import modelo.worldObjects.DeadBox;
import modelo.worldObjects.PlayerDetector;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Collider;
import motor_v1.motor.component.Renderer;
import motor_v1.motor.component.Transform;
import motor_v1.motor.entidades.Sprite;
import motor_v1.motor.util.Vector2D;
import utils.colision.ColisionInfo;
import utils.colision.Colisionable;
import utils.constants.Assets;
import utils.constants.Conf;
import utils.constants.EnemyTypes;
import utils.constants.Tags;


/**
 * Zona correspondiente al nivel 1, cuenta con 3 jefes, y cuenta con movimiento de camara para el avance del escenario
 * 
 * 
 * @author Joshua Elizondo Vasquez
 * 
 * @see Zona
 */
public class Zona1N1 extends Zona{
	//constantes
	public final static int CANTIDAD_JEFES = 3;
	
	//variables de control para el movimiento de pantalla
	private final int[] checkPoints = {-3040,-5650, -6300, -6700, -7225, -7500};
	private final Vector2D[] direccionChecpoints = {
		Vector2D.LEFT,Vector2D.LEFT, Vector2D.LEFT, 
		new Vector2D(-1, .25), new Vector2D(-.5,.1), Vector2D.LEFT
	};
	
	private int actualCheckpoint;
	private PlayerDetector barrier;
	
	//Entidades principales
	private Sprite background;
	private Enemigo[] jefes;
	private int jefeActual;
	
	
	/**
	 * Constructor sin parametros 
	 */
	public Zona1N1(boolean modoDiseno) {
		super( new Vector2D(0,0), modoDiseno);
		
		actualCheckpoint = 0;
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
		createCaja(posicionCaja4Avion2, 2000, 200);
		
		Vector2D posHBox = new Vector2D(100, 50);
		Transform transformH = new RelativeTransform(posHBox, transformar);
		Recogible hboxRecogible = new HRecogible(transformH, 300);
		
		Vector2D posRBOX = new Vector2D(100, 50);
		Transform transformR = new RelativeTransform(posRBOX, transformar);
		Recogible RBoxRecogible = new HRecogible(transformR, 300);
		
		//barrera de movimiento, para el avance por pantalla
		Rectangle rectMB = new Rectangle(Conf.WINDOW_WIDTH/2, Conf.WINDOW_HEIGHT);
		Color colorMB = new Color(128,200,100,90);
		BufferedImage imageMB = Renderer.crearTextura(rectMB, colorMB);
		Vector2D posicionMB = new Vector2D(Conf.WINDOW_WIDTH/2,0);
		Transform transformMB = new Transform(posicionMB);
		barrier = new PlayerDetector(imageMB, transformMB);
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
		mapObjects.add(hboxRecogible.getNombre(), hboxRecogible);
		mapObjects.add(RBoxRecogible.getNombre(), RBoxRecogible);
	}

	
	@Override
	public void actualizar() {
		
		if (jefeActual < jefes.length && !jefes[jefeActual].getViva()) {
			if (!barrier.isEnable()) {
				barrier.getTransformar().getPosicion().setX(Conf.WINDOW_WIDTH-1);
			}
			
			if (actualCheckpoint < checkPoints.length-1)actualCheckpoint++;
			jefeActual++;
		}
		
		if (!jefes[CANTIDAD_JEFES-1].getViva()) {
			enemigos.destruirAll();
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
	
	
	
	/**
	 * Genera los enemigos helicoptero
	 */
	private void generarHelicopteros() {
		
	}
	
	/**
	 * genera enemigos pistoleros
	 */
	private void generarPistoleros() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage imageE = Renderer.crearTextura(rect, color);
		Vector2D posicionE = new Vector2D(1400,200);
		Transform transformE = new RelativeTransform(posicionE, transformar);
		
		EnemigoPistola enemigo = new EnemigoPistola(imageE, transformE);
		enemigos.add(enemigo.getNombre(), enemigo);
		
	}
	
	/**
	 * genera enemigos granaderos
	 */
	private void generarGranaderos() {
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage imageE = Renderer.crearTextura(rect, color);
		Vector2D posicionE = new Vector2D(700,200);
		Transform transformE = new RelativeTransform(posicionE, transformar);
		
		EnemigoGranada enemigo = new EnemigoGranada(imageE, transformE);
		enemigos.add(enemigo.getNombre(), enemigo);
	}
	
	@Override
	protected void generarEnemigos() {
		jefes = new Enemigo[CANTIDAD_JEFES];
		
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
			};
		}
		
		//jefes
		
		Rectangle rect = new Rectangle(50, 80);
		Color color = new Color(128,128,0,100);
		BufferedImage imageE = Renderer.crearTextura(rect, color);
		
		Rectangle rectJ1 = new Rectangle(120, 70);
		BufferedImage imageJ1 = Renderer.crearTextura(rectJ1, color);
		Vector2D posicionJefe1 = new Vector2D(3200,0);
		Transform transformJefe1 = new RelativeTransform(posicionJefe1, transformar);
		jefes[0] = new Helicoptero(imageJ1, transformJefe1);
		
		Vector2D posicionJefe2 = new Vector2D(5800,200);
		Transform transformJefe2 = new RelativeTransform(posicionJefe2, transformar);
		jefes[1] = new EnemigoPistola(imageE, transformJefe2);
		jefes[1].setSalud(400);
		jefes[1].getArma().setShootDelay(.9);
		
		Vector2D posicionJefeFinal = new Vector2D(8000,80);
		Transform transformJefeFinal = new RelativeTransform(posicionJefeFinal, transformar);
		jefes[2] = new EnemigoPistola(imageE, transformJefeFinal);

		for (int i = 0; i < jefes.length; i++) {
			enemigos.add(jefes[i].getNombre(), jefes[i]);
		}
		
		jefeActual = 0;
	}
	
	@Override
	public void dibujar(Graphics g) {
		super.dibujar(g);
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

	public int getActualCheckpoint() {
		return actualCheckpoint;
	}

	public void setActualCheckpoint(int actualCheckpoint) {
		this.actualCheckpoint = actualCheckpoint;
	}

	public PlayerDetector getBarrier() {
		return barrier;
	}

	public void setBarrier(PlayerDetector barrier) {
		this.barrier = barrier;
	}

	public Sprite getBackground() {
		return background;
	}

	public void setBackground(Sprite background) {
		this.background = background;
	}

	public Enemigo[] getJefes() {
		return jefes;
	}

	public void setJefes(Enemigo[] jefes) {
		this.jefes = jefes;
	}

	public int getJefeActual() {
		return jefeActual;
	}

	public void setJefeActual(int jefeActual) {
		this.jefeActual = jefeActual;
	}

	public static int getCantidadJefes() {
		return CANTIDAD_JEFES;
	}

	public int[] getCheckPoints() {
		return checkPoints;
	}

	public Vector2D[] getDireccionChecpoints() {
		return direccionChecpoints;
	}

	@Override
	public String toString() {
		return "Zona1N1 [checkPoints=" + (checkPoints) + ", direccionChecpoints="
				+ (direccionChecpoints) + ", actualCheckpoint=" + actualCheckpoint + ", barrier="
				+ barrier + ", background=" + background + ", jefes=" + (jefes) + ", jefeActual="
				+ jefeActual + "]";
	}
	
	
}

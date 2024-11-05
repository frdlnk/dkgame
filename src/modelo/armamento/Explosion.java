package modelo.armamento;

import java.awt.image.BufferedImage;

import modelo.entidades.Soldado;
import modelo.mapObjects.TriggerBox;
import motor_v1.motor.Entidad;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Array;

public class Explosion extends TriggerBox{
	//private double tiempoDeExplosion;
	private double dano;
	//private double factorDeDanoXDistancia;
	//private Vector2D epicentroRelativo = Vector2D.ZERO;
	private Array<String> targetsIgnore;
	
	
	public Explosion(String nombre, BufferedImage textura, Transform transformar, Array<String> targetsIgnore) {
		super(nombre, textura, transformar);
		dano = 10;
		colisiona.actualizar();
		this.targetsIgnore = targetsIgnore;
		destruir();
	}
	

	public Explosion(String nombre, BufferedImage textura, Vector2D posicion, Array<String> targetsIgnore) {
		super(nombre, textura, posicion);
		dano = 10;
		colisiona.actualizar();
		this.targetsIgnore = targetsIgnore;
		destruir();
	}

	@Override
	public void onColision(Entidad entidad) {
		if (entidad instanceof Soldado && !targetsIgnore.contains(entidad.getNombre())) {
			hitSoldado((Soldado) entidad);
		}
	}

	private void hitSoldado(Soldado soldado) {
		//TODO: generar el epicentro y calculo de la explosion para dano dinamico segun distancia
		//Vector2D epicentroExp = transformar.getPosicion().add(epicentroRelativo);
		//double distanciaSoldado = epicentroRelativo.subtract(soldado.getCentro()).getMagnitude()+1;
		//double danoConseguido = (dano/distanciaSoldado)*factorDeDanoXDistancia;
		soldado.recibirDano(dano);
	}
	
	@Override
	public void actualizar() {
		super.actualizar();
	}
}

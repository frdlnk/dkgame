package modelo.componentes;

import motor_v1.motor.GameLoop;
import motor_v1.motor.component.Physics;
import motor_v1.motor.component.Transform;
import motor_v1.motor.util.Vector2D;
import utils.Array;
import utils.Conf;

public class Fisica extends Physics{
	private double gravity;
	private Vector2D vectorMovimiento;
	private Vector2D ultimaDireccion;
	private Transform transform;
	private int masa;
	private Array<Vector2D> fuerzasAplicadas;
	
	public Fisica(Transform transform) {
		this(1,1,transform);
	}

	public Fisica(int masa, double gravity, Transform transform) {
		this.gravity = gravity;
		this.transform = transform;
		this.masa = masa;
		setFriccion(0);
		vectorMovimiento = Vector2D.ZERO;
		fuerzasAplicadas = new Array<>();
	}
    
	public void impulsar(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	public void impulsar(double force) {
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	public void addForce(Vector2D vector) {
		Vector2D newVector = new Vector2D(vector.getX()/masa, vector.getY()/masa);
		fuerzasAplicadas.add(newVector);
		vectorMovimiento = vectorMovimiento.add(newVector);
	}
	
	public void addForce(double force) {
		fuerzasAplicadas.add(Vector2D.ONE.scale(force/masa));
		vectorMovimiento = vectorMovimiento.add(force/masa);
	}
	
	public void actualizar() {
		vectorMovimiento = vectorMovimiento.scale(1-getFriccion());
		Vector2D nuevaPosicion = transform.getPosicion().add(vectorMovimiento);
		ultimaDireccion = vectorMovimiento;
		for (int i = 0; i < fuerzasAplicadas.getSize(); i++) {
			Vector2D fuerzaAplicada = fuerzasAplicadas.get(i);
			vectorMovimiento = vectorMovimiento.subtract(fuerzaAplicada);
		}
		fuerzasAplicadas.clear();
		transform.trasladarloA(nuevaPosicion);
		vectorMovimiento.setY(vectorMovimiento.getY() + (gravity*Conf.GRAVITY*GameLoop.dt/1000));
	}

	public void acelerar() {
		vectorMovimiento = vectorMovimiento.scale(getAceleracion());
	}
	
	public Vector2D getUltimaDireccion() {
		return ultimaDireccion;
	}

	public void setUltimaDireccion(Vector2D ultimaDireccion) {
		this.ultimaDireccion = ultimaDireccion;
	}

	public int getMasa() {
		return masa;
	}

	public void setMasa(int masa) {
		this.masa = masa;
	}


	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public Vector2D getVectorMovimiento() {
		return vectorMovimiento;
	}

	public void setVectorMovimiento(Vector2D vectorMovimiento) {
		this.vectorMovimiento = vectorMovimiento;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
	}


}

package modelo;

import java.io.Serializable;

import utils.constants.ArmasDisponibles;
import utils.constants.EnemyTypes;

/**
 * Modelo de datos para las configuraciones del usuario
 */
public class UserConfig implements Serializable{
	private int id;
	private String[] enemigosActivos;
	private int vidasIniciales;
	private double multiplicadorDano;
	private double multiplicadorDanoEnemigo;
	private String armainicial;
	
	public UserConfig(String[] enemigosActivos, int vidasIniciales, double multiplicadorDano,
			double multiplicadorDanoEnemigo, String armainicial) {
		setId(0);
		this.enemigosActivos = enemigosActivos;
		this.vidasIniciales = vidasIniciales;
		this.multiplicadorDano = multiplicadorDano;
		this.multiplicadorDanoEnemigo = multiplicadorDanoEnemigo;
		this.armainicial = armainicial;
	}

	public UserConfig() {
		setId(0);
		this.enemigosActivos = EnemyTypes.values();
		this.vidasIniciales = 3;
		this.multiplicadorDano = 1;
		this.multiplicadorDanoEnemigo = 1;
		this.armainicial = ArmasDisponibles.PISTOLA;
	}

	public String[] getEnemigosActivos() {
		return enemigosActivos;
	}

	public void setEnemigosActivos(String[] enemigosActivos) {
		this.enemigosActivos = enemigosActivos;
	}

	public int getVidasIniciales() {
		return vidasIniciales;
	}

	public void setVidasIniciales(int vidasIniciales) {
		this.vidasIniciales = vidasIniciales;
	}

	public double getMultiplicadorDano() {
		return multiplicadorDano;
	}

	public void setMultiplicadorDano(double multiplicadorDano) {
		this.multiplicadorDano = multiplicadorDano;
	}

	public double getMultiplicadorDanoEnemigo() {
		return multiplicadorDanoEnemigo;
	}

	public void setMultiplicadorDanoEnemigo(double multiplicadorDanoEnemigo) {
		this.multiplicadorDanoEnemigo = multiplicadorDanoEnemigo;
	}

	public String getArmainicial() {
		return armainicial;
	}

	public void setArmainicial(String armainicial) {
		this.armainicial = armainicial;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserConfig [enemigosActivos=" + enemigosActivos + ", cantEnemigosPorTipo="
				+ ", vidasIniciales=" + vidasIniciales + ", multiplicadorDano="
				+ multiplicadorDano + ", multiplicadorDanoEnemigo=" + multiplicadorDanoEnemigo + ", armainicial="
				+ armainicial + "]";
	}

	
}

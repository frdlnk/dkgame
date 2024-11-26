package modelo;

import java.io.Serializable;
import java.util.Arrays;

import modelo.armamento.armas.Arma;
import modelo.armamento.armas.Pistola;

public class UserConfig implements Serializable{
	private int id;
	private String[] enemigosActivos;
	private int vidasIniciales;
	private double multiplicadorDano;
	private double multiplicadorDanoEnemigo;
	private Arma armainicial;
	
	public UserConfig(String[] enemigosActivos, int vidasIniciales, double multiplicadorDano,
			double multiplicadorDanoEnemigo, Arma armainicial) {
		setId(0);
		this.enemigosActivos = enemigosActivos;
		this.vidasIniciales = vidasIniciales;
		this.multiplicadorDano = multiplicadorDano;
		this.multiplicadorDanoEnemigo = multiplicadorDanoEnemigo;
		this.armainicial = armainicial;
	}

	public UserConfig() {
		setId(0);
		this.enemigosActivos = new String[]{"all"};
		this.vidasIniciales = 3;
		this.multiplicadorDano = 1;
		this.multiplicadorDanoEnemigo = 1;
		this.armainicial = new Pistola();
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

	public Arma getArmainicial() {
		return armainicial;
	}

	public void setArmainicial(Arma armainicial) {
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
		return "UserConfig [enemigosActivos=" + Arrays.toString(enemigosActivos) + ", cantEnemigosPorTipo="
				+ ", vidasIniciales=" + vidasIniciales + ", multiplicadorDano="
				+ multiplicadorDano + ", multiplicadorDanoEnemigo=" + multiplicadorDanoEnemigo + ", armainicial="
				+ armainicial + "]";
	}

	
}

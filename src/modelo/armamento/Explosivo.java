package modelo.armamento;

/**
 * Interfaz que define que un objeto puede explotar y generar una explosion
 */
public interface Explosivo {
	public Explosion generarExplosion();
	public void explotar();
}

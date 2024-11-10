package modelo.armamento;

/**
 * Interfaz que define el comportamiento de un objeto 
 * puede explotar y generar una explosion
 * 
 * @author Joshua Elizondo Vasquez
 * @see Explosion
 */
public interface Explosivo {
	public Explosion generarExplosion();
	public void explotar();
}

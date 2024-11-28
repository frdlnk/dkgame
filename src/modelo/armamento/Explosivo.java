package modelo.armamento;

/**
 * Interfaz que define el comportamiento de un objeto 
 * puede explotar y generar una explosion
 * 
 * @author Joshua Elizondo Vasquez
 * @see Explosion
 */
public interface Explosivo {
	/**
	 * Genera una explosion 
	 * @return Un objetop Explosion generado
	 */
	public Explosion generarExplosion();
	
	/**
	 * Hace que el objeto comience su proceso de explosion
	 * 
	 * @implNote este metodo puede solo generar la explocion o agregarle comportamientos extra
	 * <br> como hacer que genere varias explosiones en cadena o racimo
	 */
	public void explotar();
}

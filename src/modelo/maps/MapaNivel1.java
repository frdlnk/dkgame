package modelo.maps;

import modelo.maps.zonas.nivel1.Zona1N1;
import modelo.maps.zonas.nivel1.Zona2N1;

public class MapaNivel1 extends Map {

	public MapaNivel1() {
		super();
	}

	@Override
	protected void crearZonas() {
		Zona zona1 = new Zona1N1();
		zonas.add(zona1);
		Zona zona2 = new Zona2N1();
		zonas.add(zona2);
	}

}

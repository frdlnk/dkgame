package vista.mapas;

import vista.zonas.Zona;
import vista.zonas.Zona1N1;
import vista.zonas.Zona2N1;

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

	@Override
	public void destruir() {
		setViva(false);
	}

}

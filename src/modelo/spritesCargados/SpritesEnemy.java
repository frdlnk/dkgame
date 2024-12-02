package modelo.spritesCargados;

import modelo.entidades.enemigos.Enemigo;
import motor_v1.motor.component.Animation;
import motor_v1.motor.entidades.Gif;
import motor_v1.motor.util.Vector2D;
import utils.constants.Assets;

import java.awt.Graphics;

public class SpritesEnemy extends Animation {

    private double escala;
    private Enemigo enemy;

    public SpritesEnemy(Enemigo enemy) {
        super("enemy");
        this.enemy = enemy;
        this.setTransformar(this.enemy.getTransformar());
        loadGifs();
        escala = 2;
    }

    private void loadGifs() {
        Assets.load();
        for (int i = 0; i < Assets.gifsE.size(); i++) {
            Gif gif = new Gif(Assets.enemNames[i], Assets.gifsE.getImages(Assets.enemNames[i]), this.getTransformar(), 150);
            this.add(gif.getNombre(), gif);
        }
        cambiarAnimacionA(Assets.enemNames[0]);
    }

    @Override
    public void dibujar(Graphics g) {
        Vector2D pos = getTransformar().getPosicion();
        Vector2D escala = getTransformar().getEscala();
        getTransformar().setEscala(new Vector2D(this.escala, this.escala));
        pos.setY(pos.getY()+.6);
        super.dibujar(g);
        pos.setY(pos.getY()-.6);
        getTransformar().setEscala(escala);
    }

	public double getEscala() {
		return escala;
	}

	public void setEscala(double escala) {
		this.escala = escala;
	}

	public Enemigo getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemigo enemy) {
		this.enemy = enemy;
	}

	@Override
	public String toString() {
		return "SpritesEnemy [escala=" + escala + ", enemy=" + enemy + "]";
	}
    
    
}

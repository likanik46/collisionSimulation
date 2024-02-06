package domaci;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Figura {

	private Vektor vektPolozaja;
	private Vektor vektPomeraja;
	private double r;
	
	public Figura(Vektor vektPolozaja, Vektor vektPomeraja, double r) {
		this.vektPolozaja = vektPolozaja;
		this.vektPomeraja = vektPomeraja;
		this.r = r;
	}
	
	public Figura(Vektor vektPolozaja, Vektor vektPomeraja) {
		this(vektPolozaja, vektPomeraja, 20);
	}

	public abstract Color getBoja();
	public abstract void iscrtaj(Graphics g);

	public Vektor getVektPolozaja() {
		return vektPolozaja;
	}


	public Vektor getVektPomeraja() {
		return vektPomeraja;
	}


	public double getR() {
		return r;
	}
	
	public boolean vektUnutarKruznice(Vektor vektor) {
		return (Math.sqrt(Math.pow(vektPolozaja.getX() - vektor.getX(), 2) + Math.pow(vektPolozaja.getY() - vektor.getY(), 2)) <= r);
	}
	
	public boolean figuraPreklapaSa(Figura figura) {
		double d = Math.sqrt(Math.pow(vektPolozaja.getX() - figura.vektPolozaja.getX(), 2)+Math.pow(vektPolozaja.getY() - figura.vektPolozaja.getY(), 2));
		if (d <= r + figura.r) {
			return true;
		}
		else {
			return false;
		}
	}
	
}

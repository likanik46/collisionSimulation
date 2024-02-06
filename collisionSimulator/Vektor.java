package domaci;

public class Vektor {
	
	private double x;
	private double y;
	
	public Vektor(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vektor randomVektor() {
		double x;
		double y;
		do {
		x = -1 + Math.random() * 2;
		y = -1 + Math.random() * 2;
		}while(x == 0 && y == 0);
		return new Vektor(x, y);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Vektor ortVektor() {
		double magnituda =  Math.sqrt(Math.pow(x, 2) + (Math.pow(y, 2)));
		return new Vektor(x / magnituda, y / magnituda);
	}
	
}

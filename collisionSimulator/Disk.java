package domaci;

import java.awt.Color;
import java.awt.Graphics;

public class Disk extends Figura {

	
	
	public Disk(Vektor vektPolozaja, Vektor vektPomeraja, double r) {
		super(vektPolozaja, vektPomeraja, r);
	}

	public Disk(Vektor vektPolozaja, Vektor vektPomeraja) {
		super(vektPolozaja, vektPomeraja);
	}

	@Override
	public Color getBoja() {
		return Color.BLUE;
	}

	
	@Override
	public void iscrtaj(Graphics g) {
		g.setColor(Color.BLUE);
		int[] xPoints = new int[8];
        for (int i = 0; i < 8; i++) {
            double angle = 2 * Math.PI * i / 8;
            xPoints[i] = (int) (getVektPolozaja().getX() + getR() * Math.cos(angle));
        }
		int[] yPoints = new int[8];
	    for (int i = 0; i < 8; i++) {
	        double angle = 2 * Math.PI * i / 8;
	        yPoints[i] = (int) (getVektPolozaja().getY() + getR() * Math.sin(angle));
	    }
	    g.fillPolygon(xPoints, yPoints, 8);
	}
}
package domaci;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.plaf.synth.SynthColorChooserUI;

public class Scena extends Canvas implements Runnable{
	
	private LinkedList<Figura> figure;
	private Thread thread;
	private boolean isPaused;
	
	public Scena() {
		figure = new LinkedList<Figura>();
		isPaused = true;
		thread = new Thread(this);
		setBackground(Color.GRAY);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				synchronized (Scena.this) {
					
					if (Scena.this.isPaused) {
					
						Disk noviDisk = new Disk(new Vektor(e.getX(), e.getY()), Vektor.randomVektor());
						
						if ( (!(vecPostojiFiguraOvde(noviDisk, figure))) && upadaNaEkran(noviDisk)) {
							dodajFiguru(noviDisk);
						}
					}
				}	
			}
		});
		
		
		thread.start();
	}
	
	public boolean scenaPauzirana() {
		return isPaused;
	}
	
	public void pauzirajScenu() {
		isPaused = true;
		
	}
	
	public void odpauzirajScenu() { //pokrebiScenu
		isPaused = false;
	}
	
	public void zaustavi() {
		thread.interrupt();
	}
	
	public void dodajFiguru(Figura figura) {
		figure.add(figura);
		repaint();
;	}
	
	
	@Override
	public void run() {
		
		try {
			while (!Thread.interrupted()) {
				
				synchronized (this) {
					while (isPaused) wait();
				}
				
				Thread.sleep(100);
				updatePozicije();				
				repaint();
						
			}
		}catch (InterruptedException e) {}
		
		
		
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Figura figaro : figure) {
			figaro.iscrtaj(g);
		}
		if (isPaused) {
             g.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
             FontMetrics fm = g.getFontMetrics();
             int tw = fm.stringWidth("PAUZA");
             int th = fm.getHeight();
        
             int x = (getWidth() - tw) / 2;
             int y = (getHeight() - th) / 2 + fm.getAscent();
             g.setColor(Color.BLACK);
             g.drawString("PAUZA", x, y);
		}
		revalidate();
	}
	
	
	private void updatePozicije() {
		int factor = 3;
		
		for (Figura figaro : figure) {
			
			double xMove = figaro.getVektPomeraja().ortVektor().getX() * factor;
			double yMove = figaro.getVektPomeraja().ortVektor().getY() * factor;
			
			figaro.getVektPolozaja().setX(figaro.getVektPolozaja().getX() + xMove);
			figaro.getVektPolozaja().setY(figaro.getVektPolozaja().getY() + yMove);
			
			//provera da li je na ivici
			double trenX = figaro.getVektPolozaja().getX();
			double trenY = figaro.getVektPolozaja().getY();
			
			if (trenX - figaro.getR() <= 0 || trenX + figaro.getR() >= getWidth()) figaro.getVektPomeraja().setX(-1 * (figaro.getVektPomeraja().getX()));
			if (trenY - figaro.getR() <= 0 || trenY + figaro.getR() >= getHeight()) figaro.getVektPomeraja().setY(-1 * (figaro.getVektPomeraja().getY()));
			
		}
		
		
		for (int i = 0; i < figure.size() - 1; i++) {
			Figura figaro1 = figure.get(i);
			
			for (int j = i + 1; j < figure.size(); j++) {
				Figura figaro2 = figure.get(j);
				
				if (figaro1 != figaro2 && figaro1.figuraPreklapaSa(figaro2)) {

					double pomXtemp = figaro1.getVektPomeraja().getX();
					double pomYtemp = figaro1.getVektPomeraja().getY();
					
					figaro1.getVektPomeraja().setX(figaro2.getVektPomeraja().getX());
					figaro1.getVektPomeraja().setY(figaro2.getVektPomeraja().getY());
					
					figaro2.getVektPomeraja().setX(pomXtemp);
					figaro2.getVektPomeraja().setY(pomYtemp);

				}
			}
		}
	}
	
	private boolean vecPostojiFiguraOvde(Figura novaFigura, LinkedList<Figura> figure) {
		for (Figura figaro : figure) {
			if (novaFigura.figuraPreklapaSa(figaro)) return true;
		}
		return false;
	}
	
	private boolean upadaNaEkran(Figura figura) {
		double levo = figura.getVektPolozaja().getX() - figura.getR();
		double desno = figura.getVektPolozaja().getX() + figura.getR();
		double gore = figura.getVektPolozaja().getY() - figura.getR();
		double dole = figura.getVektPolozaja().getY() + figura.getR();
		
		if (levo >= 0 && gore >= 0 && desno <= this.getWidth() && dole <= this.getHeight()) return true;
		return false;
	}
	
}

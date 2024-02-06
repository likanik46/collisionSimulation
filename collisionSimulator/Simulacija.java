package domaci;

import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.plaf.synth.SynthColorChooserUI;

public class Simulacija extends Frame {

	
	Scena scena;
	
	public Simulacija() {
		
		this.scena = new Scena();
		
		add(scena);
		setTitle("Simulacija");
		setResizable(false);
		setBounds(700, 200, 500 ,500);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		
		scena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					
					synchronized (Simulacija.this.scena) {
						if (Simulacija.this.scena.scenaPauzirana()) {
							Simulacija.this.scena.odpauzirajScenu();
							Simulacija.this.scena.notify();
						}
						else {
							Simulacija.this.scena.pauzirajScenu();
						}
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					Simulacija.this.scena.zaustavi();
					dispose();
				}
			}
		});
	
		
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Simulacija();
	}
	
	
	
	
}

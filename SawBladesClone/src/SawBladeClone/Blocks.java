package SawBladeClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle {

	
	public static Color cor = Color.white;
	
	public Blocks(int x, int y) {
		super(x,y,32,32);
	}
	
	public void render(Graphics g) {
		g.setColor(cor);
		g.fillRect(x, y, width, height);
	}
}
	


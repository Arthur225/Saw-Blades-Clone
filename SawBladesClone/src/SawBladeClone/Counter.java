package SawBladeClone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Counter {
	
	
	public static String string;
	public static int count = 0;
	void render(Graphics g) {
		
		if(count<10) {
			string = "0"+ Integer.toString(count);
		}
		else {
			string = Integer.toString(count);
		}
		
		g.setColor(Color.DARK_GRAY);
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 300));
		g.drawString(string, 75, 400);
	}
}

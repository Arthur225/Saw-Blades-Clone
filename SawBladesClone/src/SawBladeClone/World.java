package SawBladeClone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	
	
	public static List<Blocks> blocos = new ArrayList<Blocks>();
	
	
	public World() {
		for(int xx = 0; xx < 15; xx++) {
			blocos.add(new Blocks(xx*32, 640-32));
		}
		for(int xx = -1; xx < 19; xx++) {
			blocos.add(new Blocks(0, xx*32));
		}
		for(int xx = -1; xx < 19; xx++) {
			blocos.add(new Blocks(480-32, xx*32));
		}

	}
	public static boolean isFree(int x, int y, int w, int h) {
		for(int i = 0; i < blocos.size(); i++) {
			Blocks blocoAtual = blocos.get(i);
			if(blocoAtual.intersects(new Rectangle(x,y,w,h))) {
				return false;
			}
		}
		return true;
	}
	public void render(Graphics g) {
		for(int i = 0; i < blocos.size(); i++) {
			blocos.get(i).render(g);
		}
	}
	
}

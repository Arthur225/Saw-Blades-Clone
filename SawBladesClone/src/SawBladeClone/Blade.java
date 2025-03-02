package SawBladeClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Blade extends Rectangle {
	
	static Random rand = new Random();
	static int size = 64;
	int spd = 6;
	int spdx = rand.nextInt(spd-1)+2;
	int spdy = (int) Math.floor(Math.sqrt(Math.pow(spd,2)-Math.pow(spdx,2)));
	int xdir;
	Rectangle linhaCima; 
	
	Color cor = Color.gray;
	boolean passou = false;
	
	int ydir = 1;
	
	public Blade() {
		super(rand.nextInt(Game.WIDTH-2*Blade.size-33)+Blade.size,-Blade.size,size,size);
		if(rand.nextInt(100)<50) {
			xdir = 1;
		} else xdir = -1;
		linhaCima = new Rectangle(x+size/2, y-500, 2,500);
	}
	
	boolean circleRect(float rx, float ry, float rw, float rh) {

		  // temporary variables to set edges for testing
			float cx = x+size/2;
			float cy = y+size/2;
			float radius = size/2;
		  float testX = cx;
		  float testY = cy;
		  
		
		  // which edge is closest?
		  if (cx < rx)         testX = rx;      // test left edge
		  else if (cx > rx+rw) testX = rx+rw;   // right edge
		  if (cy < ry)         testY = ry;      // top edge
		  else if (cy > ry+rh) testY = ry+rh;   // bottom edge
		
		  // get distance from closest edges
		  float distX = cx-testX;
		  float distY = cy-testY;
		  float distance = (float) Math.sqrt( (distX*distX) + (distY*distY) );
		
		  // if the distance is less than the radius, collision!
		  if (distance <= radius) {
		    return true;
		  }
		  return false;
		}
	
	void tick() {
		if(!Game.gameOver) {
			
			if(World.isFree(x+spdx*xdir,y,width,height)) {
				x+=spdx*xdir;
			}
			else {
				while(World.isFree(x+xdir,y,width,height)) {
					x+=xdir;
				}
				xdir*=-1;
			}
			if(World.isFree(x, y+spdy*ydir,width,height)) {
				y+=spdy*ydir;
			}
			else {
				while(World.isFree(x,y+ydir,width,height)) {
					y+=ydir;
				}
				ydir*=-1;
			}
			
			linhaCima.x = x+size/2;
			linhaCima.y = y-500;
			if(linhaCima.intersects(Game.player)) {
				cor = Color.cyan;
				passou = true;
			}
			if(y<-64 || (Game.player.y == Game.HEIGHT-32-Game.player.height && passou)) {
				Game.blades.remove(this);
				if(Game.player.y == Game.HEIGHT-32-Game.player.height && passou)
					Counter.count++;
			}
			if(this.circleRect(Game.player.x, Game.player.y, Game.player.width, Game.player.height)) {
				Blocks.cor = Color.red;
				Game.gameOver = true;
			}
				
		}
		
	}
	
	void render(Graphics g) {
		g.setColor(cor);
		g.fillOval(x, y, width, height);
		
	}

}

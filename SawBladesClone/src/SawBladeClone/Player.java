package SawBladeClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Player extends Rectangle {
	
	
	int spd = 6;
	public boolean right, left, up, down;
	int gravity = 1;
	int spdY = 0;
	public boolean doubleJump = false;
	
	public int timer = 0;
	
	boolean podePular = true;
	boolean firstJump = true;
	
	
	public Player(int x, int y) {
		super(x,y,48,64);
	}
	
	public void tick() {
	
		if(!Game.gameOver) {
			
			if(right && !left && World.isFree(x+spd, y,width, height)) {
				x+=spd;
				
			}
			else if(left && !right && World.isFree(x-spd, y, width, height)) {
				x-=spd;
				
			
				
			}
			if(up && doubleJump && timer == 0 ) {
				timer += 1;
				doubleJump = false;
				firstJump = false;
				
			}
			
			if(up && y == Game.HEIGHT-32-height) {
				timer+=1;
				
			}
			
			if(timer > 19) {
				up = false;
			}
			
			if(up && timer > 0) {
				if(firstJump) {
					doubleJump = true;
					spdY = -11;
					timer+=1;
				}
				else {
					firstJump = true;
					doubleJump = false;
					timer = 0;
					spdY = -15;
				}
				
				
			}
			
			if(World.isFree(x, y+spdY,width, height)) {
				
				y+=spdY;
				spdY+=gravity;
				
			}else {
				y = Game.HEIGHT-32-height;
				spdY = 0;
				podePular = true;
				timer = 0;
				firstJump = true;
				doubleJump = false;
			}
				
			
		}

			
			
	}
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	
	}

}

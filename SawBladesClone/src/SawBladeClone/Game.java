package SawBladeClone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;



public class Game extends Canvas implements Runnable, KeyListener {
	
	public static int WIDTH = 480, HEIGHT = 640, SCALE = 3;
	public static Player player;
	public World world;
	public Blade blade;
	public static List<Blade> blades = new ArrayList<Blade>();
	Random rand = new Random();
	public static Counter counter = new Counter();
	public static boolean gameOver = false;
	boolean restart = false;
	
	int contadorBlades = 0;

	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		player = new Player(32,32);
		world = new World();
		
		
		
		
	}
	
	public void tick() {
		if(restart) {
			blades = new ArrayList<Blade>();
			counter = new Counter();
			Counter.count = 0;
			contadorBlades = 0;
			player = new Player(32,32);
			world = new World();
			Blocks.cor = Color.white;
			gameOver = false;
			
			restart = false;
		}
		player.tick();
		for(int i = 0; i < blades.size(); i++) {
			blades.get(i).tick();
		}
		contadorBlades++;
		if(contadorBlades>150) {
			for(int i = 0; i<rand.nextInt(4)+1; i++) {
				blades.add(new Blade());
			}
			contadorBlades = 0;
		}
		
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(0,135,13));
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
		counter.render(g);
		
		player.render(g);
		world.render(g);
		for(int i = 0; i < blades.size(); i++) {
			blades.get(i).render(g);
		}
		
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("SAWBLADES");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		new Thread(game).start();
	}
	@Override
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				player.left = true;
				break;
				
			case KeyEvent.VK_RIGHT:
				player.right = true;
				break;
				
			case KeyEvent.VK_UP:
				player.up = true;
				break;
				
			case KeyEvent.VK_R:
				restart = true;
				break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		
			case KeyEvent.VK_LEFT:
				player.left = false;
				break;
			case KeyEvent.VK_RIGHT:
				player.right = false;
				break;
			case KeyEvent.VK_UP:
				player.up = false;
				player.timer= 0;
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

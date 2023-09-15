package zeldaMiniClone;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener { //import Canvas, Runnable and KeyListener (that imports the commands to control the player) 
																	//and add unimplemented methods on Game

	public static int WIDTH = 640, HEIGHT = 480;
	public static int SCALE = 3;
	
	public World world;
	public Player player;
	public List<Enemy> enemies = new ArrayList<Enemy>(); //Ctrl+Shift+O para importar 
	
	public int framesarrows = 0;
	
	public Game() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		world = new World(); //responsible for rendering "our entire world" 
		new Spritesheet(); //initiating Spritesheet static method
		player = new Player(32,32); //32, 32 so the player won't be rendered at the same spot that the block will be rendered
		this.addKeyListener(this); //add keyboard command events auto created down below
	
		enemies.add(new Enemy(128,128));
		enemies.add(new Enemy(400,300));
	}
	
	public void tick() // Responsible for game logic: Player movement, collision, etc;
	{
		player.tick();
		
		for(int i=0; i < enemies.size(); i++)
		{
			enemies.get(i).tick();
		}
	}
	
	public void render() // Render graphics;
	{
		BufferStrategy bs = this.getBufferStrategy(); //import BufferStrategy
		if (bs == null)
		{
			this.createBufferStrategy(3); //Graphic Optimization
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); //import Graphics
		
		//g.setColor(Color.black);
		g.setColor(new Color(86, 70, 66));
		g.fillRect(0,0, WIDTH*SCALE, HEIGHT*SCALE); //Black screen, clear buffer, avoid flickering
		
		//g.setColor(Color.red);
		//g.fillRect(0, 0, 50, 50); //Our Red Rectangle, the Player
		player.render(g); //render parameter from the Player class
		
		world.render(g); //render the World
		
		for(int i=0; i < enemies.size(); i++)
		{
			enemies.get(i).render(g);
		}
		
		bs.show(); //Screen Render 
	}
	
	//Main Method
	public static void main(String[] args) 
	{
		Game game = new Game(); //Instancing game
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		frame.pack(); //pack everything
		
		frame.setLocationRelativeTo(null); //method to centralize the window
		
		//Method to close the program and release the memory after closing the window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); //Method to see the frame window
	
	
		new Thread(game).start(); //Starting the RUN() which is similar to an Update Method from Unity
	}
	
	@Override
	public void run() //similar to Update Method from Unity
	{
		// TODO Auto-generated method stub
		while (true)
		{
			tick(); //to check input controller command and collisions every frame
			render(); //to render it every frame
			
			try {
				Thread.sleep(1000/60); //To try to execute at 60fps; Surrounded with Try|Catch
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Updating frame!"); //Debug the Update Method
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { //command to fire the arrow
				player.shoot = true;	
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE) { 
			player.shoot = false;		}
	}
}

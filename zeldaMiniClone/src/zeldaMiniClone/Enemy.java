package zeldaMiniClone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class Enemy extends Rectangle { //import Rectangle [Because it has the collision, 
										//vectors (for movement), etc
	
	public boolean right, up, down, left;
	public int lastSprite;
	public int spd = 2;
	public int dir = 0; //horizontal axis for the arrow
	public int ver = 1; //vertical axis for the arrow
	public int arrowsquantity = 1;
	public int moving = 0;
	public int framemove = 0;
	
	public Enemy (int x, int y) { //in parenthesis information obtained by extending Rectangle
		super(x,y, 40, 40); //X, Y, Player's Width and Height
	}
	
	public int curAnimation = 0; //Variable to control the Animation - (index for the array)
	public int curFrames = 0, targetFrames = 10; //control the frames of the animation. The higher the targetFrames, slower the animation
	
	public static List<Arrows> arrows = new ArrayList<Arrows>(); //Adding the ammo/bullets for ranged combat | CTRL+SHIFT+O to import on ArrayList
	
	public boolean shoot = false;
	public int frames = 10;
	
	public void tick() {
		
		boolean moved = false;
		frames++; //waiting for arrows to be relaunched
		framemove++;
		
		if (framemove >= 40)
		{
			int randomNum = ThreadLocalRandom.current().nextInt(0,5);
			moving = randomNum;
			framemove = 0;
		}
		
		if (moving == 0)
		{
			moved = true;
			right = false;
			left = false;
			down = true;
			up = false;
		}
		else if (moving == 1)
		{
			moved = true;
			down = false;
			right = false;
			left = false;
			up = true;
		}
		else if (moving == 2)
		{
			moved = true;
			down = false;
			right = true;
			left = false;
			up = false;
		}
		else if (moving == 3)
		{
			moved = true;
			down = false;
			right = false;
			left = true;
			up = false;
		}
		else
		{
			moved = false;
			down = false;
			right = false;
			left = false;
			up = false;
		}
		
		if(right && World.isFree(x+spd, y)) { //&& World.isFree(x+spd, y) is checking collision
			moved = true;
			ver = 0;
			dir = 1;
			x+=spd;
		}
		else if(left && World.isFree(x-spd, y)) {
			moved = true;
			ver = 0;
			dir = -1;
			x-=spd;
		}
		if(up && World.isFree(x, y-spd)) {
			moved = true;
			ver = -1;
			dir = 0;
			y-=spd; //notice how the - goes up, unlike c# and the 2D Cartesian Graphic
		}
		else if(down && World.isFree(x, y+spd)) {
			moved = true;	
			ver = 1;
			dir = 0;
			y+=spd;			//notice how the + goes down, unlike c# and the 2D Cartesian Graphic		
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.enemy_front.length)
				{
					curAnimation = 0;
				}
			}
		}
		else {
			curAnimation = 0;
		}

		//From now on, check arrow direction and axis
		
		if (lastSprite == 1)
		{
			ver = -1;
			dir = 0;
		}
		else if (lastSprite == 2)
		{
			ver = 0;
			dir = -1;
		}
		else if (lastSprite == 3)
		{
			ver = 0;
			dir = 1;
		}
		else 
		{
			ver = 1;
			dir = 0;
		}
		
		//next, the method to create new arrows and fire them
		
		if (shoot == true) {
			
			if (frames >= 10) //only allow arrows to be released after at least 10 frames since the last one
			{
				arrows.add(new Arrows(x,y,dir,ver));
				frames = 00; //reset frames counting till next arrow
				shoot = false;	
			}
			
		}
		for (int i=0; i < arrows.size(); i++)
		{
			arrows.get(i).tick();
		}
	}
	
	public void render(Graphics g) { //import Graphics g
		//g.setColor(Color.gray); //import Color
		//g.fillRect(x, y, width, height);
		if (up) {g.drawImage(Spritesheet.enemy_back[curAnimation], x, y, 40, 40, null);
		lastSprite = 1;}
		else if (left) {g.drawImage(Spritesheet.enemy_left[curAnimation], x, y, 40, 40, null);
		lastSprite = 2;}
		else if (right) {g.drawImage(Spritesheet.enemy_right[curAnimation], x, y, 40, 40, null);
		lastSprite = 3;}
		else if (down) {g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 40, 40, null);
		lastSprite = 4;}
		else { //if statement to fix mistake of returning sprite to down|front position and staying in the last pose; It checks lastSprite int variable
			if (lastSprite == 1)
			{
				g.drawImage(Spritesheet.enemy_back[curAnimation], x, y, 40, 40, null);
			}
			else if (lastSprite == 2)
			{
				g.drawImage(Spritesheet.enemy_left[curAnimation], x, y, 40, 40, null);
			}
			else if (lastSprite == 3)
			{
				g.drawImage(Spritesheet.enemy_right[curAnimation], x, y, 40, 40, null);
			}
			else
			{
				g.drawImage(Spritesheet.enemy_front[curAnimation], x, y, 40, 40, null);
			}
		}//render player_front at the desired position on the desired size (32 x 32)}
		
		for (int i=0; i < arrows.size(); i++) //method to render the arrows
		{
			arrows.get(i).render(g);
		}
	}
}

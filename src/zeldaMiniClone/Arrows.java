package zeldaMiniClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Arrows extends Rectangle {

	//extends Rectangle to import collisions
	
	public int dir = 1; //variable to define direction
	public int ver = 1; //he taught how to fire to the sides, here I'm defining the vertical axes for firing up and down;
	public int speed = 8; //speed of the arrow

	public int frames = 0; //frames to destroy the arrows after a while
	
	//Now to the constructor method:
	
	public Arrows(int x, int y, int dir, int ver) { //we need now the position x and y when we call this method
		super(x+16,y+16,8,8); //super to call the the constructor method inherited from the Rectangle class: (pos x,pos y, sizex, sizey)
		this.dir = dir; //horizontal axis for the arrow
		this.ver = ver; //vertical axis for the arrow
	}
	
	//now to the update method for our arrows:
	public void tick(){
		if (World.isFree(x, y))
		{
			x+=speed*dir; //he said x + Y || he was wrong. it is only X because we're talking about horizontal axis
			y+=speed*ver; //here we calculate the vertical axis
			frames++;
			if (frames == 30) {
				Player.arrows.remove(this);
				return;
				}
		}
		else
		{
			Player.arrows.remove(this);
			return;
		}
	}
	
	//now to the render method:
	public void render(Graphics g) {
		//g.setColor(Color.gray);
		//g.fillOval(x, y, width, height); //width and height inherited from the Rectangle class
		if (dir == 1)
		{
			g.drawImage(Spritesheet.arrow_right[0], x, y, 15, 5, null);
		}
		else if (ver == -1)
		{
			g.drawImage(Spritesheet.arrow_back[0], x, y-25, 5, 15, null);
		}
		else if (dir == -1)
		{
			g.drawImage(Spritesheet.arrow_left[0], x, y, 15, 5, null);
		}
		else 
		{
			g.drawImage(Spritesheet.arrow_front[0], x, y, 5, 15, null);
		}
		
	}
}

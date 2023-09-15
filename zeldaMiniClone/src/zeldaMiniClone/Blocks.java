package zeldaMiniClone;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle //extends and imports Rectangle - CTRL+SHIFT+O
{
	public Blocks(int x,int y)
	{
		super(x,y,32,32);
	}
	
	public void render(Graphics g)
	{
		//g.setColor(Color.DARK_GRAY);
		//g.fillRect(x, y, width, height);
		//g.setColor(Color.BLACK); //this and the line below create a black edge to the block
		//g.drawRect(x, y, width, height);
		g.drawImage(Spritesheet.tileWall, x, y, 32, 32, null); //draws the image for the wall/block
	}
}

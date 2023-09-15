package zeldaMiniClone;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class World {

	public static List<Blocks> blocks = new ArrayList<Blocks>(); //arraylist to create many different objects; "Static" is necessary to allow access on collision method ahead
	
	public World() //constructor method
	{
		for(int xx = 0; xx < 20; xx++) //Quick math: If our screen is 480x480 how many blocks can we place? 480/32
		{
			blocks.add(new Blocks(xx*32, 0));
		}
		for(int xx = 0; xx < 20; xx++) 
		{
			blocks.add(new Blocks(xx*32, 480-32)); //The bottom part of the screen
		}
		for(int yy = 0; yy < 15; yy++) //vertical, you can leave yy as xx 
		{
			blocks.add(new Blocks(0, yy*32));
		}
		for(int yy = 0; yy < 15; yy++) 
		{
			blocks.add(new Blocks(640-32, yy*32));
		}
	}
	
	public static boolean isFree(int x, int y) //Enabling collision to the blocks.
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			Blocks thisBlock = blocks.get(i);
			if (thisBlock.intersects(new Rectangle(x,y,32,32))) //Intersects is the method that checks collision
			{
				return false; //it is colliding
			}
		}
		return true; //it is not colliding
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < blocks.size(); i++)
		{
			blocks.get(i).render(g); //Render the blocks no matter how many they are
		}
	}
	
}

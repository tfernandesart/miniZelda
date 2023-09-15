package zeldaMiniClone;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Spritesheet {

	public static BufferedImage spritesheet; //importBufferedImage
	
	public static BufferedImage[] player_front; 
	public static BufferedImage[] player_back; 
	public static BufferedImage[] player_left; 
	public static BufferedImage[] player_right; 
	public static BufferedImage[] arrow_front;
	public static BufferedImage[] arrow_back;
	public static BufferedImage[] arrow_left;
	public static BufferedImage[] arrow_right;
	public static BufferedImage[] enemy_front;
	public static BufferedImage[] enemy_back;
	public static BufferedImage[] enemy_left;
	public static BufferedImage[] enemy_right;
	public static BufferedImage tileWall; //render the image of blocks/Wall;
	
	public Spritesheet() {
		//load Sprite_Sheet image
		//Import ImageIO
		//Put on a try and catch because it will try to access the resource and it may fail if it can't find the file spritesheet.png
		try {
			spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 player_front = new BufferedImage[2];
		 player_front[0] = Spritesheet.getSprite(0, 11, 16, 16); //Coordinates from the Sprite_Sheet: x, y and size. It must be initialized AFTER loading the spritesheet
		 player_front[1] = Spritesheet.getSprite(16, 11, 16, 16);
		 player_back = new BufferedImage[2];
		 player_back[0] = Spritesheet.getSprite(69, 11, 16, 16); //Coordinates from the Sprite_Sheet: x, y and size. It must be initialized AFTER loading the spritesheet
		 player_back[1] = Spritesheet.getSprite(86, 11, 16, 16);
		 player_left = new BufferedImage[2];
		 player_left[0] = Spritesheet.getSprite(163, 11, 16, 16); //Coordinates from the Sprite_Sheet: x, y and size. It must be initialized AFTER loading the spritesheet
		 player_left[1] = Spritesheet.getSprite(179, 11, 16, 16);
		 player_right = new BufferedImage[2];
		 player_right[0] = Spritesheet.getSprite(34, 11, 16, 16); //Coordinates from the Sprite_Sheet: x, y and size. It must be initialized AFTER loading the spritesheet
		 player_right[1] = Spritesheet.getSprite(52, 11, 16, 16);
		 
		 arrow_back = new BufferedImage[1];
		 arrow_back[0] = Spritesheet.getSprite(182, 258, 5, 15); //Coordinates for the Vertical Arrow in Sprite_Sheet.
		 arrow_right = new BufferedImage[1];
		 arrow_right[0] = Spritesheet.getSprite(189, 263, 15, 5); //Coordinates for Horizontal Arrow in Sprite Sheet.
		 arrow_left = new BufferedImage[1];
		 arrow_left[0] = Spritesheet.getSprite(217, 263, 15, 5); //Coordinates for Horizontal Arrow in Sprite Sheet.
		 arrow_front = new BufferedImage[1];
		 arrow_front[0] = Spritesheet.getSprite(208, 258, 5, 15); //Coordinates for the Vertical Arrow in Sprite_Sheet.
		 
		 enemy_front = new BufferedImage[2];
		 enemy_front[0] = Spritesheet.getSprite(188, 155, 16, 16);
		 enemy_front[1] = Spritesheet.getSprite(204, 155, 16, 16);
		 enemy_back = new BufferedImage[2];
		 enemy_back[0] = Spritesheet.getSprite(221, 155, 16, 16);
		 enemy_back[1] = Spritesheet.getSprite(238, 155, 16, 16);
		 enemy_left = new BufferedImage[2];
		 enemy_left[0] = Spritesheet.getSprite(255, 155, 16, 16);
		 enemy_left[1] = Spritesheet.getSprite(272, 155, 16, 16);
		 enemy_right = new BufferedImage[2];
		 enemy_right[0] = Spritesheet.getSprite(290, 155, 16, 16);
		 enemy_right[1] = Spritesheet.getSprite(307, 155, 16, 16);
		 tileWall = Spritesheet.getSprite(311, 185, 15, 15); //the same as the above
	}
	
	public static BufferedImage getSprite(int x, int y, int width, int height) { //method to get the "sub-images"
	
		return spritesheet.getSubimage(x, y, width, height);
}
}

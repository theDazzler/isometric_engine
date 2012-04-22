package com.devon.infiniteworld.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

public class WaterTile extends Tile
{
	Rectangle boundingBox;
	
	public WaterTile(int id)
	{
		super(id);
		try {
			this.texture = new Image("assets/images/tiles/water.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//this.boundingBox = new Rectangle(position.x, position.y, GameSettings.TILE_WIDTH, GameSettings.TILE_HEIGHT);
	}

	@Override
	public boolean isCollidable() 
	{
		return false;
	}
}

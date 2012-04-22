package com.devon.isometricengine.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GrassTile extends Tile 
{
	public GrassTile(int id)
	{
		super(id);
		try {
			this.texture = new Image("assets/images/tiles/grass.png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isCollidable() 
	{
		// TODO Auto-generated method stub
		return false;
	}

}

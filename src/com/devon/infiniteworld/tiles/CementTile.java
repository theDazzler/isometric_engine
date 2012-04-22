package com.devon.infiniteworld.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class CementTile extends Tile 
{
	public CementTile(int id)
	{
		super(id);
		try {
			this.texture = new Image("assets/images/tiles/cement.png");
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

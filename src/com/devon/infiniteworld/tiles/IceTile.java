package com.devon.infiniteworld.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class IceTile extends Tile
{

	public IceTile(int id)
	{
		super(id);
		try {
			this.texture = new Image("assets/images/tiles/ice.png").getScaledCopy(0.5f);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isCollidable() {
		// TODO Auto-generated method stub
		return false;
	}

}

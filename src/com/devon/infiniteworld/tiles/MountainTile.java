package com.devon.infiniteworld.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MountainTile extends Tile
{

	public MountainTile(int id) 
	{
		super(id);
		
		try 
		{
			this.texture = new Image("assets/images/tiles/mountain.png");
		} 
		catch (SlickException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public boolean isCollidable() 
	{
		// TODO Auto-generated method stub
		return true;
	}

}

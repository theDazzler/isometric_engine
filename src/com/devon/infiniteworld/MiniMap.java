package com.devon.infiniteworld;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.devon.infiniteworld.entities.Player;
import com.devon.infiniteworld.tiles.Tile;

public class MiniMap
{
	public boolean isVisible = false;
	public int nextDisplay = 0;
	float xPos;
	float yPos;
	Image background;

	int tileWidth;
	int tileHeight;

	float tileScaleFactor = 0.0625f;
	int width = 256;
	int height = 256;

	public MiniMap(float xPos, float yPos) throws SlickException
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.tileWidth = 8;
		this.tileHeight = 8;
	}

	public void draw(float x, float y, Level level, Player player) 
	{
		float startX = x;
		float startY = y;

		for(int i = 0; i < level.map.length; i++)
		{
			for(int j = 0; j < level.map[i].length; j++)
			{
				byte tileType = level.map[i][j];		

				if(tileType == Tile.water.id)
				{
					Tile.water.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.grass.id)
				{
					Tile.grass.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.dirt.id)
				{
					Tile.dirt.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.mountain.id)
				{
					Tile.mountain.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.snow.id)
				{
					Tile.snow.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.lava.id)
				{
					Tile.lava.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
				else if(tileType == Tile.cement.id)
				{
					Tile.cement.draw(x + (j * this.tileWidth), y + (i * this.tileHeight), this.tileScaleFactor);
				}
			}
			//startX = x;
		}
		Tile.lava.draw(this.xPos + (player.getX() * (tileScaleFactor * 2)), this.yPos + ((player.getY() + player.boundingBox().getHeight()) * (tileScaleFactor * 2)), this.tileScaleFactor);
		
	}

	public void update(int delta, Player player)
	{
		if(this.nextDisplay > 0)
			this.nextDisplay--;
		this.xPos = player.getX() + 200;
		this.yPos = player.getY() - 300;
	}
}

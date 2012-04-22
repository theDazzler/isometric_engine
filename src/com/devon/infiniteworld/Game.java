package com.devon.infiniteworld;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{	
	public static final int SCREEN_WIDTH = 1024;
	public static final int SCREEN_HEIGHT = 768;
	
	OutdoorLevel level;
	Camera camera;
	Cube[][][] cubes;
	FloorTile floorTile;
	FloorTile floorTile2;
	

	public Game(String title) 
	{
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		g.scale(camera.zoomLevel / 8, camera.zoomLevel / 8);
		//center player and follow player around
		g.translate((Game.SCREEN_WIDTH / (this.camera.zoomLevel / 8) / 2) - this.camera.centerOnPos.x, (Game.SCREEN_HEIGHT / (this.camera.zoomLevel / 8) / 2) - this.camera.centerOnPos.y);
		//g.rotate(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2, 45);
		
		this.level.draw(this.level.xPos, this.level.yPos, g, this.camera);
		g.drawString("Cubes Rendered:" + this.level.cubesRendered, this.camera.xPos + 300, this.camera.yPos);
		g.drawString("Faces Rendered:" + OutdoorLevel.facesRendered, this.camera.xPos + 50, this.camera.yPos);
		g.drawString("FPS:" + gc.getFPS(), this.camera.xPos + 225, this.camera.yPos);
		//g.draw(floorTile);
		
		/*
		for(int i = 0; i < cubes.length; i++)
		{
			for(int j = 0; j < cubes[i].length; j++)
			{
				for(int k = 0; k < cubes[j].length; k++)
				{
					Cube cube = cubes[i][j][k];
					if(cube != null)
					{
						if(isVisible(cube, i, j, k))
						{
							//System.out.println("ijk: " + i + " " + j + " " + k);
							cubes[i][j][k].draw(g);
						}
					}
				}
			}
			
		}
		*/

		g.resetTransform();

		
	}
	
	private boolean isVisible(Cube cube, int i, int j, int k) 
	{
		boolean result = false;
		//check if cube is attached to right side
		if(j < 64 - 1)
		{
			if(cubes[i][j + 1][k] == null)
			{
				cube.rightVisible = true;
				result = true;
			}	
			else
			{
				cube.rightVisible = false;
			}
		}
		
		
		//check if cube is attached to left side
		if(i < 64 - 1)
		{
			if(cubes[i + 1][j][k] == null)
			{
				cube.leftVisible = true;
				result = true;
			}
			else
			{
				cube.leftVisible = false;
			}
		}
		
		
		//check if cube is attached to top
		if(k < 128 - 1)
		{
			if(cubes[i][j][k + 1] == null)
			{
				cube.topVisible = true;
				result =  true;				
			}
			else
			{
				cube.topVisible = false;
			}
		}

		return result;	
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		this.level = new OutdoorLevel(0, 0, 128, 128, 128);
		this.camera = new Camera(0, 150, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
		this.camera.centerOn(Game.SCREEN_WIDTH / 2, Game.SCREEN_HEIGHT / 2);
		cubes = new Cube[64][64][128];
		cubes[0][0][0] = new Cube(0, 0, 0);
		//cubes[0][1][0] = new Cube(32, 16, 0);
		//cubes[1][0][0] = new Cube(-32, 16, 0);
		cubes[0][0][1] = new Cube(0, 0, 1);
		//floorTile = new FloorTile(0, 0);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		this.camera.update(gc, delta);
	}

}

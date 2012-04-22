package com.devon.isometricengine;

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
		//g.rotate((Game.SCREEN_WIDTH / (this.camera.zoomLevel / 8) / 2)- this.camera.centerOnPos.x, (Game.SCREEN_HEIGHT / (this.camera.zoomLevel / 8) / 2)- this.camera.centerOnPos.y, 45);
		
		this.level.draw(this.level.xPos, this.level.yPos, g, this.camera);
		g.drawString("Cubes Rendered:" + this.level.cubesRendered, this.camera.xPos + 300, this.camera.yPos);
		g.drawString("Faces Rendered:" + OutdoorLevel.facesRendered, this.camera.xPos + 50, this.camera.yPos);
		g.drawString("FPS:" + gc.getFPS(), this.camera.xPos + 225, this.camera.yPos);
		//g.draw(floorTile);
		
		g.resetTransform();

		
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		this.level = new OutdoorLevel(0, 0, 64, 64, 128);
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
		this.camera.update(gc, delta, this.level);
	}

}

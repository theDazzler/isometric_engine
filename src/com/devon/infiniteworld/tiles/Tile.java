package com.devon.infiniteworld.tiles;

import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Rectangle;

/** 
 * Super class that all other tiles inherit from
 * @author Devon Guinane
 *
 */
public abstract class Tile implements Renderable
{
	public static final int WIDTH = 64;
	public static final int HEIGHT= 32;
	
	public static Tile[] tiles = new Tile[256]; //holds all tile types
	public static Tile grass = new GrassTile(1);
	public static Tile water = new WaterTile(2);
	public static Tile snow = new SnowTile(3);
	public static Tile lava = new LavaTile(4);
	public static Tile cement = new CementTile(5);
	public static Tile dirt = new DirtTile(6);
	public static Tile mountain = new MountainTile(7);
	
	public final int id;
	public Image texture; //tile image
	private Rectangle boundingBox; //box used for collision detection
	
	public Tile(int id)
	{
		this.id = id;
		//this.texture = texture;
		//this.boundingBox = new Rectangle(position.x, position.y, width, height);
	}
	
	public abstract boolean isCollidable();
	
	@Override
	public void draw(float x, float y) 
	{
		this.getTexture().draw(x, y, 0.5f);
	}
	
	public void draw(float x, float y, float scaleFactor) 
	{
		this.getTexture().draw(x, y, scaleFactor);
	}
	
	public Image getTexture()
	{
		return this.texture;
	}
	
	/*
	public float getX()
	{
		return this.position.getX();
	}
	*/

	/*
	public float getY() 
	{
		return this.position.getY();
	}
	*/
	
	//get collision rectangle
	public Rectangle getBoundingBox()
	{
		return this.boundingBox;
	}
	
	//set tile's boundingBox
	public void setBoundingBox(float x, float y, int width, int height)
	{
		this.boundingBox = new Rectangle(x, y, Tile.WIDTH, Tile.HEIGHT);
	}
	
	/*
	public Vector2f getWorldMapPosition()
	{
		return new Vector2f(this.getX() / (GameSettings.CHUNK_PIXEL_WIDTH / GameSettings.TILE_WIDTH), this.getY() / (GameSettings.CHUNK_PIXEL_HEIGHT / GameSettings.TILE_HEIGHT));
	}
	*/
}

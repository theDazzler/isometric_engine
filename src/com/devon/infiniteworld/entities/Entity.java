package com.devon.infiniteworld.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class Entity implements Renderable
{
	protected Image texture;
	public Vector2f position;
	protected float width;
	protected float height;
	
	public Entity(Vector2f position, float width, float height, Image texture)
	{
		this.position = position;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}
	

	public abstract void draw(float x, float y);
	
	public Image getTexture() 
	{
		return texture;
	}
	
	public void setTexture(Image texture) 
	{
		this.texture = texture;
	}
	
	public float getWidth() 
	{
		return width;
	}
	
	public void setWidth(float width) 
	{
		this.width = width;
	}
	
	public float getHeight()
	{
		return height;
	}
	
	public void setHeight(float height) 
	{
		this.height = height;
	}
	
	/**
	 * get x position
	 * @return float x coordinate
	 */
	public float getX()
	{
		return this.position.x;
	}
	
	/**
	 * get y position
	 * @return float y coordinate
	 */
	public float getY()
	{
		return this.position.y;
	}	
}

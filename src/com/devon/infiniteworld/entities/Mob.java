package com.devon.infiniteworld.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public abstract class Mob extends Entity
{
	public Vector2f velocity; //direction mob is moving(1, 1 means moving to the right and down)
	
	public Mob(Vector2f position, float width, float height, Image texture)
	{
		super(position, width, height, texture);
		this.velocity = new Vector2f(0, 0);
	}
	
	public void update(GameContainer gc, int delta)
	{
		
	}
	
	//box around Mob used for collision
	public Rectangle boundingBox()
	{
		return new Rectangle(this.getX(), this.getY(), this.width, this.height);
	}
}

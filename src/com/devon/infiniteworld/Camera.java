package com.devon.infiniteworld;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;


public class Camera
{
	private int uiOffest = 0; //set this if a menu is placed at the top of the screen, it will make sure nothing under the menu is rendered
	private float minimumZoomLevel = 1;
	private float maximumZoomLevel = 32;
	private double speed  = 0.35;
	public float xPos;
	public float yPos;
	public float width;
	public float height;
	public Vector2f centerOnPos;
	public float zoomLevel;
	public CameraMouseControls mouseControls;
	
	public Camera(float xPos, float yPos, float width, float height)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.width = width;
		this.height = height;
		this.centerOnPos = new Vector2f(0, 0);
		this.zoomLevel = 8;
		this.mouseControls = new CameraMouseControls(this);
	}
	
	protected ArrayList<Float> getViewFrustram()
	{
		ArrayList<Float> frustram = new ArrayList<Float>();
		
		float topLeftX = (this.centerOnPos.x - (Game.SCREEN_WIDTH /(this.zoomLevel / 8) / 2));
		float topLeftY = (this.centerOnPos.y - (Game.SCREEN_HEIGHT / (this.zoomLevel / 8) / 2)) + (this.uiOffest / (this.zoomLevel / 8));
		float bottomRightX = (this.centerOnPos.x + (Game.SCREEN_WIDTH / (this.zoomLevel / 8) / 2));
		float bottomRightY = (this.centerOnPos.y +(Game.SCREEN_HEIGHT / (this.zoomLevel / 8) / 2));
		
		frustram.add(topLeftX);
		frustram.add(topLeftY);
		frustram.add(bottomRightX);
		frustram.add(bottomRightY);
		
		return frustram;
	}
	
	/**
	 * The higher the number, the more zoomed in the camera will be(8 is default zoom)
	 * @param amount
	 */
	public void zoom(float amount)
	{
		if(amount >= this.minimumZoomLevel && amount <= this.maximumZoomLevel)
		{
			this.zoomLevel = amount;
			this.xPos = (this.centerOnPos.x - (Game.SCREEN_WIDTH /(this.zoomLevel / 8) / 2));
			this.yPos = (this.centerOnPos.y - (Game.SCREEN_HEIGHT / (this.zoomLevel / 8) / 2)) + (this.uiOffest / (this.zoomLevel / 8));
		}
	}

	/**
	 * Checks to see if coordinates are in player's view frustrum
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return
	 */
	public boolean inFrustram(float x, float y)
	{
		ArrayList<Float> frustram = getViewFrustram();
		if(x >= frustram.get(0) && x <= frustram.get(2) && y >= frustram.get(1) && y <= frustram.get(3))
			return true;
		
		return false;
	}
	
	public void centerOn(float x, float y)
	{
		this.centerOnPos.x = x;
		this.centerOnPos.y = y;
	}
		
	public void update(GameContainer gc, int delta) 
	{
		handleInput(gc, delta);
	}
	
	//handle player's input
	private void handleInput(GameContainer gc, int delta) 
	{
		Input input = gc.getInput();
		input.addMouseListener(this.mouseControls);
		
		
		
		if(input.isKeyDown(Input.KEY_ESCAPE))
		{
			gc.exit();
		}
		
		
				
		//space bar
		if(input.isKeyDown(Input.KEY_SPACE))
		{

		}
		
		//move left
		if(input.isKeyDown(Input.KEY_A))
		{
		
			move(-1, 0, delta);

		}
		
		//move right
		if(input.isKeyDown(Input.KEY_D))
		{

			move(1, 0, delta);
		}
		
		//move down
		if(input.isKeyDown(Input.KEY_S))
		{
			
			move(0, 1, delta);
			
		}
		
		//move up
		if(input.isKeyDown(Input.KEY_W))
		{
	
			move(0, -1, delta);
			
		}

		/*
		//if not moving left or right, set x velocity to 0
		if(!(input.isKeyDown(Input.KEY_A)) && !(input.isKeyDown(Input.KEY_D)))
		{
			this.velocity.x = 0f;
		}
		
		//if not moving up or down, set y velocity to 0
		if(!(input.isKeyDown(Input.KEY_S)) && !(input.isKeyDown(Input.KEY_W)))
		{
			this.velocity.y = 0f;
		}
		*/
	}

	
	public void move(int x, int y, int delta)
	{
		this.xPos += x * (speed * delta);
		this.yPos += y * (speed * delta);
		
		//(this.centerOnPos.x - (Game.SCREEN_WIDTH /(this.zoomLevel / 8) / 2))
		
		this.centerOnPos.x += x * (speed * delta);
		this.centerOnPos.y += y * (speed * delta);
	}
}


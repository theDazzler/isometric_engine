package com.devon.infiniteworld;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

import com.devon.infiniteworld.tiles.Tile;

public class Cube implements Comparable
{
	ArrayList<Polygon> faces;
	Polygon top;
	Polygon left;
	Polygon right;
	Polygon bottom;
	public float xPos;
	public float yPos;
	public float zPos; //height(for making columns, hills, steps, etc.)
	Color color;
	
	public boolean isVisible = true;
	public boolean topVisible = true;
	public boolean rightVisible = true;
	public boolean leftVisible = true;
	
	public Random random = new Random();
	
	/**
	 * Position is the position of the bottom face. It would be the bottom side's top point
	 * @param xPos
	 * @param yPos
	 */
	public Cube(float xPos, float yPos, float zPos)
	{
		//water
		if(zPos < 0)
		{
			this.color = new Color(0, 0, 153);
		}
		//beach
		else if(zPos < 1.2)
			this.color = new Color(243, 192, 111);
		
		//grass
		else if(zPos < 3)
			this.color = new Color(0, 153, 11);
		
		//mountain
		else if(zPos > 4.5)
			this.color = new Color(184, 134, 11);
		
		else
			this.color = new Color(0, 153, 11);
		
		
		this.xPos = xPos;
		this.yPos = yPos;
		
		zPos = (float) Math.ceil(zPos);
		
		this.zPos = zPos;
		
		if(this.zPos < 0)
			this.zPos = 0;
		

		//this.color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
		
		
		createFaces();

	}
	
	private void createFaces() 
	{
		//Bottom Face
		float[] bottomPoints = new float[8];
		bottomPoints[0] = xPos; //topX
		bottomPoints[1] = yPos + (-this.zPos * Tile.HEIGHT); //topY
		bottomPoints[2] = xPos + (Tile.WIDTH / 2);//rightX
		bottomPoints[3] = yPos + (Tile.HEIGHT / 2) + + (-this.zPos * Tile.HEIGHT);//rightY
		bottomPoints[4] = xPos;//bottomX
		bottomPoints[5] = yPos + Tile.HEIGHT + (-this.zPos * Tile.HEIGHT);//bottomY
		bottomPoints[6] = xPos - (Tile.WIDTH / 2);//leftX
		bottomPoints[7] = yPos + (Tile.HEIGHT / 2) + + (-this.zPos * Tile.HEIGHT);//leftY
		this.bottom = new Polygon(bottomPoints);
		
		//Top Face		
		this.top = this.bottom.copy();
		this.top.setCenterX(this.bottom.getCenterX());
		this.top.setCenterY(this.bottom.getCenterY() - Tile.HEIGHT);
		
		//Right Face
		float[] rightPoints = new float[8];
		rightPoints[0] = this.top.getPoint(2)[0];//topLeftX
		rightPoints[1] = this.top.getPoint(2)[1];//topLeftY
		rightPoints[2] = this.top.getPoint(1)[0]; //topRightX
		rightPoints[3] = this.top.getPoint(1)[1]; //topRightY
		rightPoints[4] = rightPoints[2];//bottomRightX
		rightPoints[5] = rightPoints[3] + 32;//bottomRightY
		rightPoints[6] = rightPoints[0];//bottomLeftX
		rightPoints[7] = rightPoints[1] + 32;//bottomLeftY
		this.right = new Polygon(rightPoints);
		
		//Left Face
		this.left = this.right.copy();
		float[] leftPoints = new float[8];
		leftPoints[0] = this.top.getPoint(3)[0];//topLeftX
		leftPoints[1] = this.top.getPoint(3)[1];//topLeftY
		leftPoints[2] = this.top.getPoint(2)[0]; //topRightX
		leftPoints[3] = this.top.getPoint(2)[1]; //topRightY
		leftPoints[4] = leftPoints[2];//bottomRightX
		leftPoints[5] = leftPoints[3] + 32;//bottomRightY
		leftPoints[6] = leftPoints[0];//bottomLeftX
		leftPoints[7] = leftPoints[1] + 32;//bottomLeftY
		this.left = new Polygon(leftPoints);
	
		//only add 3 faces because I only want these 3(top, left, right) to be rendered
		this.faces = new ArrayList<Polygon>();
		this.faces.add(this.top);
		this.faces.add(this.left);
		this.faces.add(this.right);
		
	}

	public void draw(Graphics g)
	{
		if(this.topVisible)
		{
			Polygon top = this.faces.get(0);
			g.setColor(new Color(220, 220, 220));
			g.fill(top);
			g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
			g.setColor(this.color);
			g.fill(top);
			g.setDrawMode(Graphics.MODE_NORMAL);
			OutdoorLevel.facesRendered++;
		}
		
		if(this.leftVisible)
		{
			Polygon left = this.faces.get(1);
			g.setColor(new Color(128, 128, 128));
			g.fill(left);
			g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
			g.setColor(this.color);
			g.fill(left);
			g.setDrawMode(Graphics.MODE_NORMAL);
			OutdoorLevel.facesRendered++;
		}
		
		
		if(this.rightVisible)
		{
			Polygon right = this.faces.get(2);
			g.setColor(new Color(36, 36, 36));
			g.fill(right);
			g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
			g.setColor(this.color);
			g.fill(right);
			g.setDrawMode(Graphics.MODE_NORMAL);
			OutdoorLevel.facesRendered++;
		}

		g.setColor(Color.white);	
	}
	
	@Override
	public int compareTo(Object cube)
	{
		Cube cube2 = (Cube) cube;
		if(this.zPos < cube2.zPos)
			return -1;
		else if(this.zPos > cube2.zPos)
			return 1;
		else
			return 0;
	}
}

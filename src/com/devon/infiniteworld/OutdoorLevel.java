package com.devon.infiniteworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.GeomUtil;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import com.devon.infiniteworld.tiles.Tile;

public class OutdoorLevel extends Level
{
	public Polygon[][] floor;
	public Cube[][][] cubes;
	public double[][] map;
	
	public static int facesRendered = 0;
	public int cubesRendered = 0;
	
	public OutdoorLevel(float xPos, float yPos, int width, int height, int zDepth) 
	{
		super(xPos, yPos, width, height, zDepth);
		this.map = NoiseMap.getMap(width, height);
		this.cubes = new Cube[width][height][zDepth];
		
		float[] points = new float[8];
		points[0] = xPos; //topX
		points[1] = yPos; //topY
		points[2] = xPos + (Tile.WIDTH / 2);//rightX
		points[3] = yPos + (Tile.HEIGHT / 2);//rightY
		points[4] = xPos;//bottomX
		points[5] = yPos + Tile.HEIGHT;//bottomY
		points[6] = xPos - (Tile.WIDTH / 2);//leftX
		points[7] = yPos + (Tile.HEIGHT / 2);//leftY

		Polygon tile = new Polygon(points);
		
		initPolygons(tile); //creates wireframe floor
		initCubes();
	}

	private void initPolygons(Polygon polygon)
	{
		Polygon prevPoly = polygon;
		this.floor = new Polygon[this.width][this.height];
		
		for(int i = 0; i < this.width; i++)
		{
			for(int j = 0; j < this.height; j++)
			{
				floor[i][j] = prevPoly;	
				prevPoly = prevPoly.copy();
				prevPoly.setCenterX(prevPoly.getCenterX() + (Tile.WIDTH / 2));
				prevPoly.setCenterY(prevPoly.getCenterY() + (Tile.HEIGHT / 2));
			}
			prevPoly.setCenterX(polygon.getCenterX() - ((Tile.WIDTH / 2) * i));
			prevPoly.setCenterY(polygon.getCenterY() + ((Tile.HEIGHT / 2) * i));
		}
	}
	
	@SuppressWarnings("unchecked")
	private void initCubes()
	{
		for(int i = 0; i < this.map.length; i++)
		{
			for(int j = 0; j < this.map[i].length; j++)
			{
				double heightValue = this.map[i][j];
				//heightValue = Math.ceil(heightValue);
				
				if(heightValue < 0)
				{
					Cube cube = new Cube(this.floor[i][j].getX() + (Tile.WIDTH / 2), this.floor[i][j].getY(), (float) heightValue);
					this.cubes[i][j][0] = cube;
					continue;
				}
				else
				{
					for(int k = (int)heightValue; k >= 0; k--)
					{
						Cube cube = new Cube(this.floor[i][j].getX() + (Tile.WIDTH / 2), this.floor[i][j].getY(), k);
						this.cubes[i][j][k] = cube;
					}
				}
			}
		}
		
		flagCubes();
	}

	/**
	 * set all cubes' visibility properties
	 */
	private void flagCubes() 
	{
		for(int i = 0; i < this.width; i++)
		{
			for(int j = 0; j < this.height; j++)
			{
				for(int k = 0; k < this.zDepth; k++)
				{
					//System.out.println("ijk: " + i + " " + j + " " + k);
					Cube cube = this.cubes[i][j][k];
					
					if(cube != null)
					{
						flagCube(cube, i, j, k);
					}
				}
			}
		}
		
	}

	public void draw(float x, float y, Graphics g, Camera camera)
	{
		this.cubesRendered = 0;
		OutdoorLevel.facesRendered = 0;
		/*
		//draw floor
		for(int i = 0; i < this.floor.length; i++)
		{
			for(int j = 0; j < this.floor[i].length; j++)
			{
				//if(i == 0 && j == 0)
					//g.fillRect((float)(x + (j * Tile.WIDTH)), (float)(y + (i * Tile.HEIGHT)), 64, 64);
				//g.drawRect((float)(x + (j * Tile.WIDTH)), (float)(y + (i * Tile.HEIGHT)), 64, 64);
				
				Polygon poly = this.floor[i][j];
				float[] points = poly.getPoints();
				//poly = (Polygon) poly.transform(Transform.createRotateTransform((float)Math.toRadians(45)));
				
				//render all polygons that have a point in the camera frustrum
				if(camera.inFrustram(points[0], points[1]) || camera.inFrustram(points[2], points[3]) || camera.inFrustram(points[4], points[5]) || camera.inFrustram(points[6], points[7]))
				{
					//g.setColor(new Color(0, 150, 0));
					g.draw(poly);
				}				
			}
		}
		*/
		
		//draw cubes
		for(int i = 0; i < this.width; i++)
		{
			for(int j = 0; j < this.height; j++)
			{
				for(int k = 0; k < this.zDepth; k++)
				{
					Cube cube = this.cubes[i][j][k];
					
					if(cube != null)
					{
					
						float cubeX = cube.xPos;
						float cubeY = cube.yPos + (-cube.zPos * Tile.HEIGHT);
						
						if(camera.inFrustram(cubeX, cubeY - Tile.HEIGHT) || camera.inFrustram(cubeX + (Tile.WIDTH / 2), cubeY - (Tile.HEIGHT / 2)) || camera.inFrustram(cubeX + (Tile.WIDTH / 2), cubeY + (Tile.HEIGHT / 2)) || camera.inFrustram(cubeX, cubeY + Tile.HEIGHT) || camera.inFrustram(cubeX - (Tile.WIDTH / 2), cubeY + (Tile.HEIGHT / 2)) || camera.inFrustram(cubeX - (Tile.WIDTH / 2), cubeY - (Tile.HEIGHT / 2)))
						{
							if(cube.isVisible)
							{
								cube.draw(g);
								cubesRendered++;
							}
						}	
					}
					
				}
			}
		}
	}

	/**
	 * determines a cube's visibility and sets which of its sides are visible
	 * @param cube
	 * @param i
	 * @param j
	 * @param k
	 */
	private void flagCube(Cube cube, int i, int j, int k) 
	{
		boolean result = false;;
		
		//check if cube is attached to right side
		if(j < this.height - 1)
		{
			if(this.cubes[i][j + 1][k] == null)
			{
				cube.rightVisible = true;
				result = true;
			}	
			else
				cube.rightVisible = false;
		}
		
		
		//check if cube is attached to left side
		if(i < this.width - 1)
		{
			if(this.cubes[i + 1][j][k] == null)
			{
				cube.leftVisible = true;
				result = true;
			}
			else
				cube.leftVisible = false;
		}
		
		
		//check if cube is attached to top
		if(k < this.zDepth - 1)
		{
			if(this.cubes[i][j][k + 1] == null)
			{
				cube.topVisible = true;
				result = true;
			}
			else
				cube.topVisible = false;
		}

		if(result == false)
			cube.isVisible = false;
		else
			cube.isVisible = true;
		
	}

	@Override
	public void draw(float arg0, float arg1) {
		// TODO Auto-generated method stub
		
	}
}

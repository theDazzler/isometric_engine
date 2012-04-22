package com.devon.infiniteworld;

import org.newdawn.slick.geom.Polygon;

import com.devon.infiniteworld.tiles.Tile;

public class FloorTile extends Polygon
{
	public float xPos;
	public float yPos;
	
	public FloorTile(float xPos, float yPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		
		float[] points = new float[8];
		points[0] = xPos; //topX
		points[1] = yPos; //topY
		points[2] = xPos + (Tile.WIDTH / 2);//rightX
		points[3] = yPos + (Tile.HEIGHT / 2);//rightY
		points[4] = xPos;//bottomX
		points[5] = yPos + Tile.HEIGHT;//bottomY
		points[6] = xPos - (Tile.WIDTH / 2);//leftX
		points[7] = yPos + (Tile.HEIGHT / 2);//leftY

		this.addPoint(points[0], points[1]);
		this.addPoint(points[2], points[3]);
		this.addPoint(points[4], points[5]);
		this.addPoint(points[6], points[7]);

	}
}

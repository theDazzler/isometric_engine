package com.devon.isometricengine;

import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.newdawn.slick.geom.Vector2f;

import com.devon.isometricengine.tiles.Tile;

public class NoiseMap
{
	private static final Random random = new Random();
	public double[] values;
	public int w, h;
	public double[][] noiseData;
	public Vector2f origin;
	
	public NoiseMap(int w, int h, int featureSize)
	{
		this.w = w;
		this.h = h;
		this.noiseData = new double[w][h];
		
		this.values = new double[w * h];
		
		for(int y = 0; y < w; y += featureSize)
		{
			for(int x = 0; x < w; x += featureSize)
			{
				setSample(x, y, random.nextFloat() * 2 - 1);
			}
		}
		
		
		int stepSize = featureSize;
		double scale = 1.0 / w;
		double scaleMod = 1;
		do
		{
			int halfStep = stepSize / 2;
			
			for(int y = 0; y < w; y += stepSize)
			{
				for(int x = 0; x < w; x += stepSize)
				{
					double a = sample(x, y);
					double b = sample(x + stepSize, y);
					double c = sample(x, y + stepSize);
					double d = sample(x + stepSize, y + stepSize);
					
					double e = (a + b + c + d) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale;
					setSample(x + halfStep, y + halfStep, e);
				}
			}
			for(int y = 0; y < w; y += stepSize)
			{
				for(int x = 0; x < w; x += stepSize)
				{
					double a = sample(x, y);
					double b = sample(x + stepSize, y);
					double c = sample(x, y + stepSize);
					double d = sample(x + halfStep, y + halfStep);
					double e = sample(x + halfStep, y - halfStep);
					double f = sample(x - halfStep, y + halfStep);
					
					double H = (a + b + d + e) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale * 0.5;
					double g = (a + c + d + f) / 4.0 + (random.nextFloat() * 2 - 1) * stepSize * scale * 0.5;
					setSample(x + halfStep, y, H);
					setSample(x, y + halfStep, g);
				}
			}
			stepSize /= 2;
			scale *= (scaleMod + 1);
			scaleMod *= 0.3;
		}
		while(stepSize > 1);
	}
	
	public void setOrigin(float xPos, float yPos)
	{
		this.origin = new Vector2f(xPos, yPos);
	}
	
	private double sample(int x, int y)
	{
		return this.values[(x & (w - 1)) + (y & (h - 1)) * w];
	}
	
	private void setSample(int x, int y, double value)
	{
		this.values[(x & (w - 1)) + (y & (h - 1)) * w] = value;
	}
	
	public static double[][] getMap(int w, int h)
	{
		double [][] map = new double[w][h];
		NoiseMap noise1 = new NoiseMap(w, h, w / 4);
		NoiseMap noise2 = new NoiseMap(w, h, w / 8);
		NoiseMap noise3 = new NoiseMap(w, h, w / 8);

		for(int y = 0; y < h; y++)
		{
			for(int x = 0; x < w; x++)
			{
				int i = x + y * w;
				
				double val = Math.abs(noise1.values[i] - noise2.values[i]) * 11 - 1.6;
				//val = Math.abs(val - noise3.values[i]) * 3 - 2;
				
				double xd = x / (w - 1.0) * 2 - 1;
				double yd = y / (h - 1.0) * 2 - 1;
				if(xd < 0) xd = -xd;
				if(yd < 0) yd = -yd;
				double dist = xd >= yd ? xd : yd;
				dist = dist * dist * dist * dist;
				dist = dist * dist * dist * dist;

				val = val + 1 - dist * 20;
				
				//int br = val < 0 ? 0 : 255;
				//if(val < 0) map[y][x] = (byte) Tile.water.id; //water
				//else if(val > 3) map[y][x] = (byte) Tile.mountain.id; //mountain
				//else if(val < 0.5) map[y][x] = (byte)Tile.dirt.id; //dirt
				//else
				map[y][x] = val; //grass
					
				//System.out.println("index: " + i + " NOISE VAL : " + val);
			}
		}
		
		return map;
	}
	
	public static void main(String[] args)
	{
		for(int j = 0; j < 10; j++)
		{
			
			int w = 512;
			int h = 512;
			
			//NoiseMap noiseMap = new NoiseMap(w, h, w/4);
			double[][] testM = NoiseMap.getMap(w, h);
			double max = 0;
			BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			int[] pixels = new int[w * h];
			for(int y = 0; y < h; y++)
			{
				for(int x = 0; x < w; x++)
				{
					int i = x + y * w;
					//System.out.println(testM[y][x]);
					if(testM[y][x] > max)
						max = testM[y][x];
					//System.out.println(max);
					
					if(testM[y][x] < 0)pixels[i] = 0x000080;//water
					else if(testM[y][x] < 3)pixels[i] = 0x208020; //grass
					else if(testM[y][x] > 3)pixels[i] = 0xe5c08c;//mountain
					//else if(testM[y][x] == 3)pixels[i] = 0xa77939;//semi-mountain
					//else if(testM[y][x] < 3)pixels[i] = 0x404040; //dirt
						
						

				}
			}
			img.setRGB(0, 0, w, h, pixels, 0, w);
			JOptionPane.showMessageDialog(null, null, "Another", JOptionPane.YES_NO_OPTION, new ImageIcon(img));
		}
	}
	
}

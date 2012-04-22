package com.devon.infiniteworld;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Main 
{
	public static void main(String[] args)
	{
        try
        {
            AppGameContainer app = new AppGameContainer(new ScalableGame(new Game("Infinite World"), Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, true));
            app.setDisplayMode(Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT, false);
            //app.setTargetFrameRate(60);
            app.start();
        } 
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
}

package com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.TankGame;

public class Bullet {
	public static final int SPEED = 10;
	
	private static Texture pellet;
	
	float x,y;
	String direction;
	public Rectangle bulletRect;
	
	public boolean remove = false;
	
	TankGame tank = new TankGame();
	
	public Bullet(float x, float y, String direction) {
		this.x = x + 35;
		this.y = y + 35;
		this.direction = direction;
		
		
		if(pellet == null) {
			pellet = new Texture("tankShell.png");
		}
	}
	
	public void update() {
		
		
		switch (direction) {
		case "up":
			
			y += SPEED;
			break;	
		case "down":
			y -= SPEED;
			break;
		case "left":
			x -= SPEED;
			break;
		case "right":
			x += SPEED;
			break;		
        }
		
		if (y > Gdx.graphics.getHeight() || y < 0) {
			remove = true;
		}
		if (x > Gdx.graphics.getWidth() - 520 || x < 0) {
			remove = true;
		}
		bulletRect = new Rectangle(x, y, pellet.getWidth(), pellet.getHeight());

	}
	
	
	public void render(SpriteBatch batch) {
		batch.draw(pellet,  x,  y);
	}
}
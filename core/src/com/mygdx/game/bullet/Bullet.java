/**
Copyright 2021 STU Computer Science

An abstract class that contains the logic for the bullets.

*/

/**
 * @author Alec Quiroga, Andhy Gomez
 */
package com.mygdx.game.bullet;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.TankGame;

public class Bullet {
	
	// Declaring variables
	public static final int SPEED = 10;
	private static Texture pellet;
	float x,y;
	String direction;
	public Rectangle bulletRect;
	public boolean remove = false;
	TankGame tank = new TankGame();
	
	
	// Constructor that places the bullet on the screen and sets the direction it will hold.
	public Bullet(float x, float y, String direction) {
		this.x = x + 35;
		this.y = y + 35;
		this.direction = direction;
		
		
		if(pellet == null) {
			pellet = new Texture("tankShell.png");
		}
	}
	
	
	// Updates the place the pellet is on the screen.
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
		
		// Generates the rectangle of the bullets.
		bulletRect = new Rectangle(x, y, pellet.getWidth(), pellet.getHeight());

	}
	
	// Draws the pellets.
	public void render(SpriteBatch batch) {
		batch.draw(pellet,  x,  y);
	}
}
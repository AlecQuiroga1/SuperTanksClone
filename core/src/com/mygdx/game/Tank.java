/**
Copyright 2021 STU Computer Science


Class generates the first tank and the logic to move it.
*/



/**
 * @author Alec Quiroga, Andhy Gomez
 *
 */

package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Tank
{
	
	// Initializing global variables
	SpriteBatch batch;
	String tankDirection = "up";
	Texture tankUp, tankRight, tankLeft, tankDown;
	int tankPosX = 0;
	int tankPosY = 0;
	Rectangle tankRect;

	
	/**
	 * 
	 */
	public void create() {
		
		// Adds the images to the textures
		tankUp = new Texture("tankupRed.png");
		tankRight = new Texture("tankRightRed.png");
		tankLeft = new Texture("tankLeftRed.png");
		tankDown = new Texture("tankDownRed.png");
		
		tankRect = new Rectangle(tankPosX, tankPosY, tankUp.getWidth(), tankUp.getHeight());
		
	}
	
	
	//Returns the tanks x position
	public int getTankPosX() {
		return tankPosX;
	}
	
	// Returns the tanks Y position
	public int getTankPosY() {
		return tankPosY;
	}
	
	//Updates the direction and the position of the tank depending on the key pressed.
	public void tankUpdate() {
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			keyUp();
			tankDirection = "up";
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			keyDown();
			tankDirection = "down";
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			keyLeft();
			tankDirection = "left";		
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			keyRight();
			tankDirection = "right";
		}	
	}
	
	
	// Will add or subtract pixels depending on the direction the tank is going.
	public void keyUp() {
		if(tankPosY <= 1005) {
			tankPosY += 5;
		}
	}
	
	
	public void keyDown() {
		if(tankPosY >= 1) {
			tankPosY -= 5;
		}
	}
	
	public void keyLeft() {
		if(tankPosX >= 1) {
			tankPosX -= 5;
		}
	}
	
	public void keyRight() {
		if(tankPosX <= 1325) {
			tankPosX += 5;
		}
	}
}



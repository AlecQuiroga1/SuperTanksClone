package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


/**
 * @author Alec Quiroga, Andhy Gomez
 * 
 * 
 *
 */

public class Tank
{
	
	// Initializing global variables
	SpriteBatch batch;
	String tankDirection = "up";
	Texture tankUp, tankRight, tankLeft, tankDown;
	int tankPosX = 0;
	int tankPosY = 0;
	Rectangle tankRect;

	public void create() {
		tankUp = new Texture("tankupRed.png");
		tankRight = new Texture("tankRightRed.png");
		tankLeft = new Texture("tankLeftRed.png");
		tankDown = new Texture("tankDownRed.png");
		
		tankRect = new Rectangle(tankPosX, tankPosY, tankUp.getWidth(), tankUp.getHeight());
		
	}
	public Tank() {
		
	}
	
	public int getTankPosX() {
		return tankPosX;
	}
	public int getTankPosY() {
		return tankPosY;
	}
	
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
	public void drawTank() {
		
		if(tankDirection.equalsIgnoreCase("right")) {
			batch.draw(tankRight, tankPosX, tankPosY);
		}
		if(tankDirection.equalsIgnoreCase("left")) {
			batch.draw(tankLeft, tankPosX, tankPosY);
		}
		if(tankDirection.equalsIgnoreCase("up")) {
			batch.draw(tankUp, tankPosX, tankPosY);
		}
		if(tankDirection.equalsIgnoreCase("down")) {
			batch.draw(tankDown, tankPosX, tankPosY);
		}
		
	}
	   
	
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



/**
Copyright 2021 STU Computer Science

Class generates the second tank and the logic to move it.
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

public class Tank2 {
	
	// Sets the tank starting position to the top right corner.
	int tank2PosX = 1250;
	int tank2PosY = 900;
	
	SpriteBatch batch;
	String tank2Direction = "down";
	Texture tankDown, tankUp, tankLeft, tankRight;
	Rectangle tank2rect;

	public void create() {
	
		// Initializes the textures.
		tankUp = new Texture("tankupRed.png");
		tankRight = new Texture("tankRightRed.png");
		tankLeft = new Texture("tankLeftRed.png");
		tankDown = new Texture("tankDownRed.png");
		
		//Generates the tank 
		tank2rect = new Rectangle(tank2PosX, tank2PosY, tankDown.getWidth(), tankDown.getHeight());
	}
	
	
	//Updates the direction and the position of the tank depending on the key pressed.
	public void tankUpdate() {
		
		if(Gdx.input.isKeyPressed(Keys.W)) {
			keyUp();
			tank2Direction = "up";
		}
		else if(Gdx.input.isKeyPressed(Keys.S)) {
			keyDown();
			tank2Direction = "down";
		}
		else if( Gdx.input.isKeyPressed(Keys.A)) {
			keyLeft();
			tank2Direction = "left";		
		}
		else if(Gdx.input.isKeyPressed(Keys.D)) {
			keyRight();
			tank2Direction = "right";
		}	
	}
	
	

// Will add or subtract pixels depending on the direction the tank is going.
public void keyUp() {
	if(tank2PosY <= 1005) {
		tank2PosY += 5;
	}
}

public void keyDown() {
	if(tank2PosY >= 1) {
		tank2PosY -= 5;
	}
}

public void keyLeft() {
	if(tank2PosX >= 1) {
		tank2PosX -= 5;
	}
}

public void keyRight() {
	if(tank2PosX <= 1325) {
		tank2PosX += 5;
	}
}
}

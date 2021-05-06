package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Tank2 {
	int tank2PosX = 1250;
	int tank2PosY = 900;
	
	SpriteBatch batch;
	
	String tank2Direction = "down";
	
	Texture tankDown, tankUp, tankLeft, tankRight;
	Rectangle tank2rect;

	public void create() {
	
		
		tankUp = new Texture("tankupRed.png");
		tankRight = new Texture("tankRightRed.png");
		tankLeft = new Texture("tankLeftRed.png");
		tankDown = new Texture("tankDownRed.png");
		
		tank2rect = new Rectangle(tank2PosX, tank2PosY, tankDown.getWidth(), tankDown.getHeight());
	}
	
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
	
	
	public void drawTank() {
		
		if(tank2Direction.equalsIgnoreCase("right")) {
			batch.draw(tankRight, tank2PosX, tank2PosY);
		}
		if(tank2Direction.equalsIgnoreCase("left")) {
			batch.draw(tankLeft, tank2PosX, tank2PosY);
		}
		if(tank2Direction.equalsIgnoreCase("up")) {
			batch.draw(tankUp, tank2PosX, tank2PosY);
		}
		if(tank2Direction.equalsIgnoreCase("down")) {
			batch.draw(tankDown, tank2PosX, tank2PosY);
		}
		
	}

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

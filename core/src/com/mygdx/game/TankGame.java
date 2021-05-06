/**
Copyright 2021 STU Computer Science

Contains the logic for the game and generating the map.
*/

/**
 * @author Alec Quiroga, Andhy Gomez
 */

package com.mygdx.game;

import com.mygdx.game.bullet.Bullet;

//import io.socket.client.IO;
//import io.socket.client.Socket;

import java.awt.Rectangle;
import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class TankGame extends ApplicationAdapter implements InputProcessor{
	
	//Declaring the global variables
	SpriteBatch batch;
	Texture background, tankUp, tankRight, tankLeft, tankDown;
	Rectangle tankRect;
	Sprite sprite;
	TiledMap map;
	OrthographicCamera camera;
	TiledMapRenderer mapRender;
	Tank tank;
	Tank2 secondTank;
	
	
	//private Socket socket;	
	ShapeRenderer rects;
	int wallBoundsId, flagBoundsId;
	
	
	// Initialize the arraylist for the two tanks bullets.
	ArrayList<Bullet> bullets;
	ArrayList<Bullet> tank2Bullets;
	
	
	/**
	 * Initializing the variables previously declared.
	 * Creates any things need for the game to work properly. 
	 */
	@Override
	public void create () {
				
		
		// Gets the width and height of the texture.
		float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        
        // Initializes the tank asset and the map asset.
        background = new Texture("demobgv2.png");
        tankUp = new Texture("tankupRed.png");
		tankRight = new Texture("tankRightRed.png");
		tankLeft = new Texture("tankLeftRed.png");
		tankDown = new Texture("tankDownRed.png");
		
		bullets = new ArrayList<Bullet>();
		tank2Bullets = new ArrayList<Bullet>();
		
		tank = new Tank();
		secondTank = new Tank2();
        
		map = new TmxMapLoader().load("map1v3.tmx");
        mapRender = new OrthogonalTiledMapRenderer(map);
        
        
        getCollisionTilesFrom();
                
        Gdx.input.setInputProcessor(this);
        
        tankRect = new Rectangle(tank.getTankPosX(), tank.getTankPosY(), tankUp.getWidth(), tankUp.getHeight());
        batch = new SpriteBatch();
        
        wallBoundsId = 3;
        flagBoundsId = 4;
        
	}

	// The images are drawn within this method.
	@Override
	public void render () {
		
		secondTank.create();
        tank.create();
		
		
		// Code for shooting
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			bullets.add(new Bullet(tank.tankPosX, tank.tankPosY, tank.tankDirection));
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.Q)) {
			tank2Bullets.add(new Bullet(secondTank.tank2PosX, secondTank.tank2PosY, secondTank.tank2Direction));
		}
		
		// Remove bullets list.
		ArrayList<Bullet> removeBullets = new ArrayList<>();
		ArrayList<Bullet> removeTank2Bullets = new ArrayList<>();
		
		
		
		// If the bullet hits something it adds it to the remove list.
		for(Bullet bullet: bullets) {
			bullet.update();
			
			if(bullet.remove) {
				removeBullets.add(bullet);
			}
		}
		
		for(Bullet bullet: tank2Bullets) {
			bullet.update();
			
			if(bullet.remove) {
				removeTank2Bullets.add(bullet);
			}
		}
		
		
		
		// Code for tank movement
		tank.tankUpdate();
		secondTank.tankUpdate();
		
		tankRect.setLocation(tank.getTankPosX(), tank.getTankPosY());
		
		//Generates the map.
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRender.setView(camera);
        mapRender.render();
        batch.begin();
        
        
        // If the bullets hit a tank it adds it to the remove list.
        for(Bullet bullet: bullets) {
        	bullet.render(batch);
        	if (secondTank.tank2rect.overlaps(bullet.bulletRect)) {
        		removeBullets.add(bullet);
        		secondTank.tank2PosX = 1000;
        		secondTank.tank2PosY = 1200;
        	}
        }
        
        for(Bullet bullet: tank2Bullets) {
        	bullet.render(batch);
        	if(tank.tankRect.overlaps(bullet.bulletRect)) {
        		removeTank2Bullets.add(bullet);
        		tank.tankPosX = 50;
        		tank.tankPosY = 1200;
        	}
        	
        }
        
        // Clears the remove list.
        bullets.removeAll(removeBullets);
        tank2Bullets.removeAll(removeTank2Bullets);
        
        
        // Draws the tank depending on what direction it is facing.
        switch (tank.tankDirection) {
		case "up":
			drawTank(tankUp, tank.getTankPosX(), tank.getTankPosY());
			break;	
		case "down":
			drawTank(tankDown, tank.getTankPosX(), tank.getTankPosY());
			break;
		case "left":
			drawTank(tankLeft, tank.getTankPosX(), tank.getTankPosY());
			break;
		case "right":
			drawTank(tankRight, tank.getTankPosX(), tank.getTankPosY());
			break;		
        }
        
        switch (secondTank.tank2Direction) {
     		case "up":
     			drawTank(tankUp, secondTank.tank2PosX, secondTank.tank2PosY);
     			break;	
     		case "down":
     			drawTank(tankDown, secondTank.tank2PosX, secondTank.tank2PosY);
     			break;
     		case "left":
     			drawTank(tankLeft, secondTank.tank2PosX, secondTank.tank2PosY);
     			break;
     		case "right":
     			drawTank(tankRight, secondTank.tank2PosX, secondTank.tank2PosY);
     			break;		
             }
       
        
        batch.end();
	}
	
	
	//Gives the collision tiles from the maps.
	public void getCollisionTilesFrom() {
		
		TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get("walls");
		
		for (int x = 0; x < layer.getWidth(); x++) {
			
			for (int y = 0; y < layer.getHeight(); y++) {
				TiledMapTileLayer.Cell cell = layer.getCell(x, y);
				
				MapObjects objLayer = layer.getObjects();
				
				if (cell == null) {
					continue; // There is no cell
				}
				
				if (cell.getTile() == null) {
					continue; // No tile inside cell
				}
				
				// Get the ID
				System.out.println(cell.getTile().getId() + " x:" + x + " y:" + y);
				System.out.println(objLayer);
			}
		}
	}
	
	
	// Draws the first tank.
	public void drawTank(Texture texture, int tankPosX, int tankPosY) {
		batch.draw(texture, tankPosX, tankPosY);
	}
	
	
	/**
	 * Necesarry Overrides
	 */
	
	@Override
	public void dispose () {
		batch.dispose();
	}
	@Override
	public boolean keyUp(int keycode)
	{
        return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
}

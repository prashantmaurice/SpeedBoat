package com.maurice.GameHelpers;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameObjects.Pin;
import com.maurice.GameWorld.GameWorld;
import com.maurice.Screens.GameScreen;

public class GameGestureListener implements GestureListener  {
	GameWorld world;
	GameScreen gameScreen;
	int gameWidth;
	int gameHeight;
	private int touchMargin=10;
    public GameGestureListener(GameWorld world, GameScreen gameScreen ) {
    	this.world=world;
    	gameWidth=gameScreen.getWidth();
    	gameHeight=gameScreen.getHeight();
    	this.gameScreen=gameScreen;
    	//this.pinRadius=world.getPinRadius();
    }
    
	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		System.out.println("Swiped=="+velocityX);
		return false;
	}
	
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		//System.out.println("touchdown..!");
		
		
		
		if (world.isReady()) {
			if(y<gameHeight/2){				
				world.start();
				return false;
			}
			else{
				gameScreen.getSettingsMenu();
			}
        }
		else if (world.isRunning()) {
            if((gameWidth/2)<x){
            	world.goRight();
            }
            else if ((gameWidth/2)>x){
            	world.goLeft();
            }
            return false;
        }
		else if (world.isGameover()) {
            world.restart();
            return false;
        }
		return false;
	}
	public boolean touchUp(float x, float y, int pointer, int button) {
		System.out.println("touchup..!");
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		world.getPlayer().goStright();
		//System.out.println("tap..!");
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		return false;
	}


	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		System.out.println("pan..!");
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		System.out.println("PanStopped...!");
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}

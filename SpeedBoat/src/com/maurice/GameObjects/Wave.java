package com.maurice.GameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.maurice.GameWorld.GameWorld;

public class Wave extends Scrollable{
	public static int WIDTH=80;
	public static int HEIGHT=10;
	public float SPREAD=0.5f; 
	private float width=5f;
	private boolean isAlive;
	public Wave(float x, float y, int width, int height,float velX, float scrollSpeed) {
		super(x, y, width, height, velX, scrollSpeed);
		//System.out.println("Wave created at="+x+"="+y);
	}
	public void update(float delta) {
        position.add(velocity.cpy().scl(delta));
        //System.out.println("position set="+position.x);
        // If the Scrollable object is no longer visible:
        width+=SPREAD;
        if (position.y >= 700+HEIGHT) 
        	SPREAD=1f;
        //if (position.y >= 750+HEIGHT) 
        	//SPREAD=2f;
        if (position.y >= 800+HEIGHT) {
        	isScrolledDown = true;
        }
    }
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public float getSpread(){
		return width*4;
	}
}
